package com.epam.patterns.visitor;

public interface Shape {

    void move(int x, int y);
    void draw();
    String accept(MyVisitor visit);
}
