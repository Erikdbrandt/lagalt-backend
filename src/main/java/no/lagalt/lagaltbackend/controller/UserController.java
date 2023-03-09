package no.lagalt.lagaltbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "GET ALL USERS")
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<AppUserDto> getAllUser() {
        Collection<AppUser> userList = userService.findAll();
        return userList.stream()
                .map(userMapper::toAppUserDto).collect(Collectors.toList());
    }

    @Operation(summary = "GET SINGLE USER")
    @GetMapping("/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public AppUserDto findById(@PathVariable("userId") int userId) {
        return userMapper.toAppUserDto(userService.findById(userId));
    }

    @Operation(summary = "CREATE USER")
    @GetMapping("/create")
    @ResponseStatus(value = HttpStatus.OK)
    public AppUserDto create(@RequestBody AppUserDto userDto) {
        AppUser appUser = userMapper.toAppUser(userDto);
        return userMapper.toAppUserDto(userService.create(appUser));
    }

    @Operation(summary = "UPDATE SINGLE USER")
    @GetMapping("/update/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public AppUserDto updateById(@RequestBody AppUserDto appUserDto,@PathVariable("userId") int userId) {
        AppUser appUser = userMapper.toAppUser(appUserDto);
        return userMapper.toAppUserDto(userService.update(userId, appUser));
    }


    @Operation(summary = "DELETE USER BY ID")
    @GetMapping("/delete/{userId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteById(@PathVariable("userId") int userId) {
        userService.deleteById(userId);
        return "USER_DELETED";
    }
}
