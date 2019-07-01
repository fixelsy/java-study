import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestException {

    public TestException(){

    }
    public String getValueFromFile(File file) throws IOException{
        return "aa";
    }

    public void exceptionEx(){
        String strValue = "abc";
        try{
            int intvalue = Integer.valueOf(strValue);
            System.out.println("intValue is " + intvalue);
        }catch (NumberFormatException ex){

            System.out.println("숫자아님. " );
            ex.printStackTrace();
        }

    }

    public void caller(){
        try{
            callee();
        } catch (Exception ex){

        }
    }

    private void callee() throws Exception{
        Class someClass = Class.forName("className");
        SomeClass obj = (SomeClass) someClass.newInstance();
    }

    public void exceptionLambda(){
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get("W_FILENAME"))) {

        }catch (IOException ex){

        }

    }


}
