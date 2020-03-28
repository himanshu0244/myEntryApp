///*
// * QuestionsMetadataServiceImpl.java
// *
// * Copyright (c) Apple, Inc.
// * 410 N. Mary Ave, Sunnyvale, California, 94085, U.S.A.
// * All rights reserved.
// *
// * This software is the confidential and proprietary information of Apple Inc.
// * ("Confidential Information").  You shall not disclose such
// * Confidential Information and shall use it only in accordance
// * with the terms of the license agreement you entered into with Apple.
// */
//package org.myEntryApp.server.serviceImpl;
//
//import static com.apple.ist.sap.hc.util.CommonConstants.*;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Locale;
//import java.util.Optional;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ObjectUtils;
//
//import com.apple.ist.commons.util.string.StringUtil;
//import com.apple.ist.sap.hc.domain.GeneralErrorEnum;
//import com.apple.ist.sap.hc.domain.QuesJurisdictionMapping;
//import com.apple.ist.sap.hc.domain.QuestionsMetaData;
//import com.apple.ist.sap.hc.domain.ReleaseManagementData;
//import com.apple.ist.sap.hc.domain.TCCTree;
//import com.apple.ist.sap.hc.dto.QuestionsDTO;
//import com.apple.ist.sap.hc.dto.QuestionsRequestDTO;
//import com.apple.ist.sap.hc.dto.QuestionsResponseBodyDTO;
//import com.apple.ist.sap.hc.dto.QuestionsResponseDTO;
//import com.apple.ist.sap.hc.dto.ReleaseManagementDTO;
//import com.apple.ist.sap.hc.dto.TCCTreeDTO;
//import com.apple.ist.sap.hc.repository.QuestionsJurisdictionMapRepository;
//import com.apple.ist.sap.hc.repository.QuestionsMetaDataRepository;
//import com.apple.ist.sap.hc.repository.ReleaseManagementDataRepository;
//import com.apple.ist.sap.hc.repository.TCCTreeRepository;
//import com.apple.ist.sap.hc.service.QuestionsMetadataService;
//import com.apple.ist.sap.hc.service.TCCTreeService;
//import com.apple.ist.sap.hc.util.ApplicationUtils;
//import com.apple.ist.sap.hc.util.CommonConstants;
//import com.apple.ist.sap.sbweb.webcommons.context.ApplicationContextManager;
//import com.apple.ist.sap.sbweb.webcommons.exception.HCCIFRuntimeException;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * QuestionsMetadataServiceImpl is providing implementation of questionsMetadata entity level operations
// *
// * @version 1.0
// * @date 08 May 2019
// * @author himanshu
// */
//@Slf4j
//@Service
//public class QuestionsMetadataServiceImpl implements QuestionsMetadataService {
//
//  // ---------------------------------------------------------------------------
//  // Constant(s)
//  // ---------------------------------------------------------------------------
//
//  // ---------------------------------------------------------------------------
//  // Class Field(s)
//  // ---------------------------------------------------------------------------
//
//  // ---------------------------------------------------------------------------
//  // Instance Field(s)
//  // --------------------------------------------------------------------------
//  @Autowired
//  private QuestionsMetaDataRepository questionsMetaDataRepository;
//
//  @Autowired
//  private QuestionsJurisdictionMapRepository questionsJurisdictionMapRepository;
//
//  @Autowired
//  private TCCTreeRepository tccTreeRepository;
//
//  @Autowired
//  private ReleaseManagementDataRepository releaseManagementDataRepository;
//  
//  @Autowired
//  private TCCTreeService tccTreeService;
//
//  // ---------------------------------------------------------------------------
//  // Constructor(s)
//  // ---------------------------------------------------------------------------
//
//  // ---------------------------------------------------------------------------
//  // Class Method(s)
//  // ---------------------------------------------------------------------------
//
//  // ---------------------------------------------------------------------------
//  // Instance Method(s)
//  // ---------------------------------------------------------------------------
//  
//  /**
//   * Method to reset QuestionsMetaData status from MARKED_FOR_DELETE to NEW
//   * 
//   * @param tccTreeId {@link -> Long}
//   * @param questionsID {@link -> Long}
//   * @return QuestionsResponseDTO entities response possible object is {@link QuestionsResponseDTO }
//   */
//  @Override
//  public QuestionsResponseDTO resetMarkForDelQuestionsByTCCID(Long tccTreeID,Long questionsID) {
//    long startTime = System.currentTimeMillis();
//    Optional<List<QuestionsMetaData>> questionsMetadataList = questionsMetaDataRepository.findAllMarkForDelQuestions(MARKED_FOR_DELETE,tccTreeID,questionsID);
//    List<QuestionsDTO> questionsDTOList=new ArrayList<>();
//    if(questionsMetadataList.isPresent()) {
//      List<QuestionsMetaData> quesMetaDataList=questionsMetadataList.get();
//      if(!ObjectUtils.isEmpty(quesMetaDataList)) {
//        quesMetaDataList.forEach(x->x.setStatus(STATUS_NEW));
//        questionsMetaDataRepository.saveAll(quesMetaDataList);
//        questionsDTOList = prepareQuestionsDTOList(quesMetaDataList);
//      }
//    }
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    return prepareQuestionsMetadataResponse(questionsDTOList, StringUtils.EMPTY,startTime);
//  }
//  
//  /**
//  * Fetch all questions details
//  *
//  * @return questionsMetadataList possible object is {@link QuestionsResponseDTO}
//  * 
//  */
//
//  @Override
//  public QuestionsResponseDTO fetchAllQuestions() {
//    long startTime = System.currentTimeMillis();
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    List<QuestionsDTO> questionsDTOList = new ArrayList<>();
//    Optional<List<QuestionsMetaData>> questionsMetadataList = questionsMetaDataRepository.findAllActiveQuestions();
//
//    if (questionsMetadataList.isPresent()) {
//      questionsDTOList = prepareQuestionsDTOList(questionsMetadataList.get());
//    }
//    return prepareQuestionsMetadataResponse(questionsDTOList, StringUtils.EMPTY,startTime);
//  }
//
//  /**
//  * Fetch question for given ID result type
//  * @param questionsID {@link Long} 
//  * @return questionsMetadataList possible object is {@link QuestionsResponseDTO}
//  */
//  @Override
//  public QuestionsResponseDTO fetchQuestionsByID(Long questionsID) {
//    long startTime = System.currentTimeMillis();
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    List<QuestionsDTO> questionsDTOList = new ArrayList<>();
//    Optional<QuestionsMetaData> questionsMetaData = questionsMetaDataRepository.findById(questionsID);
//
//    if (questionsMetaData.isPresent()) {
//      questionsDTOList = prepareQuestionsDTOList(Arrays.asList(questionsMetaData.get()));
//    }
//    return prepareQuestionsMetadataResponse(questionsDTOList, StringUtils.EMPTY,startTime);
//  }
//
//  /**
//  * To add new QuestionsMetaData record with given question request Details
//  * 
//  * @param questionsRequest
//  * @return questionsMetadata allowed object is {@link QuestionsResponseDTO } }
//  */
//  @Override
//  public QuestionsResponseDTO addQuestion(QuestionsRequestDTO questionsRequest) {
//    long startTime = System.currentTimeMillis();
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    List<QuestionsDTO> questionsDTOList = null;
//    StringBuilder messageBuilder = new StringBuilder();
//
//    if (questionCodeAlreadyExists(questionsRequest.getRequestBody().getQuestions().getQuestionCode(),
//      questionsRequest.getRequestBody().getQuestions().getTccTreeDTO().getId(), COMPLETED_STATUS)) {
//      messageBuilder.append(QUESTION_CODE_ALREADY_EXISTS).append(questionsRequest.getRequestBody().getQuestions().getQuestionCode());
//    } else {
//      QuestionsMetaData questionsMetaData = saveQuestion(questionsRequest.getRequestBody().getQuestions());
//
//      questionsDTOList = prepareQuestionsDTOList(Arrays.asList(questionsMetaData));
//    }
//    return prepareQuestionsMetadataResponse(questionsDTOList, messageBuilder.toString(),startTime);
//  }
//
//  /**
//  * To update QuestionsMetaData record with given question request Details
//  * 
//  * @param updateQuestionsRequest
//  * @return questionsMetadata allowed object is {@link QuestionsResponseDTO }
//  */
//  @Override
//  public QuestionsResponseDTO updateQuestion(QuestionsRequestDTO updateQuestionsRequest) {
//    long startTime = System.currentTimeMillis();
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    List<QuestionsDTO> questionsDTOList = null;
//
//    QuestionsMetaData questionsMetaData = fetchQuestionByID(updateQuestionsRequest.getRequestBody().getQuestions().getId());
//    StringBuilder messageBuilder = new StringBuilder();
//
//    updateAndSaveQuestion(updateQuestionsRequest, questionsMetaData, messageBuilder);
//
//    questionsDTOList = prepareQuestionsDTOList(Arrays.asList(questionsMetaData));
//    return prepareQuestionsMetadataResponse(questionsDTOList, messageBuilder.toString(),startTime);
//  }
//
//  private void updateAndSaveQuestion(QuestionsRequestDTO updateQuestionsRequest, QuestionsMetaData questionsMetaData, StringBuilder messageBuilder) {
//    questionsMetaData.setModifiedDate(LocalDateTime.now());
//    if (STATUS_LIVE.equalsIgnoreCase(questionsMetaData.getPrevStatus())
//      && questionsMetaData.getQuestionCode().equals(updateQuestionsRequest.getRequestBody().getQuestions().getQuestionCode())) {
//      questionsMetaData.setPrevQuestionText(questionsMetaData.getQuestionDescription());
//      questionsMetaData.setQuestionDescription(updateQuestionsRequest.getRequestBody().getQuestions().getQuestionDescription());
//      questionsMetaData.setStatus(STATUS_EDIT);
//      questionsMetaDataRepository.save(questionsMetaData);
//    } else if (STATUS_LIVE.equalsIgnoreCase(questionsMetaData.getPrevStatus())
//      && !questionsMetaData.getQuestionCode().equals(updateQuestionsRequest.getRequestBody().getQuestions().getQuestionCode())) {
//        messageBuilder.append(QUESTION_CODE_NOT_EDITABLE);
//      } else {
//        String questionCode = updateQuestionsRequest.getRequestBody().getQuestions().getQuestionCode().toUpperCase(Locale.ENGLISH);
//        Long tccTreeId = updateQuestionsRequest.getRequestBody().getQuestions().getTccTreeDTO().getId();
//        if (!questionCode.equals(questionsMetaData.getQuestionCode()) && questionCodeAlreadyExists(questionCode, tccTreeId, COMPLETED_STATUS)) {
//          messageBuilder.append(QUESTION_CODE_ALREADY_EXISTS).append(updateQuestionsRequest.getRequestBody().getQuestions().getQuestionCode().toUpperCase(Locale.ENGLISH));
//        } else {
//          questionsMetaData.setQuestionDescription(updateQuestionsRequest.getRequestBody().getQuestions().getQuestionDescription());
//          questionsMetaData.setQuestionCode(questionCode);
//          questionsMetaData.setStatus(STATUS_NEW);
//          questionsMetaDataRepository.save(questionsMetaData);
//        }
//      }
//  }
//
//  /**
//  * To soft delete QuestionsMetaData record with given questionID request Details
//  * 
//  * @param questionId {@link Long}
//  * @return questionsMetadata allowed object is {@link QuestionsResponseDTO }
//  */
//  @Override
//  public QuestionsResponseDTO deleteQuestion(Long questionId) {
//    return deleteQuestionMetadata(fetchQuestionByID(questionId));
//  }
//
//  /**
//  * Method to delete Question
//  * @param questionsMetaData {@link  ->  QuestionsMetaData}
//  * @return QuestionsResponseDTO
//  */
//  private QuestionsResponseDTO deleteQuestionMetadata(QuestionsMetaData questionsMetaData) {
//    long startTime = System.currentTimeMillis();
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    List<QuestionsDTO> questionsDTOList = new ArrayList<>();
//    StringBuilder messageBuilder = new StringBuilder();
//
//    if (questionsMetaData != null && !questionsJurisdictionMappingExists(questionsMetaData.getId())) {
//      if (questionsMetaData.getStatus().equals(STATUS_NEW)) {
//        questionsMetaData.setActiveInd(1);
//        if (STATUS_LIVE.equalsIgnoreCase(questionsMetaData.getStatus())) {
//          questionsMetaData.setPrevStatus(STATUS_LIVE);
//        } else {
//          questionsMetaData.setPrevStatus(STATUS_NEW);
//        }
//        questionsMetaData.setStatus(MARKED_FOR_DELETE);
//        questionsMetaDataRepository.save(questionsMetaData);
//        questionsDTOList = prepareQuestionsDTOList(Arrays.asList(questionsMetaData));
//      } else {
//        throw new HCCIFRuntimeException(GeneralErrorEnum.NEW_STATUS_QUESTIONS_ONLY_CAN_DELETE.getErrorCode(),
//          GeneralErrorEnum.NEW_STATUS_QUESTIONS_ONLY_CAN_DELETE.getErrorMessage());
//      }
//    } else if (questionsMetaData != null) {
//      messageBuilder.append(QUESTION_MAPPING_ALREADY_EXISTS).append(questionsMetaData.getQuestionCode());
//    }
//    return prepareQuestionsMetadataResponse(questionsDTOList, messageBuilder.toString(), startTime);
//  }
//
//  private boolean questionsJurisdictionMappingExists(Long questionId) {
//
//    List<QuesJurisdictionMapping> quesJurisdictionMapping = questionsJurisdictionMapRepository.fetchExistingQuesJurisdictionMap(questionId);
//    if (!quesJurisdictionMapping.isEmpty()) {
//      return Boolean.TRUE;
//    }
//    return Boolean.FALSE;
//  }
//
//  /**
//  * Fetch question for given TCC ID and Question Code result type
//  * @param tccTreeID {@link  ->  Long}
//  * @param questionCode {@link  ->  String}}
//  * @return QuestionsResponseDTO possible object is {@link QuestionsResponseDTO}
//  * 
//  */
//
//  @Override
//  public QuestionsResponseDTO fetchByQuestionCodeAndTCCID(Long tccTreeID, String questionCode) {
//    long startTime = System.currentTimeMillis();
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    QuestionsMetaData questionsMetaData = questionsMetaDataRepository.fetchByQuestionCodeAndTCCID(tccTreeID, questionCode);
//    QuestionsDTO questionsDTO = new QuestionsDTO();
//    BeanUtils.copyProperties(questionsMetaData, questionsDTO);
//    return prepareQuestionsMetadataResponse(Arrays.asList(questionsDTO), StringUtils.EMPTY,startTime);
//  }
//
//  /**
//  * It will fetch QuestionsMetaData record with given questionID
//  * 
//  * @param questionID
//  * @return QuestionsMetaData allowed object is {@link QuestionsMetaData }
//  */
//  private QuestionsMetaData fetchQuestionByID(Long questionID) {
//    return questionsMetaDataRepository.findById(questionID)
//      .orElseThrow(() -> new HCCIFRuntimeException(GeneralErrorEnum.QUESTIONS_METADATA_ID_NOT_FOUND.getErrorCode(),
//        GeneralErrorEnum.QUESTIONS_METADATA_ID_NOT_FOUND.getErrorMessage().concat(String.valueOf(questionID))));
//  }
//
//  /**
//  * To save QuestionsMetaData record with given details
//  * 
//  * @param questionsRequest allowed object is {@link QuestionsRequestDTO }
//  * @return QuestionsMetaData
//  */
//  private QuestionsMetaData saveQuestion(QuestionsDTO questionsRequest) {
//    QuestionsMetaData questionsMetaData = new QuestionsMetaData();
//
//    if (null == questionsRequest.getId()) {
//      TCCTree tccTree = fetchTCCTreeByID(questionsRequest.getTccTreeDTO().getId());
//      questionsMetaData.setTccTree(tccTree);
//      prepareQuestionMetaDataObject(questionsMetaData, questionsRequest.getQuestionDescription(), questionsRequest.getQuestionCode().toUpperCase(Locale.ENGLISH));
//    } else {
//      questionsMetaData = fetchQuestionByID(questionsRequest.getId());
//    }
//
//    return questionsMetaDataRepository.save(questionsMetaData);
//  }
//
//  private boolean questionCodeAlreadyExists(String questionCode, Long tccTreeId, String tccTreeStatus) {
//    QuestionsMetaData existingQuestionMetaData = questionsMetaDataRepository.fetchByQuestionCodeAndTCC(questionCode.toUpperCase(Locale.ENGLISH), tccTreeId);
//    log.debug("TCCTree status is",tccTreeStatus);
//    if (!ObjectUtils.isEmpty(existingQuestionMetaData)) {
//      return Boolean.TRUE;
//    }
//    return Boolean.FALSE;
//  }
//
//  /**
//  * Method to create QuestionsMetaData to save for new Question_Text given in Request DTO
//  * @param questionCode 
//  * @param QuestionsMetaData
//  * @param  questionText {@link -> String}
//  * @return QuestionsMetaData
//  */
//  private QuestionsMetaData prepareQuestionMetaDataObject(QuestionsMetaData questionsMetaData, String questionText, String questionCode) {
//    Optional<QuestionsMetaData> existingQuestionMetaData = questionsMetaDataRepository.fetchLatestMaxQuestionId();
//    int newQuestionOrder = 0;
//    if (existingQuestionMetaData.isEmpty()) {
//      newQuestionOrder = INCREMENT;
//    } else {
//      newQuestionOrder = existingQuestionMetaData.get().getId().intValue() + INCREMENT;
//    }
//    questionsMetaData.setActiveInd(1);
//    questionsMetaData.setEndDate(ApplicationUtils.getCommonEndDate());
//    questionsMetaData.setQuestionOrder(newQuestionOrder);
//    questionsMetaData.setQuestionDescription(questionText);
//    questionsMetaData.setQuestionCode(questionCode);
//    questionsMetaData.setStatus(STATUS_NEW);
//    return questionsMetaData;
//  }
//
//  /**
//  * It will fetch TCC Tree Data record with given tccTreeIDa
//  * 
//  * @param tccTreeID
//  * @return TCCTree allowed object is {@link TCCTree }
//  */
//
//  public TCCTree fetchTCCTreeByID(Long tccTreeID) {
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    return tccTreeRepository.findByTCCTreeId(tccTreeID, DELETED_STATUS)
//      .orElseThrow(() -> new HCCIFRuntimeException(GeneralErrorEnum.TCC_TREE_ID_NOT_FOUND.getErrorCode(),
//        GeneralErrorEnum.TCC_TREE_ID_NOT_FOUND.getErrorMessage().concat(String.valueOf(tccTreeID))));
//  }
//
//  /**
//  * Prepare the questionsDTOList with given Entity questionsMetaData
//  * 
//  * @param questionsMetaDataList
//  * @return QuestionsDTOList response object possible object is {@link List<QuestionsDTO> }
//  */
//  private static List<QuestionsDTO> prepareQuestionsDTOList(List<QuestionsMetaData> questionsMetaDataList) {
//    List<QuestionsDTO> questionsDTOList = new ArrayList<>();
//
//    questionsMetaDataList.stream().forEach(metaData -> questionsDTOList.add(prepareQuestionsDTO(metaData)));
//
//    return questionsDTOList;
//  }
//  
//  /**
//   * This method checks is TCC Tree related questions are in completed status or not.
//   *
//   * @param Long {@link TCCTreeId}
//   */
//  @Override
//  public void checkIsTCCTreeRelQuesInCompletedStatus(Long tccTreeId) {
//    
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    tccTreeService.checkIsTCCTreeInCompletedStatus(tccTreeId);
//    List<QuestionsMetaData> quesMetaDataList= questionsMetaDataRepository.getNonCompletedTCC(tccTreeId, STATUS_LIVE);
//    if(quesMetaDataList==null || !quesMetaDataList.isEmpty()) {
//      throw new HCCIFRuntimeException(GeneralErrorEnum.TCC_TREE_QUESTIONS_NOT_CONFIRMED.getErrorCode(),
//        GeneralErrorEnum.TCC_TREE_QUESTIONS_NOT_CONFIRMED.getErrorMessage());
//    }
//  }
//
//  /**
//  * Prepare the QuestionsDTO with given questionsMetaData data
//  *
//  * @param questionMetaData allowed object is {@link QuestionsMetadata }
//  * @return QuestionsDTO response object possible object is {@link QuestionsDTO }
//  */
//  private static QuestionsDTO prepareQuestionsDTO(QuestionsMetaData questionMetaData) {
//    QuestionsDTO questionMetaDataDTO = new QuestionsDTO();
//    TCCTreeDTO tccTreeDTO = new TCCTreeDTO();
//    ReleaseManagementDTO releaseDTO = new ReleaseManagementDTO();
//    BeanUtils.copyProperties(questionMetaData, questionMetaDataDTO);
//
//    if (null != questionMetaData.getTccTree()) {
//      BeanUtils.copyProperties(questionMetaData.getTccTree(), tccTreeDTO);
//      questionMetaDataDTO.setTccTreeDTO(tccTreeDTO);
//    }
//    if(null!=questionMetaData.getRelease()) {
//      BeanUtils.copyProperties(questionMetaData.getRelease(), releaseDTO);
//      questionMetaDataDTO.setReleaseDTO(releaseDTO);
//    }
//
//    return questionMetaDataDTO;
//  }
//
//  /**
//  * Prepare the questionsMetadata response with given QuestionsResponseDTO data
//  * @param message {@link String} 
//  * @param questionsList allowed object is {@link List<QuestionsDTO> }
//   * @param startTime 
//  * @return questionsResponseDTO response object possible object is {@link QuestionsResponseDTO }
//  */
//  private static QuestionsResponseDTO prepareQuestionsMetadataResponse(List<QuestionsDTO> questionsList, String message, long startTime) {
//    QuestionsResponseDTO questionsResponseDTO = new QuestionsResponseDTO();
//    questionsResponseDTO.setResponseBody(new QuestionsResponseBodyDTO());
//    questionsResponseDTO.getResponseBody().setQuestions(questionsList);
//    if (!StringUtil.isEmpty(message)) {
//      questionsResponseDTO.getResponseBody().setMessage(message);
//    }
//    ApplicationContextManager.getApplicationContext().setServiceResponseTime(String.valueOf(System.currentTimeMillis() - startTime));
//    return questionsResponseDTO;
//  }
//
//  /**
//   * fetch questionsMetadata response with given TCC ID 
//   * @param tccTreeID {@link Long} 
//   * @return questionsResponseDTO response object possible object is {@link QuestionsResponseDTO }
//   */
//
//  @Override
//  public QuestionsResponseDTO fetchQuestionsByTCCID(Long tccTreeID) {
//    long startTime = System.currentTimeMillis();
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    List<QuestionsDTO> questionsDTOList = new ArrayList<>();
//    Optional<List<QuestionsMetaData>> questionsMetaData = questionsMetaDataRepository
//      .findByTCCTreeId(tccTreeID);
//
//    if (!ObjectUtils.isEmpty(questionsMetaData) && questionsMetaData.isPresent()) {
//      questionsDTOList = prepareQuestionsDTOList(questionsMetaData.get());
//    }
//    return prepareQuestionsMetadataResponse(questionsDTOList, StringUtils.EMPTY,startTime);
//  }
//
//  /**
//   * method to preview and save the questions with LIVE status in DB 
//   * @param saveQuestionRequest {@link QuestionsRequestDTO} 
//   * @return questionsResponseDTO response object possible object is {@link QuestionsResponseDTO }
//   */
//
//  @Override
//  public QuestionsResponseDTO previewAndSaveQuestions(QuestionsRequestDTO saveQuestionRequest) {
//    long startTime = System.currentTimeMillis();
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    List<QuestionsMetaData> questionsMetaDataList = Collections.emptyList();
//    Long tccTreeID = null;
//    ReleaseManagementData release = new ReleaseManagementData();
//    if (!ObjectUtils.isEmpty(saveQuestionRequest.getRequestBody().getQuestions().getTccTreeDTO())) {
//      tccTreeID = saveQuestionRequest.getRequestBody().getQuestions().getTccTreeDTO().getId();
//    }
//    Long releaseId = saveQuestionRequest.getRequestBody().getQuestions().getReleaseDTO().getId();
//
//    Optional<ReleaseManagementData> releaseEntity = releaseManagementDataRepository.findById(releaseId);
//    if (releaseEntity.isPresent()) {
//      release = releaseEntity.get();
//    }
//    tccTreeService.checkIsTCCTreeInCompletedStatus(tccTreeID);
//    Optional<List<QuestionsMetaData>> questionsMetaDataListEntity = questionsMetaDataRepository
//      .findByTCCTreeIdwithStatusNotLive(tccTreeID);
//    if (questionsMetaDataListEntity.isPresent()) {
//      questionsMetaDataList = questionsMetaDataListEntity.get();
//    }
//    
//    for (QuestionsMetaData question : questionsMetaDataList) {
//      if (MARKED_FOR_DELETE.equalsIgnoreCase(question.getStatus())) {
//        setQuestionStatusDelete(question);
//      } else {
//        setQuestionsStatusLive(question);
//      }
//    }
//    setReleaseDate(questionsMetaDataList, release);
//    QuestionsResponseDTO questionsResponseDTO = fetchQuestionsByTCCID(tccTreeID);
//    ApplicationContextManager.getApplicationContext().setServiceResponseTime(String.valueOf(System.currentTimeMillis() - startTime));
//    return questionsResponseDTO;
//  }
//
//  private void setQuestionsStatusLive(QuestionsMetaData question) {
//    question.setStatus(STATUS_LIVE);
//    question.setPrevStatus(STATUS_LIVE);
//    question.setStartDate(LocalDate.now());
//    question.setEndDate(END_DATE_VALUE);
//    questionsMetaDataRepository.save(question);
//  }
//  
//  private void setQuestionStatusDelete(QuestionsMetaData question) {
//    question.setStatus(DELETED);
//    question.setPrevStatus(MARKED_FOR_DELETE);
//    question.setActiveInd(0);
//    question.setEndDate(LocalDate.now());
//    questionsMetaDataRepository.save(question);
//  }
//
//  private void setReleaseDate(List<QuestionsMetaData> questionsMetaDataList, ReleaseManagementData release) {
//    questionsMetaDataList.stream().forEach(question -> {
//      question.setRelease(release);
//      question.setStartDate(release.getReleaseDate());
//      questionsMetaDataRepository.save(question);
//    });
//  }
//
//  /**
//   * method to fetch questionMetaData for given TCC ID with status NEW / EDIT  
//   * @param tccTreeID {@link Long} 
//   * @return questionsResponseDTO response object possible object is {@link QuestionsResponseDTO }
//   */
//  @Override
//  public QuestionsResponseDTO fetchQuestionsByTCCIDWithStatusNotLive(Long tccTreeID) {
//    long startTime = System.currentTimeMillis();
//    ApplicationContextManager.getApplicationContext().setServiceName(QUES_METADATA_SERVICE);
//    List<QuestionsDTO> questionsDTOList;
//    Optional<List<QuestionsMetaData>> questionsMetaData = questionsMetaDataRepository
//      .findByTCCTreeIdwithStatusNotLive(tccTreeID);
//
//    if (!ObjectUtils.isEmpty(questionsMetaData) && questionsMetaData.isPresent()) {
//      questionsDTOList = prepareQuestionsDTOList(questionsMetaData.get());
//    } else {
//      throw new HCCIFRuntimeException(GeneralErrorEnum.TCC_TREE_QUESTIONS_METADATA_NOT_FOUND.getErrorCode(),
//        GeneralErrorEnum.TCC_TREE_QUESTIONS_METADATA_NOT_FOUND.getErrorMessage());
//    }
//    return prepareQuestionsMetadataResponse(questionsDTOList, StringUtils.EMPTY,startTime);
//
//  }
//}
