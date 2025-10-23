package com.xiaogua.comments;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.flipkart.zjsonpatch.JsonDiff;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyc
 * @date 2021-12-11 5:03
 */
public class JsonCompare {

    public static void main(String[] args) {
        Map<String, Object> oldMap = new HashMap<>();
        oldMap.put("A", "abandon");
        oldMap.put("B", "banana");
        oldMap.put("C", 1);
        oldMap.put("D", 0.23f);
        oldMap.put("E", 1.223);
        oldMap.put("F", Arrays.asList("ABC","23","@#"));
        Map<String, String> g1 = new HashMap<>();
        g1.put("A", "ak");
        g1.put("B", "bc");
        oldMap.put("G", g1);

        Map<String, Object> newMap = new HashMap<>();
        newMap.put("A", "abandon");
        newMap.put("B", "banana");
        newMap.put("E", 1.223);
        newMap.put("C", 1);
        newMap.put("D", 0.23f);
        newMap.put("F", Arrays.asList("ABC","23","@#"));
        Map<String, String> g2 = new HashMap<>();
        g2.put("B", "bc");
        g2.put("A", "aak");
        newMap.put("G", g2);

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String oldJson = objectMapper.writeValueAsString(oldMap);
            String newJson = objectMapper.writeValueAsString(newMap);
            JsonNode oldNode = objectMapper.readTree(oldJson);
            JsonNode newNode = objectMapper.readTree(newJson);
            JsonNode patch = JsonDiff.asJson(oldNode, newNode);
            System.out.println(objectMapper.writeValueAsString(patch));
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

}
