package edu.unicen.tallerjava.todo.users;

import org.springframework.data.repository.CrudRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends CrudRepository<User, Integer>{
    Optional<User> findFirstByOrderByIdDesc();
}
