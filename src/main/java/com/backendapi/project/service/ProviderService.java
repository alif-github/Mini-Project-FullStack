package com.backendapi.project.service;

import com.backendapi.project.model.Provider;

import java.util.List;

public interface ProviderService {

    //Manifest Place CRUD service, security before repository!

    //Create the Data----------------------------------------
    void saveProvider(Provider provider);

    //Read the Data------------------------------------------

    //Read All Data
    List<Provider> findAllProvider();

    //Read the Data by id
    Provider findByIdProvider(String idProvider);

    //Read the Data by name
    Provider findByNameProvider(String provider);

    //Update the Data----------------------------------------
    void updateDataProvider(String idProvider, Provider provider);

    //Delete the Data----------------------------------------

    //Delete All Data
    void deleteAllProvider();

    //Delete the Data (1 Data) by id
    void deleteByIdProvider(String idProvider);

    //is Provider Exist,-------------------------------------
    //to check with name of Provider parameter
    boolean isProviderExist(Provider provider);
}
