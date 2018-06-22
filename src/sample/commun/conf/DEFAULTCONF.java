package sample.commun.conf;

public interface DEFAULTCONF {
    interface DatabaseConf {
        String HOST  = "127.0.0.1";
        int PORT = 3306;
        String USER = "root";
        String PASSWORD = "";
        String DATABASENAME = "tpxml";

        String DEFAULTPATH = "database.conf";
    }
}
