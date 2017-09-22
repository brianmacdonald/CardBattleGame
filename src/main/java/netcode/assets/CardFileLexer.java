package netcode.assets;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CardFileLexer {

    public List<Token> tokenize(final Stream<String> lines) {
        return lines.map(CardFileLexer::tokenizeLine).collect(Collectors.toList());
    }

    private static Token tokenizeLine(final String line) {
        final String tokenString = StringUtils.trimAllWhitespace(line);
        if (StringUtils.isEmpty(tokenString)) {
            return new EmptyLineToken();
        } else if (line.startsWith("\t") || line.startsWith("  ")) {
            final String[] cardPropertyStrings = tokenString.split(":");
            return new CardPropertyToken(cardPropertyStrings[0], cardPropertyStrings[1]);
        } else {
            return new NewCardToken(line);
        }
    }

}
