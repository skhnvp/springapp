package ru.testproj.springapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testproj.springapp.repository.User;
import ru.testproj.springapp.repository.UserRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (optionalUser.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists");
        }
        user.setAge(Period.between(user.getBirthday(), LocalDate.now()).getYears());

        return userRepository.save(user);
    }

    public void delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with this id does not exist");
        }
        userRepository.delete(optionalUser.get());
    }

    @Transactional
    public void update(Long id, String name, String email) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("User with this id does not exist");
        }
        User user = optionalUser.get();

        if (email != null && !email.equals(user.getEmail())) {
            Optional<User> foundByEmail = Optional.ofNullable(userRepository.findByEmail(email));
            if (foundByEmail.isPresent()) {
                throw new IllegalArgumentException("User with this email already exist");
            }
            user.setEmail(email);
        }

        if (name != null && !name.equals(user.getName())) {
            user.setName(name);
        }

//        userRepository.save(user); //не нужен при аннотации Transactional
    }
}
