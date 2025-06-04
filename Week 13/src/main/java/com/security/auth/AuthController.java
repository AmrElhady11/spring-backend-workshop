package com.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthinticationService authinticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthinticationResponse> register(
            @RequestBody RigsterRequest authRequest){
        return ResponseEntity.ok(authinticationService.register(authRequest));

    }
    @PostMapping("/authinticate")
    public ResponseEntity<AuthinticationResponse> authinticate(
            @RequestBody AuthinticationRequest authRequest){
        return ResponseEntity.ok(authinticationService.authinticate(authRequest));


    }

}
