package com.example.demo.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	/**
	 * ユーザー情報一覧表示処理で正しいViewが返されるか検証する
	 */
    @Test
    void displayListTest01() throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.get("/user/list")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/list"));
    }

	/**
	 * ユーザー新規登録表示処理で正しいViewが返されるか検証する
	 */
    @Test
    void displayAddTest01() throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.get("/user/add")).andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/add"));
    }

	/**
	 * 画面の入力から新規レコードがDBへ登録されるか検証
	 */
    @Test
	@DatabaseSetup(value = "/crud/create/")
	@ExpectedDatabase(value = "/crud/create/result/", table = "user_info", assertionMode=DatabaseAssertionMode.NON_STRICT)
    void createTest01() throws Exception {

        mvc.perform(
                MockMvcRequestBuilders.post("/user/create")
        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        .param("name", "test003")
        .param("password", "333333"));
    }

}
