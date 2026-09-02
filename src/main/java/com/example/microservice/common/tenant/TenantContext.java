package com.example.microservice.common.tenant;

import java.util.UUID;

/**
 * Thread-safe context holder for the current tenant identifier.
 */
public final class TenantContext {

//    public static final String DEFAULT_TENANT_ID = "default";
    public static final UUID DEFAULT_TENANT_ID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    public static final String TENANT_HEADER = "X-Tenant-ID";

//    private static final ThreadLocal<String> CURRENT_TENANT = new InheritableThreadLocal<>();
    private static final ThreadLocal<UUID> CURRENT_TENANT = new InheritableThreadLocal<>();

    private TenantContext() {
        // Utility class
    }

//    public static String getTenantId() {
//        String tenantId = CURRENT_TENANT.get();
//        return (tenantId != null && !tenantId.isBlank()) ? tenantId : DEFAULT_TENANT_ID;
//    }

    public static UUID getTenantId() {
        UUID tenantId = CURRENT_TENANT.get();

        return tenantId != null
                ? tenantId
                : DEFAULT_TENANT_ID;
    }

//    public static void setTenantId(String tenantId) {
//        if (tenantId != null && !tenantId.isBlank()) {
//            CURRENT_TENANT.set(tenantId.trim());
//        } else {
//            CURRENT_TENANT.set(DEFAULT_TENANT_ID);
//        }
//    }

    public static void setTenantId(UUID tenantId) {
        CURRENT_TENANT.set(
                tenantId != null ? tenantId : DEFAULT_TENANT_ID
        );
    }

    public static void clear() {
        CURRENT_TENANT.remove();
    }
}
