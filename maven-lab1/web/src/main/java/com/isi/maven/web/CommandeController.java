package com.isi.maven.web;


import com.isi.maven.services.service.Icommande;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommandeController {

    private final Icommande commande;
    @GetMapping("/commande")

    public String commandeController(){
        return commande.getCommande();
    }
    @GetMapping("somme")
    public Integer sommeController(){
        return commande.somme(5,6);
    }


}
