package com.personal.project.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtAuthenticationFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @BeforeEach
    void setUp() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        SecurityContextHolder.clearContext();
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void shouldNotFilter_returnsTrueForPlantsPath() {
        request.setServletPath("/plants");
        assertTrue(jwtAuthenticationFilter.shouldNotFilter(request));
    }

    @Test
    void shouldNotFilter_returnsTrueForPlantsSubPath() {
        request.setServletPath("/plants/1");
        assertTrue(jwtAuthenticationFilter.shouldNotFilter(request));
    }

    @Test
    void shouldNotFilter_returnsFalseForOtherPaths() {
        request.setServletPath("/api/auth/me");
        assertFalse(jwtAuthenticationFilter.shouldNotFilter(request));
    }

    @Test
    void doFilterInternal_noCookies_continuesFilterChain() throws Exception {
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilterInternal_noAccessTokenCookie_continuesWithoutAuth() throws Exception {
        request.setCookies(new Cookie("otherCookie", "value"));
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);
        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilterInternal_validToken_setsAuthentication() throws Exception {
        String token = "valid.jwt.token";
        request.setCookies(new Cookie("accessToken", token));

        UserDetails userDetails = User.withUsername("testuser")
                .password("password")
                .authorities("ROLE_USER")
                .build();

        when(jwtService.extractUsername(token)).thenReturn("testuser");
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtService.isTokenValid(token, userDetails)).thenReturn(true);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals("testuser", SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Test
    void doFilterInternal_invalidToken_continuesWithoutAuth() throws Exception {
        String token = "invalid.jwt.token";
        request.setCookies(new Cookie("accessToken", token));

        when(jwtService.extractUsername(token)).thenThrow(new RuntimeException("Invalid token"));

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilterInternal_usernameNull_continuesWithoutAuth() throws Exception {
        String token = "some.jwt.token";
        request.setCookies(new Cookie("accessToken", token));

        when(jwtService.extractUsername(token)).thenReturn(null);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    void doFilterInternal_authenticationAlreadySet_doesNotOverride() throws Exception {
        String token = "valid.jwt.token";
        request.setCookies(new Cookie("accessToken", token));

        // Pre-set authentication in SecurityContext
        UserDetails existingUser = User.withUsername("existinguser")
                .password("password")
                .authorities("ROLE_USER")
                .build();
        var existingAuth = new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                existingUser, null, existingUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(existingAuth);

        when(jwtService.extractUsername(token)).thenReturn("testuser");

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        // Should still be the existing auth, not overridden
        assertEquals("existinguser", SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Test
    void doFilterInternal_validTokenButInvalidForUser_noAuth() throws Exception {
        String token = "valid.jwt.token";
        request.setCookies(new Cookie("accessToken", token));

        UserDetails userDetails = User.withUsername("testuser")
                .password("password")
                .authorities("ROLE_USER")
                .build();

        when(jwtService.extractUsername(token)).thenReturn("testuser");
        when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        when(jwtService.isTokenValid(token, userDetails)).thenReturn(false);

        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain).doFilter(request, response);
        assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
}

