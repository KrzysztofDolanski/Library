package com.epam.patterns.factory;

public class HtmlButton implements Button{

    @Override
    public void render() {
        System.out.println("<html> \n </html>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Html button clicked");
    }
}
