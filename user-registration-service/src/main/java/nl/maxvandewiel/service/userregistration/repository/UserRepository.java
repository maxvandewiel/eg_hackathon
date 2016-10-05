package nl.maxvandewiel.service.userregistration.repository;

import nl.maxvandewiel.service.userregistration.repository.exceptions.UserNotFoundException;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.math.BigInteger;

/**
 * Created by max on 6/4/16.
 */
public interface UserRepository extends MongoRepository<RegisteredUser, BigInteger> {

    @Query("{'username' : ?0}")
    RegisteredUser findByUsername(String username) throws UserNotFoundException;

    @Query("{'emailAddress' : ?0}")
    RegisteredUser findByEmailAddress(String emailAddress) throws UserNotFoundException;
}
