package com.otziv.krya.service;

import com.otziv.krya.domain.dto.UserDetailsImpl;
import com.otziv.krya.domain.entity.ApplicationUser;
import com.otziv.krya.domain.entity.ApplicationUserRole;
import com.otziv.krya.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Optional;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Lazy;
@Service
@RequiredArgsConstructor
//@ComponentScan
public class UserService {
    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

//    private void encodePassword(ApplicationUser userEntity, ApplicationUser user){
//        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
//    }
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return UserDetailsImpl.fromApplicationUser(
//                repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found!"))
//        );
//    }
    public ApplicationUser findUserById(UUID userId) {
        Optional<ApplicationUser> userFromDb = repository.findById(userId);
        return userFromDb.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
    public boolean checkIfUserExist(String email) {
        return repository.findByEmail(email).isPresent();
    }
    public void register(ApplicationUser user) throws Exception{

        //Let's check if user already registered with us
        if(checkIfUserExist(user.getEmail())){
            throw new Exception("User already exists for this email");
        }
//        ApplicationUser userEntity = new ApplicationUser();
//        BeanUtils.copyProperties(user, userEntity);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setId(UUID.randomUUID());
        user.setRole(ApplicationUserRole.USER);
        repository.save(user);
    }
    public List<ApplicationUser> allUsers() {
        return repository.findAll();
    }

//    public boolean saveUser(ApplicationUser user) {
//        ApplicationUser userFromDB = repository.findByName(user.getName());
//
//        if (userFromDB != null) {
//            return false;
//        }
//
//        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        repository.save(user);
//        return true;
//    }
}
