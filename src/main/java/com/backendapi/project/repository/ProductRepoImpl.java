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
        String sql = "SELECT g.idProduct, g.product, g.value, g.provider, g.stock, g.harga, g.expiredDate, t.type " +
                "FROM (SELECT p.idProduct, p.product, p.value, v.provider, " +
                "p.idType, p.stock, p.harga, p.expiredDate FROM product p INNER JOIN " +
                "provider v ON p.idProvider = v.idProvider) g INNER JOIN type t ON g.idType = t.idType;";
        return databases.query(sql,
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("provider"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
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

        String sql = "SELECT g.idProduct, g.product, g.value, g.provider, g.stock, g.harga, g.expiredDate, t.type " +
                "FROM (SELECT p.idProduct, p.product, p.value, v.provider, " +
                "p.idType, p.stock, p.harga, p.expiredDate FROM product p INNER JOIN " +
                "provider v ON p.idProvider = v.idProvider) g INNER JOIN type t ON g.idType = t.idType ";

        List<Product> productList = databases.query(sql + start + "," + limit + ";",
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("provider"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
                                rs.getString("type")
                        ));
        return productList;
    }

    @Override
    public Product findByIdProduct(String idProduct) {
        String sql = "SELECT g.idProduct, g.product, g.value, g.provider, g.stock, g.harga, g.expiredDate, t.type " +
                "FROM (SELECT p.idProduct, p.product, p.value, v.provider, " +
                "p.idType, p.stock, p.harga, p.expiredDate FROM product p INNER JOIN " +
                "provider v ON p.idProvider = v.idProvider) g INNER JOIN type t ON g.idType = t.idType ";

        String sqlForFindId = "SELECT * FROM " + sql + " WHERE idProduct ='" + idProduct + "'";
        return databases.queryForObject(sqlForFindId,
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("provider"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
                                rs.getString("type")
                        ));
    }

    @Override
    public List<Product> findByNameProduct(String product) {
        String sql = "SELECT g.idProduct, g.product, g.value, g.provider, g.stock, g.harga, g.expiredDate, t.type " +
                "FROM (SELECT p.idProduct, p.product, p.value, v.provider, " +
                "p.idType, p.stock, p.harga, p.expiredDate FROM product p INNER JOIN " +
                "provider v ON p.idProvider = v.idProvider) g INNER JOIN type t ON g.idType = t.idType ";

        String sqlForFindName = "SELECT * FROM " + sql + " WHERE product LIKE ?";
        return databases.query(sqlForFindName,
                new Object[]{product},
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("provider"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
                                rs.getString("type")
                        ));
    }

    @Override
    public List<Product> findByNameAndType(String provider, String type) {
        String sql = "SELECT g.idProduct, g.product, g.value, g.provider, g.stock, g.harga, g.expiredDate, t.type " +
                "FROM (SELECT p.idProduct, p.product, p.value, v.provider, " +
                "p.idType, p.stock, p.harga, p.expiredDate FROM product p INNER JOIN " +
                "provider v ON p.idProvider = v.idProvider) g INNER JOIN type t ON g.idType = t.idType ";

        String sqlForFindNameNType = "SELECT * FROM " + sql + " WHERE product LIKE ? AND type LIKE ?";
        return databases.query(sqlForFindNameNType,
                new Object[]{provider, type},
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getString("value"),
                                rs.getString("provider"),
                                rs.getInt("stock"),
                                rs.getDouble("harga"),
                                rs.getString("expiredDate"),
                                rs.getString("type")
                        ));
    }

    @Override
    public void updateDataProduct(String idProduct, Product product) {
        String sql = "SELECT g.idProduct, g.product, g.value, g.provider, g.stock, g.harga, g.expiredDate, t.type " +
                "FROM (SELECT p.idProduct, p.product, p.value, v.provider, " +
                "p.idType, p.stock, p.harga, p.expiredDate FROM product p INNER JOIN " +
                "provider v ON p.idProvider = v.idProvider) g INNER JOIN type t ON g.idType = t.idType ";

        String sqlUpdate = "UPDATE " + sql + " SET product = ?, value = ?, idProvider = ?, idType = ?, stock = ?, harga = ?, expiredDate = ? WHERE idProduct = ?;";
        databases.update(sqlUpdate, product.getStock(), product.getHarga(), product.getExpiredDate(), idProduct);
    }

    @Override
    public void deleteByIdProduct(String idProduct) {
        String sqlDelete = "DELETE FROM product WHERE idProduct ='" + idProduct + "'";
        databases.execute(sqlDelete);

    }
}
