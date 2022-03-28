package com.hotelapp.hotelapp.repository;

import com.hotelapp.hotelapp.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenCalledSave_thenCorrectNumberOfRooms() {
        userRepository.save(new User(1L, "EMPLOYEE"));
        userRepository.save(new User(1L, "CUSTOMER"));
        List<User> users = userRepository.findAll();

        assertEquals(2, users.size());
    }
}
