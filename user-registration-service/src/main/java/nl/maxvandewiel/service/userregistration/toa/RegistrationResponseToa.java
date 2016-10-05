package nl.maxvandewiel.service.userregistration.toa;

import nl.maxvandewiel.service.userregistration.restfull.model.RegistrationResponse;
import org.springframework.stereotype.Component;

/**
 * Created by max on 6/4/16.
 */
@Component
public class RegistrationResponseToa implements Toa<nl.maxvandewiel.service.userregistration.restfull.model.RegistrationRequest, RegistrationResponse> {
    @Override
    public RegistrationResponse assemble(nl.maxvandewiel.service.userregistration.restfull.model.RegistrationRequest request) {
        RegistrationResponse response = new nl.maxvandewiel.service.userregistration.restfull.model.RegistrationResponse();
        response.setUsername(request.getUsername());
        response.setEmailAddress(request.getEmailAddress());
        response.setPassword(request.getPassword());
        return response;
    }
}
