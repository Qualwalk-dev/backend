package com.qualwalk.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	void contextLoads() {

		assertThat(applicationContext).isNotNull();
	}

}
