package com.wework.apiobject;

import com.wework.utils.FakerUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DepartmentObject {
    public Response createDepartment(String createName, String accessToken) {
        String createBody = "{\n" +
                "   \"name\": \"" + createName + "\",\n" +
                "   \"parentid\": 1}";

        Response response = given()
                .contentType("application/json")
                .header("charset", "UTF-8")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then()
                .extract()
                .response();
        return response;
    }

    public Response createDepartment(String accessToken) {
        String createName = "name" + FakerUtils.getTimeStamp();

        String createBody = "{\n" +
                "   \"name\": \"" + createName + "\",\n" +
                "   \"parentid\": 1}";

        Response response = given()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        return response;
    }

    public Response deleteDepartment(String departmentId, String accessToken) {
        Response response = given().log().all()
                .contentType("application/json")
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .log().body()
                .extract().response();
        return response;
    }

    public Response listDepartment(String departmentId, String accessToken) {
        Response response = given()
                .when()
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + accessToken)
                .then()
                .extract()
                .response();
        return response;
    }
}
