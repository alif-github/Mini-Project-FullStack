package com.backendapi.project.repository;

import com.backendapi.project.model.Product;

import java.util.List;

public interface ProductRepo {

    //Manifest Place CRUD repository, relations to database!

    //Create the Data----------------------------------------
    void saveProduct(Product product);

    //Read the Data------------------------------------------

    //Read All Data
    List<Product> findAllProduct();

    //Read All Data With Paggination
    List<Product> findAllProductPagging(int page , int limit);

    //Read the Data by id
    Product findByIdProduct(String idProduct);

    //Read the Data by name
    List<Product> findByNameProduct(String product);

    //Read By name provider and name type
    List<Product> findByNameAndType(String idProvider , int idType);

    //Read By id Provider
    List<Product> findByIdProvider(String idProvider);

    //Update the Data----------------------------------------
    void updateDataProduct(String idProduct, Product product);

    //Delete the Data----------------------------------------

    //Delete the Data (1 Data) by id
    void deleteByIdProduct(String idProduct);
}
