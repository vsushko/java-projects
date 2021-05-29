package com.mockitotutorial.happyhotel.booking;

import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
public class BookingService {

    private final static double BASE_PRICE_USD = 50.0;
    private final PaymentService paymentService;
    private final RoomService roomService;
    private final BookingDAO bookingDAO;
    private final MailSender mailSender;

    public BookingService(PaymentService paymentService, RoomService roomService, BookingDAO bookingDAO,
                          MailSender mailSender) {
        super();
        this.paymentService = paymentService;
        this.roomService = roomService;
        this.bookingDAO = bookingDAO;
        this.mailSender = mailSender;
    }

    public int getAvailablePlaceCount() {
        return roomService.getAvailableRooms()
                .stream()
                .map(room -> room.getCapacity())
                .reduce(0, Integer::sum);
    }

    public double calculatePrice(BookingRequest bookingRequest) {
        long nights = ChronoUnit.DAYS.between(bookingRequest.getDateFrom(), bookingRequest.getDateTo());
        return BASE_PRICE_USD * bookingRequest.getGuestCount() * nights;
    }

    public double calculatePriceEuro(BookingRequest bookingRequest) {
        return CurrencyConverter.toEuro(calculatePrice(bookingRequest));
    }

    public String makeBooking(BookingRequest bookingRequest) {
        String roomId = roomService.findAvailableRoomId(bookingRequest);
        double price = calculatePrice(bookingRequest);

        if (bookingRequest.isPrepaid()) {
            paymentService.pay(bookingRequest, price);
        }

        bookingRequest.setRoomId(roomId);
        String bookingId = bookingDAO.save(bookingRequest);
        roomService.bookRoom(roomId);
        mailSender.sendBookingConfirmation(bookingId);
        return bookingId;
    }

    public void cancelBooking(String id) {
        BookingRequest request = bookingDAO.get(id);
        roomService.unbookRoom(request.getRoomId());
        bookingDAO.delete(id);
    }
}
