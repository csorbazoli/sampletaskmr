package com.arvoia.sampletask.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import com.arvoia.sampletask.exception.CipherException;

public class SimpleCipherToolTest {

	private SimpleCipherTool testee;
	private SimpleCipherTool testee1;

	@Before
	public void setUp() {
		testee = new SimpleCipherTool("abc");
		testee1 = new SimpleCipherTool("arvoia");
	}

	@Test
	public void testCipherText() throws Exception {
		//CoreMatchers hamcrest can be used
		assertEquals("Expected result doesnt match", "bcd", testee.cipher("aaa"));
		assertEquals("Expected result doesnt match", "Scpqnh vdtm", testee.cipher("Sample task"));
		assertEquals("Expected result doesnt match", "ilpeb://cxcivt.rxn/uodacbrkar/tsieufusozvs", testee1.cipher("https://github.com/csorbazoli/sampletaskmr"));
	}

	@Test
	public void testDeCipherText() throws Exception {
		assertEquals("Expected result doesnt match", "aaa", testee.decipher("bcd"));
		assertEquals("Expected result doesnt match", "Sample task", testee.decipher("Scpqnh vdtm"));
		assertEquals("Expected result doesnt match", "https://github.com/csorbazoli/sampletaskmr", testee1.decipher("ilpeb://cxcivt.rxn/uodacbrkar/tsieufusozvs"));
	}
	
	@Test(expected = CipherException.class)
	public void testCipherException() throws Exception {
		testee = new SimpleCipherTool(null);
		assertEquals("Expected result doesnt match", "bcd", testee.cipher("aaa"));
		assertEquals("Expected result doesnt match", "Scpqnh vdtm", testee.cipher("Sample task"));
		assertEquals("Expected result doesnt match", "Scpqnh vdtm", testee.cipher("Sample task"));
	}
}
