package no.fintlabs.consent.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.personvern.samtykke.TjenesteResource;
import no.fint.model.resource.personvern.samtykke.TjenesteResources;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.FintCache;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.consent.LoadDataFile;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Slf4j
@Service
public class ServiceService {

    private final FintCache<TjenesteResource> fintCache;

    private final boolean useMockData = true;

    private final ObjectMapper objectMapper;

    public ServiceService(CacheManager cacheManager, ObjectMapper objectMapper) {
        this.fintCache = cacheManager.create(PackingTypes.DEFLATE);
        this.objectMapper = objectMapper;

        if (useMockData) addMockData();
    }

    public List<ServiceModel> getServiceResources() {
        return map(fintCache.stream().toList());
    }

    private List<ServiceModel> map(List<TjenesteResource> toList) {
        return toList
                .stream()
                .map(ServiceMapper::mapToModel).toList();
    }

    private void addMockData() {
        try {
            InputStream mock = LoadDataFile.readResource("service");
            TjenesteResources tjenesteResources = objectMapper.readValue(mock, TjenesteResources.class);
            log.info("******* Total service items found: " + tjenesteResources.getTotalItems());

            for (int i=0; i<tjenesteResources.getTotalItems(); i++)
            {
                fintCache.put(tjenesteResources.getContent().get(i).getSystemId().getIdentifikatorverdi(), tjenesteResources.getContent().get(i), new int[]{});
            }
        } catch (IOException e){
            log.error(e.getMessage());
        }
    }
}
