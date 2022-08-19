package com.epam.library.furnitures.lying.bed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/beds")
public class BedController {

    private final BedService bedService;

    @Autowired
    private BedController(BedService bedService) {
        this.bedService = bedService;
    }

    @GetMapping("{bedId}")
    public String findById(@PathVariable long bedId){
        return bedService.findById(bedId);
    }

    @PostMapping("")
    public void addBed(@RequestParam Integer materialType,
                       @RequestParam Integer price,
                       @RequestParam Integer size,
                       @RequestParam String colour){
        bedService.addBed(materialType, price, size, colour);
    }

    @DeleteMapping("{bedId}")
    public void delete(@PathVariable Long bedId){
        bedService.delete(bedId);
    }
}
