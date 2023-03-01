package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserDto;
import com.example.demo.model.UserSaveDto;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepo;

    public User findById(Long id) {
        return userRepo.findById(id).get();
    }

    public UserDto getById(Long id) {
        Optional<User> user = userRepo.findById(id);

        UserDto dto = new UserDto();

        if (user.isPresent()) {
            dto.setId(user.get().getId());
            dto.setName(user.get().getName());
            dto.setEmail(user.get().getEmail());
            dto.setPassword(user.get().getPassword());
        }

        return dto;
    }

    public List<UserDto> getAllPersons() {
         List<User> users = userRepo.findAll();
         List<UserDto> usersDto = new ArrayList<>();

        for (User user: users) {
            UserDto dto = mapToDto(user);
            usersDto.add(dto);
        }
        return usersDto;
    }

    private UserDto mapToDto (User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public Long saveNewPerson(UserSaveDto model) {

        User user = new User();

        user.setName(model.getName());
        user.setEmail(model.getEmail());
        user.setPassword(model.getPassword());
        return userRepo.save(user).getId();
    }

    public void deletePersonById(Long id) {
        userRepo.deleteById(id);
    }

    public User updateById(User user) {
        return userRepo.save(user);
    }


    //Из entity в dto
    public UserSaveDto mapToUserSaveModel(User user) {
        UserSaveDto userSaveDto = new UserSaveDto();
        userSaveDto.setName(user.getName());
        userSaveDto.setEmail(userSaveDto.getEmail());
        userSaveDto.setPassword(userSaveDto.getPassword());
        return userSaveDto;
    }


    //Из dto в entity
    public User mapToUser(UserSaveDto userSaveDto) {
        User user = new User();
        user.setName(userSaveDto.getName());
        user.setEmail(userSaveDto.getEmail());
        user.setPassword(userSaveDto.getPassword());
        return user;
    }


    public UserSaveDto updateUserById(User user) {
        return mapToUserSaveModel(userRepo.save(user));
    }

}
