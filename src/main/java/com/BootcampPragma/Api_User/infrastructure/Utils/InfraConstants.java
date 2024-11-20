package com.BootcampPragma.Api_User.infrastructure.Utils;


public class InfraConstants {

    public static final String ROLE = "ROLE_";
    public static final String AUTH_ROLE = "role";
    public static final String SPRING = "Spring";
    public static final String BEARER = "Bearer ";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    public static final String ID_ROLE = "id_role";

    public static final String ROLE_AUX_BODEGA = "AUX_BODEGA";

    public static final String AUX = "/register/aux";
    public static final String REGISTER_ADMIN = "/register/admin";
    public static final String AUTH = "/auth";
    public static final String LOGIN = "/login";
    public static final String ADMIN = "/admin";
    public static final String REGISTER = "/register";
    public static final String USER = "/{id}";
    public static final String ASTERISKS = "/**";

    public static String getPath(String basePath, String path) {
        return basePath + path;
    }


    public InfraConstants() {
        throw new UnsupportedOperationException("This is a constants class and cannot be instantiated.");

    }
}
