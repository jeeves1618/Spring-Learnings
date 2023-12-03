package net.myphenotype.Spring.Security.controller;

import net.myphenotype.Spring.Security.entity.Customer;
import net.myphenotype.Spring.Security.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /*
    ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result, we can use it to fully configure the HTTP response.
    If we want to use it, we have to return it from the endpoint; Spring takes care of the rest.
    ResponseEntity is a generic type. Consequently, we can use any type as the response body.

    Source: https://www.baeldung.com/spring-response-entity
     */
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody Customer customer){

        Customer customerSaved = null;
        ResponseEntity responseEntity = null;

        try {
            String hashPassword = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPassword);
            customerSaved = customerRepository.save(customer);
            if (customerSaved.getId() > 0 ){
                responseEntity = ResponseEntity.status(HttpStatus.CREATED).body("User successfully signed up");

            }
        } catch (Exception e){
            responseEntity = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception due to " + e.getMessage());
        }
        return responseEntity;
    }
}
