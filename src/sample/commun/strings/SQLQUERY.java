package sample.commun.strings;

import jdk.nashorn.internal.objects.annotations.Where;

/**
 * Created by maheryHaja on 6/23/2018.
 */
public interface SQLQUERY {

    String tableName = "Etudiant";

    interface Tables{
        String ID = "Id";
        String NOM = "Nom";
        String ADDRESSE = "Addresse";
        String BOURSE = "Bourse";
    }


    interface Queries{

        //creation des tables
        String CREATE_TABLE = "CREATE TABLE if NOT EXISTS Etudiant("+
                Tables.ID+" INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, "+
                Tables.NOM + " VARCHAR(100), "+
                Tables.ADDRESSE+" VARCHAR(100), "+
                Tables.BOURSE+" LONG)";

        //retrieve
        String FIND_ALL = "SELECT * FROM " + tableName;

        //insert

        String CREATE = "INSERT INTO "+tableName+"("+Tables.NOM+","+Tables.ADDRESSE+","+Tables.BOURSE+") Values(?,?,?)";

        //update
        String UPDATE = "UPDATE " + tableName + " SET " + Tables.NOM + "=?, " + Tables.ADDRESSE + "=?, " + Tables.BOURSE + "=? WHERE " + Tables.ID + "=?";

        String DELETE = "DELETE FROM " + tableName + " WHERE " + Tables.ID + "= ?";
    }

}
