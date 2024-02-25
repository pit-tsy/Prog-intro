package markup;

import java.util.List;

public class Paragraph extends ModifiedText implements ListCompatible {
    public Paragraph(List<ReadableElement> readableList) {
        super(readableList);
    }
}
