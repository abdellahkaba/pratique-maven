package com.isi.maven.services.service;

import com.isi.maven.services.dto.AppRoles;

import java.util.List;

public interface IAppRolesService {
     void deleteAppRoles(int id);
     AppRoles updateAppRoles(int id, AppRoles appRoles);
     AppRoles createAppRoles(AppRoles appRoles);
     AppRoles getAppRole(int id);
     List<AppRoles> getAppRoles();
}
