/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wcy.pracadomowa;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author student
 */
public class App {

    public static void tableRead(EntityManager dbm, String table) {
        String query = table + ".findAll";

        switch (table) {
            case "Uczniowie":
                System.out.println("~~~~ UCZNIOWIE ~~~\n");
                List<Uczniowie> res1 = dbm.createNamedQuery(query).getResultList();
                for (Uczniowie u : res1) {
                    System.out.println(u.getIdUcznia() + ". " + u.getImie() + " " + u.getNazwisko());
                }
                System.out.println("\n~~~~~~~~~~~~~~~~\n");
                break;
            case "Przedmioty":
                List<Przedmioty> res2 = dbm.createNamedQuery(query).getResultList();
                System.out.println("~~~~ PRZEDMIOTY ~~~\n");
                for (Przedmioty p : res2) {
                    System.out.println(p.getIdPrzedmiotu() + ". Przedmiot: " + p.getNazwa() + " Uczy: " + p.getNauczyciel());
                }
                System.out.println("\n~~~~~~~~~~~~~~~~~\n");
                break;
            case "Oceny":
                List<Oceny> res3 = dbm.createNamedQuery(query).getResultList();
                System.out.println("~~~~ OCENY ~~~\n");
                for (Oceny o : res3) {
                    System.out.println("ID Oceny: " + o.getIdOceny() + " Przedmiot: " + o.getIdPrzedmiotu().getNazwa()
                            + " Otrzymal: " + o.getIdUcznia().getNazwisko() + " " + o.getIdUcznia().getImie() + " Ocena: " + o.getOcena());
                }
                System.out.println("\n~~~~~~~~~~~~~~\n");
                break;
        }
    }

    public static void tableCreate(Scanner sc, EntityManager dbm, String table) {
        switch (table) {
            case "Uczniowie":
                createUczniowie(sc, dbm);
                break;
            case "Przedmioty":
                createPrzedmioty(sc, dbm);
                break;
            case "Oceny":
                createOceny(sc, dbm);
                break;
        }
    }

    public static void createUczniowie(Scanner sc, EntityManager dbm) {
        List<Uczniowie> resc1 = dbm.createNamedQuery("Uczniowie.findAll").getResultList();
        int newIdUcznia = resc1.get(resc1.size() - 1).getIdUcznia() + 1;
        String newImie;
        String newNazwisko;

        do {
            System.out.print("Podaj imie ucznia: ");
            newImie = sc.nextLine();
        } while (newImie.length() > 50 || newImie.length() < 3);

        do {
            System.out.print("Podaj nazwisko ucznia: ");
            newNazwisko = sc.nextLine();
        } while (newNazwisko.length() > 50 || newNazwisko.length() < 3);

        Uczniowie uczen = new Uczniowie(newIdUcznia, newImie, newNazwisko);
        dbm.getTransaction().begin();
        dbm.persist(uczen);
        dbm.getTransaction().commit();
        System.out.println("Dodano ucznia!\n");

    }

    public static void createPrzedmioty(Scanner sc, EntityManager dbm) {
        List<Przedmioty> resc2 = dbm.createNamedQuery("Przedmioty.findAll").getResultList();
        int newIdPrzedmiotu = resc2.get(resc2.size() - 1).getIdPrzedmiotu() + 1;
        String newNazwa;
        String newNauczyciel;
        boolean alreadyExist = false;
        do {
            System.out.print("Podaj nazwe przedmiotu: ");
            newNazwa = sc.nextLine();
        } while (newNazwa.length() > 50 || newNazwa.length() < 3);

        do {
            System.out.print("Podaj nazwisko nauczyciela: ");
            newNauczyciel = sc.nextLine();
        } while (newNauczyciel.length() > 50 || newNauczyciel.length() < 3);

        for (Przedmioty p : resc2) {
            if (p.getNazwa().equals(newNazwa) && p.getNauczyciel().equals(newNauczyciel)) {
                alreadyExist = true;
            }
        }
        if (alreadyExist) {
            System.out.println("Taki rekord juz istnieje!");
        } else {
            Przedmioty przedmiot = new Przedmioty(newIdPrzedmiotu, newNazwa, newNauczyciel);
            dbm.getTransaction().begin();
            dbm.persist(przedmiot);
            dbm.getTransaction().commit();
            System.out.println("Dodano ucznia!\n");

        }
    }

