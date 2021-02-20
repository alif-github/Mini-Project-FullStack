package com.backendapi.project.service;

import com.backendapi.project.model.Transaction;

import java.util.List;

public interface TransactionService {

    //Manifest Place CRUD repository, relations to database!

    //Create the Data----------------------------------------
    void saveTransaction(Transaction transaction);

    //Read the Data------------------------------------------

    //Read All Data
    List<Transaction> findAllTransactionSave();

    //Read All Data With Paggination
    List<Transaction> findAllTransactionPagging(int page , int limit);

    //Read the Data by id
    Transaction findByIdTransaction(String idTransaction);

    //Read the Data by no hp
    Transaction findByNoHpTransaction(String phoneNumber);

    //Update the Data----------------------------------------
    void updateDataTransaction(Transaction transaction);

    //Delete the Data----------------------------------------

    //Delete the Data (1 Data) by id
    void deleteByIdTransaction(String idTransaction);

    //is Transaction Exist,-------------------------------------
    boolean isTransactionExist(Transaction transaction);
}
