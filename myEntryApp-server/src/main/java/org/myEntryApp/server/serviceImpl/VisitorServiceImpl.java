package org.myEntryApp.server.serviceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.myEntryApp.server.constants.CommonConstants;
import org.myEntryApp.server.domain.Visitor;
import org.myEntryApp.server.dto.*;
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
		return prepareVisitorResponse(visitorDTOList, StringUtils.EMPTY);
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
		List<VisitorDTO> visitorDTOList = null;
		VisitorDTO visitorDTO = visitorRequestDTO.getRequestBody().getVisitorDTO();
		if (!visitorExists(visitorDTO)) {
			saveVisitor(visitorDTO, visitor);
			visitorDTOList = prepareVisitorDTOList(Arrays.asList(visitor));
		} else {
			messageBuilder.append("Visitor Already Exists");
		}
		return prepareVisitorResponse(visitorDTOList, messageBuilder.toString());
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
		visitor.setStatus(CommonConstants.ADDED);
		visitorRepository.save(visitor);
		return visitor;

	}

	@Override
	public VisitorResponseDTO updateVisitor(VisitorRequestDTO updateVisitorDTO) {
		Visitor visitor = fetchVisitorById(updateVisitorDTO.getRequestBody().getVisitorDTO().getId());
		StringBuilder messageBuilder = new StringBuilder();
		List<VisitorDTO> visitorDTOList = null;
		if (visitor != null) {
			updateAndSaveVisitor(visitor, updateVisitorDTO.getRequestBody().getVisitorDTO());
			visitorDTOList = prepareVisitorDTOList(Arrays.asList(visitor));
		} else {
			messageBuilder.append("visitor cannot be updated");
		}
		return prepareVisitorResponse(visitorDTOList, messageBuilder.toString());

	}

	private void updateAndSaveVisitor(Visitor visitor, VisitorDTO visitorDTO) {
		BeanUtils.copyProperties(visitorDTO, visitor);
		visitor.setFirstName(visitorDTO.getFirstName().toUpperCase(Locale.ENGLISH));
		visitor.setLastName(visitorDTO.getLastName().toUpperCase(Locale.ENGLISH));
		visitorRepository.save(visitor);
	}

	private Visitor fetchVisitorById(Long visitorId) {
		Optional<Visitor> visitorEntity = visitorRepository.findById(visitorId);
		if (visitorEntity.isPresent()) {
			return visitorEntity.get();
		}
		return null;
	}

	@Override
	public VisitorResponseDTO getVisitor(Long visitorId) {
		List<VisitorDTO> visitorDTOList = new ArrayList<>();
		Optional<Visitor> visitor = visitorRepository.findById(visitorId);
		if (visitor.isPresent()) {
			visitorDTOList = prepareVisitorDTOList(Arrays.asList(visitor.get()));
		}
		return prepareVisitorResponse(visitorDTOList, StringUtils.EMPTY);
	}

	@Override
	public VisitorResponseDTO deleteVisitor(Long visitorId) {
		return deleteVisitorAndSave(fetchVisitorById(visitorId));
	}

	private VisitorResponseDTO deleteVisitorAndSave(Visitor visitor) {
		List<VisitorDTO> visitorDTOList = new ArrayList<>();
		StringBuilder messageBuilder = new StringBuilder();
		if (visitor != null) {
			visitor.setActiveInd(0);
			visitor.setStatus(CommonConstants.DELETED);
			visitorRepository.save(visitor);
			visitorDTOList = prepareVisitorDTOList(Arrays.asList(visitor));
		} else {
			messageBuilder.append("visitor cannot be deleted");
		}
		return prepareVisitorResponse(visitorDTOList, messageBuilder.toString());
	}

	private VisitorResponseDTO prepareVisitorResponse(List<VisitorDTO> liVisitor, String message) {
		VisitorResponseDTO visitorResponseDTO = new VisitorResponseDTO();
		visitorResponseDTO.setResponseBody(new VisitorResponseBodyDTO());
		if (!StringUtils.isEmpty(message)) {
			visitorResponseDTO.getResponseBody().setMessage(message);
		} else {
			visitorResponseDTO.getResponseBody().setVisitors(liVisitor);
		}
		return visitorResponseDTO;
	}

	@Override
	public VisitorResponseDTO searchVisitor(SearchCriteria searchCriteria) {
		List<VisitorDTO> visitorDTOList = new ArrayList<>();
		LocalDateTime fromDate = LocalDateTime.MIN;
		LocalDateTime toDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		if(searchCriteria.getFromDate()=="" )
			fromDate = LocalDateTime.parse( "2000-04-12 18:34:37", formatter);
		else
			fromDate = LocalDateTime.parse(searchCriteria.getFromDate(), formatter);

		if(searchCriteria.getToDate()!="" )
			toDate = LocalDateTime.parse(searchCriteria.getToDate(), formatter);

		Optional<List<Visitor>> visitors = visitorRepository.fetchSearchVisitor(searchCriteria.getName().toUpperCase(),fromDate,toDate,searchCriteria.getLocation().toUpperCase());

		StringBuilder messageBuilder = new StringBuilder();

		if (visitors.isPresent())
			visitorDTOList = prepareVisitorDTOList(visitors.get());
		else
			messageBuilder.append("Visitor not found");

		return prepareVisitorResponse(visitorDTOList, messageBuilder.toString());
	}
}
