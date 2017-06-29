![ExStream logo](exstreamlogo.png)

Simplified Java 8 Streams.

Work in progress, the examples work, but it's too incomplete to be used productively.

```java

// flatMap with functions that return collections instead of streams.
// No need for "collect", just use a Collector directly.
stream(users,
        flatMap(User::getIds),
        Collectors.toSet());

// Collectors.toList() is the default if you leave out the last parameter.
stream(users, flatMap(User::getIds));

// Use sortBy or write your own Function<Stream<A>, Stream<B>>
stream(asList("write", "more", "haskell"),
        sortBy(String::length));

```