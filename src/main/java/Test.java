import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String args[]){
        System.out.println("Hello");

        //basicStreamAPI();
        makeStream();



    }

    /*
    * 5.1) Stream API를 사용하기 위한 기본
    * */
    public static void basicStreamAPI(){
        //5.1.1) Stream API로 컬렉션의 조작은 어떻게 변하는가?
        List<Student> studentList1 = new ArrayList<>();
        studentList1.add(new Student("Kim", 100));
        studentList1.add(new Student("So", 60));
        studentList1.add(new Student("Yeon", 80));

        studentList1.stream()                                       //작성(Stream 인스턴스 생성)
                .filter(s->s.getScore()>=70)                    //중간작업
                .forEach(s-> System.out.print('/' + s.getName()));  //종료작업
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

        //5.1.3) 메서드 참조
        List<String> list = Arrays.asList("Xxx ", "Yyyy ", "Zzzz ");
        list.forEach(System.out::print);
        System.out.println();
        list.forEach(str -> System.out.print(str));
    }

    /*
     * 5.2) Stream 작성하기
     * */
    public static void makeStream(){
        //5.2.1) List나 Set으로부터 Stream 작성하기
        List<String> list = Arrays.asList("Kim ", "Lee ", "Park ");
        Stream<String> stream = list.stream();
        stream.forEach(System.out::print);


    }

}
