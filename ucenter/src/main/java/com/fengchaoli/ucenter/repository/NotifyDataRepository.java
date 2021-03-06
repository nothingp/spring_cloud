package com.fengchaoli.ucenter.repository;

import com.fengchaoli.ucenter.model.NotifyData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource
public interface NotifyDataRepository extends JpaRepository<NotifyData, String>, JpaSpecificationExecutor<NotifyData> {

}
