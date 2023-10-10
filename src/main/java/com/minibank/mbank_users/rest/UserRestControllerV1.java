package com.minibank.mbank_users.rest;

import com.minibank.mbank_users.model.User;
import com.minibank.mbank_users.response.ResponseHandler;
import com.minibank.mbank_users.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank")
public class UserRestControllerV1
{
    UserService userService;
    @Value("${featire_flags.users_get}")
    private String featire_flags_users_get;

    @Value("${featire_flags.user_create}")
    private String featire_flags_user_create;

    public UserRestControllerV1(UserService userService) {

        this.userService = userService;

    }

    // Read Specific User Details from DB
    @GetMapping("/user/{userId}")
    @ApiOperation(value ="User id", notes="Provide user details",
            response = ResponseEntity.class)

    public ResponseEntity<Object> getUserDetails(@PathVariable("userId") Long userId)
    {

        return ResponseHandler.responseBuilder("Requested User Details are given here",
                HttpStatus.OK, userService.findById(userId));
    }

    // Read All Users Details from DB
    @GetMapping("/user/")
    public List<User> getAllUserDetails()
    {
        if (featire_flags_users_get.equals("0")) {
            return null;
        }
        return userService.findAll();
    }


    @PostMapping("/user/")
    public String createUserDetails(@RequestBody User user)
    {
        if (featire_flags_user_create.equals("0")) {
            return null;
        }
        userService.saveUser(user);
        return "User Created Successfully";
    }


























































































































    @PutMapping("/user/")
    public String updateUserDetails(@RequestBody User user)
    {
        userService.saveUser(user);
        return "User Updated Successfully";
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUserDetails(@PathVariable("userId") Long userId)
    {
        userService.deleteById(userId);
        return "User Deleted Successfully";
    }
}