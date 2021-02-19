package com.backendapi.project.service;

import com.backendapi.project.model.Product;
import com.backendapi.project.repository.ProductRepo;
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
        List<Product> productList = productRepo.findAllProductPagging(page, limit);
        return productList;
    }

    @Override
    public Product findByIdProduct(String idProduct) {
        Product product;
        try {
            product = productRepo.findByIdProduct(idProduct);
        } catch (Exception e) {
            product = null;
        }
        return product;
    }

    @Override
    public Product findByNameProduct(String product) {
        Product product1;
        try {
            product1 = (Product) productRepo.findByNameProduct(product).get(0);
        } catch (Exception e) {
            product1 = null;
        }
        return product1;
    }

    @Override
    public List<Product> findByNameAndType(String provider, String type) {
        List<Product> productList;
        try {
            productList = productRepo.findByNameAndType(provider, type);
        } catch (Exception e) {
            productList = null;
        }
        return productList;
    }

    @Override
    public void updateDataProduct(String idProduct, Product product) {
        synchronized (this) {
            productRepo.updateDataProduct(idProduct, product);
        }
    }

    @Override
    public void deleteByIdProduct(String idProduct) {
        synchronized (this) {
            productRepo.deleteByIdProduct(idProduct);
        }
    }

    @Override
    public boolean isProductExist(Product product) {
        return productRepo.findByNameProduct(product.getProduct()).size() != 0;
    }
}
