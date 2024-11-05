package com.BootcampPragma.Api_User.domain.model;

public enum RoleEnum {
    ADMIN,
    USER,
    WAREHOUSE_AUX,
    CLIENT;

    public static boolean contains(String role) {
        for (RoleEnum r : values()) {
            if (r.name().equalsIgnoreCase(role)) {
                return true;
            }
        }
        return false;
    }

}
