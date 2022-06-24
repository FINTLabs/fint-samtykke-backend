package no.fintlabs.consent.service;

import lombok.extern.slf4j.Slf4j;
import no.fintlabs.consent.policy.PolicyModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@Slf4j
public class ServiceController {

    private ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping ("/services")
    public List<ServiceModel> getTjenesteResource () {
        return serviceService.getServiceResources();
    }

    @PutMapping("/service/add")
    public ResponseEntity<ServiceModel> addService(@RequestBody ServiceModel serviceModel) {
        log.info("******* ADD NEW service:" + serviceModel.getName());
        serviceModel.setSystemId("5999");

        // this is just because I do not yet know when it should be a string or a list
        log.info("******* ADD NEW service id:" + serviceModel.getPolicySystemIds());

        return ResponseEntity.status(HttpStatus.OK).body(serviceModel);

    }

}
