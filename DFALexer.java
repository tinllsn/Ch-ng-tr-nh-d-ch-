import java.util.*;

class DFALexer {
    private static final Set<String> KEYWORDS = new HashSet<>(Arrays.asList(
            "class", "public", "static", "void", "if", "else", "for", "while", "return", "int", "double", "String"));

    private final String input;
    private int pos;
    private final int length;

    public DFALexer(String input) {
        this.input = input;
        this.pos = 0;
        this.length = input.length();
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (pos < length) {
            char currentChar = input.charAt(pos);

            if (Character.isWhitespace(currentChar)) {
                pos++;
                continue;
            }

            if (Character.isDigit(currentChar)) {
                tokens.add(scanNumber());
            } else if (Character.isLetter(currentChar) || currentChar == '_') {
                tokens.add(scanIdentifierOrKeyword());
            } else if ("|&~".indexOf(currentChar) != -1) {
                tokens.add(scanBitOperator());
            } else if ("+-*/><!=".indexOf(currentChar) != -1) {
                tokens.add(scanOperator());
            } else if ("{}();,".indexOf(currentChar) != -1) {
                tokens.add(new Token(TokenType.PUNCTUATION, Character.toString(input.charAt(pos++))));
            }
            //  else if (currentChar == '"') {
            //     tokens.add(scanString());
            // }
            //  else if (currentChar == '\'') {
            //     tokens.add(scanChar());
            // } 
            // else if (currentChar == '/' && peek() == '/') {
            //     tokens.add(scanSingleLineComment());
            // }
            //  else if (currentChar == '/' && peek() == '*') {
            //     tokens.add(scanMultiLineComment());
            // } 
            else {
                // tokens.add(new Token(TokenType.UNKNOWN, Character.toString(input.charAt(pos++))));
           pos++;
            }
        }

        return tokens;
    }

    private Token scanNumber() {
        int start = pos;
        while (pos < length && Character.isDigit(input.charAt(pos))) {
            pos++;
        }
        return new Token(TokenType.NUMBER, input.substring(start, pos));
    }

    private Token scanIdentifierOrKeyword() {
        int start = pos;
        while (pos < length && (Character.isLetterOrDigit(input.charAt(pos)) || input.charAt(pos) == '_')) {
            pos++;
        }
        String lexeme = input.substring(start, pos);
        TokenType type = KEYWORDS.contains(lexeme) ? TokenType.KEYWORD : TokenType.IDENTIFIER;
        return new Token(type, lexeme);
    }

    private Token scanOperator() {
        int start = pos;
        pos++;
        // char currentChar = input.charAt(pos++);
        
        // Kiểm tra nếu ký tự tiếp theo là '='
        if (pos < length && input.charAt(pos) == '=') {
            pos++;
        }
        
        return new Token(TokenType.OPERATOR, input.substring(start, pos));
    }

    private Token scanBitOperator() {
        int start = pos;
        pos++;
        return new Token(TokenType.BIT_OP, input.substring(start, pos));
    }

    // private Token scanString() {
    //     int start = pos++;
    //     while (pos < length && input.charAt(pos) != '"') {
    //         if (input.charAt(pos) == '\\' && pos + 1 < length) pos++;
    //         pos++;
    //     }
    //     pos++;
    //     return new Token(TokenType.STRING, input.substring(start, pos));
    // }

    // private Token scanChar() {
    //     int start = pos++;
    //     if (pos < length && input.charAt(pos) != '\'') {
    //         pos++;
    //     }
    //     if (pos < length) pos++;
    //     return new Token(TokenType.CHAR, input.substring(start, pos));
    // }

    // private Token scanSingleLineComment() {
    //     int start = pos;
    //     while (pos < length && input.charAt(pos) != '\n') {
    //         pos++;
    //     }
    //     return new Token(TokenType.COMMENT, input.substring(start, pos));
    // }

    // private Token scanMultiLineComment() {
    //     int start = pos;
    //     pos += 2;
    //     while (pos < length - 1 && !(input.charAt(pos) == '*' && input.charAt(pos + 1) == '/')) {
    //         pos++;
    //     }
    //     pos += 2;
    //     return new Token(TokenType.COMMENT, input.substring(start, pos));
    // }

    // private char peek() {
    //     return (pos + 1 < length) ? input.charAt(pos + 1) : '\0';
    // }
} 