package com.isi.maven.services.service.imp;

import com.isi.maven.services.dto.AppRoles;
import com.isi.maven.services.dto.AppUser;
import com.isi.maven.services.exception.EntityExistsException;
import com.isi.maven.services.exception.EntityNotFoundException;
import com.isi.maven.services.exception.RequestException;
import com.isi.maven.services.mapping.AppRolesMapper;
import com.isi.maven.services.mapping.AppUserMapper;
import com.isi.maven.services.model.AppRolesEntity;
import com.isi.maven.services.model.AppUserEntity;
import com.isi.maven.services.repository.IAppRolesRepository;
import com.isi.maven.services.repository.IAppUserRepository;
import com.isi.maven.services.service.IAppUserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Getter
@Setter
@AllArgsConstructor
public class AppUserServiceImpl implements IAppUserService {

    private final IAppUserRepository appUserRepository;
    private final IAppRolesRepository appRolesRepository;
    private final AppUserMapper appUserMapper;
    private final AppRolesMapper appRolesMapper;
    private final MessageSource messageSource;

    @Transactional
    @Override
    public List<AppUser> getAllUsers() {
        return StreamSupport.stream(appUserRepository.findAll().spliterator(), false)
                .map(appUserMapper::toAppUser)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public AppUser getOneUser(int id) {
        return appUserMapper.toAppUser(appUserRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    @Override
    public AppUser createAppUser(AppUser appUser) {
        if (appUserRepository.findByEmail(appUser.getEmail()).isPresent()) {
            throw new EntityExistsException(
                    messageSource.getMessage("user.exists",
                            new Object[]{appUser.getEmail()},
                            Locale.getDefault())
            );
        }
        List<AppRolesEntity> roles = new ArrayList<>();
        for (AppRoles roleDto : appUser.getAppRoleEntities() ) {
            AppRolesEntity role = appRolesRepository.findById(roleDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            messageSource.getMessage("role.notfound", new Object[]{roleDto.getId()}, Locale.getDefault())));
            roles.add(role);
        }
        appUser.setAppRoleEntities(appRolesMapper.toAppRolesList(roles));

        AppUserEntity savedUser = appUserRepository.save(appUserMapper.fromAppUser(appUser));

        return appUserMapper.toAppUser(savedUser);
    }

    @Transactional
    @Override
    public AppUser updateAppUser(int id, AppUser appUser) {
        return appUserRepository.findById(id)
                .map(entity -> {
                    appUser.setId(id);
                    return appUserMapper.toAppUser(
                            appUserRepository.save(appUserMapper.fromAppUser(appUser)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("user.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    @Override
    public void deleteAppUser(int id) {

        getOneUser(id);
        try {
            appUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("user.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }

    }
}
