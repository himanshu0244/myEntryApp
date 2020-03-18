package org.myEntryApp.server.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.myEntryApp.server.domain.Visitor;
import org.myEntryApp.server.dto.VisitorDTO;
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
	public List<VisitorDTO> getAllVisitors() {
		List<Visitor> visitors = new ArrayList<>();
		visitorRepository.fetchAllVisitors().ifPresent(liVisitor -> {
			visitors.addAll(liVisitor);
		});
		return prepareVisitorResponse(visitors);
	}

	private List<VisitorDTO> prepareVisitorResponse(List<Visitor> liVisitor) {
		List<VisitorDTO> visitors = new ArrayList<>();
		liVisitor.forEach(visitor -> {
			VisitorDTO visitorDTO = new VisitorDTO();
			BeanUtils.copyProperties(visitor, visitorDTO);
			visitors.add(visitorDTO);
		});

		return visitors;
	}

}
