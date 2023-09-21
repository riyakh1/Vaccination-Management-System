package com.example.VaccinationManagementSystem.Repository;

import com.example.VaccinationManagementSystem.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    //pre-built function : to use them you have to just define them
    User findByEmailId(String emailId);
}
