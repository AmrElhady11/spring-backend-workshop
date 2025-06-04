package com.security.auth;

import com.security.entity.Role;
import com.security.entity.Token;
import com.security.entity.TokenType;
import com.security.entity.User;
import com.security.repository.TokenRepository;
import com.security.repository.UserRepository;
import com.security.service.JwtService;
import lombok.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthinticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService ;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthinticationResponse register(RigsterRequest authRequest) {
        var user = User.builder()
                .email(authRequest.getEmail())
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .name(authRequest.getName())
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);

        return AuthinticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    private void revokeToken(User user) {
        var validToken = tokenRepository.findAllValidTokensByUserId(user.getId());
        if (validToken.isEmpty()) {
            return;
        }
        validToken.forEach(t->{ t.setExpired(true);
                                t.setRevoked(true);
        });
        tokenRepository.saveAll(validToken);
    }
    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthinticationResponse authinticate(AuthinticationRequest authRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
        var user = userRepository.findByEmail(authRequest.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
            revokeToken(user);
            saveUserToken(user, jwtToken);
        return AuthinticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
