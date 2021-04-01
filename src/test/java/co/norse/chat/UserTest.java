package co.norse.chat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    @Test
    void fullNameDisplayedCorrectlyIfFirstNameNull() {
        var user = new User();
        user.setFirstName(null);
        user.setLastName("Jordan");
        assertEquals("Jordan", user.getFullName());
    }

}
