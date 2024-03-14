package edu.java.scrapper;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.assertj.core.api.Assertions.assertThat;

public class DatabaseExistingTests extends IntegrationTest{
    @Test
    public void existingTests() throws SQLException {
        Connection connection = DriverManager.getConnection(
            POSTGRES.getJdbcUrl(),
            POSTGRES.getUsername(),
            POSTGRES.getPassword()
        );

        ResultSet resultSet
            = connection
            .getMetaData()
            .getTables(null, null, "chat", new String[]{"TABLE"});
        assertThat(resultSet.next()).isTrue();
        resultSet
            = connection
            .getMetaData()
            .getTables(null, null, "link", new String[]{"TABLE"});
        assertThat(resultSet.next()).isTrue();
    }
}
