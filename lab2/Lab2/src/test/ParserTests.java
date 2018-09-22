package test;

import static org.junit.Assert.fail;

import java.io.StringReader;

import lexer.Lexer;

import org.junit.Test;

import parser.Parser;

public class ParserTests {
	private void runtest(String src) {
		runtest(src, true);
	}

	private void runtest(String src, boolean succeed) {
		Parser parser = new Parser();
		try {
			parser.parse(new Lexer(new StringReader(src)));
			if(!succeed) {
				fail("Test was supposed to fail, but succeeded");
			}
		} catch (beaver.Parser.Exception e) {
			if(succeed) {
				e.printStackTrace();
				fail(e.getMessage());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testEmptyModule() {
		runtest("module Test { }", true);
	}
	
	/* For the following test modules, the naming is as follows:
	 * Tests scheduled to pass: testModuleX()
	 * Tests scheduled not to pass: testModuleXX()
	 * 
	 * X can be any letter of the Alphabet. 
	 */
	
	@Test
	public void testModuleA() {
		runtest("module Test "
				+ "{ "
				+ "public int x; "
				+ "int y; "
				+ "public string[] TestFunction (int a, bool b, int[] c, string d) "
				+ "{ "
				+ "int z;"
				+ "z = 1000; "
				+ "x = z + a; "
				+ "if (a == b) "
				+ "return null; "
				+ "else "
				+ "{ return true; } "
				+ "} "
				+ "void TestNull () "
				+ "{ "
				+ "} "
				+ "} "
				, true);
	}
}
