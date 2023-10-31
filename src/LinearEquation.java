import java.text.DecimalFormat;

public class LinearEquation {
    private int x1, y1, x2, y2;

    public LinearEquation(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public double distance() {
        double dx = x2 - x1;
        double dy = y2 - y1;
        return roundedToHundredth(Math.sqrt(dx * dx + dy * dy));
    }

    public double yIntercept() {
        double slope = slope();
        return roundedToHundredth(y1 - slope * x1);
    }

    public double slope() {
        double dx = x2 - x1;
        double dy = y2 - y1;

        if (dx == 0) {
            // Handle vertical line case
            return Double.POSITIVE_INFINITY;
        }

        return roundedToHundredth(dy / dx);
    }

    public String equation() {
        double slope = slope();

        if (Double.isInfinite(slope)) {
            // Handle vertical line case
            return "x = " + x1;
        }

        if (slope == 0) {
            return "y = " + yIntercept();
        }
        // Format slope as a fraction
        String slopeStr = convertToFraction(slope);

        double yInt = yIntercept();
        DecimalFormat df = new DecimalFormat("0.00");
        String yIntStr = df.format(yInt);

        return "y = " + slopeStr + "x + " + yIntStr;
    }

    private String convertToFraction(double value) {
        if (value == (int) value) {
            return Integer.toString((int) value);
        }

        int precision = 10000; // Adjust precision as needed

        int numerator = (int) Math.round(value * precision);
        int denominator = precision;

        int gcd = findGCD(numerator, denominator);

        numerator /= gcd;
        denominator /= gcd;

        return numerator + "/" + denominator;
    }

    private int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }
    public String coordinateForX(double x) {
        double y = slope() * x + yIntercept();
        return "(" + formattedDouble(x) + ", " + formattedDouble(y) + ")";
    }

    public String lineInfo() {
        return "The two points are: (" + x1 + ", " + y1 + ") and (" + x2 + ", " + y2 + ")\n" +
                "The equation of the line between these points is: " + equation() + "\n" +
                "The slope of this line is: " + slope() + "\n" +
                "The y-intercept of this line is: " + yIntercept() + "\n" +
                "The distance between these points is: " + distance();
    }

    private double roundedToHundredth(double toRound) {
        return Math.round(toRound * 100.0) / 100.0;
    }

    private String formattedDouble(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(value);
    }
}
