package com.isi.maven.web;

import com.isi.maven.services.dto.Produit;
import com.isi.maven.services.service.IProduitService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
@AllArgsConstructor
@Getter
@Setter
public class ProductController {

    private IProduitService produitService;

    @GetMapping
    public List<Produit> getProduit() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable("id") int id) {
        return produitService.getOneProduit(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Produit createProduit(@Valid @RequestBody Produit produit) {
        return produitService.createProduit(produit);
    }

    @PutMapping("/{id}")
    public Produit updateProduit(@PathVariable("id") int id, @Valid @RequestBody Produit produit) {
        return produitService.updateProduit(id, produit);
    }

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable("id") int id) {
        produitService.deleteProduit(id);
    }
}
