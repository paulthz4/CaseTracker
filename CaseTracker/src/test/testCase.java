package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import application.Case;
class testCase {
	Case a = new Case("case A");
	@Test
	void test() {
		assertNotEquals(a.getTitle(), "case A");
	}

}
