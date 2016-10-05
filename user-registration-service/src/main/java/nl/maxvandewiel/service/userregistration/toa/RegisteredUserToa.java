package nl.maxvandewiel.service.userregistration.toa;

import org.springframework.stereotype.Component;

import java.util.UUID;
/**
 * Created by max on 6/4/16.
 */
@Component
public class RegisteredUserToa implements Toa<nl.maxvandewiel.service.userregistration.restfull.model.RegistrationRequest, nl.maxvandewiel.service.userregistration.repository.RegisteredUser>{

    @Override
    public nl.maxvandewiel.service.userregistration.repository.RegisteredUser assemble(nl.maxvandewiel.service.userregistration.restfull.model.RegistrationRequest request) {
        return new nl.maxvandewiel.service.userregistration.repository.RegisteredUser(request.getUsername(), request.getPassword(), request.getEmailAddress());
    }

    private java.math.BigInteger generateId () {
        UUID uuid = UUID.randomUUID();
        java.nio.ByteBuffer bb = java.nio.ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return new java.math.BigInteger(bb.array());
    }
}
