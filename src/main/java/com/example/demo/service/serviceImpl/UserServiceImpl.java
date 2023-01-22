package com.example.demo.service.serviceImpl;

import com.example.demo.dto.UserDto;
import com.example.demo.email.EmailSenderServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private EmailSenderServiceImpl emailSender;

    @Override
    public UserDto createUser(UserDto userDto) {

        Optional<User> email = userRepository.findUserByEmail(userDto.getEmail());

        if(email.isPresent()){
                throw new EmailAlreadyExistException("Email already exist for a User");
    }
        //Convert userDto into JPA object. Since we need to save the JPA object into the database

        // User user0 = UserMapper.mapToUser(userDto); Own created Mapper method ( Check the mapper package)

        User user = modelMapper.map(userDto, User.class);  // We can also use mapStruct Library Check java guides microservice Udemy Lesson 39 - 41

        try {
        emailSender.sendSimpleEmail(userDto.getEmail(),"Congratulations!!! your registration is successful", "Microservice App");
        } catch (MailException mailException) {
            System.out.println(mailException);
        }

        System.out.println(userDto.getEmail());

        userRepository.save(user); // newUser is now a JPA entity

        //Convert user back to DTO  since we need to return UserDto

        // UserDto newUserDto =UserMapper.mapToUserDto(user);Own created Mapper method ( Check the mapper package)

        UserDto newUserDto = modelMapper.map(user, UserDto.class);

        return newUserDto;
    }

    @Override
    public List<UserDto> fetchAllUsers() {
        List<User> allUsers = userRepository.findAll();
       // List<UserDto> fetchedUsers = allUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList()); Our own define mapper

        List<UserDto> fetchedUsers = allUsers.stream().map(user -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());

        return fetchedUsers;
    }

    @Override
    public UserDto fetchUserById(Long userId) {
        User optionalUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
//                User fetchedUser = optionalUser.get();  We don't need to get again

        //return UserMapper.mapToUserDto(fetchedUser); Our own define mapper

        return modelMapper.map(optionalUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User editedUser = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userDto.getId())
        );
        editedUser.setFirstName(userDto.getFirstName());
        editedUser.setLastName(userDto.getLastName());
        editedUser.setEmail(userDto.getEmail());
        userRepository.save(editedUser);
       // UserDto changedUser = UserMapper.mapToUserDto(editedUser); Our own defined mapper

        UserDto changedUser = modelMapper.map(editedUser, UserDto.class);

        return changedUser;
    }

    @Override
    public String removeUserById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        userRepository.deleteById(userId);
        return "User with " + userId + " have been removed successfully";
    }

    @Override
    public String removeAllUser() {
        userRepository.deleteAll();
        return "All user records removed successfully";
    }
}
