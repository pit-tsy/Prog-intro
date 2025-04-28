package expression.generic.parser;

import java.util.List;

import static java.lang.Math.min;

public class BaseParser {
    private static final char END = '\0';
    protected final CharSource source;
    private char ch = 0xffff;
    private StringBuilder takeWord = new StringBuilder();
    private int ptr = 0;

    protected BaseParser(final CharSource source) {
        this.source = source;
        take();
    }

    private char takeFromSource() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected char take() {
        if (!takeWord.isEmpty() && ptr >= min(60, takeWord.length())) {
            takeWord.delete(0, ptr);
            ptr = 0;
        }

        return ptr < takeWord.length() ? takeWord.charAt(ptr++) : takeFromSource();
    }

    private final boolean testFromSource(final char expected) {
        return ch == expected;
    }

    protected final boolean test(final char expected) {
        return ptr < takeWord.length() ? takeWord.charAt(ptr) == expected : testFromSource(expected);
    }

    protected final boolean test(final char... expected) {
        for (final char c : expected) {
            if (test(c)) return true;
        }
        return false;
    }

    protected final boolean test(final String expected) {
        for (int i = 0; i < expected.length(); i++) {
            if (ptr + i < takeWord.length()) {
                if (takeWord.charAt(ptr + i) != expected.charAt(i)) {
                    return false;
                }
            } else {
                if (!testFromSource(expected.charAt(i))) {
                    return false;
                } else {
                    takeWord.append(takeFromSource());
                }
            }
        }
        return true;
    }

    protected final boolean take(final String str) {
        if (test(str)) {
            ptr += str.length();
            return true;
        } else {
            return false;
        }
    }

    protected final String take(final List<String> vars) {
        int index = -1;
        for (int i = 0; i < vars.size(); i++) {
            String str = vars.get(i);
            if (test(str) && (index == -1 || vars.get(index).length() < str.length())) {
                index = i;
            }
        }

        if (index != -1) {
            ptr += vars.get(index).length();
            return vars.get(index);
        } else {
            return "";
        }
    }


    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    protected boolean nextIsWhiteSpace() {
        return Character.isWhitespace(ptr < takeWord.length() ? takeWord.charAt(ptr) : ch);
    }

    protected final boolean bufferIsClear() {
        return ptr >= takeWord.length();
    }


    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected boolean eof() {
        return take(END);
    }

    protected int getPos() {
        return source.getPos() + (takeWord.length() - ptr);
    }

    protected IllegalArgumentException error(final String message) {
        return source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
