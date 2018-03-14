package me.mcnamara.sean.trie;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Trie
{
	private Set<String> words = new HashSet<>();
	
    public boolean add(String word) {
    		return words.add(word.toLowerCase());
    }
    
    public boolean contains(String word) {
    		return words.contains(word.toLowerCase());
    }
    
    public List<String> search(String prefix) {
    		List<String> retval = new ArrayList<String>();
    		
    		return retval;
    }
}
