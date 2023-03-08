package no.lagalt.lagaltbackend.pojo.dto;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;

public interface AppUserMapper {
    AppUserDto toAppUserDto(AppUser user);
    AppUser toAppUser(AppUserDto dto);
}
