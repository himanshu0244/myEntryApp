package org.myEntryApp.server.controller;

import org.myEntryApp.server.dto.VisitorResponseDTO;
import org.myEntryApp.server.serviceImpl.VisitorServiceImpl;
import org.myEntryApp.server.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
}
