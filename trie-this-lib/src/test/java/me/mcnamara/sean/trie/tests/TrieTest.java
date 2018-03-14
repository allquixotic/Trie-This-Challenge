package me.mcnamara.sean.trie.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import me.mcnamara.sean.trie.Trie;

/**
 * Unit test for Trie.
 */
public class TrieTest
{
    private Trie trie;
    
    @Before
    public void setUp() {
    		this.trie = new Trie();
    }
    
    @Test
    public void testAddWordEmptyTree_returnsTrue() {
    		assertThat(trie.add("hello")).isTrue();
    }
    
    @Test
    public void testaddDuplicateWord_returnsFalse() {
    		assertThat(trie.add("hello")).isTrue();
    		assertThat(trie.add("hello")).isFalse();
    }
    
    @Test
    public void testContainsLookForExistingWord_returnsTrue() {
    		trie.add("hello");
    		assertThat(trie.contains("hello")).isTrue();
    }
    
    @Test
    public void testContainsLookForNonWord_returnsFalse() {
    		trie.add("hello");
    		assertThat(trie.contains("hel")).isFalse();
    }
    
    @Test
    public void testSearchZeroResults() {
    		List.of("hello", "hellothere", "hellfire", "hem", "hew", "hi", "bye").forEach((word) -> trie.add(word));		
		List<String> words = trie.search("ha");
		assertThat(words.size()).isEqualTo(0);
    }
    
    @Test
    public void testSearchThreeResults() {
    		List.of("hello", "hellothere", "hellfire", "hem", "hew", "hi", "bye").forEach((word) -> trie.add(word));    		
    		List<String> words = trie.search("hel");
    		List.of("hello", "hellothere", "hellfire").forEach((word) -> assertThat(words.contains(word)).isTrue());
    		assertThat(words.size()).isEqualTo(3);
    }
}
