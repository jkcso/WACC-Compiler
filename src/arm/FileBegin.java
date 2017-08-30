package arm;

import java.util.HashMap;
import java.util.Map;

public class FileBegin extends Instruction {

    private HashMap<Message, Label> messages = new HashMap<>();

    public void addMessage(Message message) {
        if (!messages.containsKey(message)) {
            messages.put(message, new Label(true));
        }
    }

    public Label getLabel(Message message) {

        for (Map.Entry<Message, Label> entry : messages.entrySet()) {
            if (entry.getKey().equals(message)) {
                return entry.getValue();
            }
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Message message : messages.keySet()) {

            String messageString = message.toString();
            int length = messageString.length();

            for (int i = 0; i < messageString.length(); i++) {
                if ((messageString.charAt(i) == '\\') && (messageString.charAt(i + 1) != '\\')) {
                    length--;
                }
            }

            sb.append(String.format("%s:\n\t\t.word %s\n\t\t.ascii \"%s\"\n\n", messages.get(message), length, message));
        }

        return String.format("%s.text\n\n.global main\n", (sb.length() == 0) ? "" : ".data\n\n" + sb + "\n");
    }

}
