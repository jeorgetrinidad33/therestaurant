package com.example.therestaurant.services;

import com.example.therestaurant.entity.User;
import com.example.therestaurant.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Valid;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public String register(@Valid User user, BindingResult result) {
        boolean exists = userRepository.findByUsername(user.getUsername()).isPresent();
        if (exists) {
            FieldError nameTaken = new FieldError("blogUser", "username", "Username is taken");
            result.addError(nameTaken);
        }
        if (!user.getPassword().equals(user.getPasswordAgain())) {
            FieldError pwRepeat = new FieldError("user", "passwordAgain", "Please enter the same password");
            result.addError(pwRepeat);
        }

        if (result.hasErrors()) {
            return "register";
        }
        String encodePW = bCryptPasswordEncoder.encode(user.getPassword());
        System.out.println(encodePW);
        user.setPassword(encodePW);
        userRepository.save(user);
        return "login";
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
