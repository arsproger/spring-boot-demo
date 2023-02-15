package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserSaveModel;
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

    public Long saveNewPerson(UserSaveModel model) {

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
    public UserSaveModel mapToUserSaveModel (User user) {
        UserSaveModel userSaveModel = new UserSaveModel();
        userSaveModel.setName(user.getName());
        userSaveModel.setEmail(userSaveModel.getEmail());
        userSaveModel.setPassword(userSaveModel.getPassword());
        return userSaveModel;
    }



    //Из dto в entity
    public User mapToUser (UserSaveModel userSaveModel) {
        User user = new User();
        user.setName(userSaveModel.getName());
        user.setEmail(userSaveModel.getEmail());
        user.setPassword(userSaveModel.getPassword());
        return user;
    }


    public UserSaveModel updateUserById(User user) {
        return mapToUserSaveModel(userRepo.save(user));
    }

}
