package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.security.JwtUtil;
import com.pragma.powerup.infrastructure.security.dto.AuthLoginRequest;
import com.pragma.powerup.infrastructure.security.dto.AuthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private JwtUtil jwtUtils;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLoadUserByUsername_UserExists() {
        UserModel userModel = new UserModel();
        userModel.setCorreo("test@example.com");
        userModel.setClave("encryptedPassword");
        RoleModel role = new RoleModel();
        role.setNombre("USER");
        userModel.setRole(role);

        lenient().when(userPersistencePort.getUserByEmail("test@example.com")).thenReturn(userModel);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test@example.com");

        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("encryptedPassword", userDetails.getPassword());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }

    @Test
    void testAuthenticate_Successful() {
        UserModel userModel = new UserModel();
        userModel.setCorreo("test@example.com");
        userModel.setClave("encryptedPassword");
        RoleModel role = new RoleModel();
        role.setNombre("USER");
        userModel.setRole(role);

        lenient().when(userPersistencePort.getUserByEmail("test@example.com")).thenReturn(userModel);
        lenient().when(passwordEncoder.matches("password", "encryptedPassword")).thenReturn(true);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test@example.com");
        assertNotNull(userDetails);

        assertDoesNotThrow(() -> {
            customUserDetailsService.authenticate("test@example.com", "password");
        });
    }

    @Test
    void testAuthenticate_IncorrectPassword() {
        UserModel userModel = new UserModel();
        userModel.setCorreo("test@example.com");
        userModel.setClave("encryptedPassword");
        RoleModel role = new RoleModel();
        role.setNombre("USER");
        userModel.setRole(role);

        lenient().when(userPersistencePort.getUserByEmail("test@example.com")).thenReturn(userModel);
        lenient().when(passwordEncoder.matches("wrongPassword", "encryptedPassword")).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> {
            customUserDetailsService.authenticate("test@example.com", "wrongPassword");
        });
    }

    @Test
    void testLoginUser_Successful() {
        UserModel userModel = new UserModel();
        userModel.setCorreo("test@example.com");
        userModel.setClave("encryptedPassword");
        RoleModel role = new RoleModel();
        role.setNombre("USER");
        userModel.setRole(role);

        AuthLoginRequest authLoginRequest = new AuthLoginRequest("test@example.com", "password");

        lenient().when(userPersistencePort.getUserByEmail("test@example.com")).thenReturn(userModel);
        lenient().when(passwordEncoder.matches("password", "encryptedPassword")).thenReturn(true);
        lenient().when(jwtUtils.createToken(any())).thenReturn("jwtToken");

        AuthResponse authResponse = customUserDetailsService.loginUser(authLoginRequest);

        assertNotNull(authResponse);
        assertEquals("test@example.com", authResponse.getUsername());
        assertEquals("User loged succesfully", authResponse.getMessage());
        assertEquals("jwtToken", authResponse.getJwt());
        assertTrue(authResponse.getStatus());
    }

    @Test
    void testLoginUser_FailedDueToInvalidCredentials() {
        AuthLoginRequest authLoginRequest = new AuthLoginRequest("test@example.com", "wrongPassword");

        UserModel userModel = new UserModel();
        userModel.setCorreo("test@example.com");
        userModel.setClave("encryptedPassword");
        RoleModel role = new RoleModel();
        role.setNombre("USER");
        userModel.setRole(role);

        lenient().when(userPersistencePort.getUserByEmail("test@example.com")).thenReturn(userModel);
        lenient().when(passwordEncoder.matches("wrongPassword", "encryptedPassword")).thenReturn(false);

        assertThrows(BadCredentialsException.class, () -> {
            customUserDetailsService.loginUser(authLoginRequest);
        });
    }
}