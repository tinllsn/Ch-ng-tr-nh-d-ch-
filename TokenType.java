enum TokenType {
    KEYWORD,      // Từ khóa
    IDENTIFIER,   // Định danh
    NUMBER,       // Số
    OPERATOR,     // Toán tử (+|-|*|/|>|<|=|!)'='?
    BIT_OP,       // Toán tử bit ('|', '&','~')
    PUNCTUATION,  // Dấu câu
    // STRING,    // Chuỗi
    // CHAR,      // Ký tự
    // COMMENT,   // Chú thích
    WHITESPACE,   // Khoảng trắng
    UNKNOWN       // Không xác định
} 