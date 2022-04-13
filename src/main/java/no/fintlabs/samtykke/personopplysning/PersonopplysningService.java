package no.fintlabs.samtykke.personopplysning;

// this will listen to kafka and get out the messages that pertain to samtykke

import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.FintCache;
import no.fintlabs.cache.packing.PackingTypes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonopplysningService {

    private final FintCache<PersonopplysningResource> fintCache;

    private final boolean useMockData = true;

    public PersonopplysningService(CacheManager cacheManager) {
         this.fintCache = cacheManager.<PersonopplysningResource>create(PackingTypes.DEFLATE);

        if (useMockData) addMockData();
    }


    public List<PersonopplysningModel> getPersonopplysningResources() {
        return map(fintCache.stream().toList());
    }

    private List<PersonopplysningModel> map(List<PersonopplysningResource> toList) {
        return toList
                .stream()
                .map(personopplysningResource -> {
                    PersonopplysningModel personopplysningModel = new PersonopplysningModel();
                    personopplysningModel.setName(personopplysningResource.getNavn());
                    personopplysningModel.setCode(personopplysningResource.getKode());
                    personopplysningModel.setSystemId(personopplysningResource.getSystemId().getIdentifikatorverdi());
                    return personopplysningModel;
                }).toList();
    }

    private void addMockData() {
        /*TjenesteResource tjenesteResource = new TjenesteResource();
        tjenesteResource.setNavn("Test resource name");

        Identifikator identifikator = new Identifikator();
        identifikator.setIdentifikatorverdi("1001");
        tjenesteResource.setSystemId(identifikator);

        fintCache.put(tjenesteResource.getSystemId().getIdentifikatorverdi(), tjenesteResource, new int[]{});*/
    }

}
