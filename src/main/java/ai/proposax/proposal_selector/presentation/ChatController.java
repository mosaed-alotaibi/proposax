package ai.proposax.proposal_selector.presentation;

import ai.proposax.proposal_selector.service.AiAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author MOSAED ALOTAIBI
 */

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private AiAgentService aiAgentService;

    @PostMapping("/compare")
    public ResponseEntity<String> compareDocuments(@RequestBody Map<String, List<String>> request) {
        List<String> fileUrls = request.get("fileUrls");
        if (fileUrls == null || fileUrls.isEmpty()) {
            return ResponseEntity.badRequest().body("At least two file URLs are required for comparison.");
        }

        try {
            String response = aiAgentService.queryChatGPTWithMultiplePDFs(fileUrls);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing comparison.");
        }
    }
}
