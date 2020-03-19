package org.myEntryApp.server.service;

import org.myEntryApp.server.dto.VisitorRequestDTO;
import org.myEntryApp.server.dto.VisitorResponseDTO;

public interface VisitorService {

	public VisitorResponseDTO getAllVisitors();
	
	public VisitorResponseDTO createVisitor(VisitorRequestDTO visitor );
	
	public VisitorResponseDTO updateVisitor(VisitorRequestDTO visitor);
	
	public VisitorResponseDTO getVisitor(Long visitorId);
	
	public VisitorResponseDTO deleteVisitor(Long visitorId);
	
	
}
