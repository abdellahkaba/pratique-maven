package com.isi.maven.services.service.imp;

import com.isi.maven.services.dto.AppRoles;
import com.isi.maven.services.dto.AppUser;
import com.isi.maven.services.exception.EntityNotFoundException;
import com.isi.maven.services.mapping.AppRolesMapper;
import com.isi.maven.services.mapping.AppUserMapper;
import com.isi.maven.services.model.AppRolesEntity;
import com.isi.maven.services.model.AppUserEntity;
import com.isi.maven.services.repository.IAppRolesRepository;
import com.isi.maven.services.repository.IAppUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {


    @Mock
    private IAppRolesRepository iAppRolesRepository;
    @Mock
    private AppRolesMapper appRolesMapper;
    @Mock
    private IAppUserRepository iAppUserRepository;
    @Mock
    private AppUserMapper appUserMapper;
    @InjectMocks
    AppUserServiceImpl appUserService;
    @Mock
    MessageSource messageSource;

    private final AppUserEntity appUserEntity = new AppUserEntity();
    private final AppUser appUser = new AppUser();
    private final AppRolesEntity appRolesEntity = new AppRolesEntity();
    private final AppRoles appRoles = new AppRoles();


    @Test
    void getAllUsers_ShouldReturnAllMappedUsers() {
        var entityProduit1 = new AppUserEntity();
        var dtoProduit1 = new AppUser();

        when(iAppUserRepository.findAll()).thenReturn(List.of(entityProduit1));
        when(appUserMapper.toAppUser(entityProduit1)).thenReturn(dtoProduit1);

        List<AppUser> result = appUserService.getAllUsers();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(iAppUserRepository).findAll();
    }

    @Test
    void getAppUser_WithValidId_ShouldReturnAppUser() {
        int id = 1;
        var entityUser = new AppUserEntity();
        var dtoUser = new AppUser();

        when(iAppUserRepository.findById(id)).thenReturn(Optional.of(entityUser));
        when(appUserMapper.toAppUser(entityUser)).thenReturn(dtoUser);

        AppUser result = appUserService.getOneUser(id);

        assertNotNull(result);
        verify(iAppUserRepository).findById(id);
    }

    @Test
    void getAppRole_WithInvalidId_ShouldThrowEntityNotFoundException() {
        int id = 9;
        when(iAppUserRepository.findById(id)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("user.notfound"), any(), eq(Locale.getDefault())))
                .thenReturn("User not found");

        assertThrows(EntityNotFoundException.class, () -> appUserService.getOneUser(id));
        verify(iAppUserRepository).findById(id);
    }

    @Test
    void updateAppUser_WithValidId_ShouldReturnUpdatedUser() {
        int id = 1;
        var dtoUser = new AppUser();
        var entityUser = new AppUserEntity();

        when(iAppUserRepository.findById(id)).thenReturn(Optional.of(entityUser));
        when(appUserMapper.fromAppUser(dtoUser)).thenReturn(entityUser);
        when(iAppUserRepository.save(entityUser)).thenReturn(entityUser);
        when(appUserMapper.toAppUser(entityUser)).thenReturn(dtoUser);

        AppUser result = appUserService.updateAppUser(id, dtoUser);

        assertNotNull(result);
        verify(iAppUserRepository).findById(id);
        verify(iAppUserRepository).save(entityUser);
    }

    @Test
    void updateAppUser_WithInvalidId_ShouldThrowEntityNotFoundException() {
        int id = 999;
        var dtoRole = new AppUser();

        when(iAppUserRepository.findById(id)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("user.notfound"), any(), eq(Locale.getDefault())))
                .thenReturn("User not found");

        assertThrows(EntityNotFoundException.class, () -> appUserService.updateAppUser(id, dtoRole));
        verify(iAppUserRepository).findById(id);
        verify(iAppUserRepository, never()).save(any());
    }

}