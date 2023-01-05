import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void validNameWhenNull() {
        var validator = new Validator();
        assertFalse(validator.validName(null));
    }

    @Test
    void validNameWhenEmpty() {
        var validator = new Validator();
        assertFalse(validator.validName(""));
    }

    @Test
    void validNameWithForbiddenCharacters() {
        var validator = new Validator();
        assertFalse(validator.validName("Jan123"));
    }

    @Test
    void validNameCorrectName() {
        var validator = new Validator();
        assertTrue(validator.validName("Jan"));
    }

    @Test
    void validEmailWhenNull(){
        var validator = new Validator();
        assertFalse(validator.validEmail(null));
    }

    @Test
    void validEmailWhenEmpty(){
        var validator = new Validator();
        assertFalse(validator.validEmail(""));
    }

    @Test
    void validEmailWhenIncorrect(){
        var validator = new Validator();
        assertFalse(validator.validEmail("zlymail.com"));
    }

    @Test
    void validEmailWhenCorrect(){
        var validator = new Validator();
        assertTrue(validator.validEmail("dobrymail@gmail.com"));
    }

    @Test
    void validPhoneNumberWhenNull(){
        var validator = new Validator();
        assertFalse(validator.phoneNumber(null));
    }

    @Test
    void validPhoneNumberWhenEmpty(){
        var validator = new Validator();
        assertFalse(validator.phoneNumber(""));
    }

    @Test
    void validPhoneNumberWhenIncorrectFormat(){
        var validator = new Validator();
        assertFalse(validator.phoneNumber("1221"));
    }

    @Test
    void validPhoneNumberWhenForbiddenCharacters(){
        var validator = new Validator();
        assertFalse(validator.phoneNumber("12345678a"));
    }

    @Test
    void validPhoneNumberWhenCorrect(){
        var validator = new Validator();
        assertTrue(validator.phoneNumber("123456789"));
    }
}