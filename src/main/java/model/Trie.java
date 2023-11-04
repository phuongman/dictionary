package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


class TrieNode {

    private HashMap<Character, TrieNode> children;
    private boolean isWord;
    private Word word;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
        word = null;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }

    public boolean isWord() {
        return isWord;
    }

    public void setWord(boolean word) {
        isWord = word;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }
}
public class Trie {

    private TrieNode root;
    public Trie() {
        root = new TrieNode();
    }
    public void add(Word word) {
        TrieNode node = root;
        for (char c : word.getWordTarget().toCharArray()) {
            if (node.getChildren().containsKey(c)) {
                node = node.getChildren().get(c);
            } else {
                TrieNode newNode = new TrieNode();
                node.getChildren().put(c, newNode);
                node = newNode;
            }
        }
        node.setWord(true);
        node.setWord(word);
    }

    public Word lookup(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.getChildren().containsKey(c)) {
                node = node.getChildren().get(c);
            } else {
                return null;
            }
        }
        if (node.isWord()) {
            return node.getWord();
        } else {
            return null;
        }
    }

    private void collectWordsFromNode(TrieNode node, String currentWord, List<String> result) {
        if (node.isWord()) {
            result.add(currentWord);
        }

        for (char c = 32; c <= 45; c+=13) {
            if (node.getChildren().containsKey(c)) {
                collectWordsFromNode(node.getChildren().get(c), currentWord + c, result);
            }
        }
        for (char c = 48; c <= 57; c++) {
            if (node.getChildren().containsKey(c)) {
                collectWordsFromNode(node.getChildren().get(c), currentWord + c, result);
            }
        }
        for (char c = 97; c <= 122; c++) {
            if (node.getChildren().containsKey(c)) {
                collectWordsFromNode(node.getChildren().get(c), currentWord + c, result);
            }
        }
    }
    public List<String> search(String preWord) {
        List<String> result = new ArrayList<>();
        TrieNode node = root;

        for (char c : preWord.toCharArray()) {
            if (node.getChildren().containsKey(c)) {
                node = node.getChildren().get(c);
            } else {
                return result;
            }
        }

        if(node != null) collectWordsFromNode(node, preWord, result);
        return result;
    }

    private void collectWords(TrieNode node, List<Word> result) {
        if (node.isWord()) {
            result.add(node.getWord());
            return;
        }
        for (TrieNode child : node.getChildren().values()) {
            collectWords(child, result);
        }
    }
}
