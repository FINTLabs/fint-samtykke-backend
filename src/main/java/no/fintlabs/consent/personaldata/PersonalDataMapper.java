package no.fintlabs.consent.personaldata;

import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;

public class PersonalDataMapper {

    static PersonalDataModel mapToModel(PersonopplysningResource personopplysningResource) {
        PersonalDataModel personalDataModel = new PersonalDataModel();
        personalDataModel.setName(personopplysningResource.getNavn());
        personalDataModel.setCode(personopplysningResource.getKode());
        personalDataModel.setSystemId(personopplysningResource.getSystemId().getIdentifikatorverdi());
        return personalDataModel;
    }
}
