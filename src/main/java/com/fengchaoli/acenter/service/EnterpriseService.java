package com.fengchaoli.acenter.service;

import com.fengchaoli.acenter.event.sync.enterprise.EnterpriseSyncEvent;
import com.fengchaoli.acenter.form.EnterpriseForm;
import com.fengchaoli.acenter.model.Enterprise;
import com.fengchaoli.acenter.model.EnterpriseMeta;
import com.fengchaoli.acenter.repository.EnterpriseRepository;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class EnterpriseService {

    @Autowired
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public Enterprise getOne(String id){
        return enterpriseRepository.getOne(id);
    }

    public Enterprise insert(String id, EnterpriseForm enterpriseForm, String clientId){
        Enterprise enterprise = new Enterprise();
        enterprise.setName(enterpriseForm.getName());

        EnterpriseMeta enterpriseMeta = new EnterpriseMeta();
        enterpriseMeta.setClientId(clientId);
        enterpriseMeta.setExtra(enterpriseForm.getExtra());
        enterprise.getEnterpriseMetas().add(enterpriseMeta);

        enterpriseRepository.save(enterprise);
        applicationEventMulticaster.multicastEvent(new EnterpriseSyncEvent(enterprise,clientId));
        return enterprise;
    }

    public Enterprise update(String id,EnterpriseForm enterpriseForm,String clientId){
        Enterprise enterprise = enterpriseRepository.getOne(id);
        EnterpriseMeta enterpriseMeta = enterprise.getEnterpriseMetas().stream().filter(meta ->
                ObjectUtil.equal(clientId,meta.getClientId())).findFirst().get();
        if(enterpriseMeta==null){
            enterpriseMeta = new EnterpriseMeta();
            enterpriseMeta.setClientId(clientId);
            enterpriseMeta.setExtra(enterpriseForm.getExtra());
            enterprise.getEnterpriseMetas().add(enterpriseMeta);
        }else{
            enterpriseMeta.setExtra(enterpriseForm.getExtra());
        }

        enterpriseRepository.save(enterprise);
        applicationEventMulticaster.multicastEvent(new EnterpriseSyncEvent(enterprise,clientId));
        return enterprise;
    }
}