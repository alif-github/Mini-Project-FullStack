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

    //Read All Data With Paggination
    List<Product> findAllProductPagging(int page, int limit);

    //Read the Data by id
    Product findByIdProduct(String idProduct);

    //Read the Data by name
    Product findByNameProduct(String product);

    //Read By name provider and name type
    List<Product> findByNameAndType(String provider , String type);

    //Update the Data----------------------------------------
    void updateDataProduct(String idProduct, Product product);

    //Delete the Data----------------------------------------

    //Delete the Data (1 Data) by id
    void deleteByIdProduct(String idProduct);

    //is Product Exist,-------------------------------------
    //to check with name of Product parameter
    boolean isProductExist(Product product);
}
