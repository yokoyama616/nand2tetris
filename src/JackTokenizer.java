import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

class JackTokenizer {
    private File file;
    private List<Token> tokenList;

    JackTokenizer(File file) throws IOException {
        this.file = file;
        tokenList = new ArrayList<>();
        var fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String text = "";
        StringBuilder allText = new StringBuilder();
        while ((text = br.readLine()) != null) {
            allText.append(text.trim()).append("\n");// 前後の空白消す
        }
        var tokens = Token.parseTokens(allText.toString());
        tokenList.addAll(tokens);
        br.close();
    }

    public void write() {
        StringBuilder body = new StringBuilder("<tokens>");
        for (var t : tokenList) {
            body.append(t.convert());
        }
        body.append("</tokens>");
        var fileName = this.file.getName().replaceAll("\\..*", "");
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(this.file.getParent() + "\\" + fileName + "T.xml"))) {
            writer.append(body.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Token {
    private static final List<String> KEYWORDS = Arrays.asList("class", "constructor", "function", "method", "field", "static", "var", "int", "char", "boolean", "void", "true", "false", "null", "this", "let", "do", "if", "else", "while", "return");
    private static final List<String> SYMBOLS = Arrays.asList("{", "}", "(", ")", "[", "]", ".", ",", ";", "+", "-", "*", "/", "&", "|", "<", ">", "=", "~");

    private static final Map<String, String> SANITIZE = Map.of(
            "&", "&amp;",
            "<", "&;t;",
            ">", "&gt;");

    private String token;

    Token(String token) {
        this.token = token;
    }

    public String convert() {
        // 空文字
        if (this.token.equals("")) return "";
        if (KEYWORDS.contains(this.token)) {
            return "<keyword> " + token + " </keyword>";
        }
        if (SYMBOLS.contains((this.token))) {
            return "<symbol> " + token + " </symbol>";
        }
        // "がある場合
        if (this.token.contains("\"")) {
            return "<stringConstant> " + token + " </stringConstant>";
        }

        // 数値かの確認はもっといい方法あるきがする
        try {
            Integer.parseInt(this.token);
            return "<integerConstant> " + token + " </integerConstant>";
        } catch (NumberFormatException e) {
            return "<identifier> " + token + " </identifier>";
        }
    }

    public static ArrayList<Token> parseTokens(String text) {
        var tokens = new ArrayList<Token>();
        String prevStr = "";
        // 1文字調査
        for (var i = 0; i < text.length(); i++) {
            var check = text.charAt(i);
            // "が出現した場合は、次の"までを格納(見つからないのは想定しない)
            if (check == '"') {
                // 今の保持しているものを登録
                tokens.add(new Token(prevStr));
                prevStr = "";

                var next = text.indexOf('"', i);
                tokens.add(new Token(text.substring(i, next)));
                i = next;
                continue;
            }

            // シンボルに含まれる文字
            if (SYMBOLS.contains("" + check)) {
                // //の場合は以降コメントとして扱うため、抜ける
                if (check == '/') {
                    // 次の改行コードまでスキップ
                    if (text.charAt(i + 1) == '/') {
                        var next = text.indexOf('\n', i);
                        i = next;
                        continue;
                    }
                    // */が出現するまでスキップ
                    if (text.charAt(i + 1) == '*') {
                        var next = text.indexOf("*/", i);
                        i = next + 1;
                        continue;
                    }
                }
                // 前の文と今回のシンボルをTokenとして登録
                tokens.add(new Token(prevStr));
                tokens.add(new Token("" + check));
                prevStr = "";
                continue;
            }
            // 空白文字が来たならいまの段階のものを保持
            if (check == ' ') {
                tokens.add(new Token(prevStr));
                prevStr = "";
                continue;
            }
            if (check == '\n') {
                continue;
            }
            prevStr += check;
        }

        return tokens;
    }


}