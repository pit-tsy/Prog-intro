package markup;

import java.util.List;

public abstract class Markup extends ModifiedText implements ReadableElement {
    protected Markup(List<ReadableElement> elems) {
        super(elems);
    }
}
