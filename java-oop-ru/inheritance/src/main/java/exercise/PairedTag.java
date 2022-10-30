package exercise;

import java.util.Map;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> tags;

    public PairedTag(String tagName, Map<String, String> tagAttributes, String body, List<Tag> tags) {
        super(tagName, tagAttributes);
        this.body = body;
        this.tags = tags;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("", "", "</" + super.getTagName() + ">");
        sj.add(super.toString()).add(body);
        tags.forEach(tag -> sj.add(tag.toString()));
        return sj.toString();
    }
}
// END
