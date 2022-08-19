package com.epam.library.furnitures.lying.sofa;

import com.epam.library.furnitures.Materials;
import com.epam.library.furnitures.MaxSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
class SofaService {

    private final SofaRepository sofaRepository;

    @Autowired
    SofaService(SofaRepository sofaRepository) {
        this.sofaRepository = sofaRepository;
    }

    String findById(Long id) {
        return sofaRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)).toString();
    }

    void save(Integer materialType, Integer price, Integer size, String colour) {
        Sofa sofa = new Sofa();
        Materials material = Materials.values()[materialType];
        MaxSize chosenSize = MaxSize.values()[size];
        sofa.setMaterialType(material);
        sofa.setPrice(price);
        sofa.setSize(chosenSize);
        sofa.setColour(colour);
        sofaRepository.save(sofa);
    }

    void deleteById(Long id) {
        sofaRepository.deleteById(id);
    }
}
