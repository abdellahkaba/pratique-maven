package com.isi.maven.web;


import com.isi.maven.services.dto.AppUser;
import com.isi.maven.services.service.IAppUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("appUser")
@AllArgsConstructor
@Getter
@Setter
public class AppUserController {

    private IAppUserService appUserService;

    @GetMapping
    public List<AppUser> getAppUser() {
        return appUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public AppUser getAppUser(@PathVariable("id") int id) {
        return appUserService.getOneUser(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public AppUser createAppUser(@Valid @RequestBody AppUser appUser) {
        return appUserService.createAppUser(appUser);
    }

    @PutMapping("/{id}")
    public AppUser updateAppUser(@PathVariable("id") int id, @Valid @RequestBody AppUser appUser) {
        return appUserService.updateAppUser(id, appUser);
    }

    @DeleteMapping("/{id}")
    public void deleteAppUser(@PathVariable("id") int id) {
        appUserService.deleteAppUser(id);
    }
}
