package com.wework.apiobject;

import static io.restassured.RestAssured.given;

public class TokenHelper {
    private static String corpid = "wwc0d2037957675e5c";
    private static String corpsecret = "wmdXrWkyzXbTVSloLsyKXvxn7fBBujj6c-TTpEIv064";
    private static String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken";

    public static String getAccessToken() {
        String access_token =
                given()
                        .when()
                        .param("corpid", corpid)
                        .param("corpsecret", corpsecret)
                        .when()
                        .get(url)
                        .then()
                        .extract()
                        .response()
                        .path("access_token");
        return access_token;
    }
}
