package com.activitytracker.services.servicesimpl;

import com.activitytracker.dtos.userdto.UserResponseDto;
import com.activitytracker.dtos.userdto.UserSignupDto;
import com.activitytracker.entities.ApiResponse;
import com.activitytracker.util.ResponseManager;
import com.activitytracker.entities.User;
import com.activitytracker.exceptions.AlreadyExistsException;
import com.activitytracker.exceptions.NotFoundException;
import com.activitytracker.exceptions.NotNullException;
import com.activitytracker.repositories.UserRepository;
import com.activitytracker.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ResponseManager responseManager;
    private final HttpSession httpSession;

    @Override
    public boolean isUserExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isUserExistByEmailAndPassword(String email, String password) {
        return userRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public ApiResponse<User> signup(UserSignupDto userSignupDto) throws AlreadyExistsException, NotNullException {
        boolean userExistStatus = isUserExist(userSignupDto.getEmail());
        ApiResponse apiResponse;

        if(!userExistStatus){
            if(!userSignupDto.getFirstName().equals("") || !userSignupDto.getLastName().equals("") || !userSignupDto.getEmail().equals("") || !userSignupDto.getPassword().equals("")){
                    User user = new User();
                    BeanUtils.copyProperties(userSignupDto,user);
                    user.setCreatedAt(LocalDateTime.now());
                    userRepository.save(user);

                    apiResponse = responseManager.success(user);
                    return apiResponse;
            } else {
                throw new NotNullException("You're missing one of the required inputs");
            }
        } else {
           throw new AlreadyExistsException("User already exists");
        }
    }

    @Override
    public ApiResponse login(String email, String password) throws NotNullException, NotFoundException {
        boolean isUserExistByEmailAndPasswordStatus = isUserExistByEmailAndPassword(email, password);

        if(email.equals("") || password.equals("")){
            System.out.println("No email or password provided");
            throw new NotNullException("Email or password not provided");
        } else {
            if(isUserExistByEmailAndPasswordStatus){
                Optional<User> user = userRepository.findByEmailAndPassword(email, password);

                UserResponseDto userResponseDto = new UserResponseDto();
                userResponseDto.setId(user.get().getId());
                userResponseDto.setFirstName(user.get().getFirstName());
                userResponseDto.setLastName(user.get().getLastName());
                userResponseDto.setEmail(user.get().getEmail());
                userResponseDto.setDob(user.get().getDob());
                userResponseDto.setGender(user.get().getGender());
                userResponseDto.setCreatedAt(user.get().getCreatedAt());

                httpSession.setAttribute("userId", userResponseDto.getId());
                return responseManager.success(userResponseDto);
            } else {
                throw new NotFoundException("Invalid Credentials");
            }
        }
    }

}
