import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    if (get().check("static", "field")) write(next());
    compType();
    compVarName();
    while (get().check(",")) {
      write(next());//,
      compVarName();
    }
    write(next());
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

  private void compSubroutineBody(){
      write(next());// {
      while (checkVarDec())compVarDec();
      compStatements();
      write(next());// }
  }

  private boolean checkVarDec(){
    return get().check("var");
  }
  private void compVarDec(){
    write(next());// var
    write(next());// type
    compVarName();
    while (get().check(",")){
      write(next());//,
      write(next());//type
      compVarName();
    }
    write(next());//;
  }

  private void compStatements(){
    while(checkStatement())compStatement();
  }
  private boolean checkStatement(){
    return get().check("let", "if", "while", "do", "return");
  }
  private void compStatement(){
    switch (get().val){
      case "let":compLetStatement();break;
      case "if":compIfStatement();break;
      case "while":compWhileStatement();break;
      case "do":compDoStatement();break;
      case "return":compReturnStatement();break;
      default:break;
    }
  }
  private void compLetStatement(){
    write(next());// let
    compVarName();
    if(get().check("[")){
      write(next());//[
      compExpression();
      write(next());//]
    }
    write(next());//=
    compExpression();
    write(next());//;
  }
  private void compIfStatement(){
    write(next());//if
    write(next());//(
    compExpression();
    write(next());// )
    write(next());//{
    compStatements();
    write(next());//}
    if(get().check("else")) {
      write(next());//else
      write(next());//{
      compStatements();
      write(next());//}
    }
  }
  private void compWhileStatement(){
    write(next());//while
    write(next());//(
    compExpression();
    write(next());//)
    write(next());//{
    compStatements();
    write(next());//}
  }

  private void compDoStatement(){
    write(next());// do
    compSubroutineCall();
    write(next());//;
  }
  private void compReturnStatement(){
    write(next());//return
    if(checkExpression())compExpression();
    write(next());//;
  }

  private boolean checkExpression(){
    return checkTerm();
  }
  private void compExpression(){

  }

  // TODO:未実装
  private boolean checkTerm(){
    return false;
  }

  private void compParameterList(){
    result += "<parameterList>\n";
    if(checkParameterList()) {
      write(next());// type
      compVarName();
      while (get().check(",")){
        write(next());//,
        write(next());//type
        compVarName();
      }
    }
    result += "</parameterList>\n";
  }
  private boolean checkParameterList(){
    return get().isType();
  }

  private CToken get() {
    return tokens.get(n);
  }

  private CToken next() {
    return tokens.get(n++);
  }

  private void write(CToken ct) {
    result += "<" + ct.type + "> " + ct.val + " </" + ct.type + ">\n";
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
      this.val = m.group(2).trim();
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
}
