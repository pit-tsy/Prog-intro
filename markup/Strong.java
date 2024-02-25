package markup;

import java.util.List;

public class Strong extends Markup {
    public Strong(List<ReadableElement> readableList) {
        super(readableList);
    }

    @Override
    protected String getMarkdownBracket() {
        return "__";
    }

    @Override
    protected String getOpenBBCodeTag() {
        return "[b]";
    }

    @Override
    protected String getCloseBBCodeTag() {
        return "[/b]";
    }
}
