package com.bfhl.controller;

import com.bfhl.dto.InputData;
import com.bfhl.dto.OutputData;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class bfhlController {

    @PostMapping("/bfhl")
    public OutputData processData(@RequestBody InputData inputData) {
        List<String> data = inputData.getData();
        OutputData response = new OutputData();

        List<String> even = new ArrayList<>();
        List<String> odd = new ArrayList<>();
        List<String> alpha = new ArrayList<>();
        List<String> special = new ArrayList<>();
        StringBuilder alphaConcat = new StringBuilder();
        int sum = 0;

        for (String item : data) {
            if (item.matches("\\d+")) {
                int num = Integer.parseInt(item);
                if (num % 2 == 0) {
                    even.add(item);
                } else {
                    odd.add(item);
                }
                sum += num;
            } else if (item.matches("[a-zA-Z]+")) {
                alpha.add(item.toUpperCase());
                alphaConcat.append(item);
            } else {
                special.add(item);
            }
        }

        String reversed = new StringBuilder(alphaConcat.toString()).reverse().toString();
        StringBuilder concatString = new StringBuilder();
        for (int i = 0; i < reversed.length(); i++) {
            char c = reversed.charAt(i);
            concatString.append(i % 2 == 0 ? Character.toUpperCase(c) : Character.toLowerCase(c));
        }

        response.setIs_success(true);
        response.setUser_id("john_doe_17091999");
        response.setEmail("john@xyz.com");
        response.setRoll_number("ABCD123");
        response.setEven_numbers(even);
        response.setOdd_numbers(odd);
        response.setAlphabets(alpha);
        response.setSpecial_characters(special);
        response.setSum(String.valueOf(sum));
        response.setConcat_string(concatString.toString());

        return response;
    }
}
