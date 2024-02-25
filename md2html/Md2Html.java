package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: you need to enter two paths to the files");
            return;
        }

        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(args[0]),
                        StandardCharsets.UTF_8
                )
        )) {
            HtmlParser.parseHtml(reader, result);
        } catch (IOException e) {
            System.err.println("Error with reading input-file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        StandardCharsets.UTF_8
                )
        )) {
            writer.write(result.toString());
        } catch (IOException e) {
            System.err.println("Error with writing output file: " + e.getMessage());
        }
    }
}
