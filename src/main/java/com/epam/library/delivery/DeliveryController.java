package com.epam.library.delivery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DeliveryController {

    @GetMapping(value = "/delivery")
    public String getDelivery(){
        return "delivery";
    }


    /**
     * This method is to redirect to proper page the user, after selected method of delivery.
     * @param delivery picked by user method
     * @return depending on delivery method endpoint.
     */
    @PostMapping("/delivery")
    public String deliverySelector(@RequestParam("delivery") String delivery){
        return switch (delivery) {
            case "post" -> "deliveryPost";
            case "email" -> "deliveryEmail";
            case "personal" -> "personal";
            default -> "index";
        };
    }
}
