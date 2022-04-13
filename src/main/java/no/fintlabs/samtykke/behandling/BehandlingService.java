package no.fintlabs.samtykke.behandling;

import no.fint.model.resource.personvern.samtykke.BehandlingResource;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.FintCache;
import no.fintlabs.cache.packing.PackingTypes;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BehandlingService {
    private final FintCache<BehandlingResource> fintCache;

    private final boolean useMockData = true;

    public BehandlingService(CacheManager cacheManager) {
        this.fintCache = cacheManager.<BehandlingResource>create(PackingTypes.DEFLATE);

        if (useMockData) addMockData();
    }

    public List<BehandlingModel> getBehandlingrResources() {
        return map(fintCache.stream().toList());
    }

    private List<BehandlingModel> map(List<BehandlingResource> toList) {
        return toList
                .stream()
                .map(BehandlingResource -> {
                    BehandlingModel behandlingModel = new BehandlingModel();

                    return behandlingModel;
                }).toList();
    }

    private void addMockData() {
       /* TjenesteResource tjenesteResource = new TjenesteResource();
        tjenesteResource.setNavn("Test resource name");

        Identifikator identifikator = new Identifikator();
        identifikator.setIdentifikatorverdi("1001");
        tjenesteResource.setSystemId(identifikator);

        fintCache.put(tjenesteResource.getSystemId().getIdentifikatorverdi(), tjenesteResource, new int[]{});*/
    }
}
