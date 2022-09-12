package com.epam.patterns.factory;

public class WindowButton implements Button{
    @Override
    public void render() {
        System.out.println("<window> \n </window>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Window button clicked");
    }
}
