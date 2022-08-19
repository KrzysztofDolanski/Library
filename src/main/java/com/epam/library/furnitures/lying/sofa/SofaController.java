package com.epam.library.furnitures.lying.sofa;

import com.epam.library.furnitures.FurnitureControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sofas")
public class SofaController implements FurnitureControl {

    private final SofaService sofaService;

    @Autowired
    public SofaController(SofaService sofaService) {
        this.sofaService = sofaService;
    }

    @Override
    public String findById(Long id) {
        return sofaService.findById(id);
    }

    @Override
    public void add(Integer materialType, Integer price, Integer size, String colour) {
        sofaService.save(materialType, price, size, colour);

    }

    @Override
    public void deleteById(Long id) {
        sofaService.deleteById(id);
    }
}
