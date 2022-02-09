package com.frdetection.customer;


import com.frdetection.amqp.RabbitMQMessageProducer;
import com.frdetection.clients.fraud.FraudClient;
import com.frdetection.clients.fraud.ResponseFraudCheck;
import com.frdetection.clients.notification.RequestNotification;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    //    private final NotificationClient notificationClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

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

        RequestNotification notificationRequest = new RequestNotification(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi there, test message for customer %s", customer.getFirstName())
        );
        if (response.isCustomerFraudulent())
            throw new IllegalStateException("Fraudster!");
        else
            rabbitMQMessageProducer.publish(
                    notificationRequest,
                    "internal.exchange",
                    "internal.notification.routing-key"
            );
            /*notificationClient.registerNotification(
                    new RequestNotification(
                            customer.getId(),
                            customer.getEmail(),
                            String.format("Hi there, test message for customer %s", customer.getFirstName())
                    )
            );*/

    }

}
