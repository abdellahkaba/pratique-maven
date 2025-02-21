package com.isi.maven.services.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    private int id;
    @NotNull(message = "Le nom ne doit pas etre null")
    private String nom;
    @NotNull(message = "Le prenom ne doit pas etre null")
    private String prenom;
    @NotNull(message = "L'email ne doit pas etre null")
    private String email;
    @NotNull(message = "Le mot de passe ne doit pas etre null")
    private String password;

    private List<AppRoles> appRoleEntities;
    @NotNull
    private int etat;

}
