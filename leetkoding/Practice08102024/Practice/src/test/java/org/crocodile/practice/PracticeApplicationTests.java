package org.crocodile.practice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PracticeApplicationTests {

	@Test
	void testCaseV1() {
		String text = "ATYSDFA*Y*";
		// String txt1 = text.replace('A', 'B');
		String txt1 = text.replaceAll("A", "B");
		System.out.println(txt1);
		// String txt2 = text.replace("A", "B");
		String txt2 = text.replaceAll("\\*", "C");
		System.out.println(txt2);
		System.out.println("==========");
		System.out.println("text = " + text);
	}

}
