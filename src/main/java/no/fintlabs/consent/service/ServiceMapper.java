package no.fintlabs.consent.service;

import no.fint.model.resource.personvern.samtykke.TjenesteResource;
import no.fintlabs.consent.LoadDataFile;

public class ServiceMapper {

    static ServiceModel mapToModel(TjenesteResource tjenesteResource) {
        ServiceModel serviceModel = new ServiceModel();
        serviceModel.setName(tjenesteResource.getNavn());
        serviceModel.setSystemId(tjenesteResource.getSystemId().getIdentifikatorverdi());

        tjenesteResource
                .getBehandling()
                .stream()
                .forEach(link -> serviceModel.getPolicySystemIds().add(LoadDataFile.extractSystemId(link.getHref())));

        return serviceModel;
    }
}
