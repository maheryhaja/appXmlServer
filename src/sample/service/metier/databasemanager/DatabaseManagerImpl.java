package sample.service.metier.databasemanager;

import sample.commun.exception.DatabaseConnexionFailedException;
import sample.commun.exception.GenericRuntimeException;
import sample.commun.exception.NoConnectionToDatabaseException;
import sample.commun.strings.SQLQUERY;
import sample.donnee.databaseconf.DatabaseConfDto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by maheryHaja on 6/22/2018.
 */
public class DatabaseManagerImpl implements DatabaseManager{

    private Connection connection;

    @Override
    public void openConnection(DatabaseConfDto databaseConfDto) {
        try {

            System.out.println(databaseConfDto.getUser());
            connection = DriverManager.getConnection(
                formatDatabaseUrl(databaseConfDto),
                    databaseConfDto.getUser(),
                    databaseConfDto.getPassword()
            );

            connection.createStatement().executeUpdate("CREATE DATABASE if NOT EXISTS " + databaseConfDto.getDatabaseName());
            connection.createStatement().execute("use " + databaseConfDto.getDatabaseName());
            connection.createStatement().executeUpdate(SQLQUERY.Queries.CREATE_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseConnexionFailedException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GenericRuntimeException(e.getMessage());
        }
    }

    @Override
    public Connection getConnection() {
        if (connection == null) {
            throw new NoConnectionToDatabaseException();
        }
        return connection;
    }

    public static String formatDatabaseUrl(DatabaseConfDto databaseConfDto) {
        return String.format("jdbc:mysql://%s:%d/?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                databaseConfDto.getHost(),
                databaseConfDto.getPort()
                );
    }
}
