package com.jenkins.dev.repository;

import com.jenkins.dev.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository <User,Long> {
    boolean existsByName(String name);
}
