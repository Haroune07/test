
import java.io.File;
public class Animal {
    private String nom;

    private int poids;

    private String espèce;

    //Constructeur animal
    public Animal(String nom, int poids, String espèce){
        this.nom = nom;
        this.poids = poids;
        this.espèce = espèce;
    }

    //Nom
    public String getName() {
        return this.nom;
    }
    //poids
    public int getPoids() {
        return this.poids;
    }
    //espèce
    public String getEspèce(){
        return this.espèce;
    }

    public String toString(){
        return this.nom + " , " + this.poids + " , " + this.espèce;
    }

    public void setPoids(int nouveauPoids) {
        this.poids = nouveauPoids;
    }
}

