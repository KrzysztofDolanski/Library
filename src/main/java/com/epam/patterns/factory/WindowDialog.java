package com.epam.patterns.factory;

public class WindowDialog extends Factory{
    @Override
    public Button createButton() {
        return new WindowButton();
    }
}
