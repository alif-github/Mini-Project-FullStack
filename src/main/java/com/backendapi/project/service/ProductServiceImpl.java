package com.backendapi.project.service;

import com.backendapi.project.model.Product;
import com.backendapi.project.repository.ProductRepo;
import com.sun.el.parser.AstPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Override
    public void saveProduct(Product product) {
        synchronized (this) { //Critical section synchronized
            productRepo.saveProduct(product);
        }
    }

    @Override
    public List<Product> findAllProduct() {
        List<Product> productList = productRepo.findAllProduct();
        return productList;
    }

    @Override
    public List<Product> findAllProductPagging(int page, int limit) {
        return null;
    }

    @Override
    public Product findByIdProduct(String idProduct) {
        return null;
    }

    @Override
    public Product findByNameProduct(String product) {
        return null;
    }

    @Override
    public void updateDataProduct(String idProduct, Product product) {

    }

    @Override
    public void deleteByIdProduct(String idProduct) {

    }

    @Override
    public boolean isProductExist(Product product) {
        return false;
    }
}
