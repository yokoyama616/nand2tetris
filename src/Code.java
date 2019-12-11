import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Code {
    static String fileName;
    private List<CodeCommand> commands = new ArrayList<>();

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("args error");
            return;
        }
        var code = new Code();
        code.read(args[0]);
        code.write(args[1], code.convert());
        System.out.println("end");
    }

    public Code() {
    }

    // ファイル読み込み
    private void read(String filePath) {
        File file = new File(filePath);
        try {
            fileName = file.getName().replaceAll("\\..*", "");
            String text;
            if (file.isDirectory()) {
                // ブートストラップ呼び出し
                commands.add(new CodeCommand("init"));
                commands.add(new CodeCommand("call Sys.init 0"));
                for (var f : Objects.requireNonNull(file.listFiles())) {
                    // vmファイルを読み込む
                    if (f.getName().contains(".vm")) {
                        readFile(f);
                    }
                }
            } else {
                readFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFile(File file) throws IOException {
        String text = "";
        var fr = new FileReader(file);
        fileName = file.getName().replaceAll("\\..*", "");
        BufferedReader br = new BufferedReader(fr);

        while ((text = br.readLine()) != null) {
            text = text.replaceAll("//.*", "");
            text = text.replaceAll("   *", "");
            if (text.equals("")) continue;
            commands.add(new CodeCommand(text));
        }
        br.close();
    }

    private String convert() {
        StringBuilder output = new StringBuilder();
        for (CodeCommand c : commands) {
            output.append(c.comment()).append(c.convert());
        }
        // System.out.println(output.toString());
        return output.toString();
    }

    // ファイル書き込み
    private void write(String path, String txt) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            writer.append(txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class CodeCommand {
    private static int ra = 0;
    private static int eqCnt = 0;
    private static int gtCnt = 0;
    private static int ltCnt = 0;

    private static int[] counter;
    private String txt;
    private String[] args;

    CodeCommand(String command) {
        txt = command;
        args = command.split(" ");
    }

    public String comment() {
        return "// " + txt + "\n";
    }

    public String convert() {
        switch (args[0]) {
            case "add":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@SP", "AM=M-1", "M=D+M", "@SP", "M=M+1"});
            case "sub":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@SP", "AM=M-1", "M=M-D", "@SP", "M=M+1"});
            case "neg":
                return contact(new String[]{"@SP", "A=M-1", "M=-M"});
            case "eq":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@SP", "AM=M-1", "D=M-D", "@EQ" + (++eqCnt), "D;JEQ", "@SP", "A=M", "M=0", "@SKIP_EQ" + eqCnt, "0;JMP", "(EQ" + eqCnt + ")", "@SP", "A=M", "M=-1", "(SKIP_EQ" + eqCnt + ")", "@SP", "M=M+1"});
            case "gt":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@SP", "AM=M-1", "D=M-D", "@GT" + (++gtCnt), "D;JGT", "@SP", "A=M", "M=0", "@SKIP_GT" + gtCnt, "0;JMP", "(GT" + gtCnt + ")", "@SP", "A=M", "M=-1", "(SKIP_GT" + gtCnt + ")", "@SP", "M=M+1"});
            case "lt":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@SP", "AM=M-1", "D=M-D", "@LT" + (++ltCnt), "D;JLT", "@SP", "A=M", "M=0", "@SKIP_LT" + ltCnt, "0;JMP", "(LT" + ltCnt + ")", "@SP", "A=M", "M=-1", "(SKIP_LT" + ltCnt + ")", "@SP", "M=M+1"});
            case "and":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@SP", "AM=M-1", "M=D&M", "@SP", "M=M+1",});
            case "or":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@SP", "AM=M-1", "M=D|M", "@SP", "M=M+1"});
            case "not":
                return contact(new String[]{"@SP", "A=M-1", "M=!M"});
            case "push":
                return convertPush(args[1], args[2]);
            case "pop":
                return convertPop();
            case "label":
                return contact(new String[]{"(" + args[1] + ")"});
            case "goto":
                return contact(new String[]{"@" + args[1], "0;JMP"});
            case "if-goto":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@" + args[1], "D;JNE"});
            case "function":
                StringBuilder r = new StringBuilder(contact(new String[]{"(" + args[1] + ")"}));
                var loop = Integer.parseInt(args[2]);
                for (var i = 0; i < loop; i++) {
                    r.append(convertPush("constant", "0"));
                }
                return r.toString();
            case "return":
                return contact(new String[]{
                        "// FRAME = LCL", "@LCL", "D=M", "@R13", "M=D",
                        "// RET = *(FRAME-5)", "@5", "A=D-A", "D=M", "@R14", "M=D",
                        "// *ARG = POP()", "@ARG", "D=M", "@R15", "M=D", "@SP", "AM=M-1", "D=M", "@R15", "A=M", "M=D",
                        "// SP = ARG + 1", "@ARG", "D=M", "@SP", "M=D+1",
                        "// THAT = *(FRAME-1)", "@R13", "AM=M-1", "D=M", "@THAT", "M=D",
                        "// THIS = *(FRAME-2)", "@R13", "AM=M-1", "D=M", "@THIS", "M=D",
                        "// ARG = *(FRAME-3)", "@R13", "AM=M-1", "D=M", "@ARG", "M=D",
                        "// LCL = *(FRAME-4)", "@R13", "AM=M-1", "D=M", "@LCL", "M=D",
                        "// goto_RET", "@R14", "A=M", "0;JMP"});
            case "call":
                var r = "RET_ADDRESS_"+(++ra);
                return contact(new String[]{
                        "// push_return-address", "@" + r, "D=A", "@SP", "A=M", "M=D", "@SP", "M=M+1",
                        "// push_LCL", "@LCL", "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1",
                        "// push_ARG", "@ARG", "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1",
                        "// push_THIS", "@THIS", "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1",
                        "// push_THAT", "@THAT", "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1",
                        "// ARG = SP-n-5", "@SP", "D=M", "@5", "D=D-A", "@" + args[2], "D=D-A", "@ARG", "M=D",
                        "// LCL = SP", "@SP", "D=M", "@LCL", "M=D",
                        "// goto_f", "@" + args[1], "0;JMP",
                        "// (" + r + ")", "(" + r + ")"});
            case "init":
                return contact(new String[]{"@256", "D=A", "@SP", "M=D"});
        }
        return "";
    }

    private String convertPush(String l, String a) {
        switch (l) {
            case "constant":
                return contact(new String[]{"@" + a, "D=A", "@SP", "A=M", "M=D", "@SP", "M=M+1"});
            case "local":
                return contact(new String[]{"@" + a, "D=A", "@LCL", "A=M+D", "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1"});
            case "argument":
                return contact(new String[]{"@" + a, "D=A", "@ARG", "A=M+D", "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1"});
            case "this":
                return contact(new String[]{"@" + a, "D=A", "@THIS", "A=M+D", "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1"});
            case "that":
                return contact(new String[]{"@" + a, "D=A", "@THAT", "A=M+D", "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1"});
            case "pointer":
                return contact(new String[]{"@" + (3 + Integer.parseInt(a)), "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1"});
            case "temp":
                return contact(new String[]{"@" + (5 + Integer.parseInt(a)), "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1"});
            case "static":
                return contact(new String[]{"@" + (Code.fileName) + "." + a, "D=M", "@SP", "A=M", "M=D", "@SP", "M=M+1"});
        }
        return "";
    }

    private String convertPop() {
        switch (args[1]) {
            case "constant":
                return "";
            case "local":
                return contact(new String[]{"@" + args[2], "D=A", "@LCL", "D=M+D", "@R13", "M=D", "@SP", "AM=M-1", "D=M", "@R13", "A=M", "M=D"});
            case "argument":
                return contact(new String[]{"@" + args[2], "D=A", "@ARG", "D=M+D", "@R13", "M=D", "@SP", "AM=M-1", "D=M", "@R13", "A=M", "M=D"});
            case "this":
                return contact(new String[]{"@" + args[2], "D=A", "@THIS", "D=M+D", "@R13", "M=D", "@SP", "AM=M-1", "D=M", "@R13", "A=M", "M=D"});
            case "that":
                return contact(new String[]{"@" + args[2], "D=A", "@THAT", "D=M+D", "@R13", "M=D", "@SP", "AM=M-1", "D=M", "@R13", "A=M", "M=D"});
            case "pointer":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@" + (3 + Integer.parseInt(args[2])), "M=D"});
            case "temp":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@" + (5 + Integer.parseInt(args[2])), "M=D"});
            case "static":
                return contact(new String[]{"@SP", "AM=M-1", "D=M", "@" + (Code.fileName + "." + args[2]), "M=D"});
        }
        return "";
    }

    private static String contact(String[] asmCode) {
        StringBuilder ret = new StringBuilder();
        for (var asm : asmCode) {
            ret.append(asm).append("\n");
        }
        return ret.toString();
    }
}
