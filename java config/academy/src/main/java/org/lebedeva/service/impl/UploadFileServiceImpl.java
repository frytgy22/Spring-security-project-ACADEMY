package org.lebedeva.service.impl;

import org.lebedeva.service.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UploadFileServiceImpl implements UploadFileService {

    private final ResourceLoader resourceLoader;

    @Autowired
    public UploadFileServiceImpl(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public List<String> listFiles(String uploadsDir) throws IOException {
        Resource resource = resourceLoader.getResource(uploadsDir);
        if (resource.exists()) {
            return Files.list(Paths.get(resource.getURI()))
                    .map(path -> path.getFileName().toString())
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void uploadFile(MultipartFile file, String dir, Integer folderName) throws IOException {
        Resource resource = resourceLoader.getResource(dir);

        if (resource.exists()) {
            Path path = Paths.get(resource.getFile().getAbsolutePath() + "/" + folderName);

            if (path.toFile().exists()) {
                Files.walk(path)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            }
            Path directories = Files.createDirectories(path);
            File uploadDir = directories.toFile();
            System.out.println(uploadDir);
            if (uploadDir.exists()) {
                Path dest = Paths.get(uploadDir.getAbsolutePath(), file.getOriginalFilename());
                System.out.println(dest);
                file.transferTo(dest);
            }
        }
    }
}
