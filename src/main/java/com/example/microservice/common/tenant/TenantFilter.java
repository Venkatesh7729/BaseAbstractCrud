package com.example.microservice.common.tenant;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

/**
 * Filter that extracts the tenant identifier from HTTP request headers
 * and populates the TenantContext for the lifecycle of the request.
 */

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TenantFilter extends OncePerRequestFilter {

    private static final Logger log =
            LoggerFactory.getLogger(TenantFilter.class);

    @Value("${app.tenant.header-name:X-Tenant-ID}")
    private String tenantHeaderName;

    @Value("${app.tenant.default-tenant:00000000-0000-0000-0000-000000000001}")
    private String defaultTenantId;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {

            String tenantHeader = request.getHeader(tenantHeaderName);

            UUID tenantId;

            if (StringUtils.hasText(tenantHeader)) {

                try {
                    tenantId = UUID.fromString(tenantHeader.trim());
                } catch (IllegalArgumentException ex) {

                    log.warn("Invalid tenant ID: {}", tenantHeader);

                    response.sendError(
                            HttpServletResponse.SC_BAD_REQUEST,
                            "Invalid X-Tenant-ID"
                    );

                    return;
                }

                log.trace(
                        "Resolved tenant '{}' from header '{}'",
                        tenantId,
                        tenantHeaderName
                );

            } else {

                tenantId = UUID.fromString(defaultTenantId);

                log.trace(
                        "No tenant header found. Using default tenant '{}'",
                        tenantId
                );
            }

            TenantContext.setTenantId(tenantId);

            filterChain.doFilter(request, response);

        } finally {

            TenantContext.clear();
        }
    }
}