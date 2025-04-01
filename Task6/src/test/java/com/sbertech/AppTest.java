package com.sbertech;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
/**
 * Unit test for simple App.
 */
public class AppTest {

        @Test
        void testMultiplySuccess() throws EvenException {
            Multiple multiplier = new Multiple();
            assertEquals(8, multiplier.multiply(2, 4));
        }

        @Test
        void testThrowsException() {
            Multiple multiplier = new Multiple();
            assertThrows(EvenException.class, () -> multiplier.multiply(3, 4));
        }
}
