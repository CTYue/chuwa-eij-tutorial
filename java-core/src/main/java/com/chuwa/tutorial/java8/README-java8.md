# Java 8 New Features

## General Introduction
[Java 8 new features](https://www.cnblogs.com/54chensongxia/p/13858850.html)

## Stream API

### Feature:

* There are many ways to create a stream instance of different sources. Once created, the instance **will not modify its
  source**, therefore allowing the creation of multiple instances from a single source.
* Java 8 streams **can't be reused**. That is, One stream only can be used once. Details is at section: Referencing a
  Stream

### Stream examples && Stream Structure

```java
List<Integer> list=new ArrayList<Integer>();

for(int i=1;i< 10;i++){
    list.add(i);
}

Stream<Integer> stream=list.stream();
Integer[]evenNumbersArr=stream.filter(i->i%2==0).toArray(Integer[]::new);
System.out.print(evenNumbersArr);
```

```java
List<Integer> list=new ArrayList<Integer>();

for(int i=1;i< 10;i++){
    list.add(i);
}

Stream<Integer> stream=list.stream();
List<Integer> evenNumbersList = stream
        .filter(i->i%2==0)
        .collect(Collectors.toList());
System.out.print(evenNumbersList);
```

* stream creation
    * `Stream<Integer> stream = list.stream();`
* stream intermediate operation
    * `stream.filter(i -> i%2 == 0)`
* stream collection/termination
    * `.collect(Collectors.toList());`

### Creation

#### Empty Stream

```java
Stream<String> streamEmpty=Stream.empty()
```

#### Stream of Collection

```java
Collection<String> collection=Arrays.asList("a","b","c");
Stream<String> streamOfCollection=collection.stream();
```

#### Stream of Array

```java
String[]arr=new String[]{"a","b","c"};
Stream<String> streamOfArrayFull=Arrays.stream(arr);
Stream<String> streamOfArrayPart=Arrays.stream(arr,1,3);    // elements at index of 1, 2 are placed to stream
```

#### Stream.builder()

When builder is used, the desired type should be additionally specified in the right part of the statement, otherwise
the build() method will create an instance of the Stream<Object>:

```java
// <String> is specified
Stream<String> streamBuilder = Stream.<String>builder()
        .add("a")
        .add("b")
        .add("c")
        .build();
```

#### Stream.generate()

The generate() method accepts a Supplier<T> for element generation. As the resulting stream is infinite, the developer
should **specify the desired size**, or the generate() method **will work until it reaches the memory limit**

```java
Stream<String> streamGenerated = Stream.generate(()->"element").limit(10);
```

#### Stream.iterate()

The first element of the resulting stream is the first parameter of the iterate() method. When creating every following
element, the specified function is applied to the previous element. In the example above the second element will be 42.

```java
Stream<Integer> streamIterated = Stream.iterate(40,n->n+2).limit(20);
```

#### Stream of Primitives

Since Stream<T> is a generic interface, and there is no way to use primitives as a type parameter with generics,
however, Java 8 provide **IntStream, LongStream, DoubleStream** to create streams out of primitive types.

```java
IntStream intStream = IntStream.range(1,3);
LongStream longStream = LongStream.rangeClosed(1,3);
Random random = new Random();
DoubleStream doubleStream = random.doubles(3);
```

#### Stream of String

```java
IntStream streamOfChars = "abc".chars();
```

Since there is no interface for CharStream in JDK, we use the IntStream to represent a stream of chars instead.

#### Stream of File

```java
Path path=Paths.get("C:\\file.txt");
Stream<String> streamOfStrings = Files.lines(path);
Stream<String> streamWithCharset = Files.lines(path,Charset.forName("UTF-8"));
```

### Referencing a Stream

```java
Stream<String> stream = Stream.of("a","b","c")
        .filter(element->element.contains("b"));    // stream object
Optional<String> anyElement = stream.findAny(); // success since stream object can be used only once.
Optional<String> firstElement = stream.findFirst(); // failed, throw IllegalStateException. since it can not be reused
```

As the IllegalStateException is a RuntimeException, a compiler will not signalize about a problem. So it is very
important to remember that **Java 8 streams can't be reused.**

If we want to reuse it, we need convert it to collections, then use collection to create stream, then use it.

```java
List<String> elements = Stream.of("a","b","c")
        .filter(element->element.contains("b"))
        .collect(Collectors.toList());
Optional<String> anyElement = elements.stream().findAny();
Optional<String> firstElement = elements.stream().findFirst();
```

### Stream Intermediate Operation

* Stream.filter()
* Stream.map()
* Stream.sorted()
  **Intermediate operations are lazy**. This means that **they will be invoked only if it is necessary for the terminal
  operation execution.**

### Stream Termination Operations

* Stream.forEach()
    * `memberNames.forEach(System.out::println);`
* Stream.collect()
    * `List<String> memNamesInUppercase = memberNames.stream().sorted().map(String::toUpperCase).collect(Collectors.toList());`
* Stream.match()
    * `boolean matchedResult = memberNames.stream().anyMatch((s) -> s.startsWith("A"));`
    * `matchedResult = memberNames.stream().allMatch((s) -> s.startsWith("A"));`
    * `matchedResult = memberNames.stream().noneMatch((s) -> s.startsWith("A"));`
* Stream.count()
    * `long totalMatched = memberNames.stream()
      .filter((s) -> s.startsWith("A"))
      .count();`
* Stream.reduce()
    * The reduce() method performs a reduction on the elements of the stream with the given function. The result is
      an **Optional** holding the reduced value.
    * `Optional<String> reduced = memberNames.stream()
      .reduce((s1,s2) -> s1 + "#" + s2);`
    * `reduced.ifPresent(System.out::println);`
    * `Amitabh#Shekhar#Aman#Rahul#Shahrukh#Salman#Yana#Lokesh`

### Stream Collectors

* Collectors.toList()
    * List<Integer> evenNumbersList = stream.filter(i -> i%2 == 0)
      .collect(Collectors.toList());
* toArray(Integer[]::new)
    * Integer[] evenNumbersArr = stream.filter(i -> i%2 == 0).toArray(Integer[]::new);
* [more collectors](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Collectors.html)

### Short-circuit Operations

* Stream is usually performed on all elements inside a collection.
* In some cases, we want to stop the operation when we get what we want instead of processing all elements
    * Stream.anyMatch()
        * `boolean matched = memberNames.stream()
          .anyMatch((s) -> s.startsWith("A"));`
        * return true when it finds fist name starting with 'A' and stop performing the rest of elements
    * Stream.findFirst()
        * String firstMatchedName = memberNames.stream()
          .filter((s) -> s.startsWith("L"))
          .findFirst()
          .get();
        * return the firstMatchedName and stop performing on the rest of elements

### Parallel Streams

n any of the above-listed stream examples, anytime we want to do a particular job using multiple threads in parallel
cores, all we have to call parallelStream() method instead of stream() method.

```java
List<Integer> list=new ArrayList<Integer>();
for(int i = 1;i < 10; i++){
    list.add(i);
}

//Here creating a parallel stream
Stream<Integer> stream = list.parallelStream();
Integer[] evenNumbersArr = stream
        .filter(i->i%2==0)
        .toArray(Integer[]::new);
System.out.print(evenNumbersArr);
```

## References

* [how to do in Java: stream api](https://howtodoinjava.com/java/stream/java-streams-by-examples/)
* [baeldung - stream api](https://www.baeldung.com/java-8-streams)