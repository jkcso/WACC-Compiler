package arm;

public class Message {

    private String message;
    private boolean isUserDefined;

    public Message(String message, boolean isUserDefined) {
        this.message = removeExtraQuotes(message);
        this.isUserDefined = isUserDefined;
    }

    private String removeExtraQuotes(String message) {
        return message.replaceAll("^\"|\"$", "");
    }

    @Override
    public boolean equals(Object o) {
        return o != null && o.getClass().equals(getClass()) && message.equals(((Message) o).message);
    }

    @Override
    public int hashCode() {
        if (isUserDefined) {
            return super.hashCode();
        } else {
            return message.hashCode();
        }
    }

    @Override
    public String toString() {
        return message;
    }
}
