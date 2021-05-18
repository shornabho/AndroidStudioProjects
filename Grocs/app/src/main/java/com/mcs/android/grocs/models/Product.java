package com.mcs.android.grocs.models;

public class Product {
    private int productImage;
    private String productTitle;
    private String productShop;
    private String productCategory;
    private double quantityInKg;
    private double productMRP;
    private double productSellingPrice;

    public Product(int productImage, String productTitle, String productShop, String productCategory, double quantityInKg, double productMRP, double productSellingPrice) {
        this.productImage = productImage;
        this.productTitle = productTitle;
        this.productShop = productShop;
        this.productCategory = productCategory;
        this.quantityInKg = quantityInKg;
        this.productMRP = productMRP;
        this.productSellingPrice = productSellingPrice;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductShop() {
        return productShop;
    }

    public void setProductShop(String productShop) {
        this.productShop = productShop;
    }

    public double getQuantityInKg() {
        return quantityInKg;
    }

    public void setQuantityInKg(double quantityInKg) {
        this.quantityInKg = quantityInKg;
    }

    public double getProductMRP() {
        return productMRP;
    }

    public void setProductMRP(double productMRP) {
        this.productMRP = productMRP;
    }

    public double getProductSellingPrice() {
        return productSellingPrice;
    }

    public void setProductSellingPrice(double productSellingPrice) {
        this.productSellingPrice = productSellingPrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
