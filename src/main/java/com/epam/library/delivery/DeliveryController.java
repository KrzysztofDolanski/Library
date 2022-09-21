package com.epam.library.delivery;

import com.epam.library.delivery.addresses.AddressNotAvailableException;
import com.epam.library.delivery.post.DeliverByPost;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/deliverystatus")
public class DeliveryController {

    @GetMapping
    public String get(){
        return "status";
    }

    @PostMapping(path = "/{city}{streetName}{homeNumber}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView deliver(@PathVariable(value = "city") String city,
                                @PathVariable(value = "streetName") String streetName,
                                @PathVariable(value = "homeNumber") Integer homeNumber) {

            ResponseEntity<DeliverByPost> delivery = ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new DeliverByPost(city, streetName,homeNumber, 1));
            ModelAndView mav = new ModelAndView("status");
            mav.addObject("status", delivery);
            return mav;
    }

}
