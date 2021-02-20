package com.backendapi.project.controller;

import com.backendapi.project.model.Transaction;
import com.backendapi.project.service.TransactionService;
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
public class TransactionController {

    public static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionService transactionService; //connect to transaction service for CRUD

    //Create Category Data--------------------------------------------------------------------
    @RequestMapping(value = "/transaction/", method = RequestMethod.POST)
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        logger.info("Creating Transaction..");

        String phoneNumberResp = transaction.getPhoneNumber();
        int lengthPhoneNumber = phoneNumberResp.length();

        //validation in phone number
        //no Hp is not able empty and length must be 11
        if (phoneNumberResp == "" || lengthPhoneNumber != 11) {
            return new ResponseEntity<>("Bad-Request", HttpStatus.BAD_REQUEST);
        } else {
            transactionService.saveTransaction(transaction);
            List<Transaction> transaction1 = transactionService.findAllTransactionSave();
            return new ResponseEntity<>(transaction1, HttpStatus.CREATED);
        }
    }

    //Show All Data---------------------------------------------------------------------------
    @RequestMapping(value = "/transaction/", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> showTransaction() {
        logger.info("Showing Transaction ...");
        List<Transaction> transactionList = transactionService.findAllTransactionSave();

        if (transactionList.isEmpty()) {
            logger.error("Unable to show Transaction, because empty on Database");
            return new ResponseEntity<>(transactionList, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
        }
    }

    //Show All Data with Pagging--------------------------------------------------------------
    @RequestMapping(value = "/transaction/page/", method = RequestMethod.GET)
    public ResponseEntity<List<Transaction>> showTransactionPagging(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        logger.info("Showing Transaction ...");
        List<Transaction> transactionList = transactionService.findAllTransactionPagging(page, limit);

        if (transactionList.isEmpty()) {
            logger.error("Unable to show Transaction, because empty on Database");
            return new ResponseEntity<>(transactionList, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(transactionList, HttpStatus.OK);
        }
    }

    //Show Data By Id-------------------------------------------------------------------------
    @RequestMapping(value = "/transaction/id/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleTransactionId(@RequestParam("id") String idTransaction) {
        logger.info("Showing Transaction with id {} ...", idTransaction);

        Transaction transaction = transactionService.findByIdTransaction(idTransaction);
        if (transaction == null) {
            logger.error("Unable to show transaction, because transaction id {} is not found", idTransaction);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Product , because not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        }
    }

    //Show Data By Phone Number---------------------------------------------------------------
    @RequestMapping(value = "/transaction/phone/", method = RequestMethod.GET)
    public ResponseEntity<?> showSinglePhoneNumber(@RequestParam("phone") String phoneNumber) {
        logger.info("Showing Transaction with phone Number {} ...", phoneNumber);

        Transaction transaction = transactionService.findByNoHpTransaction(phoneNumber);
        if (transaction == null) {
            logger.error("Unable to show Transaction, because Phone Number {} is not found", phoneNumber);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Transaction, because not found"), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(transaction, HttpStatus.OK);
        }
    }

    //Update Data By Id-----------------------------------------------------------------------
    @RequestMapping(value = "/transaction/update/id/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSingleTransaction(@RequestParam("id") String idTransaction ,@RequestBody Transaction transaction) {
        logger.info("Showing Transaction...");

        Transaction beforeUpdateTransaction = transactionService.findByNoHpTransaction(transaction.getPhoneNumber());
        if (beforeUpdateTransaction == null) {
            logger.error("Unable to show Provider, because phone Number {} is not found", transaction.getPhoneNumber());
            return new ResponseEntity<>(new CustomErrorType("Unable to show Transaction, because not found"), HttpStatus.NOT_FOUND);
        } else {
            transactionService.updateDataTransaction(transaction);
            Transaction afterUpdateTransaction = transactionService.findByNoHpTransaction(transaction.getPhoneNumber());
            return new ResponseEntity<>(afterUpdateTransaction, HttpStatus.OK);
        }
    }

    //Delete Data By Id-----------------------------------------------------------------------
    @RequestMapping(value = "/transaction/id/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSingleTransactionById(@RequestParam("id") String idTransaction) {
        logger.info("Delete Provider with id {} ...", idTransaction);

        Transaction findingId = transactionService.findByIdTransaction(idTransaction);
        if (findingId == null) {
            logger.error("Unable to deleting that Transaction, because transaction id {} is not found", idTransaction);
            return new ResponseEntity<>(new CustomErrorType("Unable to deleting that Transaction " + idTransaction + " , because not found"), HttpStatus.NOT_FOUND);
        } else {
            transactionService.deleteByIdTransaction(idTransaction);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
