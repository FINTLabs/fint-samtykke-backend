package no.fintlabs.samtykke.tjeneste;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class TjenesteController {

    // option-enter
    private TjenesteService tjenesteService;

    public TjenesteController(TjenesteService tjenesteService) {
        this.tjenesteService = tjenesteService;
    }

    @GetMapping ("/tjeneste")
    public List<TjenesteModel> getTjenesteResource () {
        return tjenesteService.getTjenesterResources();
    }

}
