package edu.java.domain.jpa.dao;

import edu.java.domain.jpa.dto.StackoverflowLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaStackoverflowLinkRepository extends JpaRepository<StackoverflowLink, Long> {
}
