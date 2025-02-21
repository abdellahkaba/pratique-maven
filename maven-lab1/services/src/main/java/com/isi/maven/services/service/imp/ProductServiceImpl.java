package com.isi.maven.services.service.imp;

import com.isi.maven.services.dto.Produit;
import com.isi.maven.services.exception.EntityExistsException;
import com.isi.maven.services.exception.EntityNotFoundException;
import com.isi.maven.services.exception.RequestException;
import com.isi.maven.services.mapping.ProduitMapper;
import com.isi.maven.services.model.AppUserEntity;
import com.isi.maven.services.model.ProduitEntity;
import com.isi.maven.services.repository.IAppUserRepository;
import com.isi.maven.services.repository.IProduitRepository;
import com.isi.maven.services.service.IProduitService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProduitService {

    private final IProduitRepository repository;
    private final IAppUserRepository iAppUserRepository;
    private final ProduitMapper produitMapper;
    private final MessageSource messageSource;
    @Override
    public List<Produit> getAllProduits() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(produitMapper::toProduit)
                .collect(Collectors.toList());
    }

    @Override
    public Produit getOneProduit(int id) {
        return produitMapper.toProduit(repository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("produit.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Override
    public Produit createProduit(Produit produit) {
        if (repository.findByNom((produit.getNom())).isPresent()) {
            throw new EntityExistsException(
                    messageSource.getMessage("produit.exists",
                            new Object[]{produit.getNom()},
                            Locale.getDefault())
            );
        }
        AppUserEntity user = iAppUserRepository.findById(produit.getAppUserId())
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("user.notfound", new Object[]{produit.getAppUserId()}, Locale.getDefault())));

        ProduitEntity savedProduit = produitMapper.fromProduit(produit);
        savedProduit.setAppUserEntity(user);
        savedProduit = repository.save(savedProduit);

        return produitMapper.toProduit(savedProduit);
    }

    @Override
    public Produit updateProduit(int id, Produit produit) {
        return repository.findById(id)
                .map(entity -> {
                    produit.setId(id);
                    return produitMapper.toProduit(
                            repository.save(produitMapper.fromProduit(produit)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("produit.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Override
    public void deleteProduit(int id) {

        getOneProduit(id);
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("produit.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }

    }
}
