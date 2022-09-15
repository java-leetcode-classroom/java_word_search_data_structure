public class Solution {
  public String[] RunWith(String[] commands, String[][] payloads) {
    int nCommand = commands.length;
    String[] result = new String[nCommand];
    result[0] = "null";
    WordDictionary dict = new WordDictionary();
    for (int pos = 1; pos < nCommand; pos++) {
      String command = commands[pos];
      String payload = payloads[pos][0];
      switch (command) {
        case "addWord":
          dict.addWord(payload);
          result[pos] = "null";
          break;
        case "search":
          result[pos] = String.valueOf(dict.search(payload));
          break;
      }
    }
    return result;
  }
}
