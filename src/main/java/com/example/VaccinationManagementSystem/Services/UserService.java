package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.UpdateEmailDto;
import com.example.VaccinationManagementSystem.Models.Dose;
import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User addUser(User user) {
        user = userRepository.save(user);
        return user;
    }

    public Date getVaccinationDate(Integer userId) {
        User user = userRepository.findById(userId).get();

        //User has access to dose so i'll get the dose entity from user entity
        Dose dose = user.getDose();

        return dose.getVaccinationDate();
    }

    public String updateEmail(UpdateEmailDto updateEmailDto) {
        int userId = updateEmailDto.getUserId();
        User user = userRepository.findById(userId).get();

        //Modify the entity with new parameters
        user.setEmailId(updateEmailDto.getNewEmailId());

        //saving the entity
        userRepository.save(user);

        return "Old email has been modified with new email : " + updateEmailDto.getNewEmailId();
    }

    public User getUserByEmail(String emailId) {
        User user = userRepository.findByEmailId(emailId);
        return user;
    }
}
