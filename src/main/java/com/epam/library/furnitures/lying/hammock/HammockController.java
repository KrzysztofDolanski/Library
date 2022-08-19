package com.epam.library.furnitures.lying.hammock;

import com.epam.library.furnitures.FurnitureControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hammocks")
public class HammockController implements FurnitureControl {


    private final HammockService hammockService;

    @Autowired
    public HammockController(HammockService hammockService) {
        this.hammockService = hammockService;
    }

    @Override
    public String findById(Long id) {
        return hammockService.findById(id);
    }

    @Override
    public void add(Integer materialType, Integer price, Integer size, String colour) {
        hammockService.save(materialType, price, size, colour);

    }

    @Override
    public void deleteById(Long id) {
        hammockService.deleteById(id);
    }
}
