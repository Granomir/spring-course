package com.patrushev.home_work_03.impl;

import com.patrushev.home_work_03.UserInputService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class UserConsoleInputServiceImpl implements UserInputService {
    private BufferedReader br;

    public UserConsoleInputServiceImpl() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String getUserInput() {
        try {
            return br.readLine().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void close() {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
