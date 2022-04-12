package no.fintlabs.samtykke.behandlingsgrunnlag;

import no.fintlabs.samtykke.tjeneste.TjenesteModel;
import no.fintlabs.samtykke.tjeneste.TjenesteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BehandlingsgrunnlagController {
    private BehandlingsgrunnlagService behandlingsgrunnlagService;

    public BehandlingsgrunnlagController(BehandlingsgrunnlagService behandlingsgrunnlagService) {
        this.behandlingsgrunnlagService = behandlingsgrunnlagService;
    }

    @GetMapping("/behandlingsgrunnlag")
    public List<BehandlingsgrunnlagModel> getbehandlingsgrunnlagResource () {
        return behandlingsgrunnlagService.getBehandlingsgrunnlagResources();
    }
}
