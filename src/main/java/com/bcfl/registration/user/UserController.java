package com.bcfl.registration.user;

import com.bcfl.registration.authentication.UserExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@CrossOrigin()
@RestController
@Slf4j
public class UserController {

    @Resource
    UserRepository userRepository;

    @Resource
    UserServiceImpl userService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @GetMapping("/users/{email}")
    public User findUserByEmail(@PathVariable String email){
        return userRepository.findByUsername(email);
    }

    /**
     * Update User info (e.g. password, roles).
     * {
     *     "username" : "user@email.com",
     *     "name" : "John Doe",
     *     "password" : "mypassword"
     *     "role" : [ "USER", "ADMIN"]
     * }
     * @param email
     * @param user
     * @return
     * @throws UserExistException
     */
    @PutMapping("/users/{email}")
    public User updateRole(@PathVariable String email, @Valid @RequestBody User user) throws UserExistException {
        return userService.save(user);
    }



}
