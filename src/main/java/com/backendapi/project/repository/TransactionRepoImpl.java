package com.backendapi.project.repository;

import com.backendapi.project.model.Product;
import com.backendapi.project.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository("cartRepo")
public class TransactionRepoImpl implements TransactionRepo {

    @Autowired
    JdbcTemplate databases;

    @Override
    public void saveTransaction(Transaction transaction) {

        //setting Random ID for Transaction
        String idRandom = UUID.randomUUID().toString();
        String idRandomTrx = "TRX - " + idRandom;
        transaction.setIdTransaction(idRandomTrx);
        transaction.setTransactionDate(new Date().toString());

        String sqlHead = "INSERT INTO transaction(idTransaction,transactionDate,phoneNumber) VALUES (?,?,?)";
        databases.update(sqlHead,
                transaction.getIdTransaction(),
                transaction.getTransactionDate(),
                transaction.getPhoneNumber()
        );

        List<Product> productList = transaction.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            String idRandomDetail = UUID.randomUUID().toString();
            String idRandomDetailTRD = "TRD - " + idRandomDetail;
            String sqlDetail = "INSERT INTO transactiondetail(idTransactionDetail,idTransaction,idProduct) VALUES(?,?,?)";
            databases.update(sqlDetail, idRandomDetailTRD, transaction.getIdTransaction(), productList.get(i).getIdProduct());
        }

    }

    @Override
    public List<Transaction> findAllTransactionSave() {
        String sqlHead = "SELECT * FROM transaction";
        List<Transaction> transactionList;
        transactionList = databases.query(sqlHead,
                (rs, rowNum) ->
                        new Transaction(
                                rs.getString("idTransaction"),
                                rs.getString("transactionDate"),
                                rs.getString("phoneNumber"),
                                null
                        ));
        for (Transaction transaction : transactionList) {

            String sqlDetail = "select * from transactiondetail t inner join product p " +
                    "on t.idProduct = p.idProduct inner join provider r " +
                    "on r.idProvider = p.idProvider inner join type y " +
                    "on y.idType = p.idType where idTransaction = ?;";

            transaction.setProductList(databases.query(sqlDetail,
                    preparedStatement -> preparedStatement.setString(1, transaction.getIdTransaction()),
                    (rs, rowNum) ->
                            new Product(
                                    rs.getString("idProduct"),
                                    rs.getString("product"),
                                    rs.getString("value"),
                                    rs.getString("idProvider"),
                                    rs.getInt("idType"),
                                    rs.getInt("stock"),
                                    rs.getDouble("harga"),
                                    rs.getString("provider"),
                                    rs.getString("expiredDate"),
                                    rs.getString("type")
                            )));
        }
        return transactionList;
    }

    @Override
    public List<Transaction> findAllTransactionPagging(int page, int limit) {
        int numPages;
        numPages = databases.query("SELECT COUNT(*) as count FROM transaction",
                (rs, rowNum) ->
                        rs.getInt("count")).get(0);

        //validatePage
        if (page < 1) page = 1;
        if (page > numPages) page = numPages;

        int start = (page - 1) * limit;

        String sqlHead = "SELECT * FROM transaction LIMIT " + start + "," + limit + "";
        List<Transaction> transactionList;
        transactionList = databases.query(sqlHead,
                (rs, rowNum) ->
                        new Transaction(
                                rs.getString("idTransaction"),
                                rs.getString("transactionDate"),
                                rs.getString("phoneNumber"),
                                null
                        ));
        for (Transaction transaction : transactionList) {

            String sqlDetail = "select * from transactiondetail t inner join product p " +
                    "on t.idProduct = p.idProduct inner join provider r " +
                    "on r.idProvider = p.idProvider inner join type y " +
                    "on y.idType = p.idType where idTransaction = ?;";

            transaction.setProductList(databases.query(sqlDetail,
                    preparedStatement -> preparedStatement.setString(1, transaction.getIdTransaction()),
                    (rs, rowNum) ->
                            new Product(
                                    rs.getString("idProduct"),
                                    rs.getString("product"),
                                    rs.getString("value"),
                                    rs.getString("idProvider"),
                                    rs.getInt("idType"),
                                    rs.getInt("stock"),
                                    rs.getDouble("harga"),
                                    rs.getString("provider"),
                                    rs.getString("expiredDate"),
                                    rs.getString("type")
                            )));
        }
        return transactionList;
    }

    @Override
    public Transaction findByIdTransaction(String idTransaction) {
        String sqlHead = "SELECT * FROM transaction WHERE idTransaction ='" + idTransaction + "'";
        Transaction transaction;
        transaction = databases.queryForObject(sqlHead,
                (rs, rowNum) ->
                        new Transaction(
                                rs.getString("idTransaction"),
                                rs.getString("transactionDate"),
                                rs.getString("phoneNumber"),
                                null
                        ));

        String sqlDetail = "select * from transactiondetail t inner join product p " +
                "on t.idProduct = p.idProduct inner join provider r " +
                "on r.idProvider = p.idProvider inner join type y " +
                "on y.idType = p.idType where idTransaction = ?;";

        transaction.setProductList(databases.query(sqlDetail,
                preparedStatement -> preparedStatement.setString(1, transaction.getIdTransaction()),
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("idProvider"),
                                rs.getInt("idType"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("provider"),
                                rs.getString("expiredDate"),
                                rs.getString("type")
                        )));

        return transaction;
    }

    @Override
    public Transaction findByNoHpTransaction(String phoneNumber) {

        String sqlHead = "SELECT * FROM transaction WHERE phoneNumber ='" + phoneNumber + "'";
        Transaction transaction;
        transaction = databases.queryForObject(sqlHead,
                (rs, rowNum) ->
                        new Transaction(
                                rs.getString("idTransaction"),
                                rs.getString("transactionDate"),
                                rs.getString("phoneNumber"),
                                null
                        ));

        String sqlDetail = "select * from transactiondetail t inner join product p " +
                "on t.idProduct = p.idProduct inner join provider r " +
                "on r.idProvider = p.idProvider inner join type y " +
                "on y.idType = p.idType where idTransaction = ?;";

        transaction.setProductList(databases.query(sqlDetail,
                preparedStatement -> preparedStatement.setString(1, transaction.getIdTransaction()),
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("idProvider"),
                                rs.getInt("idType"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("provider"),
                                rs.getString("expiredDate"),
                                rs.getString("type")
                        )));

        return transaction;
    }

    @Override
    public void updateDataTransaction(Transaction transaction) {
        String idTransactionTemp = transaction.getIdTransaction();
        String dateTransactionTemp = transaction.getTransactionDate();

        String sqlTransactionDetail = "DELETE FROM transactiondetail WHERE idTransaction = ?";
        String sqlTransaction = "DELETE FROM transaction WHERE idTransaction = ?";
        databases.update(sqlTransactionDetail, transaction.getIdTransaction());
        databases.update(sqlTransaction, transaction.getIdTransaction());

        String sqlHead = "INSERT INTO transaction(idTransaction,transactionDate,phoneNumber) VALUES (?,?,?)";
        databases.update(sqlHead,
                idTransactionTemp,
                dateTransactionTemp,
                transaction.getPhoneNumber()
        );

        List<Product> productList = transaction.getProductList();
        for (int i = 0; i < productList.size(); i++) {
            String idRandomDetail = UUID.randomUUID().toString();
            String idRandomDetailTRD = "TRD - " + idRandomDetail;
            String sqlDetail = "INSERT INTO transactiondetail(idTransactionDetail,idTransaction,idProduct) VALUES(?,?,?)";
            databases.update(sqlDetail, idRandomDetailTRD, idTransactionTemp, productList.get(i).getIdProduct());
        }
    }

    //Delete All from transaction until transaction detail
    @Override
    public void deleteByIdTransaction(String idTransaction) {
        String sqlTransactionDetail = "DELETE FROM transactiondetail WHERE idTransaction = '" + idTransaction + "'";
        String sqlTransaction = "DELETE FROM transaction WHERE idTransaction = '" + idTransaction + "'";
        databases.execute(sqlTransactionDetail);
        databases.update(sqlTransaction);
    }
}
