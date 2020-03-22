package org.myEntryApp.server.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.myEntryApp.server.domain.Visitor;
import org.myEntryApp.server.dto.VisitorDTO;
import org.myEntryApp.server.dto.VisitorRequestDTO;
import org.myEntryApp.server.dto.VisitorResponseBodyDTO;
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
		List<VisitorDTO> visitorDTOList = new ArrayList<>();
		Optional<List<Visitor>> visitors = visitorRepository.fetchAllVisitors();
		if (visitors.isPresent()) {
			visitorDTOList = prepareVisitorDTOList(visitors.get());
		}
		return prepareVisitorResponse(visitorDTOList);
	}

	private List<VisitorDTO> prepareVisitorDTOList(List<Visitor> liVisitor) {
		List<VisitorDTO> visitors = new ArrayList<>();
		liVisitor.stream().forEach(visitor -> {
			visitors.add(prepareVisitorDTO(visitor));
		});
		return visitors;

	}

	private VisitorDTO prepareVisitorDTO(Visitor visitor) {

		VisitorDTO visitorDTO = new VisitorDTO();
		BeanUtils.copyProperties(visitor, visitorDTO);
		return visitorDTO;
	}

	@Override
	public VisitorResponseDTO createVisitor(VisitorRequestDTO visitorRequestDTO) {
		Visitor visitor = new Visitor();
		StringBuilder messageBuilder = new StringBuilder();

		VisitorDTO visitorDTO = visitorRequestDTO.getRequestBody().getVisitorDTO();
		if (!visitorExists(visitorDTO)) {
			saveVisitor(visitorDTO, visitor);
		} else {
			messageBuilder.append("Visitor Already Exists with id");
		}
		List<VisitorDTO> visitorDTOList = prepareVisitorDTOList(Arrays.asList(visitor));
		return prepareVisitorResponse(visitorDTOList);
	}

	private boolean visitorExists(VisitorDTO visitorDTO) {
		Optional<Visitor> visitorEntity = visitorRepository.findByEmployeeId(visitorDTO.getVisitorEmpId());
		if (visitorEntity.isPresent()) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	private Visitor saveVisitor(VisitorDTO visitorDTO, Visitor visitor) {
		BeanUtils.copyProperties(visitorDTO, visitor);
		visitor.setActiveInd(1);
		visitor.setFirstName(visitorDTO.getFirstName().toUpperCase(Locale.ENGLISH));
		visitor.setLastName(visitorDTO.getLastName().toUpperCase(Locale.ENGLISH));
		visitorRepository.save(visitor);
		return visitor;

	}

	@Override
	public VisitorResponseDTO updateVisitor(VisitorRequestDTO visitorDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VisitorResponseDTO getVisitor(Long visitorId) {
		List<VisitorDTO> visitorDTOList = new ArrayList<>();
		Optional<Visitor> visitor = visitorRepository.findById(visitorId);
		if (visitor.isPresent()) {
			visitorDTOList = prepareVisitorDTOList(Arrays.asList(visitor.get()));
		}
		return prepareVisitorResponse(visitorDTOList);
	}

	@Override
	public VisitorResponseDTO deleteVisitor(Long visitorId) {
		// TODO Auto-generated method stub
		return null;
	}

	private VisitorResponseDTO prepareVisitorResponse(List<VisitorDTO> liVisitor) {
		VisitorResponseDTO visitorResponseDTO = new VisitorResponseDTO();
		visitorResponseDTO.setResponseBody(new VisitorResponseBodyDTO());
		visitorResponseDTO.getResponseBody().setVisitors(liVisitor);
		return visitorResponseDTO;
	}

}
