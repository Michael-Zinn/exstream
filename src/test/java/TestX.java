import de.michaelzinn.exstream.X;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static de.michaelzinn.exstream.X.*;
import static java.util.Arrays.asList;

import static java.util.Collections.emptyList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by michael on 26.06.17.
 */
public class TestX {

    class User {
        List<String> ids;

        User(List<String> ids) {
            this.ids = ids;
        }

        public List<String> getIds() {
            return ids;
        }
    }

    List<User> users = asList(
            new User(asList("one", "two")),
            new User(asList("three", "four"))
    );

    @Test
    public void testStream() {
        assertThat(

                stream(users, flatMap(User::getIds)),

                is(asList("one", "two", "three", "four"))

        );
    }

    @Test
    public void testSortBy() {
        assertThat(

                stream(asList("write", "more", "haskell"),
                        sortBy(String::length)),

                is(asList("more", "write", "haskell"))
        );
    }

    @Test
    public void testStreamCollector() {
        Set<String> set = new HashSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        set.add("four");

        assertThat(

                stream(users,
                        flatMap(User::getIds),
                        Collectors.toSet()),

                is(set)
        );
    }

    @Test
    public void testStreamOptional() {
        Optional<User> someUser = Optional.of(new User(asList("one", "two")));

        Optional<User> noUser = Optional.empty();

        // confusing
        assertThat(
                someUser.map(User::getIds).orElse(Collections.emptyList()),

                is(asList("one", "two"))
        );

        assertThat(

                stream(someUser, flatMap(User::getIds)),

                is(asList("one", "two"))
        );


        assertThat(

                stream(noUser, flatMap(User::getIds)),

                is(emptyList())
        );
    }


}
