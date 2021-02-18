package com.backendapi.project.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Provider {
    private String idProvider;

    @NotNull
    @Size(max = 2)
    private String provider;

    public Provider(String idProvider, String provider) {
        this.idProvider = idProvider;
        this.provider = provider;
    }

    public String getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(String idProvider) {
        this.idProvider = idProvider;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "idProvider='" + idProvider + '\'' +
                ", provider='" + provider + '\'' +
                '}';
    }
}
