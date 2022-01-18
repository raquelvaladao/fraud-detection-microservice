package com.frdetection;

import com.frdetection.clients.notification.RequestNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void send(RequestNotification request) {

        notificationRepository.save(
                Notification.builder()
                        .toCustomerId(request.getCustomerId())
                        .toCustomerEmail(request.getCustomerEmail())
                        .sender("Fraud detector (aka me)")
                        .sentAt(LocalDateTime.now())
                        .message(request.getMessage())
                        .build()
        );
    }
}
