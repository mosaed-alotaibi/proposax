package ai.proposax.proposal_selector.presentation;

import ai.proposax.proposal_selector.service.ProposalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MOSAED ALOTAIBI
 */

@RestController
@RequestMapping("/api/files")
@CrossOrigin(origins = "*")
public class FileUploadController {

    @Autowired
    private ProposalStorageService proposalStorageService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") MultipartFile[] files) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = proposalStorageService.storeFile(file);
            fileNames.add(fileName);
        }
        return ResponseEntity.ok("Uploaded files: " + fileNames);
    }
}
