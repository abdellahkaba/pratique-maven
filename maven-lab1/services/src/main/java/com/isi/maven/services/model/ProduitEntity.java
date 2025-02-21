package com.isi.maven.services.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProduitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, length = 200)
    private String nom;
    @Column(nullable = false)
    private double qtStock;
    @ManyToOne
    private AppUserEntity appUserEntity;
}