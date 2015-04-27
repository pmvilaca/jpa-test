package com.pvilaca.web;

import com.pvilaca.jpa.entity.User;
import com.pvilaca.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public static Specification<User> usingLeftJoin() {
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                root.fetch("userDetails", JoinType.LEFT);
                root.fetch("contactDetails", JoinType.LEFT);

                return null;
            }
        };
    }

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/all")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @RequestMapping("/all/predicate")
    private List<User> findAllUsingPredicate() {
        return userRepository.findAll(usingLeftJoin());
    }


    @RequestMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return userRepository.findOne(id);
    }
}
