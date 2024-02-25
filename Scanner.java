import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class Scanner {
    private final Reader reader;
    private final String lineSep = System.lineSeparator();
    private final Checker checker;
    private final char[] block = new char[1024];
    private final StringBuilder whiteSpaces = new StringBuilder();
    private int blockPtr;
    private int read = 0;
    private StringBuilder line = new StringBuilder();
    private int linePtr = 0;
    private StringBuilder next = new StringBuilder();

    public Scanner(InputStream source) throws IOException {
        this.reader = new InputStreamReader(source);
        this.checker = new StandartChecker();
    }

    public Scanner(InputStream source, Checker checker) throws IOException {
        this.reader = new InputStreamReader(source);
        this.checker = checker;
    }


    public Scanner(String source) throws IOException {
        this.reader = new StringReader(source);
        this.checker = new StandartChecker();
    }

    public Scanner(String source, Checker checker) throws IOException {
        this.reader = new StringReader(source);
        this.checker = checker;
    }

    public Scanner(File source) throws IOException {
        this.reader = new FileReader(source);
        this.checker = new StandartChecker();
    }

    public Scanner(File source, Checker checker) throws IOException {
        this.reader = new FileReader(source);
        this.checker = checker;
    }

    private int read() throws IOException {
        if (read == -1) return -1;
        if (linePtr < line.length()) {
            return line.charAt(linePtr++);
        }
        if (blockPtr < read) {
            return block[blockPtr++];
        }

        try {
            blockPtr = 0;
            read = reader.read(block);
            if (read == -1) return -1;
            return block[blockPtr++];
        } catch (IOException e) {
            return read = -1;
        }
    }

    private int readFromLine() {
        if (linePtr < line.length()) {
            return line.charAt(linePtr++);
        } else {
            return -1;
        }
    }

    public boolean hasNext() throws IOException {
        if (next.isEmpty()) {
            while (true) {
                int ch = read();
                if (ch == -1) break;
                if (checker.inToken((char) ch)) {
                    next.append((char) ch);
                    break;
                }
                whiteSpaces.append((char) ch);
            }
            while (true) {
                int ch = read();
                if (ch == -1 || !checker.inToken((char) ch)) break;
                next.append((char) ch);
            }
        }

        return !next.isEmpty();
    }

    public String next() throws IOException {
        if (!hasNext()) throw new NoSuchElementException("Not found next token!");

        String res = next.toString();
        next.setLength(0);
        whiteSpaces.setLength(0);
        return res;
    }

    public boolean hasNextInt() throws IOException {
        if (!hasNext()) return false;
        String token = next.toString();
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int nextInt() throws IOException {
        try {
            return Integer.parseInt(next());
        } catch (NumberFormatException e) {
            throw new InputMismatchException(e.getMessage());
        }
    }

    private boolean endIsLineSeparator(StringBuilder str) {
        return str.length() >= lineSep.length() && str.substring(str.length() - lineSep.length()).equals(lineSep);
    }

    public boolean hasNextLine() throws IOException {
        if (line.isEmpty() || linePtr >= line.length()) {
            StringBuilder currentString = new StringBuilder();
            int ch = read();
            while (ch != -1) {
                currentString.append((char) ch);
                if (endIsLineSeparator(currentString)) {
                    currentString.delete(currentString.length() - lineSep.length(), currentString.length() - 1);
                    line = currentString;
                    linePtr = 0;
                    return true;
                }
                ch = read();
            }

            line = currentString;
            linePtr = 0;
            return !line.isEmpty();
        } else {
            return true;
        }
    }

    public boolean hasNextInLine() throws IOException {
        if (next.isEmpty()) {
            while (true) {
                int ch = readFromLine();
                if (ch == -1) break;
                if (checker.inToken((char) ch)) {
                    next.append((char) ch);
                    break;
                }
                whiteSpaces.append((char) ch);
            }
            while (true) {
                int ch = readFromLine();
                if (ch == -1 || !checker.inToken((char) ch)) break;
                next.append((char) ch);
            }
        }

        return !next.isEmpty();
    }

    public String nextLine() throws IOException {
        if (!hasNextLine()) throw new NoSuchElementException("Not found next line!");

        String res;
        if (!next.isEmpty()) {
            res = whiteSpaces.toString() + next + line.toString();
            next = new StringBuilder();
            whiteSpaces.setLength(0);
        } else {
            res = line.toString();
        }
        line.setLength(0);
        linePtr = 0;
        return res;
    }

    public void close() throws IOException {
        reader.close();
    }

    public interface Checker {
        boolean inToken(char x);
    }

    private static class StandartChecker implements Checker {
        @Override
        public boolean inToken(char x) {
            return !Character.isWhitespace(x);
        }
    }
}