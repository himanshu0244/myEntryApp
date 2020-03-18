package org.myEntryApp.server.repository;

import java.util.List;
import java.util.Optional;

import org.myEntryApp.server.domain.Visitor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VisitorRepository extends CrudRepository<Visitor, Long> {

	@Query("Select visitor from Visitor visitor where visitor.activeInd=1 ")
	Optional<List<Visitor>> fetchAllVisitors();
}
