package com.patrushev.students_testing_app.impl;

import com.patrushev.students_testing_app.UserInputService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserConsoleInputServiceImpl implements UserInputService {
    public String getUserInput() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            return br.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
