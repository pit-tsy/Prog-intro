package markup;

public class Text implements ReadableElement {
    private final String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void toMarkdown(StringBuilder builder) {
        builder.append(text);
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        toMarkdown(builder);
    }
}