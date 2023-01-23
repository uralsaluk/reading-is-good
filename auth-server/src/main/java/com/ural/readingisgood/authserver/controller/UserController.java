package com.ural.readingisgood.authserver.controller;


import com.ural.readingisgood.authserver.controller.model.AuthResponse;
import com.ural.readingisgood.authserver.controller.model.RegistrationRequest;
import com.ural.readingisgood.authserver.controller.model.RegistrationResponse;
import com.ural.readingisgood.authserver.controller.model.TokenResponse;
import com.ural.readingisgood.authserver.core.exception.BusinessException;
import com.ural.readingisgood.authserver.core.exception.ErrorCode;
import com.ural.readingisgood.authserver.entities.Role;
import com.ural.readingisgood.authserver.entities.UserEntity;
import com.ural.readingisgood.authserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user")
public class UserController {


    private UserRepository userRepository;


    private PasswordEncoder passwordEncoder;

    private RestTemplate restTemplate;

    @Autowired
    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.restTemplate = restTemplate;
    }

    @PostMapping("api/v0/auth/register")
    public ResponseEntity<RegistrationResponse> register(@RequestBody RegistrationRequest request) {

        Set<Role> roles = Stream.of(Role.builder().id(1L).name("ROLE_CUSTOMER").build()).collect(Collectors.toSet());

        UserEntity userEntity = new UserEntity(request.getFirstname(), request.getLastname()
                , request.getEmail(), passwordEncoder.encode(request.getPassword()), roles);

        Optional<UserEntity> byEmail = userRepository.findByEmail(request.getEmail());

        if(byEmail.isPresent()){
            throw new BusinessException(ErrorCode.MAIL_ALREADY_EXIST);

        }

        userRepository.save(userEntity);

        return new ResponseEntity<>(new RegistrationResponse("User Created"), HttpStatus.CREATED);
    }

    @PostMapping("api/v0/auth/registerAdmin")
    public ResponseEntity<RegistrationResponse> registerAdmin(@RequestBody RegistrationRequest request) {

        Set<Role> roles = Stream.of(Role.builder().id(1L).name("ROLE_ADMIN").build()).collect(Collectors.toSet());

        UserEntity userEntity = new UserEntity(request.getFirstname(), request.getLastname()
                , request.getEmail(), passwordEncoder.encode(request.getPassword()), roles);

        Optional<UserEntity> byEmail = userRepository.findByEmail(request.getEmail());

        if(byEmail.isPresent()){
            throw new BusinessException(ErrorCode.MAIL_ALREADY_EXIST);

        }

        userRepository.save(userEntity);

        return new ResponseEntity<>(new RegistrationResponse("User Created"), HttpStatus.CREATED);
    }


    @GetMapping("api/v0/auth/test")
    public ResponseEntity<AuthResponse> test(@RequestParam String code) {



        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("client_id", "orderserviceapp");
        map.add("client_secret", "9999");
        map.add("grant_type", "authorization_code");
        map.add("redirect_uri", "http://127.0.0.1:8080/user/api/v0/auth/test");
        map.add("code", code);



        HttpEntity<MultiValueMap<String,String >> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<TokenResponse> tokenResponseResponseEntity = restTemplate
                .postForEntity("http://localhost:8080/oauth2/token", entity, TokenResponse.class);


        return new ResponseEntity<>(new AuthResponse(code,tokenResponseResponseEntity.getBody()), HttpStatus.CREATED);

    }


}
