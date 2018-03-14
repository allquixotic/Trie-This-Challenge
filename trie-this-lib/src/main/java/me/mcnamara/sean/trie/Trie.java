package me.mcnamara.sean.trie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

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
		
		TrieNode node = root;
		//First navigate down the tree to the end of the prefix
		for(int i = 0; i < prefix.length(); i++) {
			String subprefix = String.valueOf(prefix.charAt(i));
			TrieNode tmp = node.getChild(subprefix);
			if(tmp == null) {
				//There's nothing with this prefix in the tree
				return retval;
			}
			node = tmp;
		}
		
		//Now do a DFS traversal from this node on all nodes and construct words
		StringBuilder sb = new StringBuilder(prefix.substring(0, prefix.length() - 1));
		List<TrieNode> visitedNodes = new LinkedList<>();
		List<TrieNode> unvisitedNodes = new LinkedList<>();
		unvisitedNodes.add(node);
		
		while(!unvisitedNodes.isEmpty()) {
			node = unvisitedNodes.get(0);
			
			//Add all children not already visited to the list
            List<TrieNode> newNodes = node.getChildren()
                    .values().stream().filter(n -> !visitedNodes.contains(n)).collect(Collectors.toList());
            unvisitedNodes.addAll(0, newNodes);
			
			//Process the current node
            if(!visitedNodes.contains(node)) {
				sb.append(node.getValue());
				if(node.isWord()) {
					retval.add(sb.toString());
				}
				visitedNodes.add(node);
            }
            
            //Delete the current node from the buffer and the stack
            if(newNodes.size() == 0) {
            		sb.deleteCharAt(sb.length() - 1);
            		unvisitedNodes.remove(0);
            }
		}
		
		return retval;
	}
}
