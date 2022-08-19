package com.epam.library.furnitures.lying.bed;

import com.epam.library.furnitures.Materials;
import com.epam.library.furnitures.MaxSize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
class BedService {

    private final BedRepository bedRepository;

    @Autowired
    BedService(BedRepository bedRepository) {
        this.bedRepository = bedRepository;
    }

    String findById(Long bedId) {
        return bedRepository.findById(bedId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND)).toString();
    }

    void addBed(Integer materialType, Integer price, Integer size, String colour) {
        Bed bed = new Bed();
        Materials[] materials = Materials.values();
        MaxSize[] sizes = MaxSize.values();
        bed.setMaterialType(materials[materialType]);
        bed.setPrice(price);
        bed.setSize(sizes[size]);
        bed.setColour(colour);
        bedRepository.save(bed);
    }

    void delete(Long bedId) {
        bedRepository.deleteById(bedId);
    }
}
