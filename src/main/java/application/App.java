package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		Scanner scanner = new Scanner(System.in);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {
			System.out.print("Enter room number: ");
			int roomNumber = scanner.nextInt();
			System.out.print("check-in date (dd/MM/yyyy): ");
			Date checkInDate = dateFormat.parse(scanner.next());
			System.out.print("check-out date (dd/MM/yyyy): ");
			Date checkOutDate = dateFormat.parse(scanner.next());

			Reservation reservation = new Reservation(roomNumber, checkInDate, checkOutDate);
			System.out.println("Reservation: " + reservation);

			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			System.out.print("check-in date (dd/MM/yyyy): ");
			checkInDate = dateFormat.parse(scanner.next());
			System.out.print("check-out date (dd/MM/yyyy): ");
			checkOutDate = dateFormat.parse(scanner.next());

			reservation.updateDates(checkInDate, checkOutDate);
			System.out.println("Reservation: " + reservation);
		}
		catch (ParseException exception) {
			System.out.println("Invalid date format");
		}
		catch (DomainException exception) {
			System.out.println("Error in reservation: " + exception.getMessage());
		}
		catch (RuntimeException exception) {
			System.out.println("Unexpected error");
		}

		scanner.close();

	}


}
