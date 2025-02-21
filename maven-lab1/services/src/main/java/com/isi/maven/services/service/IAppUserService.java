package com.isi.maven.services.service;

import com.isi.maven.services.dto.AppUser;

import java.util.List;

public interface IAppUserService {

    List<AppUser> getAllUsers();
    AppUser getOneUser(int id);
    AppUser createAppUser(AppUser appUser);
    AppUser updateAppUser(int id, AppUser appUser);
    void deleteAppUser(int id);
}
