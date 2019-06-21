import java.io.IOException;

import org.junit.jupiter.api.Test;
import rmp.rmpScrapper;

public class rmpScrapperTest {
    private rmpScrapper rmps = new rmpScrapper();

    @Test
    public String scrapTest () throws IOException {
        return rmps.getProfInfo();
    }

    @Test
    public void scrap1Test() throws IOException {
        rmps.getProfScore();
    }
}
