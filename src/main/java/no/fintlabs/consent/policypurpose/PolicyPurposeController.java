package no.fintlabs.consent.policypurpose;

import no.vigoiks.resourceserver.security.FintJwtEndUserPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PolicyPurposeController {
    private PolicyPurposeService policyPurposeService;

    public PolicyPurposeController(PolicyPurposeService policyPurposeService) {
        this.policyPurposeService = policyPurposeService;
    }

    @GetMapping("/policypurpose")
    public List<PolicyPurposeModel> getPolicyPurposeResource(@AuthenticationPrincipal Jwt jwt) {
        FintJwtEndUserPrincipal endUserPrincipal = FintJwtEndUserPrincipal.from(jwt);
        return policyPurposeService.getPolicyPurposeResources();
    }

    @PostMapping("/policypurpose")
    public PolicyPurposeModel createPolicyPurposeResource(@RequestBody PolicyPurposeModel policyPurposeModel) {

        return policyPurposeService.create(policyPurposeModel);

    }
}
