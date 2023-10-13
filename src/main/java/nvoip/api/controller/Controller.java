package nvoip.api.controller;

import lombok.RequiredArgsConstructor;
import nvoip.api.domains.User;
import nvoip.api.requests.User.UserPostRequestBody;
import nvoip.api.requests.User.UserPutRequestBody;
import nvoip.api.services.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/")
public class Controller {
    private final UserServiceImpl userService;

    @PostMapping
    public ResponseEntity<User> save (@RequestBody @Valid UserPostRequestBody userPostRequestBody) {
        return new ResponseEntity<>(userService.save(userPostRequestBody), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<User> findByID (@PathVariable Integer id) {
        return ResponseEntity.ok(userService.findUserByIdOrThrowNotFoundException(id));
    }

    @PutMapping
    public ResponseEntity<Void> replace (@RequestBody UserPutRequestBody userPutRequestBody) {
        userService.replace(userPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete (@PathVariable Integer id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}



