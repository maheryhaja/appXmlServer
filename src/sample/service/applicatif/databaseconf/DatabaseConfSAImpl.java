package sample.service.applicatif.databaseconf;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import sample.commun.conf.DEFAULTCONF;
import sample.donnee.databaseconf.DatabaseConfDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabaseConfSAImpl implements DatabaseConfSA {

    private JAXBContext jaxbContext;
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    private PublishSubject<DatabaseConfDto> databaseConfDtoPublishSubject = PublishSubject.create();



    public DatabaseConfSAImpl() {
        try {
            jaxbContext = JAXBContext.newInstance(DatabaseConfDto.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            unmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }

    @Override
    public DatabaseConfDto readDatabaseConf() {

        if (!Files.exists(getDatabaseConfPath())) {
            writeDatabaseConf(getDefaultDatabaseConf());
        }
        try {
            return (DatabaseConfDto) unmarshaller.unmarshal(Files.newBufferedReader(getDatabaseConfPath()));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void writeDatabaseConf(DatabaseConfDto databaseConfDto) {
        try {
            marshaller.marshal(databaseConfDto, Files.newOutputStream(getDatabaseConfPath()));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        databaseConfDtoPublishSubject.onNext(databaseConfDto);
    }

    @Override
    public Observable<DatabaseConfDto> onDatabaseConfUpdatedObservable() {
        return databaseConfDtoPublishSubject;
    }

    private Path getDatabaseConfPath() {
       return Paths.get(DEFAULTCONF.DatabaseConf.DEFAULTPATH);
    }

    private DatabaseConfDto getDefaultDatabaseConf() {
        DatabaseConfDto databaseConfDto =new DatabaseConfDto();
        databaseConfDto.setHost(DEFAULTCONF.DatabaseConf.HOST);
        databaseConfDto.setPassword(DEFAULTCONF.DatabaseConf.PASSWORD);
        databaseConfDto.setPort(DEFAULTCONF.DatabaseConf.PORT);
        databaseConfDto.setUser(DEFAULTCONF.DatabaseConf.USER);
        return databaseConfDto;
    }

}
