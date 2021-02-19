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
import org.springframework.web.bind.annotation.*;

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
            Product productSave = productService.findByIdProduct(product.getIdProduct());
            return new ResponseEntity<>(productSave , HttpStatus.CREATED);
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

    //Show All Data with Pagging--------------------------------------------------------------
    @RequestMapping(value = "/product/page/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> showProductPagging(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        logger.info("Showing Product ...");
        List<Product> productList = productService.findAllProductPagging(page, limit);

        if (productList.isEmpty()) {
            logger.error("Unable to show Product, because empty on Database");
            return new ResponseEntity<>(productList, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
    }

    //Show Data By Id-------------------------------------------------------------------------
    @RequestMapping(value = "/product/id/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleProductId(@RequestParam("id") String idProduct) {
        logger.info("Showing Product with id {} ...", idProduct);

        Product product = productService.findByIdProduct(idProduct);
        if (product == null) {
            logger.error("Unable to show product, because product id {} is not found", idProduct);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Product " + idProduct + " , because not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
    }

    //Show Data By Name-----------------------------------------------------------------------
    @RequestMapping(value = "/product/name/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleProductName(@RequestParam("name") String product) {
        logger.info("Showing Product with name {} ...", product);

        Product product1 = productService.findByNameProduct(product);
        if (product1 == null) {
            logger.error("Unable to show Product, because product name {} is not found", product1);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Product " + product1 + " , because not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(product1, HttpStatus.OK);
        }
    }

    //Update Data By Id-----------------------------------------------------------------------
    @RequestMapping(value = "/product/id/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSingleProduct(@RequestParam("id") String idProduct, @RequestBody Product product) {
        logger.info("Showing Product with id {} ...", idProduct);

        Product beforeUpdateProduct = productService.findByIdProduct(idProduct);
        if (beforeUpdateProduct == null) {
            logger.error("Unable to show Product, because product id {} is not found", idProduct);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Product " + idProduct + " , because not found"), HttpStatus.NOT_FOUND);
        } else {
            productService.updateDataProduct(idProduct, product);
            Product newUpdateProduct = productService.findByIdProduct(idProduct);
            return new ResponseEntity<>(newUpdateProduct, HttpStatus.OK);
        }
    }

    //Delete Data By Id-----------------------------------------------------------------------
    @RequestMapping(value = "/product/id/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSingleProductById(@RequestParam("id") String idProduct) {
        logger.info("Delete Product with id {} ...", idProduct);

        Product findingId = productService.findByIdProduct(idProduct);
        if (findingId == null) {
            logger.error("Unable to deleting that Product, because product id {} is not found", idProduct);
            return new ResponseEntity<>(new CustomErrorType("Unable to deleting that Product " + idProduct + " , because not found"), HttpStatus.NOT_FOUND);
        } else {
            productService.deleteByIdProduct(idProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
