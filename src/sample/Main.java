package sample;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sample.commun.exception.GenericRuntimeException;
import sample.donnee.databaseconf.DatabaseConfDto;
import sample.donnee.etudiant.Etudiant;
import sample.presentation.splash.SplashStage;
import sample.service.CommonInjector;
import sample.service.applicatif.server.ServerSA;
import sample.service.metier.databasemanager.DatabaseManager;
import sample.service.metier.etudiant.EtudiantRepository;

public class Main extends Application {

    private DatabaseManager databaseManager;
    private ServerSA serverSA;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // initialize injector


        CommonInjector.getInstance().initializeWithApp(this);



        Stage splash = new SplashStage();
        splash.show();

        serverSA = CommonInjector.getInstance().injectServerSA();

        serverSA.isRunningObservable().subscribe(isRunning -> System.out.println(isRunning+" : server state"));

        try {

            serverSA.start();

            EtudiantRepository etudiantRepository = CommonInjector.getInstance().injectEtudiantRepository();

            Etudiant etudiant = new Etudiant();
            etudiant.setNom("Randrianarisaona");
            etudiant.setBourse(20004);
            etudiant.setAddresse("67 Ha");
            etudiant.setId(1);

//            etudiantRepository.create(etudiant);
            etudiantRepository.findAll().stream().forEach(System.out::println);

            serverSA.stop();

            Alert alert = new Alert(Alert.AlertType.WARNING, "une erreur");
            alert.setHeaderText("Connexion vers la base échouée");
            alert.setTitle("info");
            alert.showAndWait();


        } catch (GenericRuntimeException e) {

            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
