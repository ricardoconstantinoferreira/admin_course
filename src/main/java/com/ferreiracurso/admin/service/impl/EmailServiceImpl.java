package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.model.Corrector;
import com.ferreiracurso.admin.service.EmailService;
import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.CreateEmailOptions;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Base64;
import java.util.Collections;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private Environment environment;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${resend.api.key}")
    private String apiKey;

    @Override
    public void send(Corrector corrector) throws IOException, ResendException {
        BigDecimal points = corrector.getPoints();
        String email = corrector.getStudent().getEmail();
        Resend resend = new Resend(apiKey);

        String mailFrom = environment.getProperty("resend.domain");
        String logo = environment.getProperty("mail.logo");

        byte[] imageBytes = new ClassPathResource(logo).getContentAsByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        Attachment logoAnexo = Attachment.builder()
                .fileName(logo)
                .content(base64Image)
                .contentId("ferreiracurso")
                .build();

        final Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("name", corrector.getStudent().getName());
        ctx.setVariable("points", corrector.getPoints());
        ctx.setVariable("ferreiracurso", logo);

        final String htmlContent = templateEngine.process("corrector", ctx);

        CreateEmailOptions params = CreateEmailOptions.builder()
                .from(mailFrom)
                .to(corrector.getStudent().getEmail())
                .subject("Correção da Prova")
                .html(htmlContent)
                .attachments(Collections.singletonList(logoAnexo))
                .build();

        resend.emails().send(params);
    }
}
