package djoseffer.agregadorinvestimentos.controller;

import djoseffer.agregadorinvestimentos.dto.CreateUserDto;
import djoseffer.agregadorinvestimentos.dto.UpdateUserDto;
import djoseffer.agregadorinvestimentos.entity.User;
import djoseffer.agregadorinvestimentos.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);
        return ResponseEntity.created(URI.create("v1/users/" + userId.toString())).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        var user = userService.getUserById(userId);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<User>> listUsers() {
         return ResponseEntity.ok(userService.listUsers());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable String userId,
                                               @RequestBody UpdateUserDto updateUserDto) {
       userService.updateUserId(userId, updateUserDto);
       return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String userId) {
        userService.deleteById(userId);
        return ResponseEntity.noContent().build();
    }
}
