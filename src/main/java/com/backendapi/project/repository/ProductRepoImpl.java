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
        return null;
    }

    @Override
    public Product findByIdProduct(String idProduct) {
        return null;
    }

    @Override
    public List<Product> findByNameProduct(String product) {
        return null;
    }

    @Override
    public void updateDataProduct(String idProduct, Product product) {

    }

    @Override
    public void deleteByIdProduct(String idProduct) {

    }
}
