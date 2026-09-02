package com.example.microservice.config;

import com.example.microservice.common.tenant.TenantContext;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;

/**
 * Hibernate CurrentTenantIdentifierResolver bridge that hooks Hibernate's
 * tenant scoping to TenantContext.
 */
@Component
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver<UUID>, HibernatePropertiesCustomizer {

//    @Override
//    public String resolveCurrentTenantIdentifier() {
//        UUID tenantId = TenantContext.getTenantId();
//        return StringUtils.hasText(String.valueOf(tenantId)) ? String.valueOf(tenantId) : TenantContext.DEFAULT_TENANT_ID.toString();
//    }

    @Override
    public UUID resolveCurrentTenantIdentifier() {
        return TenantContext.getTenantId();
    }


    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }

    @Override
    public void customize(Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, this);
    }
}
