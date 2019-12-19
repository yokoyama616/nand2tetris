import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JackAnalyzer {
    private List<JackTokenizer> tokenizerList;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("args error");
            return;
        }
        var jackAnalyzer = new JackAnalyzer();
        jackAnalyzer.read(args[0]);
        jackAnalyzer.write();
    }

    private JackAnalyzer() {
        tokenizerList = new ArrayList<>();
    }

    // ファイル読み込み
    private void read(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.isDirectory()) {
            for (var f : Objects.requireNonNull(file.listFiles())) {
                if (f.getName().contains(".jack")) {
                    tokenizerList.add(new JackTokenizer(f));
                }
            }
        } else {
            tokenizerList.add(new JackTokenizer(file));
        }
    }

    /**
     * ファイル書き込み
     */
    private void write(){
        for (var tokenizer:this.tokenizerList) {
            tokenizer.write();
        };
    }
}
