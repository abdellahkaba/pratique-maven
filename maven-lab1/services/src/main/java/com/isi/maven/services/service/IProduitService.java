package com.isi.maven.services.service;

import com.isi.maven.services.dto.Produit;

import java.util.List;

public interface IProduitService {

    List<Produit> getAllProduits();
    Produit getOneProduit(int id);
    Produit createProduit(Produit produit);
    Produit updateProduit(int id, Produit produit);
    void deleteProduit(int id);
}
