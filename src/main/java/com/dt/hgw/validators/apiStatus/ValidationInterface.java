package com.dt.hgw.validators.apiStatus;

public interface ValidationInterface {

    int getHttpResponseCode();
    Object getErrorCode();
    String getErrorMessages();
    String getMessage();
     interface DetailErrorsInterface{
        String getErrorDetailField();
        String getErrorDetailMessage();
    }

}
