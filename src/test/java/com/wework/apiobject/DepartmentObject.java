package com.wework.apiobject;

import com.wework.utils.FakerUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class DepartmentObject extends BasePO {
    public static Response createDepartment(String createName, String accessToken) {
        String createBody = "{\n" +
                "   \"name\": \"" + createName + "\",\n" +
                "   \"parentid\": 4}";

        Response response = given()
                .contentType("application/json")
                .header("charset", "UTF-8")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then()
                .extract()
                .response();
        log.i("创建部门名称为: " + createName);
        return response;
    }

    public static String createDepartment(String accessToken) {
        String createName = "name" + FakerUtils.getTimeStamp();

        Response response = createDepartment(createName, accessToken);
        String departmentId = response.path("id") != null ? response.path("id").toString() : null;

        log.i("创建部门id为: " + departmentId);
        return departmentId;
    }

    public static Response updateDepartment(String updateName, String departmentId, String accessToken) {
        String updateBody = "{\n" +
                "   \"id\": " + departmentId + ",\n" +
                "   \"name\": \"" + updateName + "\" \n" +
                "}\n";

        Response response = given()
                .contentType("application/json")
                .body(updateBody)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + accessToken)
                .then()
                .extract()
                .response();
        log.i("更新部门名称为: " + updateName);
        return response;
    }

    public static Response deleteDepartment(String departmentId, String accessToken) {
        Response response = given()
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then()
                .extract()
                .response();
        log.i("删除部门成功");
        return response;
    }

    public static Response listDepartment(String departmentId, String accessToken) {
        Response response = given()
                .when()
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token=" + accessToken)
                .then()
                .extract()
                .response();
        log.i("获取的部门列表为: " + response.path("department.id").toString());
        return response;
    }
}
