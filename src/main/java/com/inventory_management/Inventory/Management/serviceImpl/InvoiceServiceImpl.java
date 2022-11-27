package com.inventory_management.Inventory.Management.serviceImpl;

import com.inventory_management.Inventory.Management.dto.InvoiceStocksDTO;
import com.inventory_management.Inventory.Management.entity.Invoice;
import com.inventory_management.Inventory.Management.entity.Stock;
import com.inventory_management.Inventory.Management.error.NotFoundException;
import com.inventory_management.Inventory.Management.repository.InvoiceRepository;
import com.inventory_management.Inventory.Management.repository.StockRepository;
import com.inventory_management.Inventory.Management.service.InvoiceService;
import com.inventory_management.Inventory.Management.utilities.QuantityLowEmailAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private QuantityLowEmailAlert quantityLowEmailAlert;

    @Override
    public String saveInvoice(Invoice invoice, Long stockId) throws NotFoundException {
        if (!stockRepository.existsById(stockId)) {
            throw new NotFoundException("Product with this ID does not exist");
        }

        Stock stock = stockRepository.findById(stockId).get();

        Long stocksId = stock.getStockId();
        String productName = stock.getProduct().getProductName();
        String productCategory = stock.getProduct().getCategory().getCategoryName();
        Long productPrice = stock.getProduct().getProductSellingPrice().getSellingPrice();

        Long stockQty = stock.getStockQuantity();
        Long sellingQty = invoice.getSellingQuantity();

        if (sellingQty > stockQty) {
            return " Stock quantity is less than Selling Quantity";

        }

        invoice.setStockId(stocksId);
        invoice.setProductName(productName);
        invoice.setCategoryName(productCategory);
        invoice.setProductPrice(productPrice);

        invoiceRepository.save(invoice);
        stockQty = stockQty - sellingQty;
        stock.setStockQuantity(stockQty);
        stockRepository.save(stock);

        if (stockQty < 50) {
            quantityLowEmailAlert.sendOrderSuccessfulEmail(
                    "gokuldas1999@gmail.com",
                    "Alert!! The quantity of stock with id " + stock.getStockId() + " is low.\n" +
                            "Quantity available is " + stock.getStockQuantity(),
                    "Alert"
            );

            return "Alert!! Stock quantity is low.";
        }

        return "Invoice Generated";
    }

    @Override
    public List<InvoiceStocksDTO> fetchByInvoiceId(Long invoiceId) throws NotFoundException {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new NotFoundException("Invoice with this id does not exist");
        }
        return invoiceRepository.findById(invoiceId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceStocksDTO> fetchAllInvoice() {
        return invoiceRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void updateInvoice(Long invoiceId, Invoice invoice) throws NotFoundException {
        Invoice invoiceDB = invoiceRepository.findById(invoiceId).get();

        if (!invoiceRepository.existsById(invoiceId)) {
            throw new NotFoundException("Invoice with this id does not exist");
        }

        if (Objects.nonNull(invoice.getSellingQuantity()) &&
                !"".equalsIgnoreCase(String.valueOf(invoice.getSellingQuantity()))) {
            invoiceDB.setSellingQuantity(invoice.getSellingQuantity());
        }
        invoiceRepository.save(invoiceDB);
    }

    @Override
    public void deleteInvoice(Long invoiceId) throws NotFoundException {
        if (!invoiceRepository.existsById(invoiceId)) {
            throw new NotFoundException("Invoice with this id does not exist");
        }
        invoiceRepository.deleteById(invoiceId);
    }

    @Override
    public List<InvoiceStocksDTO> getByInvoiceId(Long invoiceId) {
        return invoiceRepository.findById(invoiceId)
                .stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private InvoiceStocksDTO convertEntityToDto(Invoice invoice) {
        InvoiceStocksDTO invoiceStocksDTO =
                new InvoiceStocksDTO();


        invoiceStocksDTO.setInvoiceId(invoice.getInvoiceId());
//        invoiceStocksDTO.setDateOfIssue(invoice.getDateOfIssue());
        invoiceStocksDTO.setProductName(invoice.getProductName());
        invoiceStocksDTO.setCategoryName(invoice.getCategoryName());
        invoiceStocksDTO.setProductPrice(invoice.getProductPrice());
        invoiceStocksDTO.setSellingQuantity(invoice.getSellingQuantity());

        return invoiceStocksDTO;
    }
}
