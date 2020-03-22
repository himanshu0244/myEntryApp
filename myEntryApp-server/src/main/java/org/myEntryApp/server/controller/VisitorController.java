package org.myEntryApp.server.controller;

import org.myEntryApp.server.constants.UrlConstants;
import org.myEntryApp.server.dto.VisitorRequestDTO;
import org.myEntryApp.server.dto.VisitorResponseDTO;
import org.myEntryApp.server.serviceImpl.VisitorServiceImpl;
import org.myEntryApp.server.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UrlConstants.VISITOR_BASE_URL)
public class VisitorController {

  @Autowired
  private VisitorServiceImpl visitorService;

  @GetMapping
  public VisitorResponseDTO getAllVisitors() {
    long startTime = System.currentTimeMillis();
    VisitorResponseDTO visitorResponseDTO = visitorService.getAllVisitors();
    visitorResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
    return visitorResponseDTO;
  }
  
  @PostMapping
  public VisitorResponseDTO saveVisitor(@RequestBody VisitorRequestDTO visitorRequestDTO ) {
    long startTime = System.currentTimeMillis();
    VisitorResponseDTO visitorResponseDTO = visitorService.createVisitor(visitorRequestDTO);
    visitorResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
    return visitorResponseDTO;
  }
}
