package markup;

import java.util.List;

public abstract class AbstractList implements ListCompatible {
    private final List<ListItem> elems;

    protected AbstractList(List<ListItem> elems) {
        this.elems = elems;
    }

    protected abstract String getOpenBBCodeTag();

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append(getOpenBBCodeTag());
        for (var elem : elems) {
            elem.toBBCode(builder);
        }
        builder.append("[/list]");
    }
}
