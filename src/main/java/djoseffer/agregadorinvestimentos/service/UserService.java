package djoseffer.agregadorinvestimentos.service;

import djoseffer.agregadorinvestimentos.dto.CreateUserDto;
import djoseffer.agregadorinvestimentos.dto.UpdateUserDto;
import djoseffer.agregadorinvestimentos.entity.User;
import djoseffer.agregadorinvestimentos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UUID createUser(CreateUserDto userDto) {
        var entity = new User();
        entity.setUsername(userDto.username());
        entity.setEmail(userDto.email());
        entity.setPassword(userDto.password());

        var userSaved = userRepository.save(entity);
        return userSaved.getUserId();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(UUID.fromString(id));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserId(String userId, UpdateUserDto updateUserDto) {
        Optional.ofNullable(userId)
                .map(UUID::fromString)
                .flatMap(userRepository::findById)
                .ifPresent(user -> {
                    Optional.ofNullable(updateUserDto.username())
                            .ifPresent(user::setUsername);
                    Optional.ofNullable(updateUserDto.password())
                            .ifPresent(user::setPassword);
                    userRepository.save(user);
                });
    }

    public void deleteById(String userId) {
        Optional.ofNullable(userId)
                .map(UUID::fromString)
                .filter(userRepository::existsById)
                .ifPresent(userRepository::deleteById);
    }
}
