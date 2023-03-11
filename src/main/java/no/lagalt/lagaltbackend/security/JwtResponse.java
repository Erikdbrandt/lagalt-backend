package no.lagalt.lagaltbackend.security;

import lombok.Data;
import no.lagalt.lagaltbackend.pojo.dto.AppUserDto;

import java.io.Serializable;

@Data
public class JwtResponse implements Serializable {


    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private AppUserDto user;

    public JwtResponse(String jwttoken, AppUserDto user) {
        this.jwttoken = jwttoken;
        this.user = user;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
