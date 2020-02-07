import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class CompilationEngine {

  public static void main(String[] args) throws IOException {
    if (args.length != 1) {
      System.out.println("args error");
      return;
    }

    var compilationEngine = new CompilationEngine(args[0]);
  }

  private int n = 0;
  List<CToken> tokens = new ArrayList<CToken>();
  String result = "";

  private CompilationEngine(String filePath) throws IOException {
    // XXXT.xmlをCTokenに変換
    var file = new File(filePath);
    BufferedReader br = new BufferedReader(new FileReader(file));
    var text = "";
    while ((text = br.readLine()) != null) {
      if (text.equals("<tokens>") || text.equals("</tokens>")) continue;
      tokens.add(new CToken(text.trim()));
    }
    br.close();
    // コンパイルクラス
    compClass();

    var fileName = file.getName().replaceAll("\\..*", "");
    fileName = fileName.substring(0, fileName.length() - 1);
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(file.getParent() + "\\" + fileName + ".xml"))) {
      writer.append(result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // ★
  private void compClass() {
    result += "<class>\n";
    try {
      write(next());
      compClassName();
      write(next());
      while (checkClassVarDec()) compClassVarDec();
      while (checkSubroutineDec()) compSubroutineDec();
      write(next());
    } catch (Exception e) {
    }
    result += "</class>\n";
  }

  // ★
  private void compClassName() {
    write(next());
  }

  // ★
  private boolean checkClassVarDec() {
    return get().check("static", "field") || get().isType();
  }

  private void compClassVarDec() {
    result += "<classVarDec>\n";
    if (get().check("static", "field")) write(next());
    compType();
    compVarName();
    while (get().check(",")) {
      write(next());//,
      compVarName();
    }
    write(next());
    result += "</classVarDec>\n";
  }

  // ★
  private void compVarName() {
    write(next());
  }

  // ★
  private void compType() {
    write(next());
  }

  private boolean checkSubroutineDec() {
    return get().check("constructor", "function", "method");
  }

  private void compSubroutineDec() {
    result += "<subroutineDec>\n";
    write(next());
    write(next());// void | type
    write(next());// subroutineName
    write(next());// (
    compParameterList();
    write(next()); // )
    compSubroutineBody();
    result += "</subroutineDec>\n";
  }

  private void compSubroutineBody() {
    result += "<subroutineBody>\n";
    write(next());// {
    while (checkVarDec()) compVarDec();
    compStatements();
    write(next());// }
    result += "</subroutineBody>\n";
  }

  private boolean checkVarDec() {
    return get().check("var");
  }

  private void compVarDec() {
    result += "<varDec>\n";
    write(next());// var
    write(next());// type
    compVarName();
    while (get().check(",")) {
      write(next());//,
      compVarName();
    }
    write(next());//;
    result += "</varDec>\n";
  }

  private void compStatements() {
    result += "<statements>\n";
    while (checkStatement()) compStatement();
    result += "</statements>\n";
  }

  private boolean checkStatement() {
    return get().check("let", "if", "while", "do", "return");
  }

  private void compStatement() {
    switch (get().val) {
      case "let":
        compLetStatement();
        break;
      case "if":
        compIfStatement();
        break;
      case "while":
        compWhileStatement();
        break;
      case "do":
        compDoStatement();
        break;
      case "return":
        compReturnStatement();
        break;
      default:
        break;
    }
  }

  private void compLetStatement() {
    result += "<letStatement>\n";
    write(next());// let
    compVarName();
    if (get().check("[")) {
      write(next());//[
      compExpression();
      write(next());//]
    }
    write(next());//=
    compExpression();
    write(next());//;
    result += "</letStatement>\n";
  }

  private void compIfStatement() {
    result += "<ifStatement>\n";
    write(next());//if
    write(next());//(
    compExpression();
    write(next());// )
    write(next());//{
    compStatements();
    write(next());//}
    if (get().check("else")) {
      write(next());//else
      write(next());//{
      compStatements();
      write(next());//}
    }
    result += "</ifStatement>\n";
  }

  private void compWhileStatement() {
    result += "<whileStatement>\n";
    write(next());//while
    write(next());//(
    compExpression();
    write(next());//)
    write(next());//{
    compStatements();
    write(next());//}
    result += "</whileStatement>\n";
  }

  private void compDoStatement() {
    result += "<doStatement>\n";
    write(next());// do
    compSubroutineCall();
    write(next());//;
    result += "</doStatement>\n";
  }

  private void compReturnStatement() {
    result += "<returnStatement>\n";
    write(next());//return
    if (checkExpression()) compExpression();
    write(next());//;
    result += "</returnStatement>\n";
  }

  private boolean checkExpression() {
    return checkTerm();
  }

  private void compExpression() {
    result += "<expression>\n";
    compTerm();
    while (checkOp()) {
      compOp();
      compTerm();
    }
    result += "</expression>\n";
  }

  private void compTerm() {
    result += "<term>\n";
    if (isSubroutineCall() != 0) {
      compSubroutineCall();
    } else if (get().isIntegerConstant()) {
      write(next());
    } else if (get().isStringConstant()) {
      write(next());
    } else if (get().isKeywordConstant()) {
      write(next());
    } else if (get().isIdentifier()) {
      write(next());
      if (get().check("[")) {
        write(next());// [
        compExpression();
        write(next());// ]
      }
    } else if (get().check("(")) {
      write(next());// (
      compExpression();
      write(next());// )
    } else if (get().isUnaryOp()) {
      write(next());
      compTerm();
    }

    result += "</term>\n";
  }

  private boolean checkTerm() {
    return isSubroutineCall() != 0 || get().isIntegerConstant() || get().isStringConstant() || get().isKeywordConstant() || get().isIdentifier() ||  get().check("(") || get().isUnaryOp();
  }

  // ★
  private void compOp() {
    write(next());
  }

  // ★
  private boolean checkOp() {
    return get().check("+", "-", "*", "/", "&", "|", "<", ">", "=");
  }


  private void compSubroutineCall() {
    var s = isSubroutineCall();
    if(s == 1) {
      compSubroutineName();
      write(next());//(
      compExpressionList();
      write(next());//)
    } else if(s == 2){
      write(next());// identifier
      write(next());// .
      compSubroutineName();
      write(next());//(
      compExpressionList();
      write(next());//)
    }
  }

  private int isSubroutineCall() {
    if(get().isIdentifier()) {
      if(getA(1).check("(")){
        return 1;
      } else if(getA(1).check(".") && getA(2).isIdentifier() && getA(3).check("(")){
        return 2;
      }
    }
    return 0;
  }

  private void compExpressionList() {
    result += "<expressionList>\n";
    if (checkExpression()) {
      compExpression();
      while (get().check(",")) {
        write(next());// ,
        compExpression();
      }
    }
    result += "</expressionList>\n";
  }

  private void compSubroutineName() {
    write(next());
  }

  private void compParameterList() {
    result += "<parameterList>\n";
    if (checkParameterList()) {
      write(next());// type
      compVarName();
      while (get().check(",")) {
        write(next());//,
        write(next());//type
        compVarName();
      }
    }
    result += "</parameterList>\n";
  }

  private boolean checkParameterList() {
    return get().isType();
  }

  private CToken get() {
    return tokens.get(n);
  }

  private CToken getA(int i) {
    return tokens.get(n + i);
  }

  private CToken next() {
    return tokens.get(n++);
  }

  private void write(CToken ct) {
    var v = ct.val.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">","&gt;");
    result += "<" + ct.type + "> " + v + " </" + ct.type + ">\n";
  }
}

class CToken {
  public String type;
  public String val;
  final Pattern p = Pattern.compile("<(.*?)>(.*?)</(.*)>");

  CToken(String line) {
    Matcher m = p.matcher(line);
    if (m.find()) {
      this.type = m.group(1).trim();
      this.val = m.group(2).trim().replaceAll("&lt;", "<").replaceAll("&gt;",">").replaceAll("&amp;", "&");
    }
  }

  public boolean check(String... t) {
    return Arrays.asList(t).contains(this.val);
  }

  public boolean isType() {
    return check("int", "char", "boolean") || isIdentifier();
  }

  public boolean isIdentifier() {
    return this.type.equals("identifier");
  }

  public boolean isIntegerConstant() {
    return this.type.equals("integerConstant");
  }

  public boolean isStringConstant() {
    return this.type.equals("stringConstant");
  }

  public boolean isSymbol() {
    return this.type.equals("symbol");
  }

  public boolean isKeywordConstant() {
    return this.check("true", "false", "null", "this");
  }

  public boolean isUnaryOp() {
    return this.check("-", "~");
  }
}
