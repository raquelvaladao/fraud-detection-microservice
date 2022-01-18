package com.frdetection;


import com.frdetection.clients.fraud.FraudClient;
import com.frdetection.clients.fraud.ResponseFraudCheck;
import com.frdetection.clients.notification.NotificationClient;
import com.frdetection.clients.notification.RequestNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(RequestCustomer requestCustomer) {
        Customer customer = Customer.builder()
                .firstName(requestCustomer.getFirstName())
                .lastName(requestCustomer.getLastName())
                .email(requestCustomer.getEmail())
                .build();

        //todo: check if email valid
        //todo: check if email not taken
        customerRepository.saveAndFlush(customer);

        ResponseFraudCheck response =
                fraudClient.isFraudulent(customer.getId());

        if (response.isCustomerFraudulent())
            throw new IllegalStateException("Fraudster!");
        else
            notificationClient.registerNotification(
                    new RequestNotification(
                            customer.getId(),
                            customer.getEmail(),
                            String.format("Hi there, test message for customer %s", customer.getFirstName())
                    )
            );


        //todo: send notification
    }

}
