package com.zdw.zms.test.mailTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Author:zoudw
 * Since:JDK 8
 * Date:2019/5/24
 * Description:
 *
 * @Copyright:2019, zoudw@szinfinova.com All Rights Reserved
 */
@Component
public class MailSend {

    @Autowired
    JavaMailSender mailSender;

    /*
     * @author zoudw
     * @param []
     * @return void
     * @date 2019/5/24
     * @description :发送带附件的邮件
      */
    public void sendAttachMail(){
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            //创建multipart的邮件
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom("wugz@szinfinova.com"); //必须和发送服务器的用户名一致，否则会报错
//            helper.setFrom("zoudw@szinfinova.com");
            helper.setTo("zoudw@szinfinova.com");
            helper.setSubject("test");
            helper.setText("test========");
            ClassPathResource resource = new ClassPathResource("2.png");
            ClassPathResource resourc2 = new ClassPathResource("static/chat.html");
            helper.addAttachment(resource.getFilename(),resource);
            helper.addAttachment(resourc2.getFilename(),resourc2);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }


    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/24
     * @description :发送带有图片的邮件
      */
    public void sendPicMessage(){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setSubject("hello");
            helper.setFrom("wugz@szinfinova.com");
            helper.setTo("zoudw@szinfinova.com");
            helper.setText("<div> hello 这是带图片的邮件："+
                    "这是图片1：<div><img src='cid:p01'/></div>"+
                    "这是图片2：<div><img src='cid:p02'/></div>"+
                    "</div>",true);
           // FileSystemResource resource = new FileSystemResource("F:\\Temp\\2.png");
            ClassPathResource resource = new ClassPathResource("2.png");
            helper.addInline("p01",resource);
            helper.addInline("p02",resource);
            mailSender.send(mimeMessage);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /*
     * @author zoudw
     * @param
     * @return
     * @date 2019/5/24
     * @description :结合thymeleaf进行模板邮件发送
      */
    @Autowired
    TemplateEngine engine; //模板引擎
    public void sendThymeleafMail() throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);
        helper.setSubject("hello");
        helper.setFrom("wugz@szinfinova.com");
        helper.setTo("zoudw@szinfinova.com");

        Context context = new Context();
        context.setVariable("username","zdw");
        context.setVariable("gender","男");

        String process = engine.process("mail.html", context);
        helper.setText(process,true);
        mailSender.send(message);
    }
}
