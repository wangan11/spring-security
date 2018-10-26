package com.wangan.security;

import com.wangan.security.entity.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangan on 2018/10/26
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BrowserAppliction.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

	/**
	 * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
	 */
	@LocalServerPort
	private int port;

	private URL base;

	@Autowired
	private TestRestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		String url = String.format("http://localhost:%d/", port);
		System.out.println(String.format("port is : [%d]", port));
		this.base = new URL(url);
	}

	/**
	 * 向"/test"地址发送请求，并打印返回结果
	 *
	 * @throws Exception
	 */
	@Test
	public void test1() throws Exception {
		Map<String,Object> map=new HashMap<>();
		map.put("name","wangan");
		map.put("age",1);
		ResponseEntity<String> response = this.restTemplate.postForEntity(
				this.base.toString() + "/person/addPerson",map,String.class);
		System.out.println(String.format("测试结果为：%s", response.getBody()));
	}

}
