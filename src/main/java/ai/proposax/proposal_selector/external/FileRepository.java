package ai.proposax.proposal_selector.external;

import ai.proposax.proposal_selector.external.entity.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author MOSAED ALOTAIBI
 */

@Repository
public interface FileRepository extends JpaRepository<UploadedFile, Long> {
}
