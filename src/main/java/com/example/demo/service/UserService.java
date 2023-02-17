package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserSaveDto;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    UserRepository userRepo;

    public User getById(Long id) {
        return userRepo.findById(id).get();
    }

    public List<User> getAllPersons() {
        return userRepo.findAll();
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
    public UserSaveDto mapToUserSaveModel (User user) {
        UserSaveDto userSaveDto = new UserSaveDto();
        userSaveDto.setName(user.getName());
        userSaveDto.setEmail(userSaveDto.getEmail());
        userSaveDto.setPassword(userSaveDto.getPassword());
        return userSaveDto;
    }



    //Из dto в entity
    public User mapToUser (UserSaveDto userSaveDto) {
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
