package fr.m2miage.geocartebck.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String nom;

	@Column(nullable = false)
    private String email;

	@Column(nullable = false)
    private String motdepasse;

	@OneToMany(mappedBy = "id.utilisateur")
    private List<CarteUtilisateur> carteUtilisateurs;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public List<CarteUtilisateur> getCarteUtilisateurs() {
        return carteUtilisateurs;
    }

    public void setCarteUtilisateurs(List<CarteUtilisateur> carteUtilisateurs) {
        this.carteUtilisateurs = carteUtilisateurs;
    }

    public Utilisateur() {
    }

    public Utilisateur(String nom, String email, String motdepasse) {
        this.nom = nom;
        this.email = email;
        this.motdepasse = motdepasse;
    }

}