package netcode.assets;

import netcode.entities.CardProperty;
import netcode.entities.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CardFileParser {

    private String cardFile = getClass().getResource("/Cards.txt").getPath();

    private final CardFileLexer cardFileLexer;

    private int tokenIndex = 0;

    @Autowired
    public CardFileParser(final CardFileLexer cardFileLexer) {
        this.cardFileLexer = cardFileLexer;
    }

    public List<CardType> parseCardFile() {
        final List<CardType> cardTypes = new ArrayList<>();
        try (final FileInputStream fileInputStream = new FileInputStream(cardFile);
              BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            final List<Token> tokens = cardFileLexer.tokenize(br.lines());
            for (tokenIndex = 0; tokenIndex < tokens.size(); ) {
                final Token token = tokens.get(tokenIndex);
                if (!EmptyLineToken.TYPE.equals(token.getType())) {
                    if (NewCardToken.TYPE.equals(token.getType())) {
                        final Token peekToken = tokens.get(tokenIndex + 1);
                        if (CardPropertyToken.TYPE.equals(peekToken.getType())) {
                            cardTypes.add(parseCard(token, tokens));
                        } else {
                            tokenIndex++;
                        }
                    }
                } else {
                    tokenIndex++;
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return cardTypes;
    }

    private CardType parseCard(Token token, List<Token> tokens) {
        final CardType cardType = new CardType(((NewCardToken)token).getCardName());
        tokenIndex++;
        while (tokenIndex < tokens.size() && CardPropertyToken.TYPE.equals(tokens.get(tokenIndex).getType())) {
            final CardPropertyToken cardPropertyToken = (CardPropertyToken) tokens.get(tokenIndex);
            final CardProperty cardProperty = new CardProperty(cardPropertyToken.getKey(), cardPropertyToken.getValue());
            cardType.getCardProperties().put(cardProperty.getName(), cardProperty);
            tokenIndex++;
        }
        return cardType;
    }

    public void setCardFile(String cardFile) {
        this.cardFile = cardFile;
    }

}