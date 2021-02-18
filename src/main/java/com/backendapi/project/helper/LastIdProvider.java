package com.backendapi.project.helper;

import com.backendapi.project.model.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class LastIdProvider {

    //For help in save Provider in Provider Repository!

    @Autowired
    JdbcTemplate databases; //connect to databases

    public Provider findLastIdProvider() {
        String querySql = "SELECT idProvider FROM provider ORDER BY idProvider DESC LIMIT 1";
        return databases.queryForObject(querySql,
                (rs, rowNum) ->
                        new Provider(
                                rs.getString("idProvider"),
                                null
                        ));
    }
}
