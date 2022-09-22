package com.epam.library.delivery.post;


import com.epam.library.delivery.addresses.AddressNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeliverByPostController {

    private final DeliverByPostService deliverByPostService;

    @Autowired
    public DeliverByPostController(com.epam.library.delivery.post.DeliverByPostService deliverByPostService) {
        this.deliverByPostService = deliverByPostService;
    }

    @GetMapping(value = "/deliverpost")
    public String getDeliver() {
        return "deliverpost";
    }

    /**
     * This method is to check availability of typed postal address.
     *
     * @param deliverByPost Object with address values.
     * @return {@link templates/deliverpost.html}
     */
    @RequestMapping(value = "/deliverpost", method = RequestMethod.POST)
    public ModelAndView deliver(@ModelAttribute @Validated DeliverByPost deliverByPost) {

        if (deliverByPostService.addressAccessibility(deliverByPost.getCity(), deliverByPost.getStreet_name(), deliverByPost.getHome_number(), deliverByPost.getFlat_number())) {
            ResponseEntity<DeliverByPost> delivery = ResponseEntity
                    .status(HttpStatus.OK)
                    .body(deliverByPost);
            ModelAndView mav = new ModelAndView("deliverpost");
            mav.addObject("deliverpost", delivery);
            return mav;
        } else throw new AddressNotAvailableException();
    }

    @GetMapping(value = "/deliverystatus")
    public String get() {
        return "status";
    }

    /**
     * This method purpose is to give delivery procedure confirmation.
     *
     * @param city       parameter from uri
     * @param streetName parameter from uri
     * @param homeNumber parameter from uri
     * @return {@link templates/status.html}
     */
    @PostMapping(path = "/deliverystatus/{city}{streetName}{homeNumber}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView deliver(@PathVariable(value = "city") String city,
                                @PathVariable(value = "streetName") String streetName,
                                @PathVariable(value = "homeNumber") Integer homeNumber) {

        ResponseEntity<DeliverByPost> delivery = ResponseEntity
                .status(HttpStatus.OK)
                .body(new DeliverByPost(city, streetName, homeNumber, 1));
        ModelAndView mav = new ModelAndView("status");
        mav.addObject("status", delivery);
        return mav;
    }
}
