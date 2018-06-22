package sample.service.metier.databasemanager;

import sample.donnee.databaseconf.DatabaseConfDto;

import java.sql.Connection;

/**
 * Created by maheryHaja on 6/22/2018.
 */
public interface DatabaseManager {
    void openConnection(DatabaseConfDto databaseConfDto);

    Connection getConnection();
}
