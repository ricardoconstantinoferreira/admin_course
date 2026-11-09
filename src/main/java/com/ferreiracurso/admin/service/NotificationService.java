package com.ferreiracurso.admin.service;

import com.ferreiracurso.admin.model.Notification;

public interface NotificationService {

    Notification saveNotificationStudent(Long examId, Long studentId);

    Notification saveNotificationTeacher(Long examId, Long studentId);
}
