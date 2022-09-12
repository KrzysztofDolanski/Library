package com.epam.patterns.factory;

public class HtmlDialog extends Factory{
    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
