package com.backendapi.project.repository;

import com.backendapi.project.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("productRepository")
public class ProductRepoImpl implements ProductRepo {

    @Autowired
    JdbcTemplate databases; //connect to database

    @Override
    public void saveProduct(Product product) {
        String randomId = UUID.randomUUID().toString();
        product.setIdProduct(randomId);

        String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?)";
        databases.update(sql,
                product.getIdProduct(),
                product.getProduct(),
                product.getValue(),
                product.getIdProvider(),
                product.getIdType(),
                product.getStock(),
                product.getHarga(),
                product.getExpiredDate());
    }

    @Override
    public List<Product> findAllProduct() {
        String sql = "select * from product p inner join provider r on p.idProvider = r.idProvider inner join type t on t.idType = p.idType;";
        return databases.query(sql,
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("idProvider"),
                                rs.getInt("idType"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
                                rs.getString("provider"),
                                rs.getString("type")
                        ));
    }

    @Override
    public List<Product> findAllProductPagging(int page, int limit) {
        //Count the row

        String sqlCount = "SELECT COUNT(product) as count FROM product";
        int numberPages;
        numberPages = databases.query(sqlCount,
                (rs, rowNum) -> rs.getInt("count")).get(0);

        //Validate data page

        if (page < 1) {
            page = 1;
        }
        if (page > numberPages) {
            page = numberPages;
        }

        int start = (page - 1) * limit;

        String sql = "select * from product p inner join provider r on p.idProvider = r.idProvider inner join type t on t.idType = p.idType ";

        List<Product> productList = databases.query(""+ sql +" LIMIT "+start + "," + limit + ";",
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("idProvider"),
                                rs.getInt("idType"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
                                rs.getString("provider"),
                                rs.getString("type")
                        ));
        return productList;
    }

    @Override
    public Product findByIdProduct(String idProduct) {
        String sql = "select * from product p inner join provider r on p.idProvider = r.idProvider inner join type t on t.idType = p.idType ";

        String sqlForFindId = "" + sql + " WHERE idProduct ='" + idProduct + "'";
        return databases.queryForObject(sqlForFindId,
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("idProvider"),
                                rs.getInt("idType"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
                                rs.getString("provider"),
                                rs.getString("type")
                        ));
    }

    @Override
    public List<Product> findByNameProduct(String product) {
        String sql = "select * from product p inner join provider r on p.idProvider = r.idProvider inner join type t on t.idType = p.idType ";

        String sqlForFindName = "" + sql + " WHERE product LIKE ?";
        return databases.query(sqlForFindName,
                new Object[]{product},
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("idProvider"),
                                rs.getInt("idType"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
                                rs.getString("provider"),
                                rs.getString("type")
                        ));
    }

    @Override
    public List<Product> findByNameAndType(String idProvider, int idType) {

        String sqlForFindNameNType = "select * from product p inner join provider r on p.idProvider = r.idProvider " +
                "inner join type t on t.idType = p.idType AND p.idProvider = '"+idProvider+"' AND p.idType = '"+idType+"'";
        return databases.query(sqlForFindNameNType,
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("idProvider"),
                                rs.getInt("idType"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
                                rs.getString("provider"),
                                rs.getString("type")
                        ));
    }

    @Override
    public List<Product> findByIdProvider(String idProvider) {
        String sql = "select * from product p inner join provider r on p.idProvider = r.idProvider inner join type t on t.idType = p.idType ";

        String sqlForFindId = "" + sql + " WHERE p.idProvider ='" + idProvider + "'";
        return databases.query(sqlForFindId,
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("idProvider"),
                                rs.getInt("idType"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
                                rs.getString("provider"),
                                rs.getString("type")
                        ));
    }

    @Override
    public void updateDataProduct(String idProduct, Product product) {
        String sqlUpdate = "UPDATE product SET product = ?, value = ?, idProvider = ?, idType = ?, stock = ?, harga = ?, expiredDate = ? WHERE idProduct = ?;";

        databases.update(sqlUpdate, product.getProduct(), product.getValue(),
                product.getIdProvider(), product.getIdType(),
                product.getStock(), product.getHarga(),
                product.getExpiredDate(), idProduct);
    }

    @Override
    public void deleteByIdProduct(String idProduct) {
        String sqlDelete = "DELETE FROM product WHERE idProduct ='" + idProduct + "'";
        databases.execute(sqlDelete);

    }
}
