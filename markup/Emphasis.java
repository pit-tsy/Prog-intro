package markup;

import java.util.List;

public class Emphasis extends Markup {
    public Emphasis(List<ReadableElement> readableList) {
        super(readableList);
    }

    @Override
    protected String getOpenBBCodeTag() {
        return "[i]";
    }

    @Override
    protected String getCloseBBCodeTag() {
        return "[/i]";
    }

    @Override
    protected String getMarkdownBracket() {
        return "*";
    }
}
