package com.epam.library.furnitures.lying.futon;

import com.epam.library.furnitures.FurnitureControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("futons")
public class FutonController implements FurnitureControl {

    private final FutonService futonService;

    @Autowired
    public FutonController(FutonService futonService) {
        this.futonService = futonService;
    }

    @Override
    public String findById(Long id) {
        return futonService.findById(id);
    }

    @Override
    public void add(Integer materialType, Integer price, Integer size, String colour) {
        futonService.save(materialType, price, size, colour);
    }

    @Override
    public void deleteById(Long id) {
        futonService.delete(id);
    }
}
