package org.jetbrains.kotlin.ui.tests.editors;

import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;
import org.jetbrains.kotlin.ui.editors.ColorManager;
import org.jetbrains.kotlin.ui.editors.IColorConstants;
import org.jetbrains.kotlin.ui.editors.PartitionScanner;
import org.junit.Test;

public class KotlinHighlightningScannerTest extends KotlinHighlightningScannerTestCase {

	private static final ColorManager COLOR_MANAGER = new ColorManager();
	private static final IToken KEYWORD_TOKEN = new Token(new TextAttribute(COLOR_MANAGER.getColor(IColorConstants.KEYWORD)));
	private static final IToken STRING_TOKEN = new Token(new TextAttribute(COLOR_MANAGER.getColor(IColorConstants.STRING)));
	private static final IToken DEFAULT_TOKEN = new Token(new TextAttribute(COLOR_MANAGER.getColor(IColorConstants.DEFAULT)));
	private static final IToken COMMENT_TOKEN = new Token(PartitionScanner.KOTLIN_COMMENT);
	
	@Test
	public void oneKeywordWithoutTextIn() {
		doTest("in", KEYWORD_TOKEN, false);
	}
	
	@Test
	public void oneKeywordWithoutTextFor() {
		doTest("for", KEYWORD_TOKEN, false);
	}
	
	@Test
	public void oneKeywordWithText() {
		doTest("in sdfsd", KEYWORD_TOKEN, false);
	}
	
	@Test
	public void defaultTokenBeforeKeyword() {
		doTest("abcd for", DEFAULT_TOKEN, false);
	}
	
	@Test
	public void defaultTokenWithKeywordInSuffix() {
		doTest("main", DEFAULT_TOKEN, false);
	}
	
	@Test
	public void defaultTokenWithKeywordInPrefix() {
		doTest("inprefix", DEFAULT_TOKEN, false);
	}
	
	@Test
	public void defaultTokenWithKeywordInMiddle() {
		doTest("sinus", DEFAULT_TOKEN, false);
	}
	
	@Test
	public void stringToken() {
		doTest("\"Hello\"", STRING_TOKEN, false);
	}
	
	@Test
	public void blockCommentToken() {
		doTest(
				"/*test\n" +
				"test\n" +
				"*/", COMMENT_TOKEN, true);
	}
	
	@Test
	public void singleLineCommentToken() {
		doTest("// for main", COMMENT_TOKEN, true);
	}
}