package org.ruzun.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flattener {
    public static Map<String, String> flatMap(String parentKey, Map<String, Object> nestedMap)
    {
//        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();
        Map<String, String> flatMap = new HashMap<>();
        String prefixKey = parentKey != null ? parentKey + "." : "";
        for (Map.Entry<String, Object> entry : nestedMap.entrySet()) {
            if (entry.getValue() instanceof List) {
                flatMap.put(prefixKey + entry.getKey(), gson.toJson(entry.getValue()));
            }else {
                if (entry.getValue() instanceof String) {
                    flatMap.put(prefixKey + entry.getKey(), (String) entry.getValue());
                }
                if (entry.getValue() instanceof Map) {
                    flatMap.putAll(flatMap(prefixKey + entry.getKey(), (Map<String, Object>) entry.getValue()));
                }
            }
        }
        return flatMap;
    }
}
