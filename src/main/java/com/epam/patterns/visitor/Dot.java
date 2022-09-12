package com.epam.patterns.visitor;

public class Dot implements Shape {

    private int id;
    private int x;
    private int y;


    public Dot(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Dot() {
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public void move(int x, int y) {
        System.out.println("Dot move from " + x + " to " + y);
    }

    @Override
    public void draw() {
        System.out.println(".");
    }

    @Override
    public String accept(MyVisitor visit) {
        return visit.visitDot(this);
    }
}
