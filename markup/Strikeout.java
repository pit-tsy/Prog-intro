package markup;

import java.util.List;

public class Strikeout extends Markup {
    public Strikeout(List<ReadableElement> readableList) {
        super(readableList);
    }

    @Override
    protected String getMarkdownBracket() {
        return "~";
    }

    @Override
    protected String getOpenBBCodeTag() {
        return "[s]";
    }

    @Override
    protected String getCloseBBCodeTag() {
        return "[/s]";
    }
}
