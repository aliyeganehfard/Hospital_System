package model.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingletonConnectionTest {
    @Test
    public void testConnection(){
        assertDoesNotThrow(SingletonConnection::getInstance);
    }
}