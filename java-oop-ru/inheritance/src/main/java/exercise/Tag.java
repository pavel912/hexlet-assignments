package exercise;

import java.util.HashMap;
import java.util.StringJoiner;
import java.util.Map;

// BEGIN
public abstract class Tag {
    private String tagName;
    private Map<String, String> tagAttributes;

    public Tag(String tagName, Map<String, String> tagAttributes) {
        this.tagName = tagName;
        this.tagAttributes = tagAttributes;
    }

    public String getTagName() {
        return tagName;
    }

    public Map<String, String> getTagAttributes() {
        return tagAttributes;
    }

    @Override
    public String toString() {
        if (tagAttributes.isEmpty()) {
            return String.format("<%s>", tagName);
        }
        return String.format("<%s %s>", tagName, attributesToString());
    }

    private String attributesToString() {
        StringJoiner sj = new StringJoiner(" ");
        tagAttributes.forEach((key, value) -> sj.add(key + "=" + "\"" + value + "\""));
        return sj.toString();
    }
}
// END
