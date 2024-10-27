package com.atradius.payments.infrastructure.util;

public final class Constants {


     // Literals for fixed 'responses' of the microservice
    public static final String SERVICE_HEALTH_STATE = "The payment management service is active and running correctly.";
    public static final String SERVICE_HEALTH_HTTP_MESSAGE = "Service health check call was successful.";
    public static final String PAYMENTS_RETRIVED_SUCCESSFULLY = "Total payments retrieved successfully";
    public static final String DEBTS_RETRIVED_SUCCESSFULLY = "Users debts retrieved successfully";
    public static final String PAYMENTS_RETRIVING_ERROR = "Error occurred while retrieving total payments";
    public static final String DEBTS_RETRIVING_ERROR = "Error occurred while retrieving users debts";

    // Info logging execution messages
    public static final String TOTAL_PAYMENTS_ENDPOINT_INVOKED = "The endpoint for retrieving total payments by users has been invoked.";
    public static final String TOTAL_PAYMENTS_ENDPOINT_COMPLETED = "The endpoint for retrieving total payments by users has successfully completed.";
    public static final String DEBTS_ENDPOINT_INVOKED = "The endpoint for retrieving debts by users has been invoked.";
    public static final String DEBTS_ENDPOINT_COMPLETED = "The endpoint for retrieving debts by users has successfully completed.";

    /**
     * Private constructor to prevent instantiation of the class.
     */
    private Constants() {
        throw new UnsupportedOperationException("This is a constants class and should not be instantiated");
    }
}
