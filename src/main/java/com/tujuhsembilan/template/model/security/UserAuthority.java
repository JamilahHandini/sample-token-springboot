package com.tujuhsembilan.template.model.security;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * NOTE
 * 
 * This class shows custom class of user authority in user authority details
 * feel free to change the logic of the authority gathering process.
 * 
 * This one will just be a wrapper to a string. You could add validation,
 * translation from one service authority to another, etc.
 */

@Data
@AllArgsConstructor
public class UserAuthority implements GrantedAuthority {

    public static final long serialVersionUID = 1L;

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

}
