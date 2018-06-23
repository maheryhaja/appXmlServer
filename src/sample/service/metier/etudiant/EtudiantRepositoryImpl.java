package sample.service.metier.etudiant;

import sample.commun.exception.SQLQueryException;
import sample.commun.strings.SQLQUERY;
import sample.donnee.etudiant.Etudiant;
import sample.service.CommonInjector;
import sample.service.metier.databasemanager.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maheryHaja on 6/23/2018.
 */
public class EtudiantRepositoryImpl implements EtudiantRepository{

    private DatabaseManager databaseManager;

    public EtudiantRepositoryImpl() {
        databaseManager = CommonInjector.getInstance().injectDatabaseManager();
    }

    @Override
    public void create(Etudiant etudiant) {
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(SQLQUERY.Queries.CREATE);
            preparedStatement.setString(1, etudiant.getNom());
            preparedStatement.setString(2, etudiant.getAddresse());
            preparedStatement.setLong(3, etudiant.getBourse());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(SQLQUERY.Queries.DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException();
        }
    }

    @Override
    public void update(Etudiant etudiant) {
        try {
            PreparedStatement preparedStatement = databaseManager.getConnection().prepareStatement(SQLQUERY.Queries.UPDATE);
            preparedStatement.setString(1, etudiant.getNom());
            preparedStatement.setString(2, etudiant.getAddresse());
            preparedStatement.setLong(3, etudiant.getBourse());
            preparedStatement.setInt(4, etudiant.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException();
        }
    }

    @Override
    public List<Etudiant> findAll() {

        List<Etudiant> etudiants = new ArrayList<>();
        try {
            Statement statement = databaseManager.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(SQLQUERY.Queries.FIND_ALL);

            while (resultSet.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(resultSet.getInt(SQLQUERY.Tables.ID));
                etudiant.setNom(resultSet.getString(SQLQUERY.Tables.NOM));
                etudiant.setAddresse(resultSet.getString(SQLQUERY.Tables.ADDRESSE));
                etudiant.setBourse(resultSet.getLong(SQLQUERY.Tables.BOURSE));
                etudiants.add(etudiant);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLQueryException();
        }
        return etudiants;
    }
}
