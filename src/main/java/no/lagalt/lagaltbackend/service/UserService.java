package no.lagalt.lagaltbackend.service;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;

public interface UserService extends CrudService<AppUser,Integer>{
    AppUser findByEmail(String email);
}
