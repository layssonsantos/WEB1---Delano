package br.ufscar.dc.dsw.service.spec;

import java.io.File;

import javax.mail.internet.InternetAddress;

public interface IEmailService {
    
    void send(InternetAddress from, InternetAddress to, String subject, String body, File file);

    void send(InternetAddress from, InternetAddress to, String subject, String body);
}
