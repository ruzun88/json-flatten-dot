package org.ruzun.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flattener {
    public static Map<String, Object> flatMap(String parentKey, Map<String, Object> nestedMap)
    {
//        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();
        Map<String, Object> flatMap = new HashMap<>();
        String prefixKey = parentKey != null ? parentKey + "." : "";
        for (Map.Entry<String, Object> entry : nestedMap.entrySet()) {
            if (entry.getValue() instanceof List) {
                flatMap.put(prefixKey + entry.getKey(), gson.toJson(entry.getValue()));
            } else if (entry.getValue() instanceof String) {
                flatMap.put(prefixKey + entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue() instanceof Double
                    || entry.getValue() instanceof Float
                    || entry.getValue() instanceof Long
                    || entry.getValue() instanceof Integer
                    || entry.getValue() instanceof Date) {
                flatMap.put(prefixKey + entry.getKey(), entry.getValue());
            } else if (entry.getValue() instanceof Map) {
                flatMap.putAll(flatMap(prefixKey + entry.getKey(), (Map<String, Object>) entry.getValue()));
            } else if (entry.getValue() == null) {
                flatMap.put(prefixKey + entry.getKey(), null);
            }

        }
        return flatMap;
    }
}
