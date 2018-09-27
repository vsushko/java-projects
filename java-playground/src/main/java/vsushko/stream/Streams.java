package vsushko.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author vsushko
 */
public class Streams {

    public static void main(String[] args) throws IOException {
        // creates streams from values
        Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");
        System.out.println(streamFromValues.collect(Collectors.toList()));

        // creates stream from array
        String[] array = {"a1", "a2", "a3"};
        Stream<String> streamFromArray1 = Arrays.stream(array);
        System.out.println(streamFromArray1.collect(Collectors.toList()));

        Stream<String> streamFromArray2 = Stream.of(array);
        System.out.println(streamFromArray2.collect(Collectors.toList()));

        // creates stream from file (each row in file will be a separate string in stream)
        File file = new File("1.tmp");
        file.deleteOnExit();
        PrintWriter out = new PrintWriter(file);
        out.println("a1");
        out.println("a2");
        out.println("a3");
        out.close();

        Stream<String> streamFromFile = Files.lines(Paths.get(file.getAbsolutePath()));
        System.out.println(streamFromFile.collect(Collectors.toList()));

        // creates stream from collection
        Collection<String> collection = Arrays.asList("a1", "a2", "a3");
        Stream<String> streamFromCollection = collection.stream();
        System.out.println(streamFromCollection.collect(Collectors.toList()));

        // creates numeric stream from string
        IntStream intStream = "123".chars();
        intStream.forEach((e) -> System.out.print(e + ", "));
        System.out.println();

        // creates with Stream.builder
        Stream.Builder<String> builder = Stream.builder();
        Stream<String> streamFromBuilder = builder.add("a1").add("a2").add("a3").build();
        System.out.println(streamFromBuilder.collect(Collectors.toList()));

        // infinite streams
        // with Stream.iterate
        Stream<Integer> streamFromIterate = Stream.iterate(1, n -> n + 2);
        System.out.println(streamFromIterate.limit(3).collect(Collectors.toList()));

        // with Stream.generate
        Stream<String> streamFromGenerate = Stream.generate(() -> "a1");
        System.out.println(streamFromGenerate.limit(3).collect(Collectors.toList()));

        // empty stream
        Stream<String> streamEmpty = Stream.empty();
        System.out.println(streamEmpty.collect(Collectors.toList()));

        // creates parallel stream
        Stream<String> parallelStream = collection.parallelStream();
        System.out.println(parallelStream.collect(Collectors.toList()));

        // creates stream from list of file with specified path
        Stream<Path> stream = Files.list(Paths.get(""));
        System.out.println(stream.collect(Collectors.toList()));

        // creates stream from
        Stream<Path> streamFromFind = Files.find(Paths.get(""), 10, (p, a) -> true);
        System.out.println(streamFromFind.collect(Collectors.toList()));

        // creates stream from files tree
        Stream<Path> streamFromFileTree = Files.walk(Paths.get(""));
        System.out.println(streamFromFileTree.collect(Collectors.toList()));

        // creates stream from RegExp pattern'a
        Stream<String> streamFromPattern = Pattern.compile(":").splitAsStream("a1:a2:a3");
        System.out.println(streamFromPattern.collect(Collectors.toList()));

        // creates stream from text file using BufferedReader
        // create temp file
        Path path = Files.write(Paths.get("./test.txt"), "test 1\ntest 2".getBytes());
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Stream<String> streamFromBufferedReader = reader.lines();
            System.out.println(streamFromBufferedReader.collect(Collectors.toList()));
        }
    }
}
