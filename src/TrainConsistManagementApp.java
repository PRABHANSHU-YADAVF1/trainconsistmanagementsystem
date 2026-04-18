/**
 * Train Consist Management App
 * UC14: Handle Invalid Bogie Capacity using Custom Exception
 */

public class TrainConsistManagementApp {

    // ----- CUSTOM EXCEPTION -----
    static class InvalidCapacityException extends Exception {
        public InvalidCapacityException(String message) {
            super(message);
        }
    }

    // ----- PASSENGER BOGIE CLASS -----
    static class PassengerBogie {
        String type;
        int capacity;

        // Constructor with validation
        PassengerBogie(String type, int capacity) throws InvalidCapacityException {
            if (capacity <= 0) {
                throw new InvalidCapacityException("Capacity must be greater than zero");
            }
            this.type = type;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return type + " (" + capacity + ")";
        }
    }

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println(" Train Consist Management App");
        System.out.println("=========================================\n");

        System.out.println("========== UC14 - Custom Exception Validation ==========\n");

        try {
            // VALID bogie
            PassengerBogie b1 = new PassengerBogie("Sleeper", 72);
            System.out.println("Created: " + b1);

            // INVALID bogie (will throw exception)
            PassengerBogie b2 = new PassengerBogie("AC Chair", 0);
            System.out.println("Created: " + b2);

        } catch (InvalidCapacityException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
/**
 * Train Consist Management App
 * UC15: Safe Cargo Assignment using try-catch-finally
 */

public class TrainConsistManagementApp {

    // ----- CUSTOM RUNTIME EXCEPTION -----
    static class CargoSafetyException extends RuntimeException {
        public CargoSafetyException(String message) {
            super(message);
        }
    }

    // ----- GOODS BOGIE CLASS -----
    static class GoodsBogie {
        String shape; // Cylindrical / Rectangular
        String cargo; // Petroleum / Coal etc.

        GoodsBogie(String shape) {
            this.shape = shape;
        }

        // Method to assign cargo safely
        void assignCargo(String cargo) {
            try {
                // ----- VALIDATION -----
                if (shape.equals("Rectangular") && cargo.equals("Petroleum")) {
                    throw new CargoSafetyException(
                        "Unsafe: Rectangular bogie cannot carry Petroleum"
                    );
                }

                // Assign cargo if safe
                this.cargo = cargo;
                System.out.println("Cargo assigned successfully: " + cargo);

            } catch (CargoSafetyException e) {
                System.out.println("Error: " + e.getMessage());

            } finally {
                System.out.println("Assignment attempt completed.\n");
            }
        }

        @Override
        public String toString() {
            return shape + " (" + cargo + ")";
        }
    }

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println(" Train Consist Management App");
        System.out.println("=========================================\n");

        System.out.println("========== UC15 - Safe Cargo Assignment ==========\n");

        // Create bogies
        GoodsBogie b1 = new GoodsBogie("Cylindrical");
        GoodsBogie b2 = new GoodsBogie("Rectangular");

        // ----- SAFE assignment -----
        b1.assignCargo("Petroleum"); // valid

        // ----- UNSAFE assignment -----
        b2.assignCargo("Petroleum"); // invalid

        // Program continues
        b2.assignCargo("Coal"); // valid

        System.out.println("Final State:");
        System.out.println(b1);
        System.out.println(b2);
    }
}
