package com.testTasks.CSVParserAPI.utils;

import com.github.junrar.Archive;
import com.github.junrar.exception.RarException;
import com.github.junrar.rarfile.FileHeader;
import lombok.experimental.UtilityClass;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;

/**
 * This one is a pure copy-paste from <a href="https://github.com/edmund-wagner/junrar/blob/master/testutil/src/main/java/com/github/junrar/testutil/ExtractArchive.java">here</a>
 * A lot of deprecated methods, so probably it would be better to use unrar from OS, or make a client to use conventional file types.
 */
@UtilityClass
public class RarService {

    private static final Log logger = LogFactory.getLog(RarService.class.getName());

    public static Path extractArchive(File archive, File destination) {
        Archive arch = null;
        try {
            arch = new Archive(archive);
        } catch (RarException | IOException e) {
            logger.error(e);
        }
        try {
            if (arch != null) {
                if (arch.isEncrypted()) {
                    logger.warn("archive is encrypted cannot extreact");
                    return null;
                }
                FileHeader fh;
                while (true) {
                    fh = arch.nextFileHeader();
                    if (fh == null) {
                        break;
                    }
                    if (fh.isEncrypted()) {
                        logger.warn("file is encrypted cannot extract: "
                                + fh.getFileNameString());
                        continue;
                    }
                    logger.info("extracting: " + fh.getFileNameString());
                    try {
                        if (fh.isDirectory()) {
                            createDirectory(fh, destination);
                        } else {
                            File f = createFile(fh, destination.getParentFile());
                            OutputStream stream = new FileOutputStream(f);
                            arch.extractFile(fh, stream);
                            stream.close();
                            return f.toPath();
                        }
                    } catch (IOException e) {
                        logger.error("error extracting the file", e);
                    } catch (RarException e) {
                        logger.error("error extraction the file", e);
                    }
                }
            }
        } catch (RarException ignored) {
        }
        return null;
    }

    private static File createFile(FileHeader fh, File destination) {
        File f;
        String name;
        if (fh.isFileHeader() && fh.isUnicode()) {
            name = fh.getFileNameW();
        } else {
            name = fh.getFileNameString();
        }
        f = new File(destination, name);
        if (!f.exists()) {
            try {
                f = makeFile(destination, name);
            } catch (IOException e) {
                logger.error("error creating the new file: " + f.getName(), e);
            }
        }
        return f;
    }

    private static File makeFile(File destination, String name)
            throws IOException {
        String[] dirs = name.split("\\\\");
        String path = "";
        int size = dirs.length;
        if (size == 1) {
            return new File(destination, name);
        } else if (size > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                path = path + File.separator + dirs[i];
                new File(destination, path).mkdir();
            }
            path = path + File.separator + dirs[dirs.length - 1];
            File f = new File(destination, path);
            f.createNewFile();
            return f;
        } else {
            return null;
        }
    }

    private static void createDirectory(FileHeader fh, File destination) {
        if (fh.isDirectory() && fh.isUnicode()) {
            File f = new File(destination, fh.getFileNameW());
            if (!f.exists()) {
                makeDirectory(destination, fh.getFileNameW());
            }
        } else if (fh.isDirectory() && !fh.isUnicode()) {
            File f = new File(destination, fh.getFileNameString());
            if (!f.exists()) {
                makeDirectory(destination, fh.getFileNameString());
            }
        }
    }

    private static void makeDirectory(File destination, String fileName) {
        String[] dirs = fileName.split("\\\\");
        String path = "";
        for (String dir : dirs) {
            path = path + File.separator + dir;
            new File(destination, path).mkdir();
        }
    }
}
