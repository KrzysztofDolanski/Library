package com.epam.library.furnitures.lying;

import com.epam.library.furnitures.Materials;
import com.epam.library.furnitures.MaxSize;

import javax.persistence.*;

@Entity
@Table(name = "SOFAS")
public class Sofa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "material_type")
    Materials materialType;
    int price;
    MaxSize size;
    String colour;

    public Sofa() {
    }

    public Sofa(Materials materialType, int price, MaxSize size, String colour) {
        this.materialType = materialType;
        this.price = price;
        this.size = size;
        this.colour = colour;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Materials getMaterialType() {
        return materialType;
    }

    public void setMaterialType(Materials materialType) {
        this.materialType = materialType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MaxSize getSize() {
        return size;
    }

    public void setSize(MaxSize size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
}
