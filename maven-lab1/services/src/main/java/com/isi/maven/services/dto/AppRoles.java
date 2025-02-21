package com.isi.maven.services.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppRoles {
    private int id;
    @NotNull(message = "Le nom ne doit pas etre null")
    private String nom;

}
