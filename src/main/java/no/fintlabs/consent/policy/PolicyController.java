package no.fintlabs.consent.policy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class PolicyController {

        private PolicyService policyService;

        public PolicyController(PolicyService policyService) {
            this.policyService = policyService;
        }

        @GetMapping("/policies")
        public List<PolicyModel> getPolicyResource () {
            return policyService.getPolicyResources();
        }


}
