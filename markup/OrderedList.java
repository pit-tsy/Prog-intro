package markup;

import java.util.List;

public class OrderedList extends AbstractList {
    public OrderedList(List<ListItem> elems) {
        super(elems);
    }

    @Override
    protected String getOpenBBCodeTag() {
        return "[list=1]";
    }
}
