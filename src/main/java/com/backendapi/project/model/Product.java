package com.backendapi.project.model;

public class Product {
    private String idProduct;
    private String product;
    private String value;
    private String idProvider;
    private int idType;
    private int stock;
    private double harga;
    private  String expiredDate;

    //add join provider and type:

    private String provider;
    private String type;

    public Product() {
    }

    public Product(String idProduct, String product, String value, String idProvider, int idType, int stock, double harga, String expiredDate) {
        this.idProduct = idProduct;
        this.product = product;
        this.value = value;
        this.idProvider = idProvider;
        this.idType = idType;
        this.stock = stock;
        this.harga = harga;
        this.expiredDate = expiredDate;
    }

    public Product(String idProduct, String product, String value, String provider , int stock, double harga, String expiredDate , String type) {
        this.idProduct = idProduct;
        this.product = product;
        this.value = value;
        this.provider = provider;
        this.stock = stock;
        this.harga = harga;
        this.expiredDate = expiredDate;
        this.type = type;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(String idProvider) {
        this.idProvider = idProvider;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct='" + idProduct + '\'' +
                ", product='" + product + '\'' +
                ", value='" + value + '\'' +
                ", idProvider='" + idProvider + '\'' +
                ", idType=" + idType +
                ", stock=" + stock +
                ", harga=" + harga +
                ", expiredDate='" + expiredDate + '\'' +
                ", provider='" + provider + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
