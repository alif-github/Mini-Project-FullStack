package com.backendapi.project.model;

public class Product {
    private String idOperator;
    private String operator;

    public Product(String idOperator, String operator) {
        this.idOperator = idOperator;
        this.operator = operator;
    }

    public String getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idOperator='" + idOperator + '\'' +
                ", operator='" + operator + '\'' +
                '}';
    }
}
