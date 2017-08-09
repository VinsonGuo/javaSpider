package com.guoziwei.model;

/**
 * Created by dell on 2017/8/9.
 */
public class Rating {

    /**
     * max : 10
     * average : 7.5
     * stars : 40
     * min : 0
     */

    private int max;
    private double average;
    private int stars;
    private int min;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
