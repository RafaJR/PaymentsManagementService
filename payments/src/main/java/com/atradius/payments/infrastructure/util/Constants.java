package com.atradius.payments.infrastructure.util;

public final class Constants {

    /**
     * Literals for fixed 'responses' of the microservice
     */
    // Service up message
    public static final String SERVICE_HEALTH_STATE = "The payment management service is active and running correctly.";
    public static final String SERVICE_HEALTH_HTTP_MESSAGE = "Service health check call was successful.";

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private Constants() {
        throw new UnsupportedOperationException("This is a constants class and should not be instantiated");
    }
}
