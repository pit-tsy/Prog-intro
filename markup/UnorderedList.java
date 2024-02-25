package markup;

import java.util.List;

public class UnorderedList extends AbstractList {
    public UnorderedList(List<ListItem> elems) {
        super(elems);
    }

    @Override
    protected String getOpenBBCodeTag() {
        return "[list]";
    }
}
