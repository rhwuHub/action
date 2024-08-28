package com.blog.service.impl;import com.blog.service.MsmService;import jakarta.annotation.Resource;import org.springframework.beans.factory.annotation.Value;import org.springframework.mail.MailException;import org.springframework.mail.SimpleMailMessage;import org.springframework.mail.javamail.JavaMailSender;import org.springframework.stereotype.Service;//发送邮箱验证码的方法@Servicepublic class MsmServiceImpl implements MsmService {  @Resource  private JavaMailSender javaMailSender;  @Value("${email.from}")  private String from;  @Override  public boolean sendEmail(String email,String content) {      // 构建一个邮件对象      SimpleMailMessage message = new SimpleMailMessage();      // 设置邮件发送者，这个跟application.yml中设置的要一致 recipients      message.setFrom(from);      // 设置邮件接收者，可以有多个接收者，中间用逗号隔开，以下类似      message.setTo(email);      // 设置邮件的主题      message.setSubject("芮豪-提醒事项");      message.setText(content);      // 发送邮件      try {          javaMailSender.send(message);          return true;      } catch (MailException e) {          e.printStackTrace();      }      return false;  }}