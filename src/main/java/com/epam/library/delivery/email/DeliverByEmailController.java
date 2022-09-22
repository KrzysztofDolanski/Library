
package com.epam.library.delivery.email;


import com.epam.library.delivery.addresses.AddressNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeliverByEmailController {

    private final DeliverByEmailService deliverByEmailService;

    @Autowired
    public DeliverByEmailController(DeliverByEmailService deliverByEmailService) {
        this.deliverByEmailService = deliverByEmailService;
    }


    @GetMapping(value = "/email")
    public String getDeliver() {
        return "deliveryEmail";
    }

    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public ModelAndView deliver(@ModelAttribute @Validated DeliverByEmail deliverByEmail) {

        if (deliverByEmailService.validateEmail(deliverByEmail.getEmail())) {
            ResponseEntity<DeliverByEmail> delivery = ResponseEntity
                    .status(HttpStatus.OK)
                    .body(deliverByEmail);
            ModelAndView mav = new ModelAndView("deliveryEmail");
            mav.addObject("deliveryEmail", delivery);
            return mav;
        } else throw new AddressNotAvailableException();
    }

}
