/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wcy.pracadomowa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author student
 */
@Entity
@Table(name = "UCZNIOWIE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Uczniowie.findAll", query = "SELECT u FROM Uczniowie u"),
    @NamedQuery(name = "Uczniowie.findByIdUcznia", query = "SELECT u FROM Uczniowie u WHERE u.idUcznia = :idUcznia"),
    @NamedQuery(name = "Uczniowie.findByImie", query = "SELECT u FROM Uczniowie u WHERE u.imie = :imie"),
    @NamedQuery(name = "Uczniowie.findByNazwisko", query = "SELECT u FROM Uczniowie u WHERE u.nazwisko = :nazwisko")})
public class Uczniowie implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_UCZNIA")
    private Integer idUcznia;
    @Basic(optional = false)
    @Column(name = "IMIE")
    private String imie;
    @Basic(optional = false)
    @Column(name = "NAZWISKO")
    private String nazwisko;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUcznia")
    private Collection<Oceny> ocenyCollection;

    public Uczniowie() {
    }

    public Uczniowie(Integer idUcznia) {
        this.idUcznia = idUcznia;
    }

    public Uczniowie(Integer idUcznia, String imie, String nazwisko) {
        this.idUcznia = idUcznia;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Integer getIdUcznia() {
        return idUcznia;
    }

    public void setIdUcznia(Integer idUcznia) {
        this.idUcznia = idUcznia;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @XmlTransient
    public Collection<Oceny> getOcenyCollection() {
        return ocenyCollection;
    }

    public void setOcenyCollection(Collection<Oceny> ocenyCollection) {
        this.ocenyCollection = ocenyCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUcznia != null ? idUcznia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uczniowie)) {
            return false;
        }
        Uczniowie other = (Uczniowie) object;
        if ((this.idUcznia == null && other.idUcznia != null) || (this.idUcznia != null && !this.idUcznia.equals(other.idUcznia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wcy.pracadomowa.Uczniowie[ idUcznia=" + idUcznia + " ]";
    }
    
}
