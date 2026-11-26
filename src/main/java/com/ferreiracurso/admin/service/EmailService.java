package com.ferreiracurso.admin.service;

import com.resend.core.exception.ResendException;
import org.thymeleaf.context.Context;

import java.io.IOException;

public interface EmailService {

    void notification(Context context, String template, String email, String subject) throws IOException, ResendException;

}
