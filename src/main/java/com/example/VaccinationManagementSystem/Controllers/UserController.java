package com.example.VaccinationManagementSystem.Controllers;

import com.example.VaccinationManagementSystem.Dtos.RequestDtos.UpdateEmailDto;
import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        //System.out.println(user.getName());
        return userService.addUser(user);
    }

    @GetMapping("/getVaccinationDate")
    public Date getVaccinationDate(@RequestParam("userId")Integer userId){
        return userService.getVaccinationDate(userId);
    }

    @PutMapping("/updateEmail")
    public String updateEmail(@RequestBody UpdateEmailDto updateEmailDto){
        return userService.updateEmail(updateEmailDto);
    }

    @GetMapping("/getUserByEmail")
    public User getUserByEmail(@RequestParam("emailId")String emailId){
        return userService.getUserByEmail(emailId);
    }
}
