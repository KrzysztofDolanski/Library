package com.epam.library.furnitures.lying.bed;

import com.epam.library.furnitures.Materials;
import com.epam.library.furnitures.MaxSize;

import javax.persistence.*;

@Entity
@Table(name = "BEDS")
class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "material_type")
    private Materials materialType;
    private int price;
    private MaxSize size;
    private String colour;

    public Bed() {
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

    @Override
    public String toString() {
        return "Bed{" +
                "id=" + id +
                ", materialType=" + materialType +
                ", price=" + price +
                ", size=" + size +
                ", colour='" + colour + '\'' +
                '}';
    }
}
