package pl.slabonart.epam.itms.song_service.exceptions;

import java.util.List;

public class SongMetaDataNotFoundException extends RuntimeException {

    private static final String MESSAGE = "The song metadata with id %s does not exist";
    private static final String MESSAGE_LIST = "The song metadata with ids %s do not exist";

    public SongMetaDataNotFoundException(long id) {
        super(String.format(MESSAGE, id));
    }

    public SongMetaDataNotFoundException(List<Long> ids) {
        super(String.format(MESSAGE_LIST, ids));
    }
}
