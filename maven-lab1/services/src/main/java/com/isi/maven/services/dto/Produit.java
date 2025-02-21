package com.isi.maven.services.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produit {
    private int id;

    @NotBlank(message = "Le nom est requis.")
    private String nom;

    @NotNull(message = "La quantité en stock est requise.")
    @Min(value = 0, message = "La quantité ne peut pas être négative")
    private double qtStock;

    @NotNull(message = "Le user est requis.")
    private int appUserId;

}
