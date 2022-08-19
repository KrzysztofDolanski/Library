package com.epam.library.furnitures.lying.hammock;

import com.epam.library.furnitures.Materials;
import com.epam.library.furnitures.MaxSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class HammockService {

    private final HammockRepository hammockRepository;

    @Autowired
    public HammockService(HammockRepository hammockRepository) {
        this.hammockRepository = hammockRepository;
    }

    void deleteById(Long id) {
        hammockRepository.deleteById(id);
    }

    String findById(Long id) {
        return hammockRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).toString();
    }

    void save(Integer materialType, Integer price, Integer size, String colour) {
        Hammock hammock = new Hammock();
        Materials material = Materials.values()[materialType];
        MaxSize chosenSize = MaxSize.values()[size];
        hammock.setMaterialType(material);
        hammock.setPrice(price);
        hammock.setSize(chosenSize);
        hammock.setColour(colour);
        hammockRepository.save(hammock);
    }
}
