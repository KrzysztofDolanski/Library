package com.epam.patterns.visitor;

public class Rectangle implements Shape {

    private int id;
    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(int id, int x, int y, int width, int height) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void move(int x, int y) {
        System.out.println("Move Rectangle from " + x + " to " + y);

    }

    @Override
    public void draw() {
        System.out.println("<|");
    }

    @Override
    public String accept(MyVisitor visit) {
        return visit.visitRectangle(this);
    }
}
