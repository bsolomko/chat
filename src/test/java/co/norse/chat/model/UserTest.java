package co.norse.chat.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class UserTest {

    @Test
    void fullNameDisplayedCorrectlyIfFirstNameNull() {
        var user = new User();
        user.setFirstName(null);
        user.setLastName("Jordan");
        assertEquals("Jordan", user.getFullName());
    }

    @Test
    void fullNameDisplayedCorrectlyIfLastNameNull() {
        var user = new User();
        user.setFirstName("Alex");
        user.setLastName(null);
        assertEquals("Alex", user.getFullName());
    }

    @Test
    void fullNameDisplayedCorrectlyIfFirstLastNameNull() {
        var user = new User();
        user.setFirstName(null);
        user.setLastName(null);
        assertNull(user.getFullName());
    }

    @Test
    void fullNameDisplayedCorrectlyIfFullName() {
        var user = new User();
        user.setFirstName("Alex");
        user.setLastName("Cold");
        assertEquals("Alex Cold", user.getFullName());
    }


}
