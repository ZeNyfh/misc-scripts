import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class VideoCompressor {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        int targetSize = 10; // MB
        String fullName = String.join(" ", args);
        Path inputPath = Paths.get(fullName);
        String inputFile = inputPath.getFileName().toString();
        String inputFileName = inputFile.substring(0, inputFile.lastIndexOf('.'));
        String targetFile = inputFileName + "_ZenCompress.mp4"; // filename out
        System.out.println("Started: " + inputPath.getFileName().toString());
        try {

            // get the duration
            ProcessBuilder durationProcessBuilder = new ProcessBuilder(
                    "ffprobe", "-v", "error", "-show_entries", "format=duration", "-of", "csv=p=0", inputPath.toString()
            ).redirectErrorStream(true);
            Process durationProcess = durationProcessBuilder.start();
            String durationOutput = new BufferedReader(new InputStreamReader(durationProcess.getInputStream())).readLine();
            float originalDuration = Float.parseFloat(durationOutput.trim());

            // get the bitrate
            ProcessBuilder bitrateProcessBuilder = new ProcessBuilder(
                    "ffprobe", "-v", "error", "-select_streams", "a:0", "-show_entries", "stream=bit_rate", "-of", "csv=p=0", inputPath.toString()
            ).redirectErrorStream(true);
            Process bitrateProcess = bitrateProcessBuilder.start();
            String bitrateOutput = new BufferedReader(new InputStreamReader(bitrateProcess.getInputStream())).readLine();

            // set vars for calculation
            int originalAudioRate = Integer.parseInt(bitrateOutput) / 1024;
            double targetVideoRate = ((targetSize * 8192.0) / (1.06 * originalDuration)) - originalAudioRate; // used to be 1.048576, but I got an edge case.
            double targetMinSize = (originalAudioRate * originalDuration) / 8192;

            // check if the resize is possible
            if (targetMinSize > targetSize) {
                System.out.println("Target size " + targetSize + "MB is too small!");
                System.out.println("Try values larger than " + targetMinSize + "MB");
                System.exit(1);
            }

            // 1st pass
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "ffmpeg", "-y", "-i", inputPath.toString(), "-c:v", "libx264", "-b:v", (int) targetVideoRate + "k",
                    "-pass", "1", "-an", "-f", "mp4", "-map", "0", "-map_metadata", "-1", targetFile
            );
            Process passProcess = processBuilder.start();

            // Read errorstream (they print to that for some reason) and update filesize progress. 
            InputStream stream = passProcess.getErrorStream();
            InputStreamReader streamReader = new InputStreamReader(stream);
            BufferedReader bufferedReader = new BufferedReader(streamReader);
            String line;

            line = bufferedReader.readLine();
            while (line != null) {
                line = bufferedReader.readLine();
                System.out.format(String.format("%.3f", (float) new File(targetFile).length()/1024/1024) + "MB / " + targetSize + "MB \r");
            }
            System.out.println("\rPass 1 Complete.");

            // 2nd pass
            processBuilder = new ProcessBuilder(
                    "ffmpeg", "-y", "-i", inputPath.toString(), "-c:v", "libx264", "-b:v", (int) targetVideoRate + "k",
                    "-pass", "2", "-c:a", "aac", "-b:a", originalAudioRate + "k", "-map", "0", "-map_metadata", "-1", targetFile
            );
            passProcess = processBuilder.start();

            // Read errorstream (they print to that for some reason) and update filesize progress. 
            stream = passProcess.getErrorStream();
            streamReader = new InputStreamReader(stream);
            bufferedReader = new BufferedReader(streamReader);
            line = bufferedReader.readLine();
            
            while (line != null) {
                line = bufferedReader.readLine();
                System.out.format(String.format("%.3f", (float) new File(targetFile).length()/1024/1024) + "MB / " + targetSize + "MB \r");
            }
            System.out.println("Pass 2 complete.\n" + (System.currentTimeMillis() - time) / 1000 + " seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
