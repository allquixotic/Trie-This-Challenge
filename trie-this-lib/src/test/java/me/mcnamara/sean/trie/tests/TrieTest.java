package me.mcnamara.sean.trie.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import me.mcnamara.sean.trie.Trie;

/**
 * Unit test suite for Trie class with prefix search functionality.
 */
public class TrieTest
{
    private Trie trie;
    
    @Before
    public void setUp() {
    		this.trie = new Trie();
    }
    
    //****Unit tests for explicitly stated requirements.****
    
    //Requirement: Adding a word returns true.
    @Test
    public void testAddWordEmptyTree_returnsTrue() {
    		assertThat(trie.add("hello")).isTrue();
    }
    
    //Requirement: Adding duplicate word returns false;
    @Test
    public void testAddDuplicateWord_returnsFalse() {
    		assertThat(trie.add("hello")).isTrue();
    		assertThat(trie.add("hello")).isFalse();
    }
    
    //Requirement: Contains returns true if word is in the trie.
    @Test
    public void testContainsLookForExistingWord_returnsTrue() {
    		trie.add("hello");
    		assertThat(trie.contains("hello")).isTrue();
    }
    
    //Requirement: Contains returns false if word doesn't exist in the trie. 
    //This test also makes sure it doesn't return true just because the input is a prefix of a word that exists.
    @Test
    public void testContainsLookForNonWord_returnsFalse() {
    		trie.add("hello");
    		assertThat(trie.contains("hel")).isFalse();
    }
    
    //Requirement: no results for search prefix that doesn't exist.
    @Test
    public void testSearchZeroResults() {
    		List.of("hello", "hellothere", "hellfire", "hem", "hew", "hi", "bye").forEach(word -> trie.add(word));		
		List<String> words = trie.search("ha");
		assertThat(words.size()).isEqualTo(0);
    }
    
    //Requirement: happy path search.
    @Test
    public void testSearchThreeResults() {
    		List.of("hello", "hellothere", "hellfire", "hem", "hew", "hi", "bye").forEach(word -> trie.add(word));    		
    		List<String> words = trie.search("hel");
    		assertThat(words.size()).isEqualTo(3);
    		List.of("hello", "hellothere", "hellfire").forEach(word -> assertThat(words.contains(word)).isTrue());
    }
    
    //Extra requirement: output list should be sorted.
    @Test
    public void testSearchResultSortOrder() {
    		List.of("azalia", "aardvark", "abacus", "alchemy", "adagio", "hem", "hew", "hi", "bye", "acquiesce").forEach(word -> trie.add(word));    		
    		List<String> words = trie.search("a");
    		assertThat(words.size()).isEqualTo(6);
    		List<String> expectedList = List.of("aardvark", "abacus", "acquiesce", "adagio", "alchemy", "azalia");
    		int i = 0; 
    		for(String expected : expectedList) {
    			assertThat(words.get(i++)).isEqualTo(expected);
    		}
    }
    
    //****Acceptance Tests - look for odd behavior and potential bugs.****
    
    //Make sure casing doesn't affect the search results.
    @Test
    public void testCasing() {
    		List<String> inputs = List.of("azalia", "AARDVARK", "Abacus", "aLcHeMy");
    		List<String> lowerInputsSorted = List.of("aardvark", "abacus", "alchemy", "azalia");
    		inputs.forEach(word -> assertThat(trie.add(word)).isTrue());
    		
    		List<String> search = trie.search("a");
    		List<String> searchUpper = trie.search("A");
    		
    		inputs.forEach(word -> assertThat(trie.contains(word)));
    		assertThat(search.equals(searchUpper));
    		assertThat(search.equals(lowerInputsSorted));
    }
}
