package no.lagalt.lagaltbackend.service.services;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.service.services.CrudService;

public interface UserService extends CrudService<AppUser,Integer> {
    AppUser getCurrentTokenUser();

}
