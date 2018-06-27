package sample.service.applicatif.server;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;
import sample.commun.exception.NoConnectionToDatabaseException;
import sample.service.CommonInjector;
import sample.service.applicatif.databaseconf.DatabaseConfSA;
import sample.service.metier.databasemanager.DatabaseManager;

import java.sql.SQLException;

public class ServerSAImpl implements ServerSA {

    private DatabaseConfSA databaseConfSA;
    private DatabaseManager databaseManager;
    BehaviorSubject<Boolean> isRunningSubject = BehaviorSubject.createDefault(false);

    public ServerSAImpl() {
        databaseConfSA = CommonInjector.getInstance().injectDatabaseConfSA();
        databaseManager = CommonInjector.getInstance().injectDatabaseManager();
    }

    @Override
    public void start() {
        closeActualConnection();
        databaseManager.openConnection(databaseConfSA.readDatabaseConf());
        if (databaseManager.getConnection() != null) {
            isRunningSubject.onNext(true);
        }

    }

    @Override
    public void stop() {
        closeActualConnection();
    }

    private void closeActualConnection() {
        try {
            if (databaseManager.getConnection() != null) {
                try {
                    databaseManager.getConnection().close();
                    isRunningSubject.onNext(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (NoConnectionToDatabaseException e) {

        }
    }

    @Override
    public Observable<Boolean> isRunningObservable() {
        return isRunningSubject;
    }
}
