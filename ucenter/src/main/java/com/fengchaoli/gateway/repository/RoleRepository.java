package com.fengchaoli.gateway.repository;

import com.fengchaoli.gateway.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface RoleRepository extends JpaRepository<Role, String>, JpaSpecificationExecutor<Role> {
}
