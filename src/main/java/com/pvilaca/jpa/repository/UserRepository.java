package com.pvilaca.jpa.repository;

import com.pvilaca.jpa.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User, Long> {

    @EntityGraph("withAllDetails")
    List<User> findAll();

}
