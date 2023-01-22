package com.example.demo.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@AllArgsConstructor
public class EmailSenderServiceImpl {

    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String receiverMailAddress, String body, String subject){

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("omojolawarith@gmail.com"); // Use at value to fetch
        message.setTo(String.valueOf(receiverMailAddress));
        message.setText(body);
        message.setSubject(subject);
        // message.setCc("Admin mail address");

        javaMailSender.send(message);
        System.out.println("Simple email sent Successfully");
    }

    public void sendEmailWithAttachment(String receiverMailAddress, String body, String subject, String attachment) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

        mimeMessageHelper.setFrom("omojolawarith@gmail.com"); // Use at value to fetch
        mimeMessageHelper.setTo(receiverMailAddress);
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject(subject);

        FileSystemResource file = new FileSystemResource(new File(attachment));

        mimeMessageHelper.addAttachment(file.getFilename(), file);

        javaMailSender.send(mimeMessage);
        System.out.println("Attachment email sent Successfully");
    }
}
