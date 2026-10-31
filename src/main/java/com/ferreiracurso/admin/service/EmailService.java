package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.model.Corrector;
import com.resend.core.exception.ResendException;

import java.io.IOException;

public interface EmailService {

    void send(Corrector corrector) throws IOException, ResendException;

}
