package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.model.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobService {

    UserService userService;
    DefaultEmailService emailService;
    CurrencyRatesService currencyRatesService;

//    @Scheduled(initialDelay=5000, fixedDelay = 3000)
//    @Scheduled(cron="0 15 10 1 * *")
    public void sendEmail() {
        List<UserDto> users =  userService.getAllPersons();
        for (UserDto user : users) {
            emailService.sendSimpleEmail(user.getEmail(), "Task Manager", "To do");
        }
    }

//    @Scheduled(fixedDelay = 5000) // каждые 5 секунд
    private void saveCurrencyRates() {
        currencyRatesService.saveModelFromApi();
    }
}