    public static void createOceny(Scanner sc, EntityManager dbm) {
        List<Uczniowie> resc1 = dbm.createNamedQuery("Uczniowie.findAll").getResultList();
        List<Przedmioty> resc2 = dbm.createNamedQuery("Przedmioty.findAll").getResultList();
        List<Oceny> resc3 = dbm.createNamedQuery("Oceny.findAll").getResultList();
        int newIdOceny = resc3.get(resc3.size() - 1).getIdOceny() + 1;
        int newIdPrzedmiotu, newIdUcznia, newOcena;
        boolean goFlag = true;
        if (resc2.isEmpty()) {
            System.out.println("Lista przedmiotow jest pusta!");
        } else if(resc1.isEmpty()) {
            System.out.println("Lista uczniow jest pusta!");
        } else {
            do {
                for (Przedmioty p : resc2) {
                    System.out.println(p.getIdPrzedmiotu() + ". " + p.getNazwa());
                }
                System.out.print("Wybierz numer przedmiotu z dostepnej powyzej listy: ");
                newIdPrzedmiotu = sc.nextInt();
                
                for (Przedmioty p : resc2) {
                    if(p.getIdPrzedmiotu() == newIdPrzedmiotu){
                        goFlag=false;
                    }
                }
                
            } while (goFlag);
            
            goFlag=true;
            
            do {
                for (Uczniowie u: resc1) {
                    System.out.println(u.getIdUcznia() + ". " + u.getImie() + " " + u.getNazwisko());
                }
                System.out.print("Wybierz numer ucznia z dostepnej powyzej listy: ");
                newIdUcznia = sc.nextInt();
                for (Uczniowie u: resc1) {
                    if(u.getIdUcznia()==newIdUcznia){
                        goFlag = false;
                    }
                }
                
            } while (goFlag);
            
            do{
                System.out.print("Wybierz ocene (od 2 do 5):");
                newOcena = sc.nextInt();
            }
            while(newOcena>5 || newOcena<2);
            
            Oceny ocena = new Oceny(newIdOceny, newOcena);
            for(Przedmioty p : resc2){
                if(p.getIdPrzedmiotu() == newIdPrzedmiotu){
                    ocena.setIdPrzedmiotu(p);
                }
            }
            for(Uczniowie u : resc1){
                if(u.getIdUcznia()==newIdUcznia){
                    ocena.setIdUcznia(u);
                }
            }
            dbm.getTransaction().begin();
            dbm.persist(ocena);
            dbm.getTransaction().commit();
            System.out.println("Dodano ocene!");
        }

    }

    public static void menuCreate(Scanner sc, EntityManager dbm) {
        System.out.println("Do jakiej tabeli chcesz utworzyc rekord?");
        System.out.println("~~~~");
        System.out.println("1. Uczniowie\n2. Przedmioty\n3. Oceny");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                tableCreate(sc, dbm, "Uczniowie");
                break;
            case 2:
                tableCreate(sc, dbm, "Przedmioty");
                break;
            case 3:
                tableCreate(sc, dbm, "Oceny");
                break;
            default:
                stopFlag = false;
                break;
        }
    }

    public static void menuRead(Scanner sc, EntityManager dbm) {
        System.out.println("Jaka tabele chcesz ogladac?");
        System.out.println("~~~~");
        System.out.println("1. Uczniowie\n2. Przedmioty\n3. Oceny");

        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                tableRead(dbm, "Uczniowie");
                break;
            case 2:
                tableRead(dbm, "Przedmioty");
                break;
            case 3:
                tableRead(dbm, "Oceny");
                break;
            default:
                stopFlag = false;
                break;
        }
    }

    public static void clearScreen() {
        //beta testing, try not to use this
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String persistanceUnitName = "wcy_PracaDomowa_jar_1.0-SNAPSHOTPU";

    public static boolean stopFlag;

    public static void main(String[] args) {
        EntityManager dbm = Persistence.createEntityManagerFactory(persistanceUnitName).createEntityManager();
        Scanner sc = new Scanner(System.in);
        stopFlag = true;
        while (stopFlag) {
            System.out.println("Co chcesz zrobic?");
            System.out.println("~~~~");
            System.out.println("1. Create\n2. Read\n3. Update\n4. Delete");
            int crudChoice = sc.nextInt();
            switch (crudChoice) {
                case 1:
                    menuCreate(sc, dbm);
                    break;
                case 2:
                    menuRead(sc, dbm);
                    break;
                case 3:

                    break;
                case 4:

                    break;
                default:
                    stopFlag = false;
                    break;
            }
        }
        //shutting down...
    }
}
