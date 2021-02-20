package com.backendapi.project.model;

import java.util.List;

public class Transaction {

    private String idTransaction;
    private String transactionDate;
    private String phoneNumber;
    List<Product> productList;

    public Transaction(String idTransaction, String transactionDate, String phoneNumber, List<Product> productList) {
        this.idTransaction = idTransaction;
        this.transactionDate = transactionDate;
        this.phoneNumber = phoneNumber;
        this.productList = productList;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idTransaction='" + idTransaction + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", productList=" + productList +
                '}';
    }
}
