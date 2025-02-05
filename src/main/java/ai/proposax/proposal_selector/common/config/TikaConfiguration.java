package ai.proposax.proposal_selector.common.config;

import org.apache.tika.Tika;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author MOSAED ALOTAIBI
 */

@Configuration
public class TikaConfiguration {

    @Bean
    public Tika tika() {
        return new Tika();
    }
}
