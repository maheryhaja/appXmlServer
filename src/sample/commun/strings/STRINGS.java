package sample.commun.strings;

public interface STRINGS {

   interface ErrorMessage{
       String NO_CONNECTION_TO_DATABASE = "La connexion vers la base de données est vide";
       String ERROR_DATABASE_CONF = "Impossible de se connecter au serveur avec les configurations actuelles";
       String ERROR_SQL_QUERY = "Votre requete a échouée";
   }
}
