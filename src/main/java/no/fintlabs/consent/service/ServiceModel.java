package no.fintlabs.consent.service;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ServiceModel {
    private String name;
    private String systemId;
    private List<String> policySystemIds;

    public ServiceModel() {
        policySystemIds = new ArrayList<>();
    }
}
