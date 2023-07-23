package com.quizmaker.services.user;

import com.quizmaker.models.User;
import com.quizmaker.models.dtos.AuthenticationResponse;
import com.quizmaker.models.dtos.IsUserLoggedDTO;
import com.quizmaker.models.dtos.RegisterAuthenticateDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    Boolean isUserLogged(IsUserLoggedDTO token);

    AuthenticationResponse register(RegisterAuthenticateDTO request);
    AuthenticationResponse login(RegisterAuthenticateDTO request);

}
