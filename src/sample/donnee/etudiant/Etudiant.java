package sample.donnee.etudiant;

/**
 * Created by maheryHaja on 6/23/2018.
 */
public class Etudiant {
    private int id;
    private String nom;
    private String addresse;
    private long bourse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public long getBourse() {
        return bourse;
    }

    public void setBourse(long bourse) {
        this.bourse = bourse;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", addresse='" + addresse + '\'' +
                ", bourse=" + bourse +
                '}';
    }
}
