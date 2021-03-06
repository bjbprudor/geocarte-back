package fr.m2miage.geocartebck.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departement")
public class Departement implements Serializable
{

	@Id
	private String numero;

	@Column(nullable = false)
    private String nom;

    @OneToMany(mappedBy = "departement")
    private List<Commune> communes;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }

    public Departement() {
    }

    public Departement(String numero, String nom) {
        this.numero = numero;
        this.nom = nom;
    }

}