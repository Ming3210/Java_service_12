package ra.java_service_12.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ra.java_service_12.model.dto.request.UserLogin;
import ra.java_service_12.model.dto.request.UserRegister;
import ra.java_service_12.model.dto.response.JWTResponse;
import ra.java_service_12.model.entity.Role;
import ra.java_service_12.model.entity.User;
import ra.java_service_12.repository.RoleRepository;
import ra.java_service_12.repository.UserRepository;
import ra.java_service_12.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserRegister userRegister) {
        User user = User.builder().
                username(userRegister.getUsername()).
                password(passwordEncoder.encode(userRegister.getPassword())).
                isEnable(true).
                roles(mapRoleStringToRole(userRegister.getRoles())).
                build();

        return userRepository.save(user);
    }

    @Override
    public JWTResponse login(UserLogin userLogin) {
        return null;
    }


    private List<Role> mapRoleStringToRole(List<String> roles){
        List<Role> roleList= new ArrayList<>();


        if(roles != null && !roles.isEmpty()){
            roles.forEach(role->{
                switch (role){
                    case "ADMIN":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()->new NoSuchElementException("Role not found")));
                        break;
                    case "USER":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
                        break;
                    case "MODERATOR":
                        roleList.add(roleRepository.findByRoleName(role).orElseThrow(()-> new NoSuchElementException("Khong ton tai role_moderator")));
                        break;
                    default:
                        roleList.add(roleRepository.findByRoleName("USER").orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
                }
            });
        }else {
            roleList.add(roleRepository.findByRoleName("USER").orElseThrow(()-> new NoSuchElementException("Khong ton tai role_user")));
        }
        return roleList;
    }
}
