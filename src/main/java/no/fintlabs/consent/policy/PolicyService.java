package no.fintlabs.consent.policy;

import lombok.extern.slf4j.Slf4j;
import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fint.model.resource.personvern.samtykke.BehandlingResources;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.FintCache;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.consent.LoadDataFile;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PolicyService {
    private final FintCache<BehandlingResource> fintCache;

    private final boolean useMockData = true;
    private final ObjectMapper objectMapper;

    public PolicyService(CacheManager cacheManager, ObjectMapper objectMapper) {
        this.fintCache = cacheManager.create(PackingTypes.DEFLATE);
        this.objectMapper = objectMapper;

        if (useMockData) addMockData();
    }

    public List<PolicyModel> getPolicyResources() {
        return map(fintCache.stream().toList());
    }

    private List<PolicyModel> map(List<BehandlingResource> toList) {
        return toList
                .stream()
                .map(PolicyMapper::mapToModel).collect(Collectors.toList());
    }

    private void addMockData() {
        try {
            InputStream mock = LoadDataFile.readResource("policy");


            BehandlingResources behandlingResources = objectMapper.readValue(mock, BehandlingResources.class);
            log.info("******* Total policy items found: " + behandlingResources.getTotalItems());

            for (int i=0; i<behandlingResources.getTotalItems(); i++)
            {
                fintCache.put(behandlingResources.getContent().get(i).getSystemId().getIdentifikatorverdi(), behandlingResources.getContent().get(i), new int[]{});
            }
        } catch (IOException e){
            log.error(e.getMessage());
        }
    }
}
