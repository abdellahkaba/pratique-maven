package com.isi.maven.services.mapping;

import com.isi.maven.services.dto.AppRoles;
import com.isi.maven.services.model.AppRolesEntity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper
public interface AppRolesMapper {
    AppRoles toAppRoles(AppRolesEntity appRolesEntity);
    AppRolesEntity fromAppRoles(AppRoles appRoles);
    List<AppRoles> toAppRolesList(List<AppRolesEntity> appRolesEntity);
}
