package nl.maxvandewiel.service.userregistration.restfull.controller;

import nl.maxvandewiel.service.userregistration.repository.RegisteredUser;
import nl.maxvandewiel.service.userregistration.repository.UserRepository;
import nl.maxvandewiel.service.userregistration.repository.exceptions.RegisterUserException;
import nl.maxvandewiel.service.userregistration.repository.exceptions.UserAllreadyExistsException;
import nl.maxvandewiel.service.userregistration.repository.exceptions.UserNotFoundException;
import nl.maxvandewiel.service.userregistration.restfull.model.RegistrationRequest;
import nl.maxvandewiel.service.userregistration.restfull.model.RegistrationResponse;
import nl.maxvandewiel.service.userregistration.toa.RegisteredUserToa;
import nl.maxvandewiel.service.userregistration.toa.RegistrationResponseToa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by max on 6/4/16.
 */
@RestController
public class UserRegistrationController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserRegistrationController.class);

    @Autowired
    private RegisteredUserToa registeredUserToa;

    @Autowired
    private RegistrationResponseToa registrationResponseToa;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public @ResponseBody
    RegistrationResponse register(@Validated @RequestBody RegistrationRequest request) throws RegisterUserException, UserAllreadyExistsException  {
        LOGGER.trace("Handling user registration request");
        RegistrationResponse response = registrationResponseToa.assemble(request);
        RegisteredUser user = registeredUserToa.assemble(request);
        userRepository.save(user);
        return response;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public @ResponseBody
    List<RegisteredUser> getAllUsers() throws RegisterUserException  {
        LOGGER.trace("Delivering all users on path /users");
        return userRepository.findAll();
    }

    @RequestMapping(path = "/users/{username}", method = RequestMethod.GET)
    public @ResponseBody
    RegisteredUser getUserByUsername(@PathVariable String username) throws UserNotFoundException {
        LOGGER.trace(String.format("Delivering user: %s", username));
        return userRepository.findByUsername(username);
    }
}