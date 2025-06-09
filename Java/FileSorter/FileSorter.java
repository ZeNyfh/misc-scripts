import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class FileSorter {
    public static void main(String[] args) {
        String directory = System.getProperty("user.dir");
        System.out.println(directory);
        File[] files = new File(directory).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    if (file.getName().equals("FileSorter.java") || (file.getName().equals("FileSorter.jar"))) {
                        continue;
                    }
                    String fileName = file.getName();
                    String fileExtension = getFileExtension(fileName);
                    String newName = generateUniqueFileName(directory, fileName);
                    if (newName == null) {
                        System.out.println("Unable to generate a unique file name for: " + fileName);
                        continue;
                    }
                    System.out.println("Moving: " + fileName);
                    renameFile(file, newName);
                    moveFileToDestination(newName, fileExtension);
                }
            }
        }
        System.out.println("Finished moving files.");
    }

    private static String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex);
        }
        return "";
    }

    private static String generateUniqueFileName(String directory, String fileName) {
        String nameWithoutExtension = fileName.substring(0, fileName.lastIndexOf('.'));
        String fileExtension = getFileExtension(fileName);
        Random random = new Random();
        int maxAttempts = 100;
        int attempt = 0;
        while (attempt < maxAttempts) {
            String newName = nameWithoutExtension + random.nextInt(1000000) + fileExtension;
            File newFile = new File(directory, newName);
            if (!newFile.exists()) {
                return newName;
            }
            attempt++;
        }
        return null;
    }

    private static void renameFile(File file, String newName) {
        try {
            Path source = file.toPath();
            Path target = new File(file.getParent(), newName).toPath();
            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void moveFileToDestination(String fileName, String fileExtension) {
        String destinationDirectory = "";
        if (isAudioFile(fileExtension)) {
            destinationDirectory = "M:/Music";
        } else if (isVideoFile(fileExtension)) {
            destinationDirectory = "M:/Videos";
        } else if (isDocumentFile(fileExtension)) {
            destinationDirectory = "C:/Users/ZeNyfh/Documents";
        } else if (isImageFile(fileExtension)) {
            destinationDirectory = "M:/Images";
        } else {
            destinationDirectory = "M:/Unknown";
        }
        try {
            Files.move(new File(fileName).toPath(), new File(destinationDirectory, fileName).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isAudioFile(String fileExtension) {
        String[] audioExtensions = { ".mp3", ".ogg", ".flac", ".wav", ".mid", ".mod", ".midi", ".xm" };
        return endsWithAny(fileExtension, audioExtensions);
    }

    private static boolean isVideoFile(String fileExtension) {
        String[] videoExtensions = { ".mp4", ".m4a", ".webm", ".wmv", ".mov" };
        return endsWithAny(fileExtension, videoExtensions);
    }

    private static boolean isDocumentFile(String fileExtension) {
        String[] documentExtensions = { ".docx", ".txt", ".rtf", ".doc", ".odt" };
        return endsWithAny(fileExtension, documentExtensions);
    }

    private static boolean isImageFile(String fileExtension) {
        String[] imageExtensions = { ".jpg", ".jpeg", ".gif", ".png", ".apng", "webp", "jfif" };
        return endsWithAny(fileExtension, imageExtensions);
    }

    private static boolean endsWithAny(String value, String[] suffixes) {
        for (String suffix : suffixes) {
            if (value.endsWith(suffix)) {
                return true;
            }
        }
        return false;
    }
}
