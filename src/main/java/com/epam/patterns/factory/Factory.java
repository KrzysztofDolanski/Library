package com.epam.patterns.factory;

public abstract class Factory {


    public void renderWindow(){
        Button button = createButton();
        button.render();
    }

    public abstract Button createButton();

}
