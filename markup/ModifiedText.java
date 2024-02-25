package markup;

import java.util.List;

public abstract class ModifiedText implements Markdown, BBCode {
    private List<ReadableElement> readableList;

    protected ModifiedText(List<ReadableElement> list) {
        this.readableList = list;
    }

    protected String getMarkdownBracket() {
        return "";
    }

    ;

    protected String getCloseBBCodeTag() {
        return "";
    }

    ;

    protected String getOpenBBCodeTag() {
        return "";
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        builder.append(getMarkdownBracket());
        for (var element : readableList) {
            element.toMarkdown(builder);
        }
        builder.append(getMarkdownBracket());
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append(getOpenBBCodeTag());
        for (var element : readableList) {
            element.toBBCode(builder);
        }
        builder.append(getCloseBBCodeTag());
    }
}
