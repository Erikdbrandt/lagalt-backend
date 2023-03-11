package no.lagalt.lagaltbackend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.lagalt.lagaltbackend.pojo.dto.AppUserDto;
import no.lagalt.lagaltbackend.pojo.dto.AppUserMapper;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.security.JwtRequest;
import no.lagalt.lagaltbackend.security.JwtResponse;
import no.lagalt.lagaltbackend.security.JwtTokenUtil;
import no.lagalt.lagaltbackend.security.JwtUserDetailsService;
import no.lagalt.lagaltbackend.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final UserService userService;
    private final AppUserMapper userMapper;



    @GetMapping
    public AppUser getCurrentTokenUser(){
        return userService.getCurrentTokenUser();
    }


    @PostMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        log.debug("In auth controller");
        log.debug("Authenticating user:" + authenticationRequest.getEmail());

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        AppUser user = userService.getByUsername(authenticationRequest.getEmail());
        AppUserDto appUserDto = userMapper.toAppUserDto(user);
        JwtResponse jwtResponse = new JwtResponse(token, appUserDto);

        return ResponseEntity.ok(jwtResponse);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
