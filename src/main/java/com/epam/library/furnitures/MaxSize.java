package com.epam.library.furnitures;

public enum MaxSize {

    SMALL(10, 10), MEDIUM(20, 20), LARGE(45, 45);

    int wight;
    int high;

    MaxSize(int wight, int high) {
        this.wight = wight;
        this.high = high;
    }
}
