import InfoNeeded.Section;
import Support.Term.*;
import org.junit.jupiter.api.*;

import static Support.Term.*;

public class SectionTest {

    @Test
    void hasTimeConflict_Test(){
        Section s1 = new Section();
        s1.setTitle("Test 101");
        s1.setTerm(TERM_1);
    }
}
