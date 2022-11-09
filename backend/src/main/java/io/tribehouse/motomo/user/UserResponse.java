package io.tribehouse.motomo.user;

import lombok.Data;

@Data
public class UserResponse {
    private String email;
    private String password;
    private Boolean enabled;
}
