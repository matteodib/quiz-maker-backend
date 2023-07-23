package com.quizmaker.services.user;

import com.quizmaker.config.JwtService;
import com.quizmaker.models.User;
import com.quizmaker.models.dtos.AuthenticationResponse;
import com.quizmaker.models.dtos.IsUserLoggedDTO;
import com.quizmaker.models.dtos.RegisterAuthenticateDTO;
import com.quizmaker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Override
    public Boolean isUserLogged(IsUserLoggedDTO token) {
        return true;
    }

    @Override
    public AuthenticationResponse register(RegisterAuthenticateDTO request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthenticationResponse login(RegisterAuthenticateDTO request) throws UsernameNotFoundException{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        User user = userRepository.findOneByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        if(user != null) {
            String jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).build();
        }
        return null;
    }
}
