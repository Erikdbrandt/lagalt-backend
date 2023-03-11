package no.lagalt.lagaltbackend.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import no.lagalt.lagaltbackend.pojo.dto.AppUserDto;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class JwtResponse implements Serializable {


    @Serial
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private AppUserDto user;

}
