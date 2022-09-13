package com.epam.tasks.create;

public class Man extends Human{

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return  (obj instanceof Man);
    }
}
