package com.wework.interfaceTest;

import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Department extends BaseTest {

    @BeforeEach
    @AfterEach
    void clear() {
//        EnvHelperTask.clearDepartmentTask("", accessToken);
    }

    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv", numLinesToSkip = 1)
    void createDepartment(String creatName, String returncode) {
        Response response = departInit.createDepartment(creatName, accessToken);
        assertEquals(returncode, response.path("errcode").toString());
    }

}
