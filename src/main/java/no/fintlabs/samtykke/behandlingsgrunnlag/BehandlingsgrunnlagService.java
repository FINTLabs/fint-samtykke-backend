package no.fintlabs.samtykke.behandlingsgrunnlag;

import no.fint.model.resource.personvern.kodeverk.BehandlingsgrunnlagResource;
import no.fintlabs.cache.FintCache;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BehandlingsgrunnlagService {
    private final FintCache<BehandlingsgrunnlagResource> fintCache;

    private final boolean useMockData = true;

    public BehandlingsgrunnlagService(FintCache<BehandlingsgrunnlagResource> fintCache) {
        this.fintCache = fintCache;

        if (useMockData) addMockData();
    }

    public List<BehandlingsgrunnlagModel> getBehandlingsgrunnlagResources() {
        return map(fintCache.stream().toList());
    }

    private List<BehandlingsgrunnlagModel> map(List<BehandlingsgrunnlagResource> toList) {
        return toList
                .stream()
                .map(behandlingsgrunnlagResource -> {
                    BehandlingsgrunnlagModel behandlingsgrunnlagModel = new BehandlingsgrunnlagModel();
                    behandlingsgrunnlagModel.setCode(behandlingsgrunnlagResource.getKode());
                    behandlingsgrunnlagModel.setName(behandlingsgrunnlagResource.getKode());
                    behandlingsgrunnlagModel.setSystemId(behandlingsgrunnlagResource.getSystemId().getIdentifikatorverdi());
                    return behandlingsgrunnlagModel;
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
