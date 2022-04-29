package no.fintlabs.consent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Slf4j
public class LoadDataFile {

    public static InputStream readResource(String fileType) {
        try {
            File file = ResourceUtils.getFile("classpath:springer/" + fileType + ".json");
            FileInputStream stream = new FileInputStream(file);

            return stream;
        } catch (FileNotFoundException e) {
            log.error("Error", e);
        }
        return null;

    }

    public static String extractSystemId(String href) {
        if (href.lastIndexOf("/") < 0) return "";
        return href.substring(href.lastIndexOf("/") + 1);
    }

}
