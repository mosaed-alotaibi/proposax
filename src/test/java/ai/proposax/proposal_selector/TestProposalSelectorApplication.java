package ai.proposax.proposal_selector;

import org.springframework.boot.SpringApplication;

public class TestProposalSelectorApplication {

	public static void main(String[] args) {
		SpringApplication.from(ProposalSelectorApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
