package md2html;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

import static java.lang.Math.min;

public class HtmlParser {
    private final static Map<Character, String> SPECIAL_SYMBOLS = Map.of(
            '<', "&lt",
            '>', "&gt",
            '&', "&amp"
    );

    private static void addSymbol(StringBuilder builder, char c) {
        builder.append(SPECIAL_SYMBOLS.getOrDefault(c, "" + c));
    }

    private static void addTags(StringBuilder result, StringBuilder block, Tags tags) {
        tags.removeSingle();
        for (int i = 0; i < block.length(); ++i) {
            if (block.charAt(i) == '\\' && i < block.length() - 1) {
                addSymbol(result, block.charAt(i + 1));
                i += 1;
                continue;
            }

            boolean isTag = false;
            for (int tagLen = min(2, block.length() - i); tagLen > 0; --tagLen) {
                String substr = block.substring(i, i + tagLen);
                if (tags.existTag(substr)) {
                    isTag = true;
                    result.append(tags.getHtmlTag(substr));
                    i += tagLen - 1;
                    break;
                }
            }

            if (!isTag) {
                addSymbol(result, block.charAt(i));
            }
        }
    }

    private static void parseHtmlBlock(StringBuilder text, String line, String BlockTag, BufferedReader reader) throws IOException {
        text.append('<').append(BlockTag).append('>');

        Tags tags = new Tags();
        StringBuilder block = new StringBuilder();
        while (true) {
            for (int i = 0; i < line.length(); ++i) {
                if (line.charAt(i) == '\\' && i < line.length() - 1) {
                    block.append(line.charAt(i + 1));
                    i += 1;
                    continue;
                }

                boolean isTag = false;
                for (int tagLen = min(Tags.MAX_TAG_LEN, line.length() - i); tagLen > 0; --tagLen) {
                    String substr = line.substring(i, i + tagLen);
                    if (tags.isTag(substr)) {
                        isTag = true;
                        tags.incrementTag(substr);
                        i += tagLen - 1;
                        block.append(substr);
                        break;
                    }
                }

                if (!isTag) {
                    block.append(line.charAt(i));
                }
            }

            line = reader.readLine();
            if (line == null || line.isEmpty()) {
                break;
            } else {
                block.append(System.lineSeparator());
            }
        }
        addTags(text, block, tags);

        text.append("</").append(BlockTag).append('>');
        text.append(System.lineSeparator());
    }

    public static void parseHtml(BufferedReader reader, StringBuilder text) throws IOException {
        String line = reader.readLine();
        while (line != null) {
            if (!line.isEmpty()) {
                int level = 0;
                while (level < line.length() && line.charAt(level) == '#') {
                    level++;
                }
                if (level < line.length() && line.charAt(level) != ' ') {
                    level = 0;
                }

                if (level == 0) {
                    parseHtmlBlock(text, line, "p", reader);
                } else {
                    parseHtmlBlock(text, line.substring(level + 1), "h" + level, reader);
                }
            }
            line = reader.readLine();
        }
    }

}
