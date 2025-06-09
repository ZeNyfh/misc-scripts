import java.io.*;
import java.nio.file.*;
import java.security.*;
import java.util.*;

public class DuplicateFileRemover {
    private static Map<String, String> fileHashes = new HashMap<>();
    private static final String HASH_CACHE_FILE = System.getProperty("java.io.tmpdir") + File.separator + "fileHashes.cache";
    private static final int BUFFER_SIZE = 8192; // 8 KB buffer size
    private static final String SELF_FILENAME = "duplicateFileFinder.java";

    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current Working Directory: " + currentDir);

        try {
            loadFileHashes();
            scanFiles(currentDir);
            removeDuplicateFiles();
            saveFileHashes();
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static void loadFileHashes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(HASH_CACHE_FILE))) {
            fileHashes = (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Ignore if cache file doesn't exist or is corrupted
        }
    }

    private static void saveFileHashes() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(HASH_CACHE_FILE))) {
            oos.writeObject(fileHashes);
        }
    }

    private static void scanFiles(String directory) throws IOException, NoSuchAlgorithmException {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory))) {
            for (Path path : directoryStream) {
                if (Files.isRegularFile(path)) {
                    String fileName = path.getFileName().toString();
                    if (fileName.equals(SELF_FILENAME)) {
                        continue; // Skip the current file (duplicateFileFinder.java)
                    }

                    String fileHash = computeFileHash(path);

                    if (!fileHashes.containsKey(fileHash)) {
                        fileHashes.put(fileHash, fileName);
                    } else {
                        System.out.println("Duplicate file found: " + fileName);
                        deleteFile(path);
                    }
                }
            }
        }
    }

    private static String computeFileHash(Path file) throws IOException, NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] buffer = new byte[BUFFER_SIZE];
        try (InputStream is = Files.newInputStream(file)) {
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
        }
        byte[] hashBytes = digest.digest();
        return bytesToHex(hashBytes);
    }

    private static void deleteFile(Path file) throws IOException {
        Files.delete(file);
        System.out.println("Deleted file: " + file.getFileName());
    }

    private static void removeDuplicateFiles() throws IOException {
        for (String fileName : fileHashes.values()) {
            System.out.println("Keeping original file: " + fileName);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
