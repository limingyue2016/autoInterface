package com.wework.task;

import com.wework.apiobject.DepartmentObject;

import java.util.ArrayList;

public class EnvHelperTask {
    public static DepartmentObject departInit = new DepartmentObject();

    public static void clearDepartmentTask(String departmentId, String accessToken) {
        ArrayList<Integer> departmentids = departInit.listDepartment(departmentId, accessToken).path("department.id");
        for (int departmentid : departmentids) {
            if (departmentid == 1) {
                continue;
            }
            departInit.deleteDepartment(departmentid + "", accessToken);
        }
    }
}
