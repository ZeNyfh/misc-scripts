#include <iostream>
#include <list>
#include <algorithm>
#include <filesystem>

namespace fs = std::filesystem;

void print(const std::string& message) {
    std::cout << message << "\n";
}

fs::path getUniqueDestinationPath(const fs::path& file, const fs::path& destinationDir) {
    fs::path target = destinationDir / file.filename();
    while (fs::exists(target)) {
        int randomNumber = rand() % 100000;
        target = destinationDir / (std::to_string(randomNumber) + file.filename().string());
    }
    return target;
}

std::string toLower(const std::string& str) {
    std::string lowerStr = str;
    std::transform(lowerStr.begin(), lowerStr.end(), lowerStr.begin(), [](unsigned char c) {
        return std::tolower(c);
    });
    return lowerStr;
}

bool containsIgnoreCase(const std::list<std::string>& list, const std::string& value) {
    std::string target = toLower(value);
    for (const auto& item : list) {
        if (toLower(item) == target) return true;
    }
    return false;
}

void moveFile(const fs::path& source, const fs::path& destinationDir) {
    try {
        fs::path newPath = getUniqueDestinationPath(source, destinationDir);
        fs::copy(source, newPath, fs::copy_options::overwrite_existing);
        fs::remove(source);
    } catch (const fs::filesystem_error& e) {
        std::cerr << "Filesystem error: " << e.what() << '\n';
    } catch (const std::exception& e) {
        std::cerr << "Error: " << e.what() << '\n';
    }
}

int main() {
    const std::list<std::string> musicExts    = {".mp3", ".ogg", ".flac", ".wav", ".mid", ".mod", ".midi", ".xm"};
    const std::list<std::string> videoExts    = {".mp4", ".m4a", ".webm", ".wmv", ".mov"};
    const std::list<std::string> documentExts = {".docx", ".txt", ".rtf", ".doc", ".odt", ".pdf"};
    const std::list<std::string> imageExts    = {".jpg", ".jpeg", ".gif", ".png", ".apng", ".webp", ".jfif"};

    const fs::path musicDir = "/mnt/Media/Music";
    const fs::path videoDir = "/mnt/Media/Videos";
    const fs::path docDir   = "/home/zenyfh/Documents";
    const fs::path imageDir = "/mnt/Media/Images";
    const fs::path otherDir = "/mnt/Media/Unknown";

    const fs::path currentDir = fs::current_path();

    try {
        for (const auto& entry : fs::directory_iterator(currentDir)) {
            fs::path filePath = entry.path();
            std::string fileName = filePath.filename().string();

            if (fileName == "fileSorter" || fileName == "fileSorter.cpp" || "compile.sh") {
                print("Skipping sorter file: " + fileName);
                continue;
            }

            if (entry.is_directory()) {
                print("Moving directory: " + fileName);
                fs::path destination = otherDir / fileName;
                print("Destination: " + destination.string());

                if (fs::exists(destination)) {
                    std::cerr << "Error: Destination already exists: " << destination << '\n';
                    continue;
                }

                fs::copy(filePath, destination, fs::copy_options::recursive);
                if (fs::exists(destination) && fs::is_directory(destination)) {
                    fs::remove_all(filePath);
                }
                continue;
            }

            if (entry.is_regular_file()) {
                print("Processing file: " + fileName);
                std::string ext = filePath.extension().string();

                if (containsIgnoreCase(musicExts, ext)) {
                    moveFile(filePath, musicDir);
                } else if (containsIgnoreCase(videoExts, ext)) {
                    moveFile(filePath, videoDir);
                } else if (containsIgnoreCase(documentExts, ext)) {
                    moveFile(filePath, docDir);
                } else if (containsIgnoreCase(imageExts, ext)) {
                    moveFile(filePath, imageDir);
                } else {
                    moveFile(filePath, otherDir);
                }
            }
        }
    } catch (const std::exception& e) {
        std::cerr << "Unhandled error: " << e.what() << '\n';
    }

    return 0;
}
