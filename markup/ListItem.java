package markup;

import java.util.List;

public class ListItem implements BBCode {
    private final List<ListCompatible> elems;

    public ListItem(List<ListCompatible> elems) {
        this.elems = elems;
    }

    @Override
    public void toBBCode(StringBuilder builder) {
        builder.append("[*]");
        for (var elem : elems) {
            elem.toBBCode(builder);
        }
    }
}
