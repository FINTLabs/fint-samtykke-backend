package no.fintlabs.consent.policy;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PolicyModel {
    private String description;
    private String systemId;
    private boolean active;
    private List<String> policyPurposeSystemIds;
    private List<String> personalDataSystemIds;

    public PolicyModel() {
        policyPurposeSystemIds = new ArrayList<>();
        personalDataSystemIds = new ArrayList<>();
    }
}
