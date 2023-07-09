package com.activitytracker.controllers;

import com.activitytracker.dtos.userdto.UserResponseDto;
import com.activitytracker.dtos.userdto.UserSignupDto;
import com.activitytracker.entities.ApiResponse;
import com.activitytracker.entities.User;
import com.activitytracker.exceptions.NotFoundException;
import com.activitytracker.exceptions.NotNullException;
import com.activitytracker.services.UserService;
import com.activitytracker.exceptions.AlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/activity-tracker")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<User>> signup(@RequestBody UserSignupDto userSignupDto) throws AlreadyExistsException, NotNullException {
        ApiResponse<User> user = userService.signup(userSignupDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse<UserResponseDto>> login(@RequestBody User user) throws NotFoundException, NotNullException {
        ApiResponse<UserResponseDto> userResponseDtoApiResponse = userService.login(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(userResponseDtoApiResponse, HttpStatus.ACCEPTED);
    }


}
