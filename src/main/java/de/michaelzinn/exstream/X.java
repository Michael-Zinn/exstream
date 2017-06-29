package de.michaelzinn.exstream;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by michael on 26.06.17.
 */
public class X {

    public static <A, B extends Collection<A>, C>
    List<C>
    stream(
            B collection,
            Function<Stream<A>, Stream<C>> transformation
    ) {
        return transformation.apply(collection.stream()).collect(Collectors.toList());
    }

    public static <A, B extends Collection<A>, C, D>
    D
    stream(
            B collection,
            Function<Stream<A>, Stream<C>> transformation,
            Collector<C, ?, D> collector
    ) {
        return transformation.apply(collection.stream()).collect(collector);
    }

    public static <A, C>
    List<C>
    stream(
            Optional<A> optional,
            Function<Stream<A>, Stream<C>> transformation
    ) {
        return transformation.apply(toStream(optional)).collect(Collectors.toList());
    }

    private static <A>
    Stream<A> toStream(Optional<A> optional) {
        return optional.map(Stream::of).orElse(Stream.empty());
    }

    public static <A, B>
    Function<Stream<A>, Stream<B>>
    flatMap(Function<A, List<B>> f) {
        return (Stream<A> as) -> as.flatMap(a -> f.apply(a).stream());
    }


    public static <A, B extends Comparable<B>>
    Function<Stream<A>, Stream<A>>
    sortBy(Function<A, B> f) {
        return (Stream<A> as) -> as.sorted(Comparator.comparing(f));
    }

}
