import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class zoo {

    public final Map<String, Animal> animaux = new HashMap<>();
    public final File SaveAnimal = new File("Tp prog/src/SaveAnimal.txt");


    public zoo() {
        Load();
    }

    public void add(Animal animal) {
        animaux.put(animal.getName(), animal);
        Save();
    }

    public void Save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SaveAnimal))) {
            for (Animal animal : animaux.values()) {
                writer.write(animal.toString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde : ");
        }
    }

    public Map<String, Animal> getAnimaux() {
        return animaux;
    }

    public void Load() {
        if (!SaveAnimal.exists()) {
            System.out.println("Le fichier SaveAnimal.txt n'existe pas encore.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(SaveAnimal))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" , ");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String espèce = parts[2].trim();
                    int poids = Integer.parseInt(parts[1]);
                    Animal animal = new Animal(name, poids, espèce);
                    animaux.put(name, animal);
                } else {
                    System.out.println("Ligne mal formatée ignorée : " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement  ");
        } catch (NumberFormatException e) {
            System.out.println("Erreur de format du poids dans la ligne  ");
        }
    }
}