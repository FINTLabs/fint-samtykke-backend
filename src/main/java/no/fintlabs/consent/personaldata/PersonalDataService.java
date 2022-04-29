package no.fintlabs.consent.personaldata;

// this will listen to kafka and get out the messages that pertain to samtykke

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.personvern.kodeverk.PersonopplysningResource;
import no.fint.model.resource.personvern.kodeverk.PersonopplysningResources;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.FintCache;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.consent.LoadDataFile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonalDataService {

    private final FintCache<PersonopplysningResource> fintCache;

    private final boolean useMockData = true;
    private final ObjectMapper objectMapper;


    public PersonalDataService(CacheManager cacheManager, ObjectMapper objectMapper) {
        this.fintCache = cacheManager.<PersonopplysningResource>create(PackingTypes.DEFLATE);
        this.objectMapper = objectMapper;

        if (useMockData) addMockData();
    }


    public List<PersonalDataModel> getPersonalDataResource() {
        List<PersonopplysningResource> list = fintCache.stream().toList();
        return map(list);
    }

    private List<PersonalDataModel> map(List<PersonopplysningResource> toList) {
        return toList
                .stream()
                .map(PersonalDataMapper::mapToModel)
                .collect(Collectors.toList());
    }

    private void addMockData() {
        try {
            InputStream mock = LoadDataFile.readResource("personalData");


            PersonopplysningResources personopplysningResources = objectMapper.readValue(mock, PersonopplysningResources.class);

            log.info("******* Total personal data items found: " + personopplysningResources.getTotalItems());

            for (int i=0; i<personopplysningResources.getTotalItems(); i++)
            {
                fintCache.put(personopplysningResources.getContent().get(i).getSystemId().getIdentifikatorverdi(), personopplysningResources.getContent().get(i), new int[]{});
            }
        } catch (IOException e){
            log.error(e.getMessage());
        }
    }

}
