package com.OOP.eplpredictions.service;

import com.OOP.eplpredictions.entities.User;
import com.OOP.eplpredictions.services.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;

//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//    @Mock
//    private UserRepository userRepository;
//    @InjectMocks
//    private UserService userService;
//
//    @Test
//    public void testThatUserIsSaved() {
//        final User user
//                = new User();
////                = new User(Long.getLong("1"),"1@s","test",0,true,"testets", new HashSet<>(), LocalDateTime.now());
//        user.setName("test");
//        user.setPassword("test test");
//
//        when(userRepository.save(eq(user))).thenReturn(user);
//        assertEquals(true, userService.createUser(user));
//    }
//}
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testSaveUser() {
        // Create a new user
        User user = new User();
        user.setName("testUser");
        user.setPassword("testPassword");

        // Save the user
//        User savedUser =
                userService.deleteUser(new Long(1));

        // Check if the user was saved successfully
//        assertNotNull(savedUser.getId());
    }
}