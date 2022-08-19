package com.epam.library.furnitures.lying.futon;

import com.epam.library.furnitures.Materials;
import com.epam.library.furnitures.MaxSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class FutonService {

    private final FutonRepository futonRepository;

    @Autowired
    FutonService(FutonRepository futonRepository) {
        this.futonRepository = futonRepository;
    }

    void delete(Long id) {
        futonRepository.deleteById(id);
    }

    String findById(Long id) {
        return futonRepository.findById(id).orElseThrow().toString();
    }

    void save(Integer materialType, Integer price, Integer size, String colour) {
        Futon futon = new Futon();
        Materials[] materials = Materials.values();
        MaxSize[] sizes = MaxSize.values();
        futon.setMaterialType(materials[materialType]);
        futon.setPrice(price);
        futon.setSize(sizes[size]);
        futon.setColour(colour);
        futonRepository.save(futon);
    }
}
