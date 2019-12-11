import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Hack {
  List<Command> commands = new ArrayList<>();
  public static void main(String[] args){
    if(args.length != 2) {
      System.out.println("args error");
      return;
    }
    var hack = new Hack();
    hack.read(args[0]);
    hack.write(args[1], hack.convert());
  }

  public Hack(){}

  void read(String filePath){
    File file = new File(filePath);
    FileReader fr = null;
    try {
      fr = new FileReader(file);
      BufferedReader br = new BufferedReader(fr);

      String text;
      while ((text = br.readLine()) != null) {
        if(text.contains("//")) continue;
        text = text.replaceAll(" ", "");
        if(text.equals("")) continue;
        commands.add(new Command(text));
      }
      br.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  String convert(){
    StringBuilder output = new StringBuilder();
    for (Command c : commands) {
      output.append(c.toBinary()).append("\n");
    }
    // System.out.println(output.toString());
    return output.toString();
  }

  void write(String path, String txt){
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
      writer.append(txt);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class Command{
  enum TYPE {
    NONE,
    A,
    C,
    L
  }

  private String txt;
  Command(String command){
    txt = command;
  }

  public TYPE getType(){
    if(txt.contains("@")) return TYPE.A;
    if(txt.contains("()")) return TYPE.L;
    if(txt.contains("=") || txt.contains(";"))return TYPE.C;

    System.out.println(txt);
    return TYPE.NONE;
  }

  public String symbol(){
   return txt.replaceAll("@","").replaceAll("\\(","").replaceAll("\\)","");
  }

  public String dest(){
    if (!txt.contains("=")) return "null"; // =がない場合はnull
    return txt.substring(0, txt.indexOf("="));// =の左辺を返す
  }

  public String comp(){
    var d = txt.substring(txt.indexOf("=") + 1); // =まで除外
    return d.contains(";") ? d.substring(0, d.indexOf(";")) : d; // ;まで取得
  }

  public String jump(){
    if (!txt.contains(";")) return "null"; // ;がないならnull
    return txt.substring(txt.indexOf(";") + 1);
  }
  public String codeA(){
    return String.format("%16s", Integer.toBinaryString(Integer.parseInt(symbol()))).replaceAll(" ","0");
  }
  public String codeDest(){
    var r = "";
    var d = dest();
    r += d.contains("A") ? "1" : "0";
    r += d.contains("D") ? "1" : "0";
    r += d.contains("M") ? "1" : "0";
    return r;
  }
  public String codeComp() {
    var c = comp();
    var r = "";
    r += c.contains("M") ? "1" : "0"; // Mがある場合はaは1
    // 難しいので対応表作る
    switch (c.replace("M", "A")) {// MをAに変換
      case "0":
        r += "101010";
        break;
      case "1":
        r += "111111";
        break;
      case "-1":
        r += "111010";
        break;
      case "D":
        r += "001100";
        break;
      case "A":
        r += "110000";
        break;
      case "!D":
        r += "001101";
        break;
      case "!A":
        r += "110001";
        break;
      case "-D":
        r += "001111";
        break;
      case "-A":
        r += "110011";
        break;
      case "D+1":
        r += "011111";
        break;
      case "A+1":
        r += "110111";
        break;
      case "D-1":
        r += "001110";
        break;
      case "A-1":
        r += "110010";
        break;
      case "D+A":
        r += "000010";
        break;
      case "D-A":
        r += "010011";
        break;
      case "A-D":
        r += "000111";
        break;
      case "D&A":
        r += "000000";
        break;
      case "D|A":
        r += "010101";
        break;
    }
    return r;
  }
  public String codeJump() {
    switch (jump()) {
      case "null":
        return "000";
      case "JGT":
        return "001";
      case "JEQ":
        return "010";
      case "JGE":
        return "011";
      case "JLT":
        return "100";
      case "JNE":
        return "101";
      case "JLE":
        return "110";
      case "JMP":
        return "111";
    }
    return "";
  }
  public String toBinary(){
    switch(getType()){
      case A:return codeA();
      case C:return "111"  + codeComp() + codeDest()+ codeJump();
      case L:return "";
    }
    return "";
  }
}
