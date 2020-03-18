package org.myEntryApp.server.service;

import java.util.List;

import org.myEntryApp.server.dto.VisitorDTO;

public interface VisitorService {

	public List<VisitorDTO> getAllVisitors();
	
	public VisitorDTO createVisitor(VisitorDTO visitor );
	
	public VisitorDTO updateVisitor(Long visitorId);
	
	public VisitorDTO getVisitor(Long visitorId);
	
	public VisitorDTO deleteVisitor(Long visitorId);
	
	
}
