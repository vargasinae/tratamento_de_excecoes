package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date dateCheckIn;
    private Date dateCheckOut;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer roomNumber, Date dateCheckIn, Date dateCheckOut) {
        if (!dateCheckOut.after(dateCheckIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getDateCheckIn() {
        return dateCheckIn;
    }

    public Date getCheckOut() {
        return dateCheckOut;
    }

    public long reservationDuration() {
        long diff = dateCheckOut.getTime() - dateCheckIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date dateCheckIn, Date dateCheckOut) throws DomainException {
        Date today = new Date();
        if (dateCheckIn.before(today) || dateCheckOut.before(today)) {
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if (!dateCheckOut.after(dateCheckIn)) {
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
    }

    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + dateFormat.format(dateCheckIn)
                + ", check-out: "
                + dateFormat.format(dateCheckOut)
                + ", "
                + reservationDuration()
                + " nights";
    }
}
