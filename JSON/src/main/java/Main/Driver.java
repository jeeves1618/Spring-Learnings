package Main;

import Domain.Student;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Driver {

    public static void main(String[] args) {
        try {

            ObjectMapper mapper = new ObjectMapper();

            Student student = mapper.readValue(new File("src/main/resources/sample-lite.json"),Student.class);

            System.out.println(student.getFirstName());
            System.out.println(student.getLastName());
            System.out.println(student.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
