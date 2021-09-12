package com.dt.hgw.apiRequestBuilder.createUser;

import com.dt.hgw.apiRequestBuilder.APIInterface;
import com.dt.hgw.enums.api.commons.ResponseHeaders;
import com.dt.hgw.global.APIEndPoints;
import com.dt.hgw.global.LocalConfig;
import com.dt.hgw.model.createUser.CreateUserRequest;
import com.dt.hgw.model.createUser.CreateUserResponse;
import com.dt.hgwqa.autumn.api.BaseApi;
import com.dt.hgwqa.autumn.api.ContentType;
import com.dt.hgwqa.autumn.api.MethodType;
import com.dt.hgwqa.autumn.api.jsonProcessor.JacksonJsonImpl;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.*;

public class CreateUsers extends BaseApi implements APIInterface {

    public static final String DEFAULT_CREATE_USER_REQUEST = "{\"name\":\"morpheus\",\"job\":\"leader\"}";

    private CreateUserRequest createUserRequest;
    private CreateUserResponse createUserResponse;
    private Response response;
    private Map<String, String> headerMap = new HashMap<>();
    private String request;
    Map<String, String> responseHeadersMap = new LinkedHashMap<>();


    @Override
    public void setResponseHeadersMap(List<Header> responseHeaders){
        for(Header header : responseHeaders){
            this.responseHeadersMap.put(header.getName(),header.getValue());
        }
    }

    @Override
    public String getResponseHeader(ResponseHeaders key){
        return responseHeadersMap.get(key.getValue());
    }


    public CreateUsers(String request) {
        setBaseUri(LocalConfig.BaseURL); //setting base URL
        setMethod(MethodType.POST); //setting the method type to execute e.g. GET, POST
        setContentType(ContentType.JSON); //setting the content type application/json
        setBasePath(APIEndPoints.Users.USERS);
        this.request = request;
        if (this.request != "") {
            try {
                createUserRequest = JacksonJsonImpl.getInstance().fromJson(this.request,
                        CreateUserRequest.class);
                System.out.println("------------Creating User------------");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    @Override
    public Map<String, String> getHeaderMap() {
        return this.headerMap;
    }

    public CreateUserRequest getRequestPojo() {
        return createUserRequest;
    }

    public Response getApiResponse() {
        return response;
    }

    public CreateUserResponse getResponsePojo() {
        return createUserResponse;
    }

    public void createRequestJsonAndExecute() {
        try {
            if (this.request != "")
                this.request = JacksonJsonImpl.getInstance().toJSon(createUserRequest); //converting POJO request to JSON
            setBody(request); //Set to builder method
            response = execute();
            setResponseHeadersMap(new ArrayList<>(response.getHeaders().asList()));
            createUserResponse = JacksonJsonImpl.getInstance().fromJson(response.asString(),
                    CreateUserResponse.class); //convert the response to POJO
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

}

