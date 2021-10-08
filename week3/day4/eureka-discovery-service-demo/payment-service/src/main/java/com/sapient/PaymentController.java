package com.sapient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/{amount}")
    public String processPayment(@PathVariable Double amount) {
        return "Received payment of INR " + amount + "/- at port number " + port;
    }
}
