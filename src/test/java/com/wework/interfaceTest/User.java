package com.wework.interfaceTest;

import com.wework.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class User extends BaseTest{
    @DisplayName("创建成员")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createUser.csv", numLinesToSkip = 1)
    void createUser(String userid, String name, String mobile, String returncode) {
        Response response = userInit.createUser(userid, name, mobile, accessToken);
        assertEquals(returncode, response.path("errcode").toString());
    }

    @DisplayName("获取成员信息")
    @Test()
    void getUser() {
        String userid = userInit.createUser(accessToken);

        Response response = userInit.getUser(userid, accessToken);
        assertEquals(userid, response.path("userid").toString());
    }

    @DisplayName("更新成员")
    @Test()
    void updateUser() {
        String userid = userInit.createUser(accessToken);
        String updateName = "name" + userid;

        Response response = userInit.updateUser(updateName, userid, accessToken);
        assertEquals("0", response.path("errcode").toString());
    }

    @DisplayName("删除成员")
    @Test()
    void deleteUser() {
        String userid = userInit.createUser(accessToken);

        Response response = userInit.deleteUser(userid, accessToken);
        assertEquals("0", response.path("errcode").toString());
    }
}
