package no.fintlabs.consent.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ServiceController {

    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping ("/services")
    public List<ServiceModel> getTjenesteResource () {
        return serviceService.getServiceResources();
    }

}
