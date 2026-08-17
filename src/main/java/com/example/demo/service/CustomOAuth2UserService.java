package com.example.demo.service;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class CustomOAuth2UserService extends DefaultOAuth2UserService {
//    @Autowired
//    private UserRepository userRepository; // Your database repository

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // 2. Extract attributes (e.g., email, name)
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");


//        // 3. Find or register the user in your local database
//        User user = userRepository.findByEmail(email)
//                .orElseGet(() -> userRepository.save(new User(email, name, "ROLE_USER")));

        // 4. Return custom user principal with internal authorities
        return new CustomUserPrincipal(oAuth2User.getAttributes());
    }
}
