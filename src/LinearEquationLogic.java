import java.util.Scanner;

public class LinearEquationLogic {
    private Scanner scanner;

    public LinearEquationLogic() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to the Linear Equation Calculator!");

        do {
            LinearEquation linearEquation = getLinearEquationFromUser();
            System.out.println(linearEquation.lineInfo());

            System.out.print("Enter a value for x: ");
            double xValue = scanner.nextDouble();
            scanner.nextLine();  // consume the newline character

            String coordinate = linearEquation.coordinateForX(xValue);
            System.out.println("The point on the line is " + coordinate);

            System.out.print("Would you like to enter another pair of coordinates? (y/n): ");
        } while (scanner.next().equalsIgnoreCase("y"));

        System.out.println("Thank you for using the Linear Equation Calculator, goodbye!");
    }

    private LinearEquation getLinearEquationFromUser() {
        System.out.print("Enter coordinate 1: ");
        String coordinate1 = scanner.next().replaceAll("[()]", "");
        scanner.nextLine();  // consume the newline character
        String[] parts1 = coordinate1.split(",");
        int x1 = Integer.parseInt(parts1[0]);
        int y1 = Integer.parseInt(parts1[1]);

        System.out.print("Enter coordinate 2: ");
        String coordinate2 = scanner.next().replaceAll("[()]", "");
        scanner.nextLine();  // consume the newline character
        String[] parts2 = coordinate2.split(",");
        int x2 = Integer.parseInt(parts2[0]);
        int y2 = Integer.parseInt(parts2[1]);

        return new LinearEquation(x1, y1, x2, y2);
    }
}
