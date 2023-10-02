package ua.place.bet.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
}
