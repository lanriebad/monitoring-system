package com.monitor.dto;

import java.io.Serializable;


public interface ServiceResponse<T extends Serializable> extends Serializable {

    enum ResponseCode {
        ERROR("99", "Operation Error"), PAYMENT_GATEWAY_NOT_AVAILABLE("301", "Payment Gateway not available"), PENDING("P",
                "Operation Pending"), SERVICE_NOT_AVAILABLE("302",
                        "Service not available"), SUCCESS("00", "Operation Successful"), USER_ALREADY_EXISTS("307", "User Already Exits");

        protected String code;

        protected String defaultMessage;


        ResponseCode(String code, String defaultMessage) {
            this.code = code;
            this.defaultMessage = defaultMessage;
        }


        public String getCode() {
            return code;
        }


        public String getDefaultMessage() {
            return defaultMessage;
        }


        @Override
        public String toString() {
            return getCode();
        }
    }


    String getResponseCode();


    String getResponseMsg();


    void setResponseCode(String responseCode);


    void setResponseMsg(String responseMsg);
}
