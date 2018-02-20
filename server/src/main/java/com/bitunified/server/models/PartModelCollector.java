package com.bitunified.server.models;

import com.bitunified.ledconfig.domain.Model;
import com.bitunified.ledconfig.parts.Part;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;


public class PartModelCollector implements Collector<Part, List<Model>, Optional<Part>> {
    @Override
    public Supplier<List<Model>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<Model>, Part> accumulator() {
        return (acc, elem) -> acc.add(elem.getConfigModel());
    }

    @Override
    public BinaryOperator<List<Model>> combiner() {
        return (ac1,ac2)->{throw new UnsupportedOperationException();};
    }

    @Override
    public Function<List<Model>, Optional<Part>> finisher() {
        return null;

    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
