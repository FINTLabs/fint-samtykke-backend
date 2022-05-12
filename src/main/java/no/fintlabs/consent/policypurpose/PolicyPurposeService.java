package no.fintlabs.consent.policypurpose;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResource;
import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResources;
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
public class PolicyPurposeService {
    private final FintCache<BehandlingsgrunnlagResource> fintCache;

    private final ObjectMapper objectMapper;

    public PolicyPurposeService(CacheManager cacheManager, ObjectMapper objectMapper) {
        this.fintCache = cacheManager.create(PackingTypes.DEFLATE);
        this.objectMapper = objectMapper;
        boolean useMockData = true;
        if (useMockData) addMockData();
    }

    public List<PolicyPurposeModel> getPolicyPurposeResources() {
        return map(fintCache.stream().toList());
    }

    private List<PolicyPurposeModel> map(List<BehandlingsgrunnlagResource> toList) {
        return toList
                .stream()
                .map(PolicyPurposeMapper::mapToModel)
                .collect(Collectors.toList());
    }

    public PolicyPurposeModel create(PolicyPurposeModel policyPurposeModel) {

       BehandlingsgrunnlagResource behandlingsgrunnlagResource = PolicyPurposeMapper.mapToResource(policyPurposeModel);

       // todo: REMOVE THIS -- ONLY FOR MOCK TESTING
        Identifikator identifikator = new Identifikator();
        identifikator.setIdentifikatorverdi("1005");
        behandlingsgrunnlagResource.setSystemId(identifikator);

        fintCache.put(behandlingsgrunnlagResource.getSystemId().getIdentifikatorverdi(), behandlingsgrunnlagResource, new int[]{});

        return policyPurposeModel;

    }

    private void addMockData() {
        try {
            InputStream mock = LoadDataFile.readResource("policyPurpose");


            BehandlingsgrunnlagResources behandlingsgrunnlagResources = objectMapper.readValue(mock, BehandlingsgrunnlagResources.class);
            log.info("******* Total policy purpose items found: " + behandlingsgrunnlagResources.getTotalItems());

            for (int i=0; i<behandlingsgrunnlagResources.getTotalItems(); i++)
            {
                fintCache.put(behandlingsgrunnlagResources.getContent().get(i).getSystemId().getIdentifikatorverdi(), behandlingsgrunnlagResources.getContent().get(i), new int[]{});
            }
        } catch (IOException e){
            log.error(e.getMessage());
        }
    }

}
