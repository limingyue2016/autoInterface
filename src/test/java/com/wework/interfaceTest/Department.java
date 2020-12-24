package com.wework.interfaceTest;

import com.wework.task.EnvHelperTask;
import com.wework.utils.FakerUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Department extends BaseTest {

    @BeforeEach
    void clear() {
//        EnvHelperTask.clearDepartmentTask("4", accessToken);
    }

    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv", numLinesToSkip = 1)
    void createDepartment(String createName, String returncode) {
        Response response = departInit.createDepartment(createName, accessToken);
        assertEquals(returncode, response.path("errcode").toString());
    }

    @DisplayName("更新部门")
    @Test()
    void updateDepartment() {
        String updateName = "name" + FakerUtils.getTimeStamp();
        String departmentId = departInit.createDepartment(accessToken);

        Response response = departInit.updateDepartment(updateName, departmentId, accessToken);
        assertEquals("0", response.path("errcode").toString());
    }

    @DisplayName("删除部门")
    @Test()
    void deleteDepartment() {
        String departmentId = departInit.createDepartment(accessToken);

        Response response = departInit.deleteDepartment(departmentId, accessToken);
        assertEquals("0", response.path("errcode").toString());
    }
}
