package Util;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseConnectionTest {

    @Test
    void testGetInstance() {
        try {
            Connection connection = DataBaseConnection.getInstance();
            assertNotNull(connection);

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}