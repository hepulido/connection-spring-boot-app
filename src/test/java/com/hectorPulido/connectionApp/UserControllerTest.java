package com.hectorPulido.connectionApp;

import com.hectorPulido.connectionApp.user.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testSignUpUser_Success() {
        UserSignUpDTO signUpDTO = new UserSignUpDTO();
        BindingResult result = new BeanPropertyBindingResult(signUpDTO,"userSignUpDTO");
        when(userService.signUp(any(UserSignUpDTO.class))).thenReturn(new User());
        ResponseEntity<String> response = userController.signUpUser(signUpDTO, result);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testSignInUser_Success() {
        UserSignInDTO signInDTO = new UserSignInDTO();
        when(userService.signIn(any(UserSignInDTO.class))).thenReturn(new User());
        ResponseEntity<String> response = userController.signInUser(signInDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void testSignUpUser_ValidationFailure() {
        UserSignUpDTO signUpDTO = new UserSignUpDTO();
        BindingResult result = new BeanPropertyBindingResult(signUpDTO,"userSignUpDTO");
        result.addError(new FieldError("userSignUpDTO", "email", "Email cannot be empty"));
        when(userService.signUp(any(UserSignUpDTO.class))).thenReturn(null);
        ResponseEntity<String> response = userController.signUpUser(signUpDTO, result );

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Validation failed: [Email cannot be empty]", response.getBody());
    }
    @Test
    public void testSignIn_InvalidCredentials() {
        UserSignInDTO signInDTO = new UserSignInDTO();
        signInDTO.setEmail("test@example.com");
        signInDTO.setPassword("invalidPassword");
        when(userService.signIn(any(UserSignInDTO.class))).thenReturn(null);
        ResponseEntity<String> response = userController.signInUser(signInDTO);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Failed to sign in user", response.getBody());
    }

}
