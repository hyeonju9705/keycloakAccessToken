package com.psc.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@Controller
@SpringBootApplication
public class GatewayController {
    public static void main(String[] args) {
        SpringApplication.run(GatewayController.class, args);
    }


}
