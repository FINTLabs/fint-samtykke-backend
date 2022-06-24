package no.fintlabs.consent.policy;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PolicyModel {
    private String description;
    private String systemId;
    private boolean active;
    private String serviceId;
    private List<String> policyPurposeSystemIds;
    private List<String> personalDataSystemIds;

    // todo: strings or lists here?
    private String personalDataSystemId;
    private String policyPurposeSystemId;


    public PolicyModel() {
        policyPurposeSystemIds = new ArrayList<>();
        personalDataSystemIds = new ArrayList<>();
    }
}
