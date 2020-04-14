package org.myEntryApp.server.service;

import org.myEntryApp.server.dto.VisitorRequestDTO;
import org.myEntryApp.server.dto.VisitorResponseDTO;
import org.myEntryApp.server.dto.SearchCriteria;

public interface VisitorService {

	public VisitorResponseDTO getAllVisitors();
	
	public VisitorResponseDTO createVisitor(VisitorRequestDTO visitorRequestDTO );
	
	public VisitorResponseDTO updateVisitor(VisitorRequestDTO visitorRequestDTO);
	
	public VisitorResponseDTO getVisitor(Long visitorId);
	
	public VisitorResponseDTO deleteVisitor(Long visitorId);

	public VisitorResponseDTO searchVisitor(SearchCriteria searchCriteria);

}
