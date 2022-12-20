package com.inventory_management.Inventory.Management.event.listener;

import com.inventory_management.Inventory.Management.dto.MailResponse;
import com.inventory_management.Inventory.Management.entity.PurchaseRequest;
import com.inventory_management.Inventory.Management.event.PurchaseRequestEvent;
import com.inventory_management.Inventory.Management.service.PurchaseRequestService;
import com.inventory_management.Inventory.Management.utilities.RequestEmail;
//import freemarker.template.Configuration;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
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
public class PurchaseRequestCompleteEventListener implements ApplicationListener<PurchaseRequestEvent> {
    @Autowired
    private PurchaseRequestService purchaseRequestService;

//    @Autowired
//    private RequestEmail requestEmail;

    @Autowired
    private JavaMailSender sender;

    @Autowired
    private Configuration config;


    @Override
    public void onApplicationEvent(PurchaseRequestEvent event) {

        //create verification token for the USer with link

        PurchaseRequest purchaseRequest = event.getPurchaseRequest();
        String token = UUID.randomUUID().toString();
        String reject = UUID.randomUUID().toString();

        purchaseRequestService.saveTokens(purchaseRequest, token, reject);

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
            String expectedDate= String.valueOf(purchaseRequest.getExpectedDeliveryDate());

            model.put("supplierName",purchaseRequest.getSupplierName());
            model.put("productName",purchaseRequest.getProductName());
            model.put("qty",purchaseRequest.getProductQuantity());
            model.put("expectedDate",expectedDate);
            model.put("url1",url1);
            model.put("url2",url2);


            Template t = config.getTemplate("purchase_req.html");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

            helper.setTo(purchaseRequest.getSupplierEmail());
            helper.setText(html, true);
            helper.setSubject("New Order Request for "+purchaseRequest.getProductName());
            helper.setFrom("gokuldas.sayonetech@gmail.com");
            sender.send(message);

            response.setMessage("mail send to : " + purchaseRequest.getSupplierEmail());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException | IOException | TemplateException e) {
            response.setMessage("Mail Sending failure : " + e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

    }

}
