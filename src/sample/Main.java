package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import sample.donnee.databaseconf.DatabaseConfDto;
import sample.presentation.splash.SplashStage;
import sample.service.CommonInjector;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // initialize injector
        CommonInjector.getInstance().initializeWithApp(this);


        Stage splash = new SplashStage();
        splash.show();

        DatabaseConfDto databaseConfDto = CommonInjector.getInstance().injectDatabaseConfSA().readDatabaseConf();

        System.out.println(databaseConfDto);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
