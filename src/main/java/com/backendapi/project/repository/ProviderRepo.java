package com.backendapi.project.repository;

import com.backendapi.project.model.Provider;

import java.util.List;

public interface ProviderRepo {

    //Manifest Place CRUD repository, relations to database!

    //Create the Data----------------------------------------
    void saveProvider(Provider provider);

    //Read the Data------------------------------------------

    //Read All Data
    List<Provider> findAllProvider();

    //Read All Data With Paggination
    List<Provider> findAllProviderPagging(int page , int limit);

    //Read the Data by id
    Provider findByIdProvider(String idProvider);

    //Read the Data by name
    List<Provider> findByNameProvider(String provider);

    //Update the Data----------------------------------------
    void updateDataProvider(String idProvider, Provider provider);

    //Delete the Data----------------------------------------

    //Delete the Data (1 Data) by id
    void deleteByIdProvider(String idProvider);
}
