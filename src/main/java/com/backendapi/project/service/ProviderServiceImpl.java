package com.backendapi.project.service;

import com.backendapi.project.model.Provider;
import com.backendapi.project.repository.ProviderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("providerService")
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    ProviderRepo providerRepo;


    @Override
    public void saveProvider(Provider provider) {

    }

    @Override
    public List<Provider> findAllProvider() {
        return null;
    }

    @Override
    public Provider findByIdProvider(String idProvider) {
        return null;
    }

    @Override
    public Provider findByNameProvider(String provider) {
        return null;
    }

    @Override
    public void updateDataProvider(String idProvider, Provider provider) {

    }

    @Override
    public void deleteAllProvider() {

    }

    @Override
    public void deleteByIdProvider(String idProvider) {

    }

    @Override
    public boolean isProviderExist(Provider provider) {
        return false;
    }
}
