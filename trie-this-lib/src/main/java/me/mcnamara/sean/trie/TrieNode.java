package me.mcnamara.sean.trie;

import java.util.SortedMap;
import java.util.TreeMap;

import org.assertj.core.util.Arrays;

class TrieNode {
	private final String value;
	private boolean _isWord;
	private final SortedMap<String, TrieNode> _children;
	
	public TrieNode(String value, boolean isWord, TrieNode ... children) {
		this.value = value;
		this._isWord = isWord;
		this._children = new TreeMap<>();
		Arrays.asList(children).forEach((child) -> _children.put(((TrieNode) child).getValue(), (TrieNode) child));
	}
	
	public SortedMap<String, TrieNode> getChildren() {
		return _children;
	}
	
	public String getValue() {
		return value;
	}
	
	public boolean isWord() {
		return _isWord;
	}
	
	public void setIsWord(boolean isWord) {
		_isWord = isWord;
	}
	
	public TrieNode getChild(String s) {
		return _children.get(s);
	}
	
	public TrieNode addChild(TrieNode tn) {
		_children.put(tn.getValue(), tn);
		return tn;
	}
}
