package com.isi.maven.services.mapping;

import com.isi.maven.services.dto.Produit;
import com.isi.maven.services.model.ProduitEntity;
import org.mapstruct.Mapper;

@Mapper
public interface ProduitMapper {
    Produit toProduit(ProduitEntity produitEntity);
    ProduitEntity fromProduit(Produit produit);
}
