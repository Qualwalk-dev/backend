package com.qualwalk.backend.controller;

import com.qualwalk.backend.request.*;
import com.qualwalk.backend.service.*;
import com.swantech.lang.core.domain.*;
import com.swantech.lang.core.utility.*;
import com.swantech.security.keycloak.dto.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/identity")
public class IdentityRestController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<BaseResponse<String>> createUser(
            @RequestBody UserData userDto
            ) {
        this.userService.createUser(userDto);
        return ResponseEntity.ok(ResponseUtility.createBaseResponse("SUCCESS"));
    }

    @PostMapping("/verify-user")
    public ResponseEntity<BaseResponse<String>> validateOtp(@RequestBody ValidateOtp validateOtp) {
        if(userService.validateOtp(validateOtp) ) {
            return ResponseEntity.ok(ResponseUtility.createBaseResponse("SUCCESS"));
        }else {
            return new ResponseEntity<>(
                    ResponseUtility.createUnauthorizedResponse("FAILURE"),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    @PutMapping("/update-password")
    public ResponseEntity<BaseResponse<TokenDto>> updatePassword(@RequestBody Identity passwordDto) {
        try {
            TokenDto tokenDto = userService.updatePassword(passwordDto);
            return ResponseEntity.ok(ResponseUtility.createBaseResponse(tokenDto));
        }catch (Exception e) {
            log.error("An exception occurred while updating password {} ", e);
            return new ResponseEntity(
                    ResponseUtility.createUnauthorizedResponse(null),
                    HttpStatus.UNAUTHORIZED
            );
        }
    }

    @PostMapping("/token")
    public ResponseEntity<BaseResponse<TokenDto>> getToken(
            @RequestBody Identity identityDto
    ) {
        try {
            TokenDto tokenDto = userService.getToken(identityDto);
            return ResponseEntity.ok(ResponseUtility.createBaseResponse(tokenDto));
        }catch (Exception e) {
            log.error("An exception occurred while fetching token {} ", e);
            return new ResponseEntity<>(
                    ResponseUtility.createUnauthorizedResponse(null)
                    , HttpStatus.UNAUTHORIZED
            );
        }
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<BaseResponse<TokenDto>> getRefreshToken(
            @RequestBody TokenDto tokenDto
    ) {
        try {
            TokenDto tokenResponseDto = userService.getRefreshToken(tokenDto);
            return ResponseEntity.ok(ResponseUtility.createBaseResponse(tokenResponseDto));
        }catch (Exception e) {
            log.error("An exception occurred while fetching token {} ", e);
            return new ResponseEntity<>(
                    ResponseUtility.createUnauthorizedResponse(null)
                    , HttpStatus.UNAUTHORIZED
            );
        }
    }

}
