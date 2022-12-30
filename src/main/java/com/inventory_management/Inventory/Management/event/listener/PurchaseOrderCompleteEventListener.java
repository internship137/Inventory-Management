package com.inventory_management.Inventory.Management.event.listener;

import com.inventory_management.Inventory.Management.dto.MailResponse;
import com.inventory_management.Inventory.Management.entity.PurchaseOrder;
import com.inventory_management.Inventory.Management.event.PurchaseOrderEvent;
import com.inventory_management.Inventory.Management.service.PurchaseOrderService;
//import freemarker.template.Configuration;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class PurchaseOrderCompleteEventListener implements ApplicationListener<PurchaseOrderEvent> {
    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration config;


    @Override
    public void onApplicationEvent(PurchaseOrderEvent event) {

        //create verification token for the USer with link

        PurchaseOrder purchaseOrder = event.getPurchaseOrder();
        String token = UUID.randomUUID().toString();
        String reject = UUID.randomUUID().toString();

        purchaseOrderService.saveTokens(purchaseOrder, token, reject);

        //send email

        String url1 = event.getApplicationUrl1() + "/confirmOrder?token="
                + token;

        String url2 = event.getApplicationUrl2() + "/denyOrder?reject="
                + reject;


        Map<String, Object> model = new HashMap<>();
        MailResponse response = new MailResponse();

        MimeMessage message = sender.createMimeMessage();
        try {
            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED);
            String expectedDate = String.valueOf(purchaseOrder.getExpectedDeliveryDate());

            model.put("supplierName", purchaseOrder.getSupplierName());
            model.put("productName", purchaseOrder.getProductName());
            model.put("qty", purchaseOrder.getProductQuantity());
            model.put("expectedDate", expectedDate);
            model.put("url1", url1);
            model.put("url2", url2);


            Template t = config.getTemplate("purchase_req.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(purchaseOrder.getSupplierEmail());
            helper.setText(html, true);
            helper.setSubject("New Order Request for " + purchaseOrder.getProductName());
            helper.setFrom("gokuldas.sayonetech@gmail.com");
            sender.send(message);

            response.setMessage("mail send to : " + purchaseOrder.getSupplierEmail());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

    }

}
