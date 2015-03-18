package mobi.idappthat.campusconnect.Model;

/**
 * Created by Cameron on 3/18/15.
 */
public class User {

    private String parseId;
    private String name;

    public User(String parseId, String name) {
        this.parseId = parseId;
        this.name = name;
    }

    public String getParseId() {
        return parseId;
    }

    public void setParseId(String parseId) {
        this.parseId = parseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
