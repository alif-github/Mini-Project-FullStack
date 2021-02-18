package com.backendapi.project.service;

import com.backendapi.project.model.Product;

import java.util.List;

public interface ProductService {

    //Manifest Place CRUD service, security before repository!

    //Create the Data----------------------------------------
    void saveProduct(Product product);

    //Read the Data------------------------------------------

    //Read All Data
    List<Product> findAllProduct();

    //Read Data After Create
    List<Product> findAllProductSave();

    //Read the Data by id
    Product findByIdProduct(String idOperator);

    //Read the Data by name
    Product findByNameProduct(String operator);

    //Read the Data by params id and name
    Product findByIdAndNameProduct(String idOperator , String operator);

    //Delete the Data----------------------------------------

    //Delete All Data
    void deleteAllProduct();

    //Delete the Data (1 Data) by id
    void deleteByIdProduct(String idOperator);

    //is Product Exist,-------------------------------------
    //to check with name of Product parameter
    boolean isProductExist(Product product);
}
