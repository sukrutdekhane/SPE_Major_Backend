package com.example.SPE_Major_project.Repository;

import com.example.SPE_Major_project.Entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<User,Integer> {


    User findByEmail(String email);

    @Query("select u from User u where u.mobileNumber=?1")
    User findByMobileNumber(String mobileNumber);

    @Query("select u.password from User u where u.mobileNumber=?1")
    String getOldPassword(String mobileNumber);

    @Modifying
    @Transactional
    @Query("update User u set u.password=?2 where u.mobileNumber=?1")
    void setNewPassword(String mobileNumber,String newPassword);
}
