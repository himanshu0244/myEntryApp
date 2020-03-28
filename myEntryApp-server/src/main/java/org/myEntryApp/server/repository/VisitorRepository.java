package org.myEntryApp.server.repository;

import java.util.List;
import java.util.Optional;

import org.myEntryApp.server.domain.Visitor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VisitorRepository extends CrudRepository<Visitor, Long> {

	@Query("Select visitor from Visitor visitor where visitor.activeInd=1 order by createDate desc ")
	Optional<List<Visitor>> fetchAllVisitors();

	@Query("Select visitor from Visitor visitor where visitor.visitorEmpId = :visitorEmpId and visitor.activeInd=1 ")
	Optional<Visitor> findByEmployeeId(String visitorEmpId);
}
