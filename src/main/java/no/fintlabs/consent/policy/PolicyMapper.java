package no.fintlabs.consent.policy;

import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fintlabs.consent.LoadDataFile;

public class PolicyMapper {

    static PolicyModel mapToModel (BehandlingResource behandlingResource) {
        PolicyModel policyModel = new PolicyModel();
        policyModel.setActive(behandlingResource.getAktiv());
        policyModel.setDescription(behandlingResource.getFormal());
        policyModel.setSystemId(behandlingResource.getSystemId().getIdentifikatorverdi());

        behandlingResource
                .getBehandlingsgrunnlag()
                .stream()
                .forEach(link -> policyModel.getPolicyPurposeSystemIds().add(LoadDataFile.extractSystemId(link.getHref())));

        behandlingResource
                .getPersonopplysning()
                .stream()
                .forEach(link -> policyModel.getPersonalDataSystemIds().add(LoadDataFile.extractSystemId(link.getHref())));

        return policyModel;
    }


}
