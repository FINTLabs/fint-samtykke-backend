package no.fintlabs.samtykke.behandling;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class BehandlingController {

        // option-enter
        private BehandlingService behandlingService;

        public BehandlingController(BehandlingService behandlinService) {
            this.behandlingService = behandlinService;
        }

        @GetMapping("/behandling")
        public List<BehandlingModel> getBehandlinResource () {
            return behandlingService.getBehandlingrResources();
        }


}
