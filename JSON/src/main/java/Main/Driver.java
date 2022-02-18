package Main;

import Domain.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class Driver {

    public static void main(String[] args) {
        try {

            ObjectMapper mapper = new ObjectMapper();

            Employee employee = mapper.readValue(new File("src/main/resources/sample-lite.json"), Employee.class);

            System.out.println("The employee first name is : " + employee.getFirstName());
            System.out.println("The employee last name is : " + employee.getLastName());
            System.out.println("The employee name ID is : " + employee.getId());

            Employee employeeExtended = mapper.readValue(new File("src/main/resources/sample-full.json"), Employee.class);

            System.out.println("\nReading the extended file\n");

            System.out.println("The employee first name is : " + employeeExtended.getFirstName());
            System.out.println("The employee last name is : " + employeeExtended.getLastName());
            System.out.println("The employee name ID is : " + employeeExtended.getId());

            System.out.println("The employee address ID is : " + employeeExtended.getAddress().getStreet() + ", " + employeeExtended.getAddress().getCity() + ", "
                    + employeeExtended.getAddress().getState() + ", "+ employeeExtended.getAddress().getZip() + ", "+ employeeExtended.getAddress().getCountry());

            System.out.println("The list of languages known to " + employeeExtended.getFirstName() + " are ");
            for(String language: employeeExtended.getLanguages())
                System.out.println(language);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
