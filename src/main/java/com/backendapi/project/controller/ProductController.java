package com.backendapi.project.controller;

import com.backendapi.project.model.Product;
import com.backendapi.project.model.Provider;
import com.backendapi.project.service.ProductService;
import com.backendapi.project.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService; //connect to provider service for CRUD

    //Create Category Data--------------------------------------------------------------------
    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        logger.info("Creating Product : {}", product);

        if (productService.isProductExist(product)) {
            logger.error("Provider with name {} already exist", product.getProduct());
            return new ResponseEntity<>(new CustomErrorType("Provider with name " + product.getProduct() + " already exist."), HttpStatus.CONFLICT);
        } else {
            productService.saveProduct(product);
//            Provider providerSave = providerService.findByIdProvider(provider.getIdProvider());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    //Show All Data---------------------------------------------------------------------------
    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> showProduct() {
        logger.info("Showing Product ...");
        List<Product> products = productService.findAllProduct();

        if (products.isEmpty()) {
            logger.error("Unable to show Product, because empty on Database");
            return new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
    }
}
