package sample.service.applicatif.databaseconf;

import io.reactivex.Observable;
import sample.donnee.databaseconf.DatabaseConfDto;

public interface DatabaseConfSA {
    DatabaseConfDto readDatabaseConf();

    void writeDatabaseConf(DatabaseConfDto databaseConfDto);

    Observable<DatabaseConfDto> onDatabaseConfUpdatedObservable();
}
