/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wcy.pracadomowa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author student
 */
public class App {
    
    private void Read(EntityManager dbm, String table){
        String query = table + ".findAll";
        
        
        switch (table){
            case "Uczniowie":
                List<Uczniowie> res1 = dbm.createNamedQuery(query).getResultList();
                for(Uczniowie u : res1){
                    System.out.println(u.getIdUcznia() + ". " + u.getImie() + " " + u.getNazwisko());  
                    }
                break;
            case "Przedmioty":
                List<Przedmioty> res2 = dbm.createNamedQuery(query).getResultList();
                for(Przedmioty p : res2){
                    System.out.println(p.getIdPrzedmiotu() + ". Przedmiot: " + p.getNazwa() + "Uczy: " + p.getNauczyciel());  
                    }
                break;    
            case "Oceny":
                List<Oceny> res3 = dbm.createNamedQuery(query).getResultList();
                for(Oceny o : res3){
                    System.out.println(o.getIdOceny());
                    }
                break;
                
        }
    }
    
    private static String persistanceUnitName = "wcy_PracaDomowa_jar_1.0-SNAPSHOTPU";
    
    public static void main(String[] args) {
        EntityManager dbm = Persistence.createEntityManagerFactory(persistanceUnitName).createEntityManager();
        List<Uczniowie> res1 = dbm.createNamedQuery("Uczniowie.findAll").getResultList();
        for(Uczniowie u : res1){
            System.out.println(u.getIdUcznia() + " ~ " + u.getImie() + " " + u.getNazwisko());
        }
    }
}
