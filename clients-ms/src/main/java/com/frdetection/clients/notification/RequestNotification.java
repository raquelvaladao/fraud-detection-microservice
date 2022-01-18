package com.frdetection.clients.notification;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestNotification {
    private Integer customerId;
    private String customerEmail;
    private String message;
}
