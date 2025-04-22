package djoseffer.agregadorinvestimentos.service;

import djoseffer.agregadorinvestimentos.controller.dto.AccountResponseDto;
import djoseffer.agregadorinvestimentos.controller.dto.CreateAccountDto;
import djoseffer.agregadorinvestimentos.controller.dto.CreateUserDto;
import djoseffer.agregadorinvestimentos.controller.dto.UpdateUserDto;
import djoseffer.agregadorinvestimentos.entity.Account;
import djoseffer.agregadorinvestimentos.entity.BillingAddress;
import djoseffer.agregadorinvestimentos.entity.User;
import djoseffer.agregadorinvestimentos.repository.AccountRepository;
import djoseffer.agregadorinvestimentos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {

    private final AccountRepository accountRepository;
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

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(UUID.fromString(userId));
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public void updateUserById(String userId, UpdateUserDto updateUserDto) {
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

    @Transactional
    public void createAccount(String userId, CreateAccountDto createAccountDto) {
        userRepository.findById(UUID.fromString(userId))
                .map(user -> {
                    Account account = new Account(user, createAccountDto.description(), new ArrayList<>());
                    BillingAddress billingAddress = new BillingAddress(null, account,
                            createAccountDto.street(), createAccountDto.number());
                    account.setBillingAddress(billingAddress);

                    accountRepository.save(account);
                    user.getAccounts().add(account);

                    return userRepository.save(user);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não existe"));
    }

    public List<Account> findAccounts(String userId) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe"));

        return user.getAccounts();
    }

    public List<AccountResponseDto> listAccounts(String userId) {
        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não existe"));

        return user.getAccounts().stream().map(account -> new AccountResponseDto(
                account.getAccountId().toString(),
                account.getDescription())).toList();
    }
}
