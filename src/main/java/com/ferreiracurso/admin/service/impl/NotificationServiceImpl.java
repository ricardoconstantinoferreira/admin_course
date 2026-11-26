package com.ferreiracurso.admin.service.impl;

import com.ferreiracurso.admin.model.Notification;
import com.ferreiracurso.admin.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public Notification saveNotificationStudent(Long examId, Long studentId) {
        return null;
    }

    @Override
    public Notification saveNotificationTeacher(Long examId, Long studentId) {
        return null;
    }
}
