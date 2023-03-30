package com.qualwalk.backend.service;

import com.qualwalk.backend.entity.*;
import com.qualwalk.backend.repository.*;
import com.qualwalk.backend.request.*;
import com.swantech.security.keycloak.dto.*;
import com.swantech.security.keycloak.mapper.*;
import com.swantech.security.keycloak.service.*;
import com.swantech.security.keycloak.util.*;
import lombok.extern.slf4j.*;
import org.keycloak.representations.*;
import org.keycloak.representations.idm.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.time.*;
import java.util.*;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserHeaderRepository userHeaderRepository;

    @Autowired
    KeycloakService keycloakService;

    @Autowired
    TokenMapper tokenMapper;

    @Transactional
    public void createUser(UserData userData) {
        UserRepresentation createdUser = keycloakService
                .createUser(this.getUserRepresentation(userData));
        try {
            keycloakService.assignRoleToUser(createdUser.getId(), "guest");
            // save to user_hd
            this.userHeaderRepository.createUser(userData);

            // save to USER_DET
            this.userHeaderRepository.addUserDetails(userData);

            // send email

        }catch (Exception e) {
            log.error("An exception occurred while creating user {}", e);
            throw e;
        }


    }


    @Transactional
    public boolean validateOtp(ValidateOtp input) {
        return this.validateIndividual(input);
    }


    public TokenDto updatePassword(Identity identityDto) {
        return this.updateIndividualPassword(identityDto);
    }

    private UserRepresentation getUserRepresentation(UserData userData) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userData.getUsername());
        userRepresentation.setEmail(userData.getEmail());
        Map<String, List<String>> attributes = new HashMap<>();
        String otp = generateOtp();
        attributes.put("OTP", Collections.singletonList(otp));
        attributes.put("OTP_EXPIRY_TIME", Collections.singletonList(LocalDateTime.now().plusMinutes(3).toString()));
        userRepresentation.setAttributes(attributes);
        return userRepresentation;
    }

    private boolean validateIndividual(ValidateOtp input) {
        try {
            UserRepresentation userRepresentation = this.keycloakService
                    .getUserByUsername(input.getUsername());
            Map<String, List<String>> attributes = userRepresentation.getAttributes();
            String otpAttribute = attributes.get(KeycloakConstants.OTP).isEmpty() ? ""
                    : attributes.get(KeycloakConstants.OTP).get(0);
            String otpExpiryTime = attributes.get(KeycloakConstants.OTP_EXPIRY_TIME).isEmpty() ?
                    "" : attributes.get(KeycloakConstants.OTP_EXPIRY_TIME).get(0);
            Object entity =
                    userHeaderRepository
                            .findUsername(input.getUsername())
                            .orElse(null);

            if( otpAttribute.isEmpty() || otpExpiryTime.isEmpty() || Objects.isNull(entity) ) {
                throw new RuntimeException("There is no otp created for user with username "+input.getUsername());
            }
            LocalDateTime otpExpiresAt = LocalDateTime.parse(otpExpiryTime);

            if( otpAttribute.equalsIgnoreCase(input.getOtp()) &&
                    otpExpiresAt.isAfter(LocalDateTime.now()) ) {
                UserDetailEntity savedEntity = (UserDetailEntity)entity;
                keycloakService.enableUser(userRepresentation.getId());
                keycloakService.assignRoleToUser(userRepresentation.getId(), "user");
                this.userHeaderRepository.verifyUser(savedEntity.getEmail());
                keycloakService.removeGuestRole(userRepresentation.getId());

            }else {
                throw new RuntimeException("OTP verification failed");
            }

            return true;
        }catch (Exception e){
            log.error("An exception occurred while validating otp {} ", e);
            return false;
        }

    }

    private String generateOtp() {
        String numbers = "1234567890";
        Random random = new Random();
        char[] otp = new char[6];

        for(int i = 0; i< 6 ; i++) {
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return new String(otp);
    }

    private TokenDto updateIndividualPassword(Identity input){
        this.keycloakService.updatePassword(input.getUsername(), input.getPassword());
        return this.getToken(input);
    }


    public TokenDto getToken(Identity input) {
        AccessTokenResponse accessTokenResponse = this.keycloakService.getAccessTokenResponse(
                input.getUsername(),
                input.getPassword()
        );
        return tokenMapper.toTokenDto(accessTokenResponse);
    }

    public TokenDto getRefreshToken(TokenDto input) {
        AccessTokenResponse accessTokenResponse = this.keycloakService.getRefreshToken(
                input.getRefreshToken()
        );
        return tokenMapper.toTokenDto(accessTokenResponse);
    }
}
