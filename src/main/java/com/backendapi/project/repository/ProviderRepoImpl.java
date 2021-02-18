package com.backendapi.project.repository;

import com.backendapi.project.helper.LastIdProvider;
import com.backendapi.project.model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("providerRepository")
public class ProviderRepoImpl implements ProviderRepo {

    @Autowired
    JdbcTemplate databases; //connect to database

    @Autowired
    LastIdProvider lastIdProvider;

    @Override
    public void saveProvider(Provider provider) {
        //-------------------------------------------------------------------------------------------------
        // We Build Format ID for provider !!
        //-------------------------------------------------------------------------------------------------
        //because the ID is set unique and suit with request user, we made the special configure ID
        String lastID = "ID000", getNoQueue, number;
        String newNumberString = "ID001";
        int lengthNumber, idNew, idNext;

        try {
            lastID = lastIdProvider.findLastIdProvider().getIdProvider(); //get return last id
        } catch (Exception e) {
            System.out.println("Message Error not found");
        } finally {
            getNoQueue = lastID.substring(2); //get last 2 id length, slicing
            idNew = Integer.parseInt(getNoQueue); //parsing to Integer
            idNext = idNew + 1; //sum with + 1
            number = String.valueOf(idNext); //parsing to String
            lengthNumber = number.length(); //sum length
            if (lengthNumber == 2) {
                newNumberString = "ID"+"0"+number; //Regex own
            } else if (lengthNumber <= 1) {
                newNumberString = "ID"+"00"+number; //Regex own
            } else {
                newNumberString = "ID"+number; //Regex own
            }
        }
        //-------------------------------------------------------------------------------------------------

        provider.setIdProvider(newNumberString);

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
    public List<Provider> findAllProviderPagging(int page , int limit) {
        String sql = "SELECT COUNT(provider) as count FROM provider";
        int numberPages;
        numberPages = databases.query(sql,
                (rs, rowNum) -> rs.getInt("count")).get(0);

        //validate data page

        if(page < 1) {
            page = 1;
        }
        if(page > numberPages) {
            page = numberPages;
        }

        int start = (page - 1)*limit;
        List<Provider> providerList = databases.query("SELECT * FROM provider LIMIT "+start+","+limit+";",
                (rs, rowNum) ->
                    new Provider(
                            rs.getString("idProvider"),
                            rs.getString("provider")
                    ));
        return providerList;
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
                new Object[]{provider},
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
        String sql = "DELETE FROM provider WHERE idProvider ='"+idProvider+"'";
        databases.execute(sql);
    }
}
