package com.epam.tasks.create;

public class Creator {

    public Human[] create(){
        Human adam = new Man();
        Human eve = new Woman();
        Human[] result = new Human[2];
        result[0] = adam;
        result[1] = eve;
        return result;
    }
}
