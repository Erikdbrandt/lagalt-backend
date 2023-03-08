package no.lagalt.lagaltbackend.controller;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.pojo.dto.AppUserDto;
import no.lagalt.lagaltbackend.pojo.dto.AppUserMapper;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AppUserMapper userMapper;
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<AppUserDto> getAllUser() {
        Collection<AppUser> userList = userService.findAll();
        return userList.stream()
                .map(userMapper::toAppUserDto).collect(Collectors.toList());
    }
}
