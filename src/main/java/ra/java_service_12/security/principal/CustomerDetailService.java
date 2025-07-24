package ra.java_service_12.security.principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ra.java_service_12.model.entity.Role;
import ra.java_service_12.model.entity.User;
import ra.java_service_12.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
public class CustomerDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Ko ton tai user"));
        CustomUserDetail.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .isEnable(user.getIsEnable())
                .authorities(mapRoleToGrandAuthorities(user.getRoles()))
                .build();
        return null;
    }

    private Collection<? extends GrantedAuthority> mapRoleToGrandAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).toList();
    }
}
