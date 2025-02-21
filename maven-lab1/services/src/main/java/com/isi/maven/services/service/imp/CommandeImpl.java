package com.isi.maven.services.service.imp;

import com.isi.maven.services.service.Icommande;
import org.springframework.stereotype.Service;

@Service
public class CommandeImpl implements Icommande {
    @Override
    public String getCommande() {
        return "Commande de vehicules " ;
    }

    @Override
    public Integer somme(int a, int b) {
        return a + b;
    }
}
