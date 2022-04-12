package no.fintlabs.samtykke.personopplysning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonopplysningController {

    // option-enter
    private PersonopplysningService personopplysningService;

    public PersonopplysningController(PersonopplysningService personopplysningService) {
        this.personopplysningService = personopplysningService;
    }

    @GetMapping ("/personopplysning")
    public List<PersonopplysningModel> getPersonopplysningResource () {
        return personopplysningService.getPersonopplysningResources();
    }

}
