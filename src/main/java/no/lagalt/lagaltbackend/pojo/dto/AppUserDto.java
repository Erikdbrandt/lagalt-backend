package no.lagalt.lagaltbackend.pojo.dto;

import lombok.Data;
import no.lagalt.lagaltbackend.pojo.enums.AuthorityType;
import no.lagalt.lagaltbackend.pojo.enums.UserVisibility;

import java.util.List;

@Data
public class AppUserDto {
    private int user_id;
    private String full_name;
    private String email;
    private String roles;
    private UserVisibility userVisibility;
    private List<Integer> projects;
    private List<Integer> skills;


}
