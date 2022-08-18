package com.epam.library.furnitures;

public enum SIZE {

    SMALL, MEDIUM, LARGE;

    int wight;
    int high;

    SIZE(int wight, int high) {
        this.wight = wight;
        this.high = high;
    }
}
