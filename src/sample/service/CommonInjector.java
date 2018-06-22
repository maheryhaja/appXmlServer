package sample.service;


import javafx.application.Application;
import sample.service.applicatif.databaseconf.DatabaseConfSA;
import sample.service.applicatif.databaseconf.DatabaseConfSAImpl;
import sample.service.applicatif.resources.ResourceSA;
import sample.service.applicatif.resources.resourceSAImpl;

// responsable d'instancier tous les singletons
public class CommonInjector {

    private static CommonInjector commonInjectorInstance = new CommonInjector();

    //fields to inject somewhere
    private ResourceSA resourceSA;
    private DatabaseConfSA databaseConfSA;


    private Application application = null;

    public static synchronized CommonInjector getInstance() {
        if (commonInjectorInstance == null) {
            commonInjectorInstance = new CommonInjector();
        }

        return commonInjectorInstance;
    }

    private CommonInjector() {

    }

    public void initializeWithApp(Application application) {
        this.application = application;
    }

    //injecter l'application
    public Application injectApplication() {
        return application;
    }

    //injecter resourceSA

    public ResourceSA injectResourceSA() {
        if (resourceSA == null) {
            resourceSA = new resourceSAImpl();
        }
        return resourceSA;
    }

    public DatabaseConfSA injectDatabaseConfSA() {
        if (databaseConfSA == null) {
            databaseConfSA = new DatabaseConfSAImpl();
        }

        return databaseConfSA;
    }


}
