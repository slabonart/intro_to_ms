package pl.slabonart.epam.itms.song_resource_service.exception;

public class InvalidFileException extends Exception {

    private static final String MESSAGE = "Validation failed or request body is invalid MP3";

    public InvalidFileException() {
        super(MESSAGE);
    }
}
