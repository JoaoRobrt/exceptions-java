package application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		try {
			System.out.print("Room number: ");
			int number = sc.nextInt();
			sc.nextLine();
			System.out.print("Check-in date (dd/mm/yyyy): ");
			Instant checkIn = LocalDate.parse(sc.nextLine(), fmt).atStartOfDay(ZoneId.systemDefault()).toInstant();
			System.out.print("Check-out date (dd/mm/yyyy): ");
			Instant checkOut = LocalDate.parse(sc.nextLine(), fmt).atStartOfDay(ZoneId.systemDefault()).toInstant();

			Reservation reservation = new Reservation(number, checkIn, checkOut, "America/Sao_Paulo");
			System.out.println("Reservation: " + reservation);

			System.out.println();
			System.out.println("Enter data to update the reservation: ");

			checkIn = LocalDate.parse(sc.nextLine(), fmt).atStartOfDay(ZoneId.systemDefault()).toInstant();
			System.out.print("Check-out date (dd/mm/yyyy): ");
			checkOut = LocalDate.parse(sc.nextLine(), fmt).atStartOfDay(ZoneId.systemDefault()).toInstant();

			reservation.updateDates(checkIn, checkOut);

			System.out.println("Error in reservation: " + reservation);
			System.out.println("Reservation: " + reservation);

		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format");

		} catch (DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage());

		} catch (RuntimeException e) {
			System.out.println("Unexpected error!");
		}
		
		sc.close();
	}
}
