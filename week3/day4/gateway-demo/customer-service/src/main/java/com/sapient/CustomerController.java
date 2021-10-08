package com.sapient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @GetMapping("/info")
    public String info() {
        return "Customer API yet to be implemented";
    }

}
