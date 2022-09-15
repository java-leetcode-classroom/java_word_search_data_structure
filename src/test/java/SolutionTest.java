import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
  final private Solution sol = new Solution();
  @Test
  void runWithExample1() {
    assertArrayEquals(new String[]{"null", "null", "null", "null", "false", "true", "true", "true"},
        sol.RunWith(new String[]{"WordDictionary", "addWord", "addWord", "addWord", "search", "search", "search", "search"},
            new String[][]{
                null, new String[]{"bad"}, new String[]{"dad"}, new String[]{"mad"}, new String[]{"pad"},new String[]{"bad"}, new String[]{".ad"}, new String[]{"b.."},
            }));
  }
}