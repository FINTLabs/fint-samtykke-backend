package no.fintlabs.samtykke.tjeneste;

// this will listen to kafka and get out the messages that pertain to samtykke

import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.personvern.samtykke.TjenesteResource;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.FintCache;
import no.fintlabs.cache.packing.PackingTypes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TjenesteService {

    private final FintCache<TjenesteResource> fintCache;

    private final boolean useMockData = true;

    public TjenesteService(CacheManager cacheManager) {
        this.fintCache = cacheManager.<TjenesteResource>create(PackingTypes.DEFLATE);

        if (useMockData) addMockData();
    }

    public List<TjenesteModel> getTjenesterResources() {
        return map(fintCache.stream().toList());
    }

    private List<TjenesteModel> map(List<TjenesteResource> toList) {
        return toList
                .stream()
                .map(tjenesteResource -> {
                    TjenesteModel tjenesteModel = new TjenesteModel();
                    tjenesteModel.setName(tjenesteResource.getNavn());
                    tjenesteModel.setSystemId(tjenesteResource.getSystemId().getIdentifikatorverdi());
                    return tjenesteModel;
                }).toList();
    }

    private void addMockData() {
        TjenesteResource tjenesteResource = new TjenesteResource();
        tjenesteResource.setNavn("Test resource name");

        Identifikator identifikator = new Identifikator();
        identifikator.setIdentifikatorverdi("1001");
        tjenesteResource.setSystemId(identifikator);

        fintCache.put(tjenesteResource.getSystemId().getIdentifikatorverdi(), tjenesteResource, new int[]{});
    }
}
