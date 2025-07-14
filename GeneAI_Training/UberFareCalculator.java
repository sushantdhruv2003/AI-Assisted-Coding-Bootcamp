import java.util.Scanner;

public class UberFareCalculator {

    private static final double MINIMUM_FARE = 50.0;
    private static final double PER_KM_RATE = 10.0;
    private static final double OFFICE_HOUR_MULTIPLIER = 1.2;
    private static final double NIGHT_HOUR_MULTIPLIER = 1.5;
    private static final double OUTSTATION_CHARGE = 500.0;
    private static final double OUTSTATION_DISTANCE_THRESHOLD = 200.0;

    private double distanceTraveled;
    private int rideStartHour;
    private double totalFare;
    private String carType;
    private double carMultiplier;

    public UberFareCalculator() {
        this.distanceTraveled = 0.0;
        this.rideStartHour = 0;
        this.totalFare = 0.0;
        this.carType = "Mini";
        this.carMultiplier = 1.0;
    }

    public void startRide() {
        this.distanceTraveled = 0.0;
        this.rideStartHour = 0;
        this.totalFare = 0.0;
        System.out.println("Ride started.");
    }

    public void endRide(double distance, int hour, String selectedCar) {
        this.distanceTraveled = distance;
        this.rideStartHour = hour;
        setCarType(selectedCar);
        calculateFare();
        System.out.println("Ride ended.");
        System.out.println("Total distance: " + distanceTraveled + " km");
        System.out.println("Start time: " + rideStartHour + ":00 hrs");
        System.out.println("Car type: " + carType);
        System.out.println("Total fare: Rs." + totalFare);
    }

    private void setCarType(String selectedCar) {
        switch (selectedCar.toLowerCase()) {
            case "sedan":
                carType = "Sedan";
                carMultiplier = 1.5;
                break;
            case "suv":
                carType = "SUV";
                carMultiplier = 2.0;
                break;
            default:
                carType = "Mini";
                carMultiplier = 1.0;
                break;
        }
    }

    private void calculateFare() {
        double baseFare = distanceTraveled * PER_KM_RATE;
        double timeMultiplier = 1.0;
        if (isOfficeHour(rideStartHour)) {
            timeMultiplier = OFFICE_HOUR_MULTIPLIER;
        } else if (isNightHour(rideStartHour)) {
            timeMultiplier = NIGHT_HOUR_MULTIPLIER;
        }
        double surgeFare = baseFare * timeMultiplier;
        double fareAfterCar = surgeFare * carMultiplier;
        totalFare = Math.max(fareAfterCar, MINIMUM_FARE);
        if (distanceTraveled > OUTSTATION_DISTANCE_THRESHOLD) {
            totalFare += OUTSTATION_CHARGE;
        }
    }

    private boolean isOfficeHour(int hour) {
        return (hour >= 7 && hour < 9) || (hour >= 17 && hour < 19);
    }

    private boolean isNightHour(int hour) {
        return (hour >= 21 || hour < 6);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UberFareCalculator ride = new UberFareCalculator();

        System.out.println("Enter ride details:");
        System.out.print("Enter distance travelled (in km): ");
        double distance = scanner.nextDouble();

        System.out.print("Enter ride start time (hour in 24-hour format): ");
        int hour = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Select car type (Mini / Sedan / SUV): ");
        String carType = scanner.nextLine();

        ride.startRide();
        ride.endRide(distance, hour, carType);

        scanner.close();
    }
}
