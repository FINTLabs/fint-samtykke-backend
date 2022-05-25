package no.fintlabs.consent.personaldata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonalDataController {

    // test a thing
    // option-enter
    private PersonalDataService personalDataService;

    public PersonalDataController(PersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

    @GetMapping ("/personaldata")
    public List<PersonalDataModel> getPersonalDataResource () {
        return personalDataService.getPersonalDataResource();
    }

}
