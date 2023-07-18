package pl.slabonart.epam.itms.song_resource_service.util;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import pl.slabonart.epam.itms.song_resource_service.dto.SongMetaDataDTO;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class SongMetaDataExtractor {

    private SongMetaDataExtractor() {
    }

    private static final String TITLE = "title";
    private static final String ARTIST = "xmpDM:artist";
    private static final String ALBUM = "xmpDM:album";
    private static final String DURATION = "xmpDM:duration";
    private static final String RELEASE_DATE = "xmpDM:releaseDate";

    public static SongMetaDataDTO extract(MultipartFile multipartFile) throws IOException, TikaException, SAXException {

        File file = convertMultiPartToFile(multipartFile);
        Mp3Parser mp3Parser = new Mp3Parser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        mp3Parser.parse(inputstream, handler, metadata, context);

        return SongMetaDataDTO.builder()
                .name(metadata.get(TITLE))
                .artist(metadata.get(ARTIST))
                .album(metadata.get(ALBUM))
                .length(metadata.get(DURATION))
                .year(metadata.get(RELEASE_DATE))
                .build();
    }

    private static File convertMultiPartToFile(MultipartFile file) throws IOException {

        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fileOutputStream = new FileOutputStream(convFile);
        fileOutputStream.write(file.getBytes());
        fileOutputStream.close();
        return convFile;
    }
}
