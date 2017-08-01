package iurii.job.interview.trie;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by iurii.dziuban on 31/07/2017.
 */
public class TrieStTest {
    @Test
    public void test() {
        TrieST<Integer> st = new TrieST<Integer>();
        st.put("hello", 5);
        st.put("hell", 4);
        st.put("he", 3);
        assertThat(st.get("hell")).isEqualTo(4);
        assertThat(st.get("hello")).isEqualTo(5);
        assertThat(st.get("he")).isEqualTo(3);
    }
}