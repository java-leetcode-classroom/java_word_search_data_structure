# java_word_search_data_structure

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the `WordDictionary` class:

- `WordDictionary()` Initializes the object.
- `void addWord(word)` Adds `word` to the data structure, it can be matched later.
- `bool search(word)` Returns `true` if there is any string in the data structure that matches `word` or `false` otherwise. `word` may contain dots `'.'` where dots can be matched with any letter.

## Examples

**Example:**

```
Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True

```

**Constraints:**

- `1 <= word.length <= 25`
- `word` in `addWord` consists of lowercase English letters.
- `word` in `search` consist of `'.'` or lowercase English letters.
- There will be at most `3` dots in `word` for `search` queries.
- At most $`10^4`$ calls will be made to `addWord` and `search`.

## 解析

題目要設計一個資料結構 WordDictionary，內部需要實作以下方法

1. Constructor: 用來建構 WordDictionary
2. addWord(word string): 用來新增 word 到 WordDictionary
3. search(word string): 用來搜尋 word 是否已經存在 WordDictionary

要注意的是當 字元使用 ‘.’ 代表任意字元

舉例來說: ‘b..’ 代表所有以 b 為首 長度為 3 的字串

如果不考慮 ‘.’ 的狀況，使用 Trie 及可以處理

但考慮到 ‘.’ 需要對字串搜尋做特殊處理

參考下圖:

![](https://i.imgur.com/6tEhLkm.png)

針對搜尋的 word 字串拆已每個字元 char 做搜尋

當字元搜訊 index 與字串長度相等則回傳 true

當遇到字元是 ‘.’

則把當下結點的所有 children 做 loop , 以下個字元index 開始做字元比對

當字元不是 ‘.’

則檢查是否有這個字元的 edge, 如果沒有則回傳 false

如果有，則以這個字元的 edge 所指向的結點作為root 往下一個字元index 繼續做比對

## 程式碼

```java
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

```
## 困難點

1. 在處理 "." 需要透過 DFS 的方式把所有可能值搜尋過一遍

## Solve Point

- [x]  Understand what problem need to solve
- [x]  Analysis Complexity