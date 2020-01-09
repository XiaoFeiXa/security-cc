package com.security.cc.learn.securitycc;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AuthenticationExample {

    private static AuthenticationManager am  = new SampleAuthenticationManager();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            System.out.println("请输入用户名：");
            String name = in.readLine();
            System.out.println("请输入密码：");
            String password = in.readLine();

            try {
                Authentication request = new UsernamePasswordAuthenticationToken(name, password);

                Authentication result = am.authenticate(request);
                SecurityContextHolder.getContext().setAuthentication(result);
                break;
            }catch (AuthenticationException e) {
                System.out.println("认证失败：" + e.getMessage());
            }


        }
        System.out.println("认证成功：" + SecurityContextHolder.getContext().getAuthentication());



    }

}
