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
}