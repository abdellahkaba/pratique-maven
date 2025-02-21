package com.isi.maven.services.service.imp;

import com.isi.maven.services.dto.AppRoles;
import com.isi.maven.services.exception.EntityNotFoundException;
import com.isi.maven.services.mapping.AppRolesMapper;
import com.isi.maven.services.model.AppRolesEntity;
import com.isi.maven.services.repository.IAppRolesRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppRolesServiceImplTest {

    @Mock
    private MessageSource messageSource;
    @Mock
    private AppRolesMapper appRolesMapper;
    @InjectMocks
    private AppRolesServiceImpl appRolesServiceImpl;
    @Mock
    private IAppRolesRepository appRolesRepository;


    @Test
    void getAppRoles() {
        var entityRole = new AppRolesEntity();
        var dtoRole = new AppRoles();

        when(appRolesRepository.findAll()).thenReturn(List.of(entityRole));
        when(appRolesMapper.toAppRoles(entityRole)).thenReturn(dtoRole);

        List<AppRoles> result = appRolesServiceImpl.getAppRoles();

        assertNotNull(result);
    }

    @Test
    void getAppRole_WithValidId_ShouldReturnAppRole() {
        int id = 1;
        var entityRole = new AppRolesEntity();
        var dtoRole = new AppRoles();

        when(appRolesRepository.findById(id)).thenReturn(Optional.of(entityRole));
        when(appRolesMapper.toAppRoles(entityRole)).thenReturn(dtoRole);

        AppRoles result = appRolesServiceImpl.getAppRole(id);

        assertNotNull(result);
        verify(appRolesRepository).findById(id);
    }

    @Test
    void getAppRole_WithInvalidId_ShouldThrowEntityNotFoundException() {
        int id = 9;
        when(appRolesRepository.findById(id)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("role.notfound"), any(), eq(Locale.getDefault())))
                .thenReturn("Role not found");

        assertThrows(EntityNotFoundException.class, () -> appRolesServiceImpl.getAppRole(id));
        verify(appRolesRepository).findById(id);
    }

    @Test
    void updateAppRoles_WithInvalidId_ShouldThrowEntityNotFoundException() {
        int id = 999;
        var dtoRole = new AppRoles();

        when(appRolesRepository.findById(id)).thenReturn(Optional.empty());
        when(messageSource.getMessage(eq("role.notfound"), any(), eq(Locale.getDefault())))
                .thenReturn("Role not found");

        assertThrows(EntityNotFoundException.class, () -> appRolesServiceImpl.updateAppRoles(id, dtoRole));
        verify(appRolesRepository).findById(id);
        verify(appRolesRepository, never()).save(any());
    }

}