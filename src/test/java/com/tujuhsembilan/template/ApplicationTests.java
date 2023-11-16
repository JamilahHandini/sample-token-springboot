package com.tujuhsembilan.template;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@Test
	void contextLoads() {
		var e = true;
		var a = true;
		assertEquals(e, a, "placeholder");
	}

}