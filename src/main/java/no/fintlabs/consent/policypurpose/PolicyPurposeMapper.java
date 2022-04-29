package no.fintlabs.consent.policypurpose;

import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResource;

public class PolicyPurposeMapper {

    static PolicyPurposeModel mapToModel(BehandlingsgrunnlagResource behandlingsgrunnlagResource) {

        PolicyPurposeModel policyPurposeModel = new PolicyPurposeModel();
        policyPurposeModel.setCode(behandlingsgrunnlagResource.getKode());
        policyPurposeModel.setName(behandlingsgrunnlagResource.getNavn());
        policyPurposeModel.setSystemId(behandlingsgrunnlagResource.getSystemId().getIdentifikatorverdi());

        return policyPurposeModel;
    }

    static BehandlingsgrunnlagResource mapToResource(PolicyPurposeModel policyPurposeModel) {

        BehandlingsgrunnlagResource behandlingsgrunnlagResource = new BehandlingsgrunnlagResource();
        behandlingsgrunnlagResource.setKode(policyPurposeModel.getCode());
        behandlingsgrunnlagResource.setNavn(policyPurposeModel.getName());

        // todo: REMOVE THIS -- ONLY FOR MOCK TESTING
        Identifikator identifikator = new Identifikator();
        identifikator.setIdentifikatorverdi(policyPurposeModel.getSystemId());

        behandlingsgrunnlagResource.setSystemId(identifikator);

        return behandlingsgrunnlagResource;
    }
}
