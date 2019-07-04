import vo.Group;
import vo.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String args[]) {
        System.out.println("Hello");

        basicStreamAPI();
        makeStream();
        workStreamMid();
        workStreamFin();
        pointStream();
        resetListStream();
    }

    /*
     * 5.1) Stream API를 사용하기 위한 기본
     * */
    public static void basicStreamAPI() {
        //5.1.1) Stream API로 컬렉션의 조작은 어떻게 변하는가?
        System.out.println("***************************5.1.1***************************");
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(new Student("Kim", 100));
        studentList1.add(new Student("So", 60));
        studentList1.add(new Student("Yeon", 80));

        studentList1.stream()                                       //작성(Stream 인스턴스 생성)
                .filter(s -> s.getScore() >= 70)                    //중간작업
                .forEach(s -> System.out.print('/' + s.getName()));  //종료작업
        System.out.println();


        //다시
       /* studentList1.stream()
                .collect(Collectors.groupingBy(emp -> emp.name))
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().stream().filter(emp -> emp.score > 70).count()));*/

        //5.1.2) 람다식 작성법 마스터하기
        System.out.println("***************************5.1.2***************************");
        List<Student> studentList2 = new ArrayList<>();
        studentList2.add(new Student("ddoddo", 100));
        studentList2.add(new Student("sonson", 70));
        studentList2.add(new Student("ddoson", 80));
        System.out.println(studentList2);
        /*자바7 이전*/
        Collections.sort(studentList2, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Integer.compare(o1.getScore(), o2.getScore()); //오름차순
            }
        });
        System.out.println(studentList2);
        /*람다식 이용*/
        Collections.sort(studentList2, (o1, o2) -> Integer.compare(o2.getScore(), o1.getScore())); //내림차순
        System.out.println(studentList2);
        /*함수형 인터페이스 대신 사용하기*/
        Student[] students = {
                new Student("Han", 100),
                new Student("Jae", 60),
                new Student("Hee", 80)};
        Arrays.sort(students, (Student o1, Student o2) -> Integer.compare(o2.getScore(), o1.getScore())); //내림차순
        Arrays.stream(students).forEach(s -> System.out.print(" / " + s.getName() + ":" + s.getScore()));
        System.out.println();
        /*람다식 인수부분 생략*/
        Consumer<String> consumer1 = (String s) -> {
            System.out.println(s);
        };
        consumer1.accept("consumer1 : 함수형 인터페이스");
        Consumer<String> consumer2 = s -> {
            System.out.println(s);
        };
        consumer2.accept("consumer2 : 함수형 인터페이스 + 인수생략");
        Runnable runnable = () -> {
            System.out.println("람다식 사용시 인수가 0인 경우는 소괄호만 쓴다.");
        };
        runnable.run();

        //5.1.3) 메서드 참조
        System.out.println("***************************5.1.3***************************");
        List<String> list = Arrays.asList("Xxx ", "Yyyy ", "Zzzz ");
        list.forEach(System.out::print);
        System.out.println();
        list.forEach(str -> System.out.print(str));
        System.out.println();
    }

    /*
     * 5.2) Stream 작성하기
     * */
    public static void makeStream() {
        //5.2.1) List나 Set으로부터 Stream 작성하기
        System.out.println("***************************5.2.1***************************");
        List<String> list = Arrays.asList("Kim ", "Lee ", "Park ");
        Stream<String> streamList = list.stream();
        streamList.forEach(System.out::print);
        System.out.println();

        //5.2.2) 배열로부터 Stream 작성하기
        System.out.println("***************************5.2.2***************************");
        String[] array = {"Park ", "Kim ", "Lee "};
        Stream<String> streamArray1 = Arrays.stream(array);
        streamArray1.forEach(System.out::print);
        System.out.println();
        Stream<String> streamArray2 = Stream.of("Lee ", "Park ", "Kim ");
        streamArray2.forEach(System.out::print);
        System.out.println();

        //5.2.3) Map으로부터 Stream 작성하기
        System.out.println("***************************5.2.3***************************");
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Kim ");
        map.put(2, "So ");
        map.put(3, "Yeon ");
        Stream<Map.Entry<Integer, String>> streamMapSet = map.entrySet().stream();
        streamMapSet.forEach(e -> System.out.printf(e.getKey() + ":" + e.getValue()));
        System.out.println();
        Stream<String> streamMapValue = map.values().stream();
        streamMapValue.forEach(System.out::print);
        System.out.println();

        //5.2.4) 숫자범위로부터 Stream 작성하기
        System.out.println("***************************5.2.4***************************");
        IntStream intStreamRange = IntStream.range(1, 5);
        intStreamRange.forEach(System.out::print);
        System.out.println();
        IntStream intStreamRangeCls = IntStream.rangeClosed(1, 5);
        intStreamRangeCls.forEach(System.out::print);
        System.out.println();
        int count = 7;
        for (int i = 0; i < count; i++) {
            System.out.print(i);
        }
        System.out.println();
        IntStream.range(0, count).forEach(i -> {
            System.out.print(i);
        });
        System.out.println();
    }

    /*
     * 5.3) Stream에 대한 중간작업
     * */
    public static void workStreamMid() {
        //5.3.1) 요소를 치환하는 중간작업
        System.out.println("***************************5.3.1***************************");
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("또또 ", 50));
        studentList.add(new Student("손손 ", 30));
        studentList.add(new Student("또손 ", 70));
        Stream<String> streamStr = studentList.stream().map(s -> s.toString());
        streamStr.forEach(System.out::print);
        System.out.println();

        Stream<Integer> streamInt = studentList.stream().map(s -> s.getScore());
        streamInt.forEach(System.out::print);
        System.out.println();

        studentList.stream().map(Student::getName).forEach(System.out::print);
        System.out.println();

        Integer sumScore = studentList.stream().mapToInt(Student::getScore).sum();
        System.out.println(sumScore);

        List<Group> groupList = new ArrayList<>();
        Group group1 = new Group();
        group1.add(new Student("KIM", 100));
        group1.add(new Student("SO", 60));
        group1.add(new Student("YEON", 80));
        groupList.add(group1);
        Group group2 = new Group();
        group2.add(new Student("HAN", 75));
        group2.add(new Student("JAE", 95));
        group2.add(new Student("HEE", 85));
        groupList.add(group2);
        Group group3 = new Group();
        group3.add(new Student("DDO", 90));
        group3.add(new Student("SON", 65));
        group3.add(new Student("DSN", 80));
        groupList.add(group3);
        Stream<List<Student>> mappedStream = groupList.stream().map(g -> g.getStudentList());
        Stream<Student> flatMappedStream = groupList.stream().flatMap(g -> g.getStudentList().stream());
        System.out.print("MAP STREAM : ");
        mappedStream.forEach(System.out::print);
        System.out.println();
        System.out.print("FLATMAP STREAM : ");
        flatMappedStream.forEach(System.out::print);
        System.out.println();

        List<Student> allStudent = new ArrayList<>();
        groupList.stream().forEach(g -> allStudent.addAll(g.getStudentList()));
        allStudent.stream()
                .sorted((s1, s2) -> s2.getScore() - s1.getScore())
                .forEach(s -> System.out.print(s.getName() + " " + s.getScore() + " / "));
        System.out.println();
        groupList.stream()
                .flatMap(g -> g.getStudentList().stream())
                .sorted((s1, s2) -> s2.getScore() - s1.getScore())
                .forEach(s -> System.out.print(s.getName() + " " + s.getScore() + " / "));
        System.out.println();

        //5.3.2) 요소를 걸러내는 중간작업
        System.out.println("***************************5.3.2***************************");
        System.out.println(studentList.toString());
        System.out.print("Filter -> ");
        studentList.stream()
                .filter(s -> s.getScore() > 40)
                .forEach(s -> System.out.print(s.getName()));
        System.out.println();
        System.out.print("Limit -> ");
        studentList.stream()
                .limit(2)
                .forEach(s -> System.out.print(s.getName()));
        System.out.println();
        List<String> strings = new ArrayList<>();
        strings.add("kim ");
        strings.add("so ");
        strings.add("yeon ");
        strings.add("kim ");
        strings.add("so ");
        System.out.println(strings.toString());
        System.out.print("Distinct -> ");
        strings.stream()
                .distinct()
                .forEach(System.out::print);
        System.out.println();

        //5.3.3) 요소를 정렬하는 중간작업
        System.out.println("***************************5.3.3***************************");
        System.out.println(studentList.toString());
        studentList.stream()
                .sorted((s1, s2) -> s2.getScore() - s1.getScore())
                .forEach(s -> System.out.print(s.getName() + s.getScore() + " / "));
        System.out.println();
    }

    /*
     * 5.4) Stream에 대한 종료작업
     * */
    public static void workStreamFin() {
        //5.4.1) 반복 처리를 실시하는 종료 작업
        System.out.println("***************************5.4.1***************************");

        //5.4.2) 결과를 정리해서 추출하는 종료 작업
        System.out.println("***************************5.4.2***************************");
        List<String> list = Arrays.asList("KimSoYeon", "Ddoson", "yeony", "Ddoson");
        List<String> colList = list.stream().filter(p -> p.length() > 5).collect(Collectors.toList());
        Set<String> colSet = list.stream().filter(p -> p.length() > 5).collect(Collectors.toSet());
        String colJoined1 = list.stream().filter(p -> p.length() > 5).collect(Collectors.joining());
        String colJoined2 = list.stream().filter(p -> p.length() > 5).collect(Collectors.joining(" / "));
        Map<Integer, List<String>> colGroupingBy = list.stream().collect(Collectors.groupingBy(String::length));
        System.out.println("Collectors.toList : " + colList);
        System.out.println("Collectors.toSet : " + colSet);
        System.out.println("Collectors.joining() : " + colJoined1);
        System.out.println("Collectors.joining(\"/\") : " + colJoined2);
        System.out.println("Collectors.groupingBy : " + colGroupingBy);

        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("Kim", 100));
        studentList.add(new Student("Kim", 70));
        studentList.add(new Student("So", 60));
        studentList.add(new Student("Yeon", 80));
        studentList.add(new Student("Hi", 100));
        Map<Integer, List<Student>> mapKscore = studentList.stream().collect(Collectors.groupingBy(Student::getScore));
        Map<String, List<Student>> mapKname = studentList.stream().collect(Collectors.groupingBy(Student::getName));
        System.out.println(mapKscore);
        System.out.println(mapKname);
        List<Student> perfects = mapKscore.get(100);
        List<Student> kims = mapKname.get("Kim");
        perfects.forEach(s -> System.out.print(s.getName() + ":" + s.getScore() + " , "));
        System.out.println();
        kims.forEach(s -> System.out.print(s.getName() + ":" + s.getScore() + " , "));
        System.out.println();

        //5.4.3) 결과를 하나만 추출하는 종료 작업
        System.out.println("***************************5.4.3***************************");
        Optional<Student> first = studentList.stream().findFirst();
        Optional<Student> any = studentList.stream().findAny();
        Optional<Student> min = studentList.stream().min(Comparator.comparing(Student::getScore));
        Optional<Student> max = studentList.stream().max(Comparator.comparing(Student::getScore));
        System.out.println("findFirst() : " + first);
        System.out.println("findAny() : " + any);
        System.out.println("min(Comparator.comparing) : " + min);
        System.out.println("max(Comparator.comparing) : " + max);
    }

    /*
     * 5.5) Stream API를 사용하기 위한 포인트
     * */
    public static void pointStream() {
        //5.5.1) 왕도는 map, filter, collect
        System.out.println("***************************5.5.1***************************");
        List<String> list = Arrays.asList("AAAAA", "BBB", "CCCC");
        List<String> newList =
                list.stream()
                        .filter(p -> p.length() > 3)
                        .map(p -> "[" + p + "] ")
                        .collect(Collectors.toList());
        newList.forEach(System.out::print);
        System.out.println();

        //5.5.2) n번 반복하는 IntStream
        System.out.println("***************************5.5.2***************************");
        int count = 5;
        String query =
                IntStream.range(0, count)
                        .mapToObj(i -> "?")
                        .collect(Collectors.joining(", "));
        System.out.println(query);

        //5.5.3) List나 Map에 대한 효율적인 처리 실시하기
        System.out.println("***************************5.5.3***************************");
        List<String> strList = new ArrayList<>();
        strList.add("xxx");
        strList.add("yyyyyy");
        strList.add("zzzzz");
        strList.removeIf(s -> s.length() < 5);
        strList.forEach(System.out::print);
        System.out.println();
        strList.replaceAll(s -> s.toUpperCase()); //대문자로
        strList.forEach(System.out::print);
        System.out.println();

        List<String> nameList = new ArrayList<>();
        nameList.add("David");
        nameList.add("SoYeonKim");
        nameList.add("JaeHeeHan");
        nameList.add("DdoSonE");
        Map<Integer, List<String>> nameMap = new HashMap<>();
        nameList.forEach(
                name -> {
                    Integer nameLen = name.length();
                    List<String> valueList = nameMap.computeIfAbsent(nameLen, key -> new ArrayList<>());
                    valueList.add(name);
                }
        );
        System.out.println(nameMap);
    }

    /*
     * 5.6) Stream API를 사용하여 List 초기화하기
     * */
    public static void resetListStream() {
        //5.6.1~2) Stream을 이용해 열거한 값, 값의 범위로 List 작성하기
        System.out.println("***********************5.6.1 && 5.6.2***********************");
        List<Integer> integerList =
                IntStream.of(1, 62, 31, 1, 54, 31)
                        .boxed()
                        .collect(Collectors.toList());
        List<Integer> integerListRange =
                IntStream.range(1, 15)
                        .boxed()
                        .collect(Collectors.toList());
        List<Integer> integerListRangeCls =
                IntStream.rangeClosed(1, 15)
                        .boxed()
                        .collect(Collectors.toList());
        System.out.println(integerList);
        System.out.println(integerListRange);
        System.out.println(integerListRangeCls);

        List<String> stringList =
                Stream.of("aoao", "ioioio", "eoeoeo")
                        .collect(Collectors.toList());
        System.out.println(stringList);

        //5.6.3) Stream을 이용해 배열 작성하기
        System.out.println("***************************5.6.3***************************");
        Integer[] integerArray =
                IntStream.of(1, 62, 31, 1, 31, 54)
                .boxed()
                .toArray(n -> new Integer[n]);
        for ( int i = 0; i < integerArray.length ; i++){
            System.out.print(integerArray[i] + " / ");
        }

    }
}
