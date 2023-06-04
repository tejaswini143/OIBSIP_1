// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
/*public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);
        }
    }
}*/
import java.util.*;

class Reservation {
    private int roomNumber;
    protected String guestName;
    private boolean isReserved;

    public Reservation(int roomNumber, String guestName) {
        this.roomNumber = roomNumber;
        this.guestName = guestName;
        this.isReserved = false;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void reserve() {
        isReserved = true;
    }

    public void cancelReservation() {
        isReserved = false;
        guestName = "";
    }
}

class ReservationSystem {
    private List<Reservation> reservations;

    public ReservationSystem() {
        reservations = new ArrayList<>();
        // Initialize the list of reservations
        for (int i = 1; i <= 10; i++) {
            reservations.add(new Reservation(i, ""));
        }
    }

    public void displayAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Reservation reservation : reservations) {
            if (!reservation.isReserved()) {
                System.out.println("Room Number: " + reservation.getRoomNumber());
            }
        }
    }

    public boolean makeReservation(int roomNumber, String guestName) {
        Reservation reservation = getReservationByRoomNumber(roomNumber);
        if (reservation != null && !reservation.isReserved()) {
            reservation.reserve();
            reservation.guestName = guestName;
            System.out.println("Reservation confirmed. Room number: " + roomNumber);
            return true;
        } else {
            System.out.println("Room number " + roomNumber + " is not available for reservation.");
            return false;
        }
    }

    public void cancelReservation(int roomNumber) {
        Reservation reservation = getReservationByRoomNumber(roomNumber);
        if (reservation != null && reservation.isReserved()) {
            reservation.cancelReservation();
            System.out.println("Reservation canceled for room number: " + roomNumber);
        } else {
            System.out.println("No reservation found for room number: " + roomNumber);
        }
    }

    private Reservation getReservationByRoomNumber(int roomNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoomNumber() == roomNumber) {
                return reservation;
            }
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        ReservationSystem reservationSystem = new ReservationSystem();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Display available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. Cancel a reservation");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    reservationSystem.displayAvailableRooms();
                    break;
                case 2:
                    System.out.print("Enter room number: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter guest name: ");
                    scanner.nextLine(); // Consume newline character
                    String guestName = scanner.nextLine();
                    reservationSystem.makeReservation(roomNumber, guestName);
                    break;
                case 3:
                    System.out.print("Enter room number: ");
                    roomNumber = scanner.nextInt();
                    reservationSystem.cancelReservation(roomNumber);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
