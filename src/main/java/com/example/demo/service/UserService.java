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

}
