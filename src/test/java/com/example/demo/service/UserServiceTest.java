package com.example.demo.service;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.CsvDataSetLoader;
import com.example.demo.SpringPracticeApplication;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserRepository;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;

@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
@TestExecutionListeners({
	DependencyInjectionTestExecutionListener.class,
	  TransactionalTestExecutionListener.class,
	  DbUnitTestExecutionListener.class
	})
@SpringBootTest(classes = {SpringPracticeApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {

    /**
     * ユーザー情報 Repository
     */
    @Autowired
    UserRepository userRepository;

    @Test
	@DatabaseSetup(value = "/testData/")
	@Transactional
    void searchAllTest() throws Exception {

    	List<UserInfo> userlist = userRepository.findAll();
    	assertThat(userlist.size(), is(3));
    }
}
