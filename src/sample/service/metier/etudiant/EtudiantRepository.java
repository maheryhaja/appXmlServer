package sample.service.metier.etudiant;

import sample.donnee.etudiant.Etudiant;

import java.util.List;

/**
 * Created by maheryHaja on 6/23/2018.
 */
public interface EtudiantRepository {
    void create(Etudiant etudiant);

    void delete(int id);

    void update(Etudiant etudiant);

    List<Etudiant> findAll();
}
