package vo;

import java.util.ArrayList;
import java.util.List;

public class Group {
   private List<Student> studentList;

   public Group(){
       super();
       studentList = new ArrayList<>();
   }

   public void add(Student student){
       studentList.add(student);
   }

   public List<Student> getStudentList(){
       return studentList;
   }
}
