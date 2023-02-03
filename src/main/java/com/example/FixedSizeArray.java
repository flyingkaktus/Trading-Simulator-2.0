package com.example;

import java.util.LinkedList;

class FixedSizeArray {
    private LinkedList<Float> array;
    private int maxSize;

    public FixedSizeArray(int size) {
        this.array = new LinkedList<>();
        this.maxSize = size;
    }

    public void add(float value) {
        if (array.size() == maxSize) {
            array.removeFirst();
        }
        array.addLast(value);
    }

    public float get(int index) {
        return array.get(index);
    }

    public int size() {
        return array.size();
    }

    public float average() {
        float sum = 0.0f;
        for (float value : array) {
            sum += value;
        }
        return sum / array.size();
    }

}
