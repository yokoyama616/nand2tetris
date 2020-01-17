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
    StringBuilder body = new StringBuilder("<tokens>\n");
    for (var t : tokenList) {
      body.append(t.convert());
    }
    body.append("</tokens>\n");
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
      "<", "&lt;",
      ">", "&gt;");

  private String token;

  private Token(char c) {
    this.token = c + "";
  }

  private Token(String token) {
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public boolean check(String... s) {
    for (var k : s) {
      if (token.equals(k)) return true;
    }
    return false;
  }

  // token文字をxml形式に変換
  public String convert() {
    // 空文字
    if (this.token.equals("")) return "";
    if (KEYWORDS.contains(this.token)) {
      return "<keyword> " + token + " </keyword>\n";
    }
    if (SYMBOLS.contains((this.token))) {
      if (SANITIZE.containsKey(this.token)) {
        return "<symbol> " + SANITIZE.get(this.token) + " </symbol>\n";
      }
      return "<symbol> " + token + " </symbol>\n";
    }
    // "がある場合
    if (this.token.contains("\"")) {
      return "<stringConstant> " + token.replaceAll("\"", "") + " </stringConstant>\n";
    }

    // 数値かの確認はもっといい方法あるきがする
    try {
      Integer.parseInt(this.token);
      return "<integerConstant> " + token + " </integerConstant>\n";
    } catch (NumberFormatException e) {
      return "<identifier> " + token + " </identifier>\n";
    }
  }

  /**
   * tokenに分割するやつ
   *
   * @param text ファイルの内容全行まとめたやつ
   * @return tokenリスト
   */
  static ArrayList<Token> parseTokens(String text) {
    var tokens = new ArrayList<Token>();
    StringBuilder prevStr = new StringBuilder();
    // 1文字ごとに調査
    for (var i = 0; i < text.length(); i++) {
      var check = text.charAt(i);

      // "が出現した場合は、次の"までを格納(見つからないのは想定しない)
      if (check == '"') {
        // 今の保持しているものを登録
        tokens.add(new Token(prevStr.toString()));
        prevStr = new StringBuilder();
        // 次の"までを取得して格納
        var next = text.indexOf('"', i + 1);
        tokens.add(new Token(text.substring(i, next + 1)));
        i = next;
        continue;
      }

      // シンボルに含まれる文字
      if (SYMBOLS.contains("" + check)) {
        // /だけ次の文字を参照する
        if (check == '/') {
          // //は、次の改行コードまでスキップ
          if (text.charAt(i + 1) == '/') {
            i = text.indexOf('\n', i);
            continue;
          }
          // /*は、*/が出現するまでスキップ
          // TODO: // */ みたいなのがあると想定外動作になる
          if (text.charAt(i + 1) == '*') {
            i = text.indexOf("*/", i) + 1;
            continue;
          }
        }

        // /以外の場合は、 前の文と今回のシンボルをTokenとして登録
        tokens.add(new Token(prevStr.toString()));
        tokens.add(new Token(check));
        prevStr = new StringBuilder();
        continue;
      }

      // 空白文字ならいまの段階のものを登録
      if (check == ' ') {
        tokens.add(new Token(prevStr.toString()));
        prevStr = new StringBuilder();
        continue;
      }

      // 改行コードはスキップ
      if (check == '\n') {
        continue;
      }

      // 上記いづれにもはてはまらない場合のみ文字を結合
      prevStr.append(check);
    }

    return tokens;
  }


}
