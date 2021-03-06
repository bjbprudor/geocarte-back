package fr.m2miage.geocartebck.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ancienNom")
public class AncienNom implements Serializable
{

    @EmbeddedId
    private AncienNomId id;

    @Column
    private String article;

    @Column(nullable = false)
    private String nom;

    public AncienNomId getId() {
        return id;
    }

    public void setId(AncienNomId id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public AncienNom() {
    }

    public AncienNom(AncienNomId id, String article, String nom)
    {
        this.id = id;
        this.article = article;
        this.nom = nom;
    }

}