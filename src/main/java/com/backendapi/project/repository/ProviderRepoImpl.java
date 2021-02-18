package com.backendapi.project.repository;

import com.backendapi.project.model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("providerRepository")
public class ProviderRepoImpl implements ProviderRepo {

    @Autowired
    JdbcTemplate databases; //connect to database

    @Override
    public void saveProvider(Provider provider) {
        String sql = "INSERT INTO provider(idProvider , provider) VALUES(?,?)";
        databases.update(sql,
                provider.getIdProvider(),
                provider.getProvider());
    }

    @Override
    public List<Provider> findAllProvider() {
        String sql = "SELECT * FROM provider";
        return databases.query(sql,
                (rs, rowNum) ->
                        new Provider(
                                rs.getString("idProvider"),
                                rs.getString("provider")
                        ));
    }

    @Override
    public Provider findByIdProvider(String idProvider) {
        String sql = "SELECT * FROM provider WHERE idProvider ='"+idProvider+"'";
        return databases.queryForObject(sql,
                (rs, rowNum) ->
                        new Provider(
                                rs.getString("idProvider"),
                                rs.getString("provider")
                        ));
    }

    @Override
    public List<Provider> findByNameProvider(String provider) {
        String sql = "SELECT * FROM provider WHERE provider LIKE ?";
        return databases.query(sql,
                new Object[]{"%"+provider+"%"},
                (rs, rowNum) ->
                        new Provider(
                                rs.getString("idProvider"),
                                rs.getString("provider")
                        ));
    }

    @Override
    public void updateDataProvider(String idProvider, Provider provider) {
        String sql = "UPDATE provider\n" +
                "SET provider = ?\n" +
                "WHERE idProvider = ?;";
        databases.update(sql, provider.getProvider(), idProvider);
    }

    @Override
    public void deleteByIdProvider(String idProvider) {
        String sql = "DELETE FROM provider WHERE provider ='"+idProvider+"'";
    }
}
