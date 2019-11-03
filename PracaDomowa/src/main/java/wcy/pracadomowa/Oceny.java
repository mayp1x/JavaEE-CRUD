/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wcy.pracadomowa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@Entity
@Table(name = "OCENY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Oceny.findAll", query = "SELECT o FROM Oceny o"),
    @NamedQuery(name = "Oceny.findByIdOceny", query = "SELECT o FROM Oceny o WHERE o.idOceny = :idOceny"),
    @NamedQuery(name = "Oceny.findByOcena", query = "SELECT o FROM Oceny o WHERE o.ocena = :ocena")})
public class Oceny implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID_OCENY")
    private Integer idOceny;
    @Basic(optional = false)
    @Column(name = "OCENA")
    private int ocena;
    @JoinColumn(name = "ID_PRZEDMIOTU", referencedColumnName = "ID_PRZEDMIOTU")
    @ManyToOne(optional = false)
    private Przedmioty idPrzedmiotu;
    @JoinColumn(name = "ID_UCZNIA", referencedColumnName = "ID_UCZNIA")
    @ManyToOne(optional = false)
    private Uczniowie idUcznia;

    public Oceny() {
    }

    public Oceny(Integer idOceny) {
        this.idOceny = idOceny;
    }

    public Oceny(Integer idOceny, int ocena) {
        this.idOceny = idOceny;
        this.ocena = ocena;
    }

    public Integer getIdOceny() {
        return idOceny;
    }

    public void setIdOceny(Integer idOceny) {
        this.idOceny = idOceny;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public Przedmioty getIdPrzedmiotu() {
        return idPrzedmiotu;
    }

    public void setIdPrzedmiotu(Przedmioty idPrzedmiotu) {
        this.idPrzedmiotu = idPrzedmiotu;
    }

    public Uczniowie getIdUcznia() {
        return idUcznia;
    }

    public void setIdUcznia(Uczniowie idUcznia) {
        this.idUcznia = idUcznia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOceny != null ? idOceny.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Oceny)) {
            return false;
        }
        Oceny other = (Oceny) object;
        if ((this.idOceny == null && other.idOceny != null) || (this.idOceny != null && !this.idOceny.equals(other.idOceny))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "wcy.pracadomowa.Oceny[ idOceny=" + idOceny + " ]";
    }
    
}
