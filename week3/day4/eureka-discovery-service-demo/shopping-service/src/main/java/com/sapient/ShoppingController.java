package com.sapient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {

    @Autowired
    RestTemplate template;

    @GetMapping("/checkout/{amount}")
    public String checkout(@PathVariable Double amount) {
        String url = "http://PAYMENT-SERVICE/api/payment/" + amount;
        return template.getForObject(url, String.class);
    }
}
