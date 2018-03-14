package me.mcnamara.sean.trie;

import java.util.ArrayList;
import java.util.List;

public class Trie {
	private TrieNode root = new TrieNode("", false);

	public boolean add(String word) {
		return !navigate(word, true);
	}

	public boolean contains(String word) {
		return navigate(word, false);
	}

	private boolean navigate(String word, boolean addIfMissing) {
		TrieNode node = root;
		boolean alreadyExisted = true;
		for (int i = 0; i < word.length(); i++) {
			String subword = String.valueOf(word.charAt(i));
			TrieNode tmp = node.getChild(subword);
			if (tmp == null) {
				alreadyExisted = false;
				if(!addIfMissing) {
					return alreadyExisted;
				}
				node = node.addChild(new TrieNode(subword, i == word.length() - 1));
			} else {
				node = tmp;
				if (i == word.length() - 1 && node.isWord() == false) {
					if(addIfMissing) {
						node.setIsWord(true);
					}
					alreadyExisted = false;
				}
			}
		}
		return alreadyExisted;
	}

	public List<String> search(String prefix) {
		List<String> retval = new ArrayList<String>();

		return retval;
	}
}
