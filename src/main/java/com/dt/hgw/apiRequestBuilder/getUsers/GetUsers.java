package com.dt.hgw.apiRequestBuilder.getUsers;

import com.dt.hgw.apiRequestBuilder.APIInterface;
import com.dt.hgw.enums.api.commons.ResponseHeaders;
import com.dt.hgw.global.APIEndPoints;
import com.dt.hgw.global.LocalConfig;
import com.dt.hgw.model.getUsers.GetUserResponse;
import com.dt.hgwqa.autumn.api.BaseApi;
import com.dt.hgwqa.autumn.api.ContentType;
import com.dt.hgwqa.autumn.api.MethodType;
import com.dt.hgwqa.autumn.api.jsonProcessor.JacksonJsonImpl;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.*;

public class GetUsers extends BaseApi implements APIInterface {


    private GetUserResponse getUserResponse;
    private Response response;
    private Map<String, String> headerMap = new HashMap<>();
    private String request;
    private String productId;
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


    public GetUsers(String pageNumber) {
        setBaseUri(LocalConfig.BaseURL); //setting base URL
        setMethod(MethodType.GET); //setting the method type to execute e.g. GET, POST
        setContentType(ContentType.JSON); //setting the content type application/json
        setBasePath(APIEndPoints.Users.USERS);
        addQueryParam("page",pageNumber);
        System.out.println("------------Get Users ------------");

    }


    @Override
    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    @Override
    public Map<String, String> getHeaderMap() {
        return this.headerMap;
    }


    public Response getApiResponse() {
        return response;
    }

    @Override
    public RequestPojo getRequestPojo() {
        return null;
    }

    public GetUserResponse getResponsePojo() {
        return getUserResponse;
    }

    public void createRequestJsonAndExecute() {
        try {
            addHeaders(headerMap); //adding the headers
            response = execute();
            setResponseHeadersMap(new ArrayList<>(response.getHeaders().asList()));
            getUserResponse = JacksonJsonImpl.getInstance().fromJson(response.asString(),
                    GetUserResponse.class); //convert the response to POJO
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

}

