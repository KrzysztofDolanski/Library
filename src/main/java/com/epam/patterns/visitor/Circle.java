package com.epam.patterns.visitor;

public class Circle extends Dot{

    private int radius;

    public Circle(int id, int x, int y, int radius) {
        super(id, x, y);
        this.radius = radius;
    }

    @Override
    public String accept(MyVisitor visit) {
        return visit.visitCircle(this);
    }

    public int getRadius() {
        return radius;
    }
}
