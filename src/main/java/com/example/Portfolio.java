package com.example;

public class Portfolio {
    float coinAmount;
    float boughtAt;
    int boughtTime;

    Portfolio(float boughtAt, float coinAmount, int boughtTime) {
        this.boughtAt = boughtAt;
        this.coinAmount = coinAmount;
        this.boughtTime = boughtTime;
    }
}
