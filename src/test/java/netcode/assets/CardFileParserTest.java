package netcode.assets;

import netcode.entities.CardType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.List;

public class CardFileParserTest {

    private final URL testCardFileUrl = getClass().getResource("/netcode/CardFileParser/testCardFile.txt");

    private CardFileLexer cardFileLexer;

    @Before
    public void setUp() throws Exception {
        this.cardFileLexer = new CardFileLexer();
    }

    @Test
    public void parseCardFile() throws Exception {
        final CardFileParser cardFileParser = new CardFileParser(cardFileLexer);
        cardFileParser.setCardFile(testCardFileUrl.getPath());
        final List<CardType> out = cardFileParser.parseCardFile();
        Assert.assertEquals(2, out.size());
        Assert.assertEquals("RogueCard", out.get(0).getCardName());
        Assert.assertEquals("Rogue", out.get(0).getCardProperties().get("Name").getValue());
        Assert.assertEquals("yes", out.get(0).getCardProperties().get("CanAttack").getValue());
        Assert.assertEquals("no", out.get(0).getCardProperties().get("isPowerUp").getValue());
        Assert.assertEquals("10", out.get(0).getCardProperties().get("HPMax").getValue());
        Assert.assertEquals("1", out.get(0).getCardProperties().get("HPMin").getValue());
        Assert.assertEquals("FireCard", out.get(1).getCardName());
        Assert.assertEquals("Fire", out.get(1).getCardProperties().get("Name").getValue());
        Assert.assertEquals("no", out.get(1).getCardProperties().get("CanAttack").getValue());
        Assert.assertEquals("yes", out.get(1).getCardProperties().get("isPowerUp").getValue());
    }

}