package md2html;

import java.util.HashMap;
import java.util.Map;

public class Tags {
    private static final Map<String, String> HTML_TAGS = Map.of(
            "*", "em",
            "_", "em",
            "**", "strong",
            "__", "strong",
            "--", "s",
            "`", "code",
            ">>", "ins",
            "<<", "ins",
            "}}", "del",
            "{{", "del"
    );
    private static final Map<String, String> OPEN_TAGS = Map.of(
            "<<", ">>",
            "}}", "{{"
    );
    private static final Map<String, String> CLOSE_TAGS = Map.of(
            ">>", "<<",
            "{{", "}}"
    );
    public final static int MAX_TAG_LEN = 2;
    private HashMap<String, Integer> tags = new HashMap<>();

    public Tags() {
        for (String key : HTML_TAGS.keySet()) {
            tags.put(key, 0);
        }
    }


    public boolean isTag(String str) {
        return tags.containsKey(str);
    }

    public boolean isAsymTag(String str) {
        return OPEN_TAGS.containsKey(str) || CLOSE_TAGS.containsKey(str);
    }

    private boolean existAsymTag(String str) {
        int cnt = tags.getOrDefault(str, -1);
        if (CLOSE_TAGS.containsKey(str)) {
            String openTag = CLOSE_TAGS.get(str);
            if (tags.get(openTag) == 0) {
                if (cnt > 0) {
                    tags.replace(str, cnt - 1);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return OPEN_TAGS.containsKey(str) && cnt < tags.get(OPEN_TAGS.get(str));
        }
    }

    public boolean existTag(String str) {
        if (OPEN_TAGS.containsKey(str) || CLOSE_TAGS.containsKey(str)) {
            return existAsymTag(str);
        } else {
            return tags.getOrDefault(str, -1) > 0;
        }
    }

    private String getOpenHtmlTag(String str) {
        return "<" + HTML_TAGS.get(str) + ">";
    }

    private String getCloseHtmlTag(String str) {
        return "</" + HTML_TAGS.get(str) + ">";
    }


    public String getHtmlTag(String str) {
        int tagCnt = tags.getOrDefault(str, 0);
        if (OPEN_TAGS.containsKey(str)) {
            String closeTag = OPEN_TAGS.get(str);
            if (tagCnt < tags.get(closeTag)) {
                tags.replace(str, tagCnt + 1);
                return getOpenHtmlTag(str);
            }
        } else if (CLOSE_TAGS.containsKey(str)) {
            String openTag = CLOSE_TAGS.get(str);
            if (tags.get(openTag) > 0) {
                tags.replace(str, tagCnt - 1);
                tags.replace(openTag, tags.get(openTag) - 1);
                return getCloseHtmlTag(str);
            }
        } else if (tagCnt > 0) {
            tags.replace(str, tagCnt - 1);
            if (tagCnt % 2 == 0) {
                return getOpenHtmlTag(str);
            } else {
                return getCloseHtmlTag(str);
            }
        }

        return str;
    }

    public void incrementTag(String str) {
        int cnt = tags.getOrDefault(str, -1);
        if (!OPEN_TAGS.containsKey(str) && cnt != -1) {
            tags.put(str, cnt + 1);
        }
    }

    public void removeSingle() {
        for (var entry : tags.entrySet()) {
            if (!isAsymTag(entry.getKey()) && entry.getValue() % 2 == 1) {
                tags.put(entry.getKey(), entry.getValue() - 1);
            }
        }
    }
}
