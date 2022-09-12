package com.epam.patterns.visitor;

public class Square implements Shape{


    @Override
    public void move(int x, int y) {
        System.out.println("Move Square from "+ x+" to "+ y);
    }

    @Override
    public void draw() {
        System.out.println("Draw Square");
    }

    @Override
    public String accept(MyVisitor visit) {
        return visit.visitSquare(this);
    }
}
