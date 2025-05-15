package com.joao.demo.service;

import com.joao.demo.entity.User;
import com.joao.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public void save(User user) {
        var password = user.getPassword();
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
    }

    public User getByLogin(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Optional<User> update(UUID id, User user) {
       return userRepository.findById(id).map(existingUser -> {
           existingUser.setUsername(user.getUsername());
           existingUser.setPassword(user.getPassword());
           return userRepository.save(existingUser);
       });
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public List<User> list() {
        return userRepository.findAll();
    }
}
