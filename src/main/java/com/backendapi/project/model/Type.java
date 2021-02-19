package com.backendapi.project.model;

public class Type {
    private int idType;
    private String type;

    public Type(int idType, String type) {
        this.idType = idType;
        this.type = type;
    }

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Type{" +
                "idType=" + idType +
                ", type='" + type + '\'' +
                '}';
    }
}
