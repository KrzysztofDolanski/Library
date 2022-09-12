package com.epam.patterns.visitor;

import java.util.ArrayList;
import java.util.List;

public class CompoundGraphic implements Shape{

    public int id;
    public List<Shape> children = new ArrayList<>();

    public CompoundGraphic(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void draw() {

    }

    @Override
    public String accept(MyVisitor visit) {
        return visit.visitCompoundGraphic(this);
    }

    public void add(Shape shape){
        children.add(shape);
    }
}
