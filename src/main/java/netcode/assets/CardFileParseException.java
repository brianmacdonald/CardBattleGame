package netcode.assets;

public class CardFileParseException extends Error {

    public CardFileParseException(final String line, final int lineNumber, final String error) {
        super("Error parsing line: "  + line + ":" + lineNumber + "\n" + error);
    }

}
