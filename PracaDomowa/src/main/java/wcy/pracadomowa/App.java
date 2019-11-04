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
                List<Uczniowie> res1 = dbm.createNamedQuery(query).getResultList();
                for (Uczniowie u : res1) {
                    System.out.println(u.getIdUcznia() + ". " + u.getImie() + " " + u.getNazwisko());
                }
                break;
            case "Przedmioty":
                List<Przedmioty> res2 = dbm.createNamedQuery(query).getResultList();
                for (Przedmioty p : res2) {
                    System.out.println(p.getIdPrzedmiotu() + ". Przedmiot: " + p.getNazwa() + "Uczy: " + p.getNauczyciel());
                }
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
        //beta testing
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
                    //
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
