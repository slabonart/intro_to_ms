package pl.slabonart.epam.itms.song_service.exceptions;

public class InvalidRequestException extends Exception {

    private static final String MESSAGE = "Song metadata missing validation error";

    public InvalidRequestException() {
        super(MESSAGE);
    }
}
