package com.backendapi.project.controller;

import com.backendapi.project.model.Product;
import com.backendapi.project.model.Provider;
import com.backendapi.project.service.ProductService;
import com.backendapi.project.service.ProviderService;
import com.backendapi.project.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProviderController {

    public static final Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @Autowired
    ProviderService providerService; //connect to provider service for CRUD

    @Autowired
    ProductService productService; //connect to product service for CRUD

    //Create Category Data--------------------------------------------------------------------
    @RequestMapping(value = "/provider/", method = RequestMethod.POST)
    public ResponseEntity<?> createProvider(@RequestBody Provider provider) {
        logger.info("Creating Provider : {}", provider);

        if (providerService.isProviderExist(provider)) {
            logger.error("Unable to create provider, Provider with name {} already exist", provider.getProvider());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Provider with name " + provider.getProvider() + " already exist."), HttpStatus.CONFLICT);
        } else {
            providerService.saveProvider(provider);
            Provider providerSave = providerService.findByIdProvider(provider.getIdProvider());
            return new ResponseEntity<>(providerSave, HttpStatus.CREATED);
        }
    }

    //Show All Data---------------------------------------------------------------------------
    @RequestMapping(value = "/provider/", method = RequestMethod.GET)
    public ResponseEntity<List<Provider>> showProvider() {
        logger.info("Showing Provider ...");
        List<Provider> providerList = providerService.findAllProvider();

        if (providerList.isEmpty()) {
            logger.error("Unable to show Provider, because empty on Database");
            return new ResponseEntity<>(providerList, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(providerList, HttpStatus.OK);
        }
    }

    //Show All Data with Pagging--------------------------------------------------------------
    @RequestMapping(value = "/provider/page/", method = RequestMethod.GET)
    public ResponseEntity<List<Provider>> showProviderPagging(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        logger.info("Showing Provider ...");
        List<Provider> providerList = providerService.findAllProviderPagging(page, limit);

        if (providerList.isEmpty()) {
            logger.error("Unable to show Provider, because empty on Database");
            return new ResponseEntity<>(providerList, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(providerList, HttpStatus.OK);
        }
    }

    //Show Data By Id-------------------------------------------------------------------------
    @RequestMapping(value = "/provider/id/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleProviderId(@RequestParam("id") String idProvider) {
        logger.info("Showing Provider with id {} ...", idProvider);

        Provider provider = providerService.findByIdProvider(idProvider);
        if (provider == null) {
            logger.error("Unable to show Provider, because provider id {} is not found", idProvider);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Provider " + idProvider + " , because not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(provider, HttpStatus.OK);
        }
    }

    //Show Data By Name-----------------------------------------------------------------------
    @RequestMapping(value = "/provider/name/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleProviderName(@RequestParam("name") String provider) {
        logger.info("Showing Provider with name {} ...", provider);

        Provider provider1 = providerService.findByNameProvider(provider);
        if (provider1 == null) {
            logger.error("Unable to show Provider, because provider name {} is not found", provider1);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Provider " + provider + " , because not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(provider1, HttpStatus.OK);
        }
    }

    //Show Count Data-------------------------------------------------------------------------
    @RequestMapping(value = "/provider/count/", method = RequestMethod.GET)
    public ResponseEntity<?> countProvider() {
        logger.info("Showing Provider ...");
        int provider = providerService.countingProviderRows();

        return new ResponseEntity<>(provider, HttpStatus.OK);
    }

    //Update Data By Id-----------------------------------------------------------------------
    @RequestMapping(value = "/provider/id/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSingleProvider(@RequestParam("id") String idProvider, @RequestBody Provider provider) {
        logger.info("Showing Provider with id {} ...", idProvider);

        Provider beforeUpdateProvider = providerService.findByIdProvider(idProvider);
        List<Product> anyProvider = productService.findByIdProvider(idProvider);

        if (beforeUpdateProvider == null) {
            logger.error("Unable to show Provider, because provider id {} is not found", idProvider);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Provider " + idProvider + " , because not found"), HttpStatus.NOT_FOUND);
        } else if (anyProvider.size() <= 0){
            providerService.updateDataProvider(idProvider, provider);
            Provider newUpdateProvider = providerService.findByIdProvider(idProvider);
            return new ResponseEntity<>(newUpdateProvider, HttpStatus.OK);
        } else {
            logger.error("Unable to updating that Provider, because still use in other table");
            return new ResponseEntity<>(new CustomErrorType("Unable to updating that Provider " + idProvider + " , because used in other"), HttpStatus.BAD_REQUEST);
        }
    }

    //Delete Data By Id-----------------------------------------------------------------------
    @RequestMapping(value = "/provider/id/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSingleProviderById(@RequestParam("id") String idProvider) {
        logger.info("Delete Provider with id {} ...", idProvider);
        Provider findingId = providerService.findByIdProvider(idProvider);
        List<Product> anyProvider = productService.findByIdProvider(idProvider);

        if (findingId == null) {
            logger.error("Unable to deleting that Provider, because provider id {} is not found", idProvider);
            return new ResponseEntity<>(new CustomErrorType("Unable to deleting that Provider " + idProvider + " , because not found"), HttpStatus.NOT_FOUND);
        } else if (anyProvider.size() <= 0) {
            providerService.deleteByIdProvider(idProvider);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.error("Unable to deleting that Provider, because still use in other table");
            return new ResponseEntity<>(new CustomErrorType("Unable to deleting that Provider " + idProvider + " , because used in other"), HttpStatus.BAD_REQUEST);
        }
    }
}
