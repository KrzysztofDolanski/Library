package com.epam.library.delivery.post;


import com.epam.library.delivery.addresses.AddressNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeliverByPostController {

    private final DeliverByPostService deliverByPostService;

    @Autowired
    public DeliverByPostController(com.epam.library.delivery.post.DeliverByPostService deliverByPostService) {
        this.deliverByPostService = deliverByPostService;
    }

    @GetMapping(value = "/deliver")
    public String getDeliver() {
        return "deliver";
    }

    @RequestMapping(value = "/deliver", method = RequestMethod.POST)
    public ModelAndView deliver(@ModelAttribute @Validated DeliverByPost deliverByPost, Model model) {

        if (deliverByPostService.addressAccessibility(deliverByPost.getCity(), deliverByPost.getStreet_name(), deliverByPost.getHome_number(), deliverByPost.getFlat_number())) {
            ResponseEntity<DeliverByPost> delivery = ResponseEntity
                    .status(HttpStatus.OK)
                    .body(deliverByPost);
            ModelAndView mav = new ModelAndView("deliver");
            mav.addObject("deliver", delivery);
            return mav;
        } else throw new AddressNotAvailableException();
    }
}
