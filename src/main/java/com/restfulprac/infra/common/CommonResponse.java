package com.restfulprac.infra.common;

import lombok.Data;

@Data
public class CommonResponse {

    private String statusCode;
    private boolean success;
    private String version;
    private Object data;

    public static CommonResponse createResponse(String statusCode, boolean success, String version, Object data) {
        CommonResponse response = new CommonResponse();
        response.setStatusCode(statusCode);
        response.setSuccess(success);
        response.setVersion(version);
        response.setData(data);
        return response;
    }
}
