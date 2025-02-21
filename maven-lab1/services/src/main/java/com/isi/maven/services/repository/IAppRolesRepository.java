package com.isi.maven.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.isi.maven.services.model.AppRolesEntity;
import org.springframework.stereotype.Repository;


@Repository
public interface IAppRolesRepository extends JpaRepository<AppRolesEntity, Integer> {
}
