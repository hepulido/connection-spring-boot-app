package com.hectorPulido.connectionApp;
import com.hectorPulido.connectionApp.employee.EmployeeRepository;
import com.hectorPulido.connectionApp.employer.EmployerRepository;
import com.hectorPulido.connectionApp.user.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployerRepository employerRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testSignUp_Success() {
        UserSignUpDTO signUpDTO = new UserSignUpDTO();
        User user = new User();
        when(userRepository.save(any(User.class))).thenReturn(user);
        User savedUser = userService.signUp(signUpDTO);

        assertNotNull(savedUser);
    }

    @Test
    public void testSignIn_Success() {
        UserSignInDTO signInDTO = new UserSignInDTO();
        User user = new User();
        when(userRepository.findByEmailAndPassword(any(), any())).thenReturn(Optional.of(user));
        User signedInUser = userService.signIn(signInDTO);

        assertNotNull(signedInUser);

    }
    @Test
    public void testSignUp_InvalidUserData() {
        UserSignUpDTO signUpDTO = new UserSignUpDTO();
        when(userService.signUp(any(UserSignUpDTO.class))).thenReturn(null);
        User signedUpUser = userService.signUp(signUpDTO);

        assertNull(signedUpUser);
    }
    @Test
    public void testSignIn_InvalidCredentials() {
        UserSignInDTO signInDTO = new UserSignInDTO();
        when(userRepository.findByEmailAndPassword(any(), any())).thenReturn(Optional.empty());
        User signedInUser = userService.signIn(signInDTO);

        assertNull(signedInUser);
    }

}