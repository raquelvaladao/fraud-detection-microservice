package com.frdetection;

import com.frdetection.clients.fraud.ResponseFraudCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud-check")
@Slf4j
public class FraudCheckHistoryController {

    private final FraudCheckHistoryService fraudCheckHistoryService;

    public FraudCheckHistoryController(FraudCheckHistoryService fraudCheckHistoryService) {
        this.fraudCheckHistoryService = fraudCheckHistoryService;
    }

    @GetMapping("/{customerId}")
    public ResponseFraudCheck isFraudulent(
            @PathVariable(name = "customerId") Integer customerId){
            boolean isCustomerFraudulent = fraudCheckHistoryService.isFraudulent(customerId);
            log.info("check fraud for user with id: {}", customerId);
            return new ResponseFraudCheck(isCustomerFraudulent);
    }
}
