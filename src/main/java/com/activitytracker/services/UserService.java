package com.activitytracker.services;

import com.activitytracker.dtos.userdto.UserResponseDto;
import com.activitytracker.dtos.userdto.UserSignupDto;
import com.activitytracker.entities.ApiResponse;
import com.activitytracker.entities.User;
import com.activitytracker.exceptions.AlreadyExistsException;
import com.activitytracker.exceptions.NotFoundException;
import com.activitytracker.exceptions.NotNullException;

public interface UserService {
    boolean isUserExist(String email);

    boolean isUserExistByEmailAndPassword(String email, String Password);

    ApiResponse<User> signup(UserSignupDto userSignupDto) throws AlreadyExistsException, NotNullException;
    ApiResponse<UserResponseDto> login(String email, String password) throws NotNullException, NotFoundException;
}
