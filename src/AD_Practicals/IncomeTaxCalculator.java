package AD_Practicals;
import java.io.PrintWriter;
import java.io.StringWriter;

public class IncomeTaxCalculator {

    public static void main(String[] args) {
        StringWriter writer = new StringWriter();
        PrintWriter out = new PrintWriter(writer);

        String name = "John Doe";
        int age = 30;
        String gender = "male";
        String address = "123 Main St";
        double income = 50000.0;

        double tax = 0;
        if (gender.equalsIgnoreCase("female")) {
            tax = income * 0.1;
        } else if (gender.equalsIgnoreCase("male")) {
            tax = income * 0.15;
        }

        out.println("<html>");
        out.println("<head><title>Income Tax Calculation</title></head>");
        out.println("<body>");
        out.println("<h1>Income Tax Calculation for " + name + "</h1>");
        out.println("<p>Address: " + address + "</p>");
        out.println("<p>Age: " + age + "</p>");
        out.println("<p>Gender: " + gender + "</p>");
        out.println("<p>Income: $" + income + "</p>");
        out.println("<p>Tax: $" + String.format("%.2f", tax) + "</p>");
        out.println("</body>");
        out.println("</html>");

        System.out.println(writer.toString());
    }
}