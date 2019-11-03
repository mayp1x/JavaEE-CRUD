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
@Table(name = "PRZEDMIOTY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Przedmioty.findAll", query = "SELECT p FROM Przedmioty p"),
    @NamedQuery(name = "Przedmioty.findByIdPrzedmiotu", query = "SELECT p FROM Przedmioty p WHERE p.idPrzedmiotu = :idPrzedmiotu"),
    @NamedQuery(name = "Przedmioty.findByNazwa", query = "SELECT p FROM Przedmioty p WHERE p.nazwa = :nazwa"),
    @NamedQuery(name = "Przedmioty.findByNauczyciel", query = "SELECT p FROM Przedmioty p WHERE p.nauczyciel = :nauczyciel")})
public class Przedmioty implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PRZEDMIOTU")
    private Integer idPrzedmiotu;
    @Basic(optional = false)
    @Column(name = "NAZWA")
    private String nazwa;
    @Basic(optional = false)
    @Column(name = "NAUCZYCIEL")
    private String nauczyciel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrzedmiotu")
    private Collection<Oceny> ocenyCollection;

    public Przedmioty() {
    }

    public Przedmioty(Integer idPrzedmiotu) {
        this.idPrzedmiotu = idPrzedmiotu;
    }

    public Przedmioty(Integer idPrzedmiotu, String nazwa, String nauczyciel) {
        this.idPrzedmiotu = idPrzedmiotu;
        this.nazwa = nazwa;
        this.nauczyciel = nauczyciel;
    }

    public Integer getIdPrzedmiotu() {
        return idPrzedmiotu;
    }

    public void setIdPrzedmiotu(Integer idPrzedmiotu) {
        this.idPrzedmiotu = idPrzedmiotu;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNauczyciel() {
        return nauczyciel;
    }

    public void setNauczyciel(String nauczyciel) {
        this.nauczyciel = nauczyciel;
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
        hash += (idPrzedmiotu != null ? idPrzedmiotu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Przedmioty)) {
            return false;
        }
        Przedmioty other = (Przedmioty) object;
        if ((this.idPrzedmiotu == null && other.idPrzedmiotu != null) || (this.idPrzedmiotu != null && !this.idPrzedmiotu.equals(other.idPrzedmiotu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wcy.pracadomowa.Przedmioty[ idPrzedmiotu=" + idPrzedmiotu + " ]";
    }
    
}
