package model.entities;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Instant checkIn;
	private Instant checkOut;

	private ZoneId hotelZone;

	private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Reservation() {

	}

	public Reservation(Integer roomNumber, Instant checkIn, Instant checkOut, String hotelZone){
		if (!checkOut.isAfter(checkIn)) {
			throw new DomainException("Check-out must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.hotelZone = ZoneId.of(hotelZone);
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Instant getCheckIn() {
		return checkIn;
	}

	public Instant getCheckOut() {
		return checkOut;
	}

	public long duration() {
		LocalDateTime checkInLocal = LocalDateTime.ofInstant(checkIn, hotelZone);
		LocalDateTime checkOutLocal = LocalDateTime.ofInstant(checkOut, hotelZone);

		return ChronoUnit.DAYS.between(checkInLocal, checkOutLocal);
	}

	public void updateDates(Instant checkIn, Instant checkOut){

		Instant now = Instant.now();
		if (checkIn.isBefore(now) || checkOut.isBefore(now)) {
			throw new DomainException("Reservation dates for update must be future dates.");
		}
		if (!checkOut.isAfter(checkIn)) {
			throw new DomainException("Check-out must be after check-in date");
		}
		this.checkIn = checkIn;
		this.checkOut = checkOut;
	}

	@Override
	public String toString() {
		LocalDateTime checkInLocal = LocalDateTime.ofInstant(checkIn, hotelZone);
		LocalDateTime checkOutLocal = LocalDateTime.ofInstant(checkOut, hotelZone);

		return "Room " + roomNumber + ", check-in: " + fmt.format(checkInLocal) + ", check-out: "
				+ fmt.format(checkOutLocal) + ", Duration: " + duration() + " nights";
	}

}
