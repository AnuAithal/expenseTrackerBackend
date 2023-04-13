package com.example.expenseTracker.expenseTracker.filter;

import java.io.IOException;

import com.example.expenseTracker.expenseTracker.utils.CurrentUser;
import com.google.api.Http;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.expenseTracker.expenseTracker.model.User;
import com.example.expenseTracker.expenseTracker.services.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

	private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        HttpMethod method = HttpMethod.valueOf(request.getMethod());*/
        if (request.getHeader(HttpHeaders.AUTHORIZATION) == null) {
            filterChain.doFilter(request, response);
        } else {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            HttpMethod method = HttpMethod.valueOf(request.getMethod());
            String requestURI = request.getRequestURI();

            if (method.equals(HttpMethod.OPTIONS)) {
                filterChain.doFilter(request, response);
            } else if (isPublicApi(requestURI, method)) {//we need to check public api or not
                filterChain.doFilter(request, response);
            } else {
                if (StringUtils.isNotBlank(token) && (token.startsWith("Bearer "))) {
                    String actualToken = token.split(" ")[1].trim();
                    if (StringUtils.isNotBlank(actualToken)) {
                        try {
                            FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(actualToken);
                            String userUid = firebaseToken.getUid();
                            User user = userService.getByFirebaseId(userUid);
                            CurrentUser.set(user);
                            filterChain.doFilter(request, response);
                        } catch (FirebaseAuthException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                else{
                    logger.error("No token sent or blank token sent");
                    throw  new RuntimeException("No token sent or blank token sent");
                }
            }
        }
    }

    private boolean isPublicApi(String requestUri, HttpMethod httpMethod){
        if(requestUri.equals("/users/login") && httpMethod.equals(HttpMethod.POST)){
            return true;
        }
        return false;
    }
	}

