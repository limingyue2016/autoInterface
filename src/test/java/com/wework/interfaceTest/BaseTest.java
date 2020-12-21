package com.wework.interfaceTest;

import com.wework.apiobject.DepartmentObject;
import com.wework.apiobject.TokenHelper;
import com.wework.utils.LogUtils;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    public static String accessToken;
    public static LogUtils log;
    public DepartmentObject departInit = new DepartmentObject();

    @BeforeAll
    static void getAccessToken() {
        log = LogUtils.newInstance();
        accessToken = TokenHelper.getAccessToken();
        log.i("accessToken: " + accessToken);
    }
}
