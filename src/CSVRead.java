
    import java.io.*;
    import java.util.ArrayList;
    import java.util.List;

    import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

         class Employee{
            int id, salary;
            String name, address, role;

            public Employee(int id, String name, String address, String role, int salary) {
                this.id = id;
                this.name = name;
                this.address = address;
                this.role = role;
                this.salary = salary;
            }

            @Override
            public String toString() {
                return "ID: " + id + ", Name: " + name + ", Address: " + address + ", Role: " + role + ", Salary: " + salary;
            }
        }

    public class CSVRead {
        public static void main(String[] args) throws Exception {
            List<Employee> employees = ReadExcel("Sheet1");
            int higestSalaryEmpIdx = -1;
            long maxSalary = -1;
            for (Employee emp : employees) {
                System.out.println(emp); // Will call the toString() method
            }

            System.out.println("\nEmployee with the highest salary: " + findHighestSalaryEmployee(employees));
        }

        public static Employee findHighestSalaryEmployee(List<Employee> employees) {
            if (employees.isEmpty()) {
                return null; // Return null if the list is empty
            }

            Employee highestSalaryEmp = employees.get(0); // Assume the first employee has the highest salary initially
            for (Employee emp : employees) {
                if (emp.salary > highestSalaryEmp.salary) {
                    highestSalaryEmp = emp; // Update if we find an employee with a higher salary
                }
            }
            return highestSalaryEmp;
        }

        public static Employee getEmpDetails(Row row){
            int id = (int) row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            String address = row.getCell(2).getStringCellValue();
            String role = row.getCell(3).getStringCellValue();
            int salary = (int) row.getCell(4).getNumericCellValue();

            return new Employee(id, name, address, role, salary);
        }

        public static List<Employee> ReadExcel(String sheetName) {
            List<Employee> employees = new ArrayList<>();
            try {
                FileInputStream fis = new FileInputStream(".\\empdemo.xlsx");
                Workbook wb = WorkbookFactory.create(fis);
                Sheet sheet = wb.getSheet(sheetName);
                int rowSize = sheet.getLastRowNum();

                // Start reading from row 1 (skipping the header row 0)
                for (int rowIndex = 1; rowIndex < rowSize; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    // Create an Employee object and add it to the list
                    if (row != null) {
                        Employee emp = getEmpDetails(row);
                        employees.add(emp);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return employees;
        }
    }
