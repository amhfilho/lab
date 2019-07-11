package com.amhfilho.google;

public class FileSearchSizeRange {
    private double minBytes;
    private double maxBytes;

    public FileSearchSizeRange(double minBytes, double maxBytes) {
        this.minBytes = minBytes;
        this.maxBytes = maxBytes;
    }

    public static FileSearchSizeRange greaterOrEqualThan(double bytes){
        return new FileSearchSizeRange(bytes, Double.MAX_VALUE);
    }

    public static FileSearchSizeRange lessOrEqualThan(double bytes){
        return new FileSearchSizeRange(Double.MIN_VALUE, bytes);
    }

    public double getMinBytes(){
        return this.minBytes;
    }

    public double getMaxBytes(){
        return this.maxBytes;
    }

}
