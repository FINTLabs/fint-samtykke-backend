package no.fintlabs.consent.policy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class PolicyController {

        private PolicyService policyService;

        public PolicyController(PolicyService policyService) {
            this.policyService = policyService;
        }

        @GetMapping("/policies")
        public List<PolicyModel> getPolicyResource () {
            return policyService.getPolicyResources();
        }

        @PutMapping("/policy/{systemId}/{active}")
        public ResponseEntity<Void> updateActive(@PathVariable String systemId, @PathVariable String active) {
            log.info("******* update policy active:" + systemId + " active status: " + active);


            // return ResponseEntity.noContent().cacheControl(CacheControl.noStore()).build();
            // return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body();
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        @PutMapping("/policy/add")
        public ResponseEntity<PolicyModel> addPolicy(@RequestBody PolicyModel policyModel) {
            log.info("******* ADD NEW policy description:" + policyModel.getDescription());
            log.info("******* ADD NEW policy data type:" + policyModel.getPersonalDataSystemId());

            // just to test some stuff
            policyModel.setSystemId("5999");

            // always true??
            policyModel.setActive(true);

            // todo: where does the list of ids change to one id or the other way?
            policyModel.getPolicyPurposeSystemIds().add(policyModel.getPolicyPurposeSystemId());
            policyModel.getPersonalDataSystemIds().add(policyModel.getPersonalDataSystemId());

            return ResponseEntity.status(HttpStatus.OK).body(policyModel);

        }

}
