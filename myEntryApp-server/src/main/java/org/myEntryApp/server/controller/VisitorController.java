package org.myEntryApp.server.controller;

import java.util.List;

import org.myEntryApp.server.dto.VisitorDTO;
import org.myEntryApp.server.serviceImpl.VisitorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitorController {

	@Autowired
	private VisitorServiceImpl visitorService;

	@GetMapping
	public List<VisitorDTO> getAllVisitors() {

		List<VisitorDTO> visitorDtoList = visitorService.getAllVisitors();
		
		return visitorDtoList;
	}
}
