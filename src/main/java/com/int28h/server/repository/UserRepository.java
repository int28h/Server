package com.int28h.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.int28h.server.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
