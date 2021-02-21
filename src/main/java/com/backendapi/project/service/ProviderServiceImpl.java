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
        synchronized (this) { //Critical section synchronized
            providerRepo.saveProvider(provider);
        }
    }

    @Override
    public List<Provider> findAllProvider() {
        List<Provider> providerList = providerRepo.findAllProvider();
        return providerList;
    }

    @Override
    public List<Provider> findAllProviderPagging(int page , int limit) {
        List<Provider> providerList = providerRepo.findAllProviderPagging(page, limit);
        return providerList;
    }

    @Override
    public Provider findByIdProvider(String idProvider) {
        Provider provider;
        try {
            provider = providerRepo.findByIdProvider(idProvider);
        } catch (Exception e) {
            provider = null;
        }
        return provider;
    }

    @Override
    public Provider findByNameProvider(String provider) {
        Provider prov;
        try {
            prov = (Provider) providerRepo.findByNameProvider(provider).get(0);
        } catch (Exception e) {
            prov = null;
        }
        return prov;
    }

    @Override
    public void updateDataProvider(String idProvider, Provider provider) {
        synchronized (this) {
            providerRepo.updateDataProvider(idProvider , provider);
        }
    }

    @Override
    public void deleteByIdProvider(String idProvider) {
        synchronized (this) {
            providerRepo.deleteByIdProvider(idProvider);
        }
    }

    @Override
    public int countingProviderRows() {
        int count = providerRepo.countingProviderRows();
        return count;
    }

    @Override
    public boolean isProviderExist(Provider provider) {
        return providerRepo.findByNameProvider(provider.getProvider()).size() != 0;
    }
}
