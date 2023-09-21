package com.example.VaccinationManagementSystem.Services;

import com.example.VaccinationManagementSystem.Models.Dose;
import com.example.VaccinationManagementSystem.Models.User;
import com.example.VaccinationManagementSystem.Repository.DoseRepository;
import com.example.VaccinationManagementSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoseService {
    @Autowired
    DoseRepository doseRepository;
    @Autowired
    UserRepository userRepository;

    public String giveDose(String doseId, Integer userId) {
        User user = userRepository.findById(userId).get();

        Dose dose = new Dose();

        //setting the attributes
        dose.setDoseId(doseId);
        dose.setUser(user);

        //saving it in the repository
        //doseRepository.save(dose); this was unidirectional mapping

        //setting the child attribute in the parent entity
        user.setDose(dose);

        //saving the parent only , the child will get automatically saved
        userRepository.save(user);

        return "Dose have been given to the user successfully";
    }
}
