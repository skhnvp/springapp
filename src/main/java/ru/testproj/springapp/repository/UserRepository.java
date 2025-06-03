package ru.testproj.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

//    NATIVE
//    @Query(value = "select * from users where email = :email", nativeQuery = true)
//    User findByEmail(String email);

//    JPQL
//    @Query(value = "select u from User u where u.email = :email")
//    User findByEmail(String email);

    //JPA
    User findByEmail(String email);

}
