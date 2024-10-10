package com.marien.backend.utils;

import com.marien.backend.entity.User;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.*;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

//import javax.activation.DataHandler;


public class EmailSender {

    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public  void sendEmailWithQRCode(User user, byte [] qrCodeImage) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("centrocodeur@gmail.com");
        helper.setTo(user.getEmail());
        helper.setSubject("Votre bille avec le QR code");

        // Create Html email content
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("<h1> Voici votre bille en QR Code: <h1>" +
                                   "<img scr=\"cid:qrcodeImage\" >", "text/html");
        // QR code in attached file

        MimeBodyPart imageBodyPart = new MimeBodyPart();
        DataSource dataSource = new ByteArrayDataSource(qrCodeImage, "image/png");
        imageBodyPart.setDataHandler(new DataHandler(dataSource));
        imageBodyPart.setHeader("Content-ID", "<qrcodeImage");

        // combiner le contenu HTML et l'image
        MimeMultipart multipart= new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(imageBodyPart);

        Transport.send(message);


    }

}
