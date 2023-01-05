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
}