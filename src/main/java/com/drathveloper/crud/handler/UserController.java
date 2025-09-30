package com.drathveloper.crud.handler;

import com.drathveloper.crud.handler.dto.*;
import com.drathveloper.crud.handler.mapper.UserDtoMapper;
import com.drathveloper.crud.model.User;
import com.drathveloper.crud.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDtoMapper userMapper;

    private final UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("id") final long userId) {
        final User user = userService.getUser(userId);
        return ResponseEntity.ok(userMapper.userToGetUserResponse(user));
    }

    @PostMapping("/user")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody final RegisterUserRequest requestBody) {
        final User user = userMapper.registerUserRequestToUser(requestBody);
        final long id = userService.registerUser(user);
        final RegisterUserResponse response = new RegisterUserResponse(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") final long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Void> updateUser(
            @PathVariable("id") final long userId, @RequestBody final UpdateUserRequest requestBody) {
        final User user = userMapper.updateUserRequestToUser(userId, requestBody);
        userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/count")
    public ResponseEntity<CountResponse> getCount() {
        final long count = userService.count();
        final CountResponse response = new CountResponse(count);
        return ResponseEntity.ok(response);
    }
}
