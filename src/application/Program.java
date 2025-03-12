package application;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.entities.Reservation;

public class Program {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.print("Room number: ");
		int number = sc.nextInt();
		sc.nextLine();
		System.out.print("Check-in date (dd/mm/yyyy): ");
		Instant checkIn = LocalDate.parse(sc.nextLine(), fmt).atStartOfDay(ZoneId.systemDefault()).toInstant();
		System.out.print("Check-out date (dd/mm/yyyy): ");
		Instant checkOut = LocalDate.parse(sc.nextLine(), fmt).atStartOfDay(ZoneId.systemDefault()).toInstant();

		if (!checkOut.isAfter(checkIn)) {
			System.out.println("Error in reservation: Check-out must be after check-in date");
		} else {
			Reservation reservation = new Reservation(number, checkIn, checkOut, "America/Sao_Paulo");
			System.out.println("Reservation: " + reservation);

			System.out.println();
			System.out.println("Enter data to update the reservation: ");

			checkIn = LocalDate.parse(sc.nextLine(), fmt).atStartOfDay(ZoneId.systemDefault()).toInstant();
			System.out.print("Check-out date (dd/mm/yyyy): ");
			checkOut = LocalDate.parse(sc.nextLine(), fmt).atStartOfDay(ZoneId.systemDefault()).toInstant();

			String error = reservation.updateDates(checkIn, checkOut);
			if (error != null) {
				System.out.println("Error in reservation: " + error);
			} else {
				System.out.println("Reservation: " + reservation);
			}

		}

		sc.close();
	}
}
