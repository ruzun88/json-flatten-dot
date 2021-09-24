import org.junit.jupiter.api.Test;
import org.ruzun.service.Flattener;

import java.util.*;

public class FlatMapTest {

    @Test
    public void 테스트() {
        Map<String, Object> me = new HashMap<>();
        Map<String, Object> background = new HashMap<>();
        Map<String, Object> family = new HashMap<>();
        family.put("wife", "jna");
        family.put("phone", 3675);
        background.put("family", family);

        me.put("name", "yj");
        me.put("height", 176.4);
        me.put("birthday", new Date());

        List<String> schools = new ArrayList<>();
        schools.add("wolpyeon elementary");
        schools.add("hakseong middle");
        background.put("schools", schools);
        family.put("null_thing", null);

        me.put("background", background);


        Flattener.flatMap(null, me);
    }
}
