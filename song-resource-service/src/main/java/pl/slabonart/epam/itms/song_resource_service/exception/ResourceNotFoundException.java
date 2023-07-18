package pl.slabonart.epam.itms.song_resource_service.exception;

import java.util.List;

public class ResourceNotFoundException extends RuntimeException {

    private static final String MESSAGE = "The resource with id %s does not exist";
    private static final String MESSAGE_LIST = "The resources with ids %s does not exist";

    public ResourceNotFoundException(long id) {
        super(String.format(MESSAGE, id));
    }

    public ResourceNotFoundException(List<Long> ids) {
        super(String.format(MESSAGE_LIST, ids));
    }
}
