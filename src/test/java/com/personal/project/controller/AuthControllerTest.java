package com.personal.project.controller;

import com.personal.project.dtos.LoginRequest;
import com.personal.project.dtos.RegisterRequest;
import com.personal.project.entities.User;
import com.personal.project.repository.UserRepository;
import com.personal.project.security.JwtService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthController authController;

    // --- Register ---

    @Test
    void register_success() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("newuser");
        request.setEmail("new@email.com");
        request.setPassword("password");

        when(userRepository.existsByUsername("newuser")).thenReturn(false);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        ResponseEntity<?> response = authController.register(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userRepository).save(any(User.class));
    }

    @Test
    void register_usernameAlreadyTaken() {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("existinguser");

        when(userRepository.existsByUsername("existinguser")).thenReturn(true);

        ResponseEntity<?> response = authController.register(request);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verify(userRepository, never()).save(any(User.class));
    }

    // --- Login ---

    @Test
    void login_success() {
        LoginRequest request = new LoginRequest();
        request.setUsername("testuser");
        request.setPassword("password");

        MockHttpServletResponse servletResponse = new MockHttpServletResponse();

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("encoded")
                .authorities("ROLE_USER")
                .build();

        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("accessTokenValue");
        when(jwtService.generateRefreshToken(userDetails)).thenReturn("refreshTokenValue");

        ResponseEntity<?> response = authController.login(request, servletResponse);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Check cookies were set
        Cookie accessCookie = servletResponse.getCookie("accessToken");
        Cookie refreshCookie = servletResponse.getCookie("refreshToken");
        assertNotNull(accessCookie);
        assertNotNull(refreshCookie);
        assertEquals("accessTokenValue", accessCookie.getValue());
        assertEquals("refreshTokenValue", refreshCookie.getValue());
    }

    @Test
    void login_invalidCredentials() {
        LoginRequest request = new LoginRequest();
        request.setUsername("testuser");
        request.setPassword("wrong");

        MockHttpServletResponse servletResponse = new MockHttpServletResponse();

        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        ResponseEntity<?> response = authController.login(request, servletResponse);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    // --- Refresh ---

    @Test
    void refresh_success() {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        servletRequest.setCookies(new Cookie("refreshToken", "validRefreshToken"));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("encoded")
                .authorities("ROLE_USER")
                .build();

        when(jwtService.extractUsername("validRefreshToken")).thenReturn("testuser");
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtService.isTokenValid("validRefreshToken", userDetails)).thenReturn(true);
        when(jwtService.generateToken(userDetails)).thenReturn("newAccessToken");

        ResponseEntity<?> response = authController.refreshToken(servletRequest, servletResponse);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void refresh_noCookie() {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();

        ResponseEntity<?> response = authController.refreshToken(servletRequest, servletResponse);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void refresh_wrongCookieName() {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        servletRequest.setCookies(new Cookie("otherCookie", "value"));

        ResponseEntity<?> response = authController.refreshToken(servletRequest, servletResponse);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void refresh_invalidToken() {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        servletRequest.setCookies(new Cookie("refreshToken", "invalidToken"));

        when(jwtService.extractUsername("invalidToken")).thenThrow(new RuntimeException("Invalid"));

        ResponseEntity<?> response = authController.refreshToken(servletRequest, servletResponse);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void refresh_tokenNotValid() {
        MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();
        servletRequest.setCookies(new Cookie("refreshToken", "expiredToken"));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("testuser")
                .password("encoded")
                .authorities("ROLE_USER")
                .build();

        when(jwtService.extractUsername("expiredToken")).thenReturn("testuser");
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtService.isTokenValid("expiredToken", userDetails)).thenReturn(false);

        ResponseEntity<?> response = authController.refreshToken(servletRequest, servletResponse);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    // --- Logout ---

    @Test
    void logout_success() {
        MockHttpServletResponse servletResponse = new MockHttpServletResponse();

        ResponseEntity<?> response = authController.logout(servletResponse);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Cookie accessCookie = servletResponse.getCookie("accessToken");
        Cookie refreshCookie = servletResponse.getCookie("refreshToken");
        assertNotNull(accessCookie);
        assertNotNull(refreshCookie);
        assertEquals(0, accessCookie.getMaxAge());
        assertEquals(0, refreshCookie.getMaxAge());
    }

    // --- Me ---

    @Test
    void me_authenticated() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn("testuser");

        ResponseEntity<?> response = authController.getCurrentUser(authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        assertNotNull(body);
        assertEquals("testuser", body.get("username"));
    }

    @Test
    void me_notAuthenticated_null() {
        ResponseEntity<?> response = authController.getCurrentUser(null);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    void me_notAuthenticated_false() {
        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(false);

        ResponseEntity<?> response = authController.getCurrentUser(authentication);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}

