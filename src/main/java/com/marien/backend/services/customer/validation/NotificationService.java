package com.marien.backend.services.customer.validation;


import com.marien.backend.entity.User;
import com.marien.backend.entity.Validation;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.Transport;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {



    JavaMailSender javaMailSender;

    public void sendMessage(Validation validation){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("centrocodeur@gmail.com");
        message.setTo(validation.getUser().getEmail());
        message.setSubject("Votre code d'activation");
        String texte =String.format(
                "Bonjour %s\n" + " Votre code d'activation est:  %s\n" + "Veuiller votre compte"+
                        "A bientôt",
                validation.getUser().getFirstname(),
                validation.getCode() );
        message.setText(texte);

        javaMailSender.send(message);


    }


    public  void sendEmailWithQRCode(User user, byte [] qrCodeImage) throws MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();
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
