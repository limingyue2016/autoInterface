package com.wework.apiobject;

import com.wework.utils.FakerUtils;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserObject extends BasePO{
    public static Response createUser(String userid, String name, String mobile, String accessToken) {
        String createBody = "{\n" +
                "   \"userid\": \"" + userid + "\",\n" +
                "   \"name\": \"" + name + "\",\n" +
                "   \"mobile\": \"" + mobile + "\",\n" +
                "   \"department\": 4}";

        Response response = given()
                .contentType("application/json")
                .header("charset", "UTF-8")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token=" + accessToken)
                .then()
                .extract()
                .response();
        log.i("创建成员名称为: " + name);
        return response;
    }

    public static String createUser(String accessToken) {
        String userid = FakerUtils.getTimeStamp();
        String name = userid;
        String mobile = FakerUtils.getTel();

        createUser(userid, name, mobile, accessToken);
        log.i("创建成员名称为: " + name);
        return userid;
    }

    public static Response updateUser(String updateName, String userid, String accessToken) {
        String updateBody = "{\n" +
                "   \"userid\": " + userid + ",\n" +
                "   \"name\": " + updateName + ",\n" +
                "}\n";

        Response response = given()
                .contentType("application/json")
                .body(updateBody)
                .when()
                .post("https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token=" + accessToken)
                .then()
                .extract()
                .response();
        log.i("更新成员名称为: " + updateName);
        return response;
    }

    public static Response getUser(String userid, String accessToken) {
        Response response = given()
                .when()
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken + "&userid=" + userid)
                .then()
                .extract()
                .response();
        log.i("获取成员信息为: " + response.toString());
        return response;
    }

    public static Response deleteUser(String userid, String accessToken) {
        Response response = given()
                .param("access_token", accessToken)
                .param("userid", userid)
                .get("https://qyapi.weixin.qq.com/cgi-bin/user/delete")
                .then()
                .extract()
                .response();
        log.i("删除成员成功");
        return response;
    }
}
