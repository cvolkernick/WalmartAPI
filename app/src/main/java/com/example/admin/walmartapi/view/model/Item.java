package com.example.admin.walmartapi.view.model;

public class Item {

    private String name;
    private double salesPrice;
    private String shortDescription;
    private String thumbnailImage;

    public Item(String name, double salesPrice, String shortDescription, String thumbnailImage) {
        this.name = name;
        this.salesPrice = salesPrice;
        this.shortDescription = shortDescription;
        this.thumbnailImage = thumbnailImage;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    @Override
    public String toString() {
        return name + " - $" + salesPrice + " " + shortDescription;
    }
}
