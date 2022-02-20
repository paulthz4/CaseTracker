package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import application.Case;
import application.Main;
class testCase {
	String b = "case A";
	Case a = new Case("case A");
	Main main = new Main();
	@Test
	void test() {
		assertEquals(a.getTitle(),"case A");
	}

}
