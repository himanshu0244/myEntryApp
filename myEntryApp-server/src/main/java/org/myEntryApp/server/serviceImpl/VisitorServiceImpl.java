package org.myEntryApp.server.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.myEntryApp.server.domain.Visitor;
import org.myEntryApp.server.dto.VisitorDTO;
import org.myEntryApp.server.dto.VisitorRequestDTO;
import org.myEntryApp.server.dto.VisitorResponseDTO;
import org.myEntryApp.server.repository.VisitorRepository;
import org.myEntryApp.server.service.VisitorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl implements VisitorService {

  @Autowired
  VisitorRepository visitorRepository;

  @Override
  public VisitorResponseDTO getAllVisitors() {
    List<Visitor> visitors = new ArrayList<>();
    visitorRepository.fetchAllVisitors().ifPresent(liVisitor -> {
      visitors.addAll(liVisitor);
    });
    return prepareVisitorResponse(visitors);
  }

  @Override
  public VisitorResponseDTO createVisitor(VisitorRequestDTO visitorDTO) {
    Visitor visitor = new Visitor();
    BeanUtils.copyProperties(visitorDTO, visitor);
    visitorRepository.save(visitor);
    return getVisitor(visitor.getId());
  }

  @Override
  public VisitorResponseDTO updateVisitor(VisitorRequestDTO visitorDTO) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public VisitorResponseDTO getVisitor(Long visitorId) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public VisitorResponseDTO deleteVisitor(Long visitorId) {
    // TODO Auto-generated method stub
    return null;
  }

  private VisitorResponseDTO prepareVisitorResponse(List<Visitor> liVisitor) {
    List<VisitorDTO> visitors = new ArrayList<>();
    liVisitor.forEach(visitor -> {
      VisitorDTO visitorDTO = new VisitorDTO();
      BeanUtils.copyProperties(visitor, visitorDTO);
      visitors.add(visitorDTO);
    });

    return visitors;
  }

}
