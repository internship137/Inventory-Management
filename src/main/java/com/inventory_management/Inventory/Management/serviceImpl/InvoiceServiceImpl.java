package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.dto.InvoiceDTO;
import com.inventory_management.Inventory.Management.entity.Invoice;
import com.inventory_management.Inventory.Management.entity.Message;
import com.inventory_management.Inventory.Management.entity.Product;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.InvoiceRepository;
import com.inventory_management.Inventory.Management.repository.ProductRepository;
import com.inventory_management.Inventory.Management.service.InvoiceService;
import com.inventory_management.Inventory.Management.utilities.BillInvoiceEmail;
import com.inventory_management.Inventory.Management.utilities.QuantityLowEmailAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private QuantityLowEmailAlert quantityLowEmailAlert;

    @Autowired
    private BillInvoiceEmail billInvoiceEmail;

    // Create Invoice

    @Override
    public Message saveInvoice(Invoice invoice, Long productId) throws NotFoundException {
        if (!productRepository.existsById(productId)) {
            throw new NotFoundException("Product with this ID does not exist");
        }

        Product product = productRepository.findById(productId).get();

        String productName = product.getProductName();
        String productCategory = product.getCategory().getCategoryName();
        Long productPrice = product.getProductPricing().getProductSellingPrice();
        String gst = product.getProductPricing().getGstSlab();
        Long stockQty = Long.valueOf(product.getStockQuantity());
        Long sellingQty = Long.valueOf(invoice.getSellingQuantity());
        Long grndTotal = invoice.getGrandTotal();
        Long cgst = product.getProductPricing().getCgst();
        Long sgst = product.getProductPricing().getSgst();

        if (sellingQty > stockQty) {

            Message message = new Message();
            message.setMessage("Cannot create Invoice since the entered selling quantity is greater" +
                    " than the product's current stock quantity or the product maybe out of Stock");
            return message;
        }


        grndTotal = sellingQty * productPrice;

        invoice.setGrandTotal(grndTotal);
        invoice.setProductName(productName);
        invoice.setCategoryName(productCategory);
        invoice.setProductPrice(productPrice);
        invoice.setGstSlab(gst);
        invoice.setCgst(cgst);
        invoice.setSgst(sgst);
        invoice.setProductCode(product.getProductCode());

        invoiceRepository.save(invoice);
        stockQty = stockQty - sellingQty;
        product.setStockQuantity(String.valueOf(stockQty));
        productRepository.save(product);


        if (stockQty < 50) {
            quantityLowEmailAlert.sendOrderSuccessfulEmail(
                    "anson.joseph05@gmail.com",
                    "Hello,\n You receive this Alert because one of your product's current stock quantity" +
                            " is low below the threshold you have set. \n Product with product code " + product.getProductCode() +
                            " is low below 50 units",
                    "Low Quantity Stock Alert !"
            );

            Message message = new Message();
            message.setMessage("Invoice Generated Successfully, " +
                    "Alert : Stock Quantity is low below the threshold value.");
            return message;
        }



        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());


        billInvoiceEmail.sendBillInvoiceEmail("" + invoice.getCustomerEmail(),
                "Dear " + invoice.getCustomerName() + ",\nThank you for choosing us !\n"+"With Company_name " +
                        "you can always find what you need. \n"+"And we make sure in delivering services according " +
                        "to your preferences.\n\n"
                        + "The details of your purchase from Company_Name on " + currentDateTime + " are \n" +
                        "Invoice No. : " +invoice.getInvoiceId()+
                        "\nProduct Name : " + invoice.getProductName() + "\n" +
                        "Product Price : ₹" + invoice.getProductPrice() + "/-\n" +
                        "Product Quantity : " + invoice.getSellingQuantity()+" Nos. " +
                        "\n \nWe hope that the shopping experience was pleasant for you.\n" +
                        "We expect you next time.\n" +
                        "Have a nice day.",
                "Invoice"
        );


        Message message2 = new Message();
        message2.setMessage("Invoice Generated Successfully");
        return message2;
    }

    // Get All Invoice (pagination and sorting)

    @Override
    public List<InvoiceDTO> fetchAllInvoice(int pageNo, int recordCount) {
        PageRequest pageable = PageRequest.of(pageNo, recordCount,
                Sort.by("invoiceId"));
        return invoiceRepository.findAll(pageable)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Get Invoice By Id

    @Override
    public List<InvoiceDTO> fetchByInvoiceId(Long invoiceId) throws NotFoundException {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new NotFoundException("Invoice with this id does not exist");
        }
        return invoiceRepository.findById(invoiceId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }


    // Delete Invoice

    @Override
    public Message deleteInvoice(Long invoiceId) throws NotFoundException {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new NotFoundException("Invoice with this id does not exist");
        }
        invoiceRepository.deleteById(invoiceId);
        Message message = new Message();
        message.setMessage("Invoice Deleted Successfully");
        return message;
    }


    @Override
    public List<InvoiceDTO> getByInvoiceId(Long invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    // DTO

    private InvoiceDTO convertEntityToDto(Invoice invoice) {
        InvoiceDTO invoiceDTO = new InvoiceDTO();


        invoiceDTO.setInvoiceId(invoice.getInvoiceId());
        invoiceDTO.setProductName(invoice.getProductName());
        invoiceDTO.setCategoryName(invoice.getCategoryName());
        invoiceDTO.setProductPrice(invoice.getProductPrice());
        invoiceDTO.setSellingQuantity(Long.valueOf(invoice.getSellingQuantity()));
        invoiceDTO.setCustomerEmail(invoice.getCustomerEmail());
        invoiceDTO.setCustomerName(invoice.getCustomerName());
        invoiceDTO.setGstSlab(invoice.getGstSlab());
        invoiceDTO.setGrandTotal(invoice.getGrandTotal());
        invoiceDTO.setProductCode(invoice.getProductCode());
        invoiceDTO.setSgst(invoice.getSgst());
        invoiceDTO.setCgst(invoice.getCgst());

        return invoiceDTO;
    }
}
