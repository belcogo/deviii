package com.unisinos.library.model;

public class Address {
    public String street;
    public String neighborhood;
    public String number;
    public String city;
    public Point mapPoint;

    public class Point {
        public double x;
        public double y;
    }
}
