package ai.proposax.proposal_selector.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @author MOSAED ALOTAIBI
 */

@Service
public class ProposalStorageService {

    private final String storageLocation = "uploads/";

    public String storeFile(MultipartFile file) {
        try {
            Path filePath = Paths.get(storageLocation + file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + file.getOriginalFilename(), e);
        }
    }
}
