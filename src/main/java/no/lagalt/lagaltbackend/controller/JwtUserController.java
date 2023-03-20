package no.lagalt.lagaltbackend.controller;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.pojo.dto.AppUserDto;
import no.lagalt.lagaltbackend.pojo.dto.AppUserMapper;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class JwtUserController {

    private final UserService userService;
    private final AppUserMapper userMapper;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public AppUserDto getCurrentTokenUser() {
        AppUser currentTokenUse = userService.getCurrentTokenUser();
        return userMapper.toAppUserDto(currentTokenUse);
    }
}
