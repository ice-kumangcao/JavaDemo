package antlr4;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

/**
 * Antlr 是 Java写的语言识别工具
 * Hibernate用此工具分析sql语句
 * 具体的代码没有弄明白，所以这是只是一个四则运算的Demo
 * @author ice
 * @date 18-12-29 上午10:32
 */
public class TestAntlr {

    public static void main(String[] args) {

        CharStream input = CharStreams.fromString("1 + (12+12*(8-2-2)/2+3)*2");
        MathLexer lexer = new MathLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MathParser parser = new MathParser(tokens);
        ParseTree tree = parser.prog();
        MathBaseVisitorTest visitorTest = new MathBaseVisitorTest();
        visitorTest.visit(tree);
        System.out.println(tree.toStringTree(parser));
    }
}
