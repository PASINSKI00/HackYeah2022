package com.pasinski.sl.backend.security;

import com.pasinski.sl.backend.user.AppUser;
import com.pasinski.sl.backend.user.AppUserRepository;
import com.pasinski.sl.backend.user.accessManagment.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class UserSecurityService {

    private AppUserRepository appUserRepository;

    public boolean isEmailTaken(String email) {
        return appUserRepository.findByEmail(email).isPresent();
    }

    public boolean isAdmin() {

        return getRolesOfLoggedUser().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));
    }

//    public boolean isUserLoggedIn() {
//        return true;
//    }

    public Long getLoggedUserId() {
        AppUser loggedUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return loggedUser.getIdUser();
    }

    private Collection<Role> getRolesOfLoggedUser() {
        return appUserRepository.findById(getLoggedUserId()).get().getRoles();
    }
}
