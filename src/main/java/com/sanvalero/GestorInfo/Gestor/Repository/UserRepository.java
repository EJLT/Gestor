package com.sanvalero.GestorInfo.Gestor.Repository;

import com.sanvalero.GestorInfo.Gestor.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM gestor_user u WHERE (:fullname IS NULL OR u.fullname = :fullname) " +
            "AND (:username IS NULL OR u.username = :username) " +
            "AND (:email IS NULL OR u.email = :email)")
    List<User> findUsersByFilter(
            String fullname, String username, String email
    );
}