package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.commun.exception.GenericRuntimeException;
import sample.donnee.databaseconf.DatabaseConfDto;
import sample.presentation.splash.SplashStage;
import sample.service.CommonInjector;
import sample.service.metier.databasemanager.DatabaseManager;

public class Main extends Application {

    private DatabaseManager databaseManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // initialize injector


        CommonInjector.getInstance().initializeWithApp(this);


        Stage splash = new SplashStage();
        splash.show();

        DatabaseConfDto databaseConfDto = CommonInjector.getInstance().injectDatabaseConfSA().readDatabaseConf();

        try {
            databaseManager = CommonInjector.getInstance().injectDatabaseManager();
            databaseManager.openConnection(databaseConfDto);
        } catch (GenericRuntimeException e) {

            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
