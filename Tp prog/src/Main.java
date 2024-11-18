import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static Map<String, Integer> visiteursParAnimal = new HashMap<>();
    public static zoo Zoo = new zoo();
    public  static Scanner sc= new Scanner(System.in);
    public static void main(String[] args) {
        Zoo.Load();


        while (true) {
            System.out.println("1. Ajouter un nouvel animal\n" +
                    "2. Afficher la liste des animaux et leurs informations\n" +
                    "3. Vérifier si un animal est dans le zoo\n" +
                    "4. Modifier le poids d’un animal\n" +
                    "5. Ajouter des visiteurs pour une journée\n" +
                    "6. Afficher le nombre total de visiteurs\n" +
                    "7. Afficher le poids moyen des animaux\n" +
                    "8. Trouver l’animal le plus lourd et le plus léger\n" +
                    "9. Afficher un résumé complet du zoo (visiteurs et animaux)\n" +
                    "10. Quitter le programme\n");
            int choix = bonChoix();

            if (choix == 10) {
                System.out.println("Au revoirs patate!!!!");
                break;
            }


            switch (choix) {

                case 1:

                    System.out.println("1. Ajouter un nouvel animal\n");

                    System.out.println("Ajoute le nom de cet animal : ");
                    String nom = sc.nextLine();

                    System.out.println("Ajoute le poids de votre animal : ");
                    int poids = sc.nextInt();
                    sc.nextLine();


                    System.out.println("Ajoute l'espèce de cet animal : ");
                    String espèce = sc.nextLine();

                    Animal animal = new Animal(nom, poids, espèce);

                    Zoo.add(animal);
                    Zoo.Save();

                    break;
                case 2:


                    System.out.println("2. Afficher la liste des animaux et leurs informations\n");

                    System.out.println("Ceci est la liste d'animaux qu'on a ajouter pour l'instant");

                    AfficherListeAnimaux("SaveAnimal.txt");
                    System.out.println();
                    break;
                case 3:

                    System.out.println("3. Vérifier si un animal est dans le zoo\n");

                    rechercheAnimal(Zoo);
                    break;
                case 4:
                    System.out.println("4. Modifier le poids d’un animal\n");
                    modifierPoidsAnimal();

                    break;
                case 5:
                    System.out.println("5. Ajouter des visiteurs pour une journée\n");
                    ajouterVisiteursParAnimal();
                    break;
                case 6:
                    System.out.println("6. Afficher le nombre total de visiteurs\n");
                    afficherTotalVisiteurs();
                    break;
                case 7:
                    System.out.println("7. Afficher le poids moyen des animaux\n");
                    calculerPoidsAnimaux(Zoo);
                    break;
                case 8:
                    System.out.println("8. Trouver l’animal le plus lourd et le plus léger\n");
                    trouverAnimauxLePLusLegerEtLourd(Zoo);
                    break;
                case 9:
                    System.out.println("9. Afficher un résumé complet du zoo (visiteurs et animaux)\n");
                    afficherLeRésuméCompletDuZoo(Zoo, visiteursParAnimal);
                    break;
                case 10:
                    System.out.println("10. Quitter le programme\n");
                    break;
            }
        }


    }

    public static int bonChoix() {
        Scanner sc = new Scanner(System.in);
        int choix = 0;
        boolean bonChoix = false;

        while (!bonChoix) {
            System.out.print("fait un choix entre 1-10 : ");
            choix = sc.nextInt();
            if (choix >= 1 && choix <= 10) {
                bonChoix = true;
            } else {
                System.out.println("choix invalide,Choisie entre 1-10");
                sc.nextInt();
            }
        }
        return choix;
    }

    public static void AfficherListeAnimaux(String nomDuFichier) {
        try (BufferedReader AfficherListe = new BufferedReader(new FileReader(nomDuFichier));) {

            String laListe;

            System.out.println(nomDuFichier);

            while ((laListe = AfficherListe.readLine()) != null) {
                System.out.println(laListe);
            }

        } catch (IOException e) {
            System.out.println("Erreur de lecture du fichier");
        }
    }

    public static void informationAnimal(Animal animal) {
        System.out.println("Nom " + animal.getName() + ", Espèce " + animal.getEspèce() + " et le poids en kg est de " + animal.getPoids());
    }

    public static void rechercheAnimal(zoo afficherMonZoo) {

        System.out.println("Entrer le nom ou l'espèce de l'animal que vous cherchiez : ");
        String Animalchercher = sc.nextLine();

        boolean Recherche = false;

        for (Animal animal : afficherMonZoo.animaux.values()) {
            if (animal.getName().equals(Animalchercher) || animal.getEspèce().equals(Animalchercher)) {

                informationAnimal(animal);
                Recherche = true;
                System.out.println(animal.getName());
            }
        }
        if (!Recherche) {
            System.out.println("aucun animal ou espèce trouver dans notre zoo ");
        }
    }

    public static void modifierPoidsAnimal() {

        System.out.print("Nom de l'animal à modifier : ");
        String nom = sc.nextLine();

        for (Animal animal : Zoo.animaux.values()) {

            if (animal.getName().equalsIgnoreCase(nom)) {

                System.out.print("Nouveau poids (en kg) : ");

                int nouveauPoids = sc.nextInt();

                animal.setPoids(nouveauPoids);

                Zoo.Save();
                System.out.println("Poids modifié avec succès !");

            }
        }

        System.out.println("Animal non trouvé.");


    }

    public static void ajouterVisiteursParAnimal() {

        System.out.print("Nom de l'animal que vous aviez visité : ");
        String nomAnimalVisité = sc.nextLine();

        System.out.print("Le nombre de visiteurs  : ");
        int nombreVisiteurs = sc.nextInt();

        visiteursParAnimal.put(nomAnimalVisité, visiteursParAnimal.getOrDefault(nomAnimalVisité, 0) + nombreVisiteurs);
        System.out.println("Visiteurs ajoutés avec succès !");
    }

    public static void afficherTotalVisiteurs() {


        int totalVisiteurs = 0;

        for (int nombrevisiteurs : visiteursParAnimal.values()) {
            totalVisiteurs += nombrevisiteurs;
        }
        System.out.println("Nombre total de visiteurs : " + totalVisiteurs);
    }

    public static void calculerPoidsAnimaux(zoo Zoo) {
        int poidsTotal = 0;
        for (Animal animal : Zoo.animaux.values()) {
            poidsTotal += animal.getPoids();
        }
        int poidsMoyen = poidsTotal / Zoo.animaux.values().size();
        System.out.println("Poids de moyen : " + poidsMoyen);
    }

    public static void trouverAnimauxLePLusLegerEtLourd(zoo Zoo) {
        Animal Pluslourd = null;
        Animal Plusleger = null;

        for (Animal animal : Zoo.animaux.values()) {
            if (animal.getPoids() > Pluslourd.getPoids()) {
                Pluslourd = animal;
            } else if (animal.getPoids() < Plusleger.getPoids()) {
                Plusleger = animal;

            }
        }
        System.out.println("L'animal le plus leger est : " + Plusleger.getName());
        System.out.println("L'animal le plus lourd est : " + Pluslourd.getName());
    }
    public static void afficherLeRésuméCompletDuZoo(zoo Zoo,Map<String, Integer> visiteursParAnimal) {
        System.out.println("Résumé complet de tout le zoo !!!!");
        int totalVisiteurs = 0;
        for (int nombredevisiteurs : visiteursParAnimal.values()) {
            totalVisiteurs += nombredevisiteurs;
        }
        System.out.println("Total de visiteurs : " + totalVisiteurs);

        System.out.println("Liste de toute les animaux : ");
        for (Animal animal : Zoo.animaux.values()) {
            System.out.println(animal.getName() + animal.getPoids() + animal.getEspèce());
        }

        calculerPoidsAnimaux(Zoo);

        trouverAnimauxLePLusLegerEtLourd(Zoo);

    }
}




