package motemere.analiser.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageClassTest {

    @DisplayName("Test MessageClass.get()")
    @Test
    void testGet() {
        assertEquals("Simple message text", MessageClass.get());
    }

}
