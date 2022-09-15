import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WordDictionary {
  static class Node {
    HashMap<Character, Node> Children = new HashMap<>();
    boolean EndOfWord = false;
  }
  Node root;
  public WordDictionary() {
    root = new Node();
  }
  public void addWord(String word) {
    Node cur = root;
    int size = word.length();
    for (int pos = 0; pos < size; pos++) {
      char ch = word.charAt(pos);
      if (!cur.Children.containsKey(ch)) {
        cur.Children.put(ch, new Node());
      }
      cur = cur.Children.get(ch);
    }
    cur.EndOfWord = true;
  }
  public boolean search(String word) {
    return DFS(0, root, word);
  }
  public boolean DFS(int pos, Node node, String word) {
    Node cur = node;
    int size = word.length();
    if (pos == size) {
      return cur.EndOfWord;
    }
    // current ch
    char ch = word.charAt(pos);
    if (ch == '.') {
      Set<Map.Entry<Character, Node>> entrySet = node.Children.entrySet();
      for (Map.Entry<Character, Node> pair: entrySet) {
        if (DFS(pos+1, pair.getValue(), word)) {
          return true;
        }
      }
      return false;
    } else {
      if (!cur.Children.containsKey(ch)) {
        return false;
      }
      return DFS(pos+1, cur.Children.get(ch), word);
    }
  }
}
