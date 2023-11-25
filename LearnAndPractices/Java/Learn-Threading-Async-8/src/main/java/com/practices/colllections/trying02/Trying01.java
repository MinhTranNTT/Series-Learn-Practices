package com.practices.colllections.trying02;

import java.util.List;
import java.util.Map;

public class Trying01 {
    public static void main(String[] args) throws Exception {
        String jsonData = "{\"a1\":{\"b1\":{\"c1\":{\"d1\":\"d1\"}}},\"a2\":{\"b2\":{\"c2\":{\"d2\":[\"i1\",\"i2\",\"i3\"]}}}}";

        Map<String, Object> map = JsonUtils.parse(jsonData, Map.class);

        String s = JsonUtils.getValue(map, "a1.b1.c1.d1", String.class);
        System.out.println(s);

        List<String> list = JsonUtils.getValue(map, "a2.b2.c2.d2", List.class);
        System.out.println(list);
    }
}
