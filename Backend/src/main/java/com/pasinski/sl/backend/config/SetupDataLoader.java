package com.pasinski.sl.backend.config;

import com.pasinski.sl.backend.mission.Mission;
import com.pasinski.sl.backend.mission.MissionRepository;
import com.pasinski.sl.backend.user.AppUser;
import com.pasinski.sl.backend.user.AppUserRepository;
import com.pasinski.sl.backend.user.accessManagment.Privilege;
import com.pasinski.sl.backend.user.accessManagment.PrivilegeRepository;
import com.pasinski.sl.backend.user.accessManagment.Role;
import com.pasinski.sl.backend.user.accessManagment.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final PasswordEncoder passwordEncoder;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup)
            return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        AppUser user = new AppUser();
        user.setName("Charlie");
        user.setPassword(passwordEncoder.encode("Password1!"));
        user.setEmail("email@email.com");
        user.setRoles(Arrays.asList(adminRole));
        appUserRepository.save(user);


        Role userRole = roleRepository.findByName("ROLE_USER");
        AppUser user2 = new AppUser();
        user2.setName("Mike");
        user2.setPassword(passwordEncoder.encode("Password1!"));
        user2.setEmail("email2@email.com");
        user2.setRoles(Arrays.asList(userRole));
        appUserRepository.save(user2);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date startNov;
        Date endNov;
        Date startDec;
        Date endDec;

        try {
            startNov = formatter.parse("2019-11-01");
            endNov = formatter.parse("2019-11-30");
            startDec = formatter.parse("2019-12-01");
            endDec = formatter.parse("2019-12-31");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Mission mission = new Mission();
        mission.setName("Mission 1");
        mission.setDescription("Description 1");
        mission.setStartDate(startNov);
        mission.setDueDate(endNov);
        missionRepository.save(mission);

        Mission mission2 = new Mission();
        mission2.setName("Mission 2");
        mission2.setDescription("Description 2");
        mission2.setStartDate(startDec);
        mission2.setDueDate(endDec);
        missionRepository.save(mission2);

        alreadySetup = true;
    }

    @Transactional
    Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role();
            role.setName(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }


}
