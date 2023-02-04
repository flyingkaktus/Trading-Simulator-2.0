package com.example;

public class Portfolio {
    float coinAmount;
    float boughtAt;
    long boughtTime;

    Portfolio(float boughtAt, float coinAmount, long boughtTime) {
        this.boughtAt = boughtAt;
        this.coinAmount = coinAmount;
        this.boughtTime = boughtTime;
    }
}
