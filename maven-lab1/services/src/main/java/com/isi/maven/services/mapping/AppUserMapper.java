package com.isi.maven.services.mapping;

import com.isi.maven.services.dto.AppUser;
import com.isi.maven.services.model.AppUserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface AppUserMapper {
    AppUser toAppUser(AppUserEntity appUserEntity);
    AppUserEntity fromAppUser(AppUser appUser);
}
