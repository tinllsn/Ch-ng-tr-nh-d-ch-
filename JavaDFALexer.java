import java.util.*;

public class JavaDFALexer {
    public static void main(String[] args) {
        String sourceCode = """
            public class Test {
                int x = 10;
                if (x > 5) x = x - 1;
                int a = x & y;  // Bit AND
                int b = x | y;  // Bit OR
                int c = ~x;     // Bit NOT
                boolean d = x >= 10;  // Greater than or equal
                boolean e = x <= 5;   // Less than or equal
                boolean f = x != 7;   // Not equal
               
                char c = 'a';
            }
            """;

        DFALexer lexer = new DFALexer(sourceCode);
        List<Token> tokens = lexer.tokenize();
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
} 