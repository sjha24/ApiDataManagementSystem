package com.carboncell.APIManagementApp.security.user;
import com.carboncell.APIManagementApp.model.User;
import com.carboncell.APIManagementApp.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User>userDetail = userRepository.findByEmail(email);
        return userDetail.map(CustomUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public String addUser(User user){
        User existUser= userRepository.findByEmail(user.getEmail()).orElse(null);
        if(existUser == null) {
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            return "User Register Successfully !!";
        }
        return "User Already Exist";
    }

}
