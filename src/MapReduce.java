import java.util.List;
import java.util.OptionalDouble;
 
public class MapReduce {
 
    public static void main(String[] args) {
        List<Employee> employees = List.of(
            new Employee(201, "Alice", 30),
            new Employee(202, "Bob", 28),
            new Employee(203, "Charlie", 35),
            new Employee(204, "David", 40),
            new Employee(205, "Eve", 26),
            new Employee(206, "Frank", 32),
            new Employee(207, "Grace", 20), 
            new Employee(208, "Henry", 29)
        );

        double averageClassic = calculateAverage(employees);
        System.out.println("Average age (classic way): " + averageClassic);
        
        OptionalDouble averageLambda = calculateAverageWithStream(employees);
        System.out.println("Average age (lambda way): " + (averageLambda.isPresent() ? averageLambda.getAsDouble() : "No valid data"));
    }

    private static double calculateAverage(List<Employee> employees) {
        int totalEmployees = 0;
        double sum = 0;
        for (Employee e : employees) {
            if (e.getAge() > 0 && e.getAge() < 100) { // Exclude invalid ages
                sum += e.getAge();
                totalEmployees++;
            }
        }
        return totalEmployees > 0 ? sum / totalEmployees : 0;
    }

    private static OptionalDouble calculateAverageWithStream(List<Employee> employees) {
        return employees.stream()
                        .mapToInt(Employee::getAge)
                        .filter(age -> age > 0 && age < 100) // Exclude invalid ages
                        .average();
    }
}

class Employee {
    private final int id;
    private final String name;
    private final int age;

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}