package com.backendapi.project.service;

import com.backendapi.project.model.Transaction;
import com.backendapi.project.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepo transactionRepo;

    @Override
    public void saveTransaction(Transaction transaction) {
        synchronized (this) {
            transactionRepo.saveTransaction(transaction);
        }
    }

    @Override
    public List<Transaction> findAllTransactionSave() {
        List<Transaction> transactionList = transactionRepo.findAllTransactionSave();
        return transactionList;
    }

    @Override
    public List<Transaction> findAllTransactionPagging(int page, int limit) {
        List<Transaction> transactionList = transactionRepo.findAllTransactionPagging(page, limit);
        return transactionList;
    }

    @Override
    public Transaction findByIdTransaction(String idTransaction) {
        Transaction transaction;
        try {
            transaction = transactionRepo.findByIdTransaction(idTransaction);
        } catch (Exception e) {
            transaction = null;
        }
        return transaction;
    }

    @Override
    public Transaction findByNoHpTransaction(String phoneNumber) {
        Transaction transaction;
        try {
            transaction = transactionRepo.findByNoHpTransaction(phoneNumber);
        } catch (Exception e) {
            transaction = null;
        }
        return transaction;
    }

    @Override
    public void updateDataTransaction(Transaction transaction) {
        synchronized (this) {
            transactionRepo.updateDataTransaction(transaction);
        }
    }

    @Override
    public void deleteByIdTransaction(String idTransaction) {
        synchronized (this) {
            transactionRepo.deleteByIdTransaction(idTransaction);
        }
    }

    @Override
    public boolean isTransactionExist(Transaction transaction) {
        return transactionRepo.findByNoHpTransaction(transaction.getPhoneNumber()) != null;
    }
}
