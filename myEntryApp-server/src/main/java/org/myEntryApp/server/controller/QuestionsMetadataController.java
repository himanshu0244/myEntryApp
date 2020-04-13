///*
// * QuestionsMetadataController.java
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
//package org.myEntryApp.server.controller;
//
//import static com.apple.ist.sap.hc.util.CommonConstants.MIN_ID_VALIDATION_MESSAGE;
//import static com.apple.ist.sap.hc.util.CommonConstants.POST;
//import static com.apple.ist.sap.hc.util.CommonConstants.PUT;
//import static com.apple.ist.sap.hc.util.CommonConstants.READ_AUTHORITY_EXPRESSION;
//import static com.apple.ist.sap.hc.util.CommonConstants.WRITE_AUTHORITY_EXPRESSION;
//
//import javax.validation.constraints.Min;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.apple.ist.sap.hc.dto.QuestionsRequestDTO;
//import com.apple.ist.sap.hc.dto.QuestionsResponseDTO;
//import com.apple.ist.sap.hc.service.QuestionsMetadataService;
//import com.apple.ist.sap.hc.util.ApplicationUtils;
//import com.apple.ist.sap.hc.util.UrlConstants;
//import com.apple.ist.sap.hc.util.ValidationUtil;
//
///**
// * QuestionsMetadataController has the methods for Questions metadata level operations
// *
// * @version 1.0
// * @date 08 May 2019
// * @author kalyani
// */
//
//@RestController
//@RequestMapping(value = UrlConstants.QUESTIONS_METADATA_BASE_URL)
//public class QuestionsMetadataController {
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
//  private QuestionsMetadataService questionsMetadataService;
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
//   * Fetch all the active questions metadata details from questions master entity
//   *
//   * @return questionsMetadata List possible object is {@link QuestionsResponseDTO }
//   */
//  @PreAuthorize(READ_AUTHORITY_EXPRESSION)
//  @GetMapping
//  public QuestionsResponseDTO fetchAllQuestions() {
//    long startTime = System.currentTimeMillis();
//    QuestionsResponseDTO questionsResponseDTO = questionsMetadataService.fetchAllQuestions();
//    questionsResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
//    return questionsResponseDTO;
//  }
//
//  /**
//   * Fetch all the active questions metadata details for given question ID
//   *
//   * @return questionsMetadata List possible object is {@link QuestionsResponseDTO}
//   */
//  @PreAuthorize(READ_AUTHORITY_EXPRESSION)
//  @GetMapping("/{questionID}")
//  public QuestionsResponseDTO fetchQuestionsByID(@PathVariable @Min(value = 1, message = MIN_ID_VALIDATION_MESSAGE) Long questionID) {
//    long startTime = System.currentTimeMillis();
//    QuestionsResponseDTO questionsResponseDTO = questionsMetadataService.fetchQuestionsByID(questionID);
//    questionsResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
//    return questionsResponseDTO;
//  }
//
//  /**
//   * Fetch all the active questions metadata details for given TCC ID
//   *
//   * @return questionsMetadata List possible object is {@link QuestionsResponseDTO}
//   */
//  @PreAuthorize(READ_AUTHORITY_EXPRESSION)
//  @GetMapping("/tcc/{tccTreeID}")
//  public QuestionsResponseDTO fetchAllQuestionsByTCCID(@PathVariable @Min(value = 1, message = MIN_ID_VALIDATION_MESSAGE) Long tccTreeID) {
//    long startTime = System.currentTimeMillis();
//    QuestionsResponseDTO questionsResponseDTO = questionsMetadataService.fetchQuestionsByTCCID(tccTreeID);
//    questionsResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
//    return questionsResponseDTO;
//  }
//
//  /**
//   * Fetch all the active questions metadata details for given TCC ID with status new/edit
//   *
//   * @return questionsMetadata List possible object is {@link QuestionsResponseDTO}
//   */
//  @PreAuthorize(READ_AUTHORITY_EXPRESSION)
//  @GetMapping("previewquestions/{tccTreeID}")
//  public QuestionsResponseDTO fetchAllQuestionsByTCCIDWithStatusNotLive(@PathVariable @Min(value = 1, message = MIN_ID_VALIDATION_MESSAGE) Long tccTreeID) {
//    long startTime = System.currentTimeMillis();
//    QuestionsResponseDTO questionsResponseDTO = questionsMetadataService.fetchQuestionsByTCCIDWithStatusNotLive(tccTreeID);
//    questionsResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
//    return questionsResponseDTO;
//  }
//
//  /**
//   * Fetch all the active questions metadata details for given TCC ID and QestionId
//   *
//   * @return questionsMetadata List possible object is {@link QuestionsResponseDTO}
//   */
//  @PreAuthorize(READ_AUTHORITY_EXPRESSION)
//  @GetMapping("/tcc/{tccTreeID}/question/{questionCode}")
//  public QuestionsResponseDTO fetchByQuestionIDAndTCCID(@PathVariable @Min(value = 1, message = MIN_ID_VALIDATION_MESSAGE) Long tccTreeID,
//    @PathVariable @Min(value = 1, message = MIN_ID_VALIDATION_MESSAGE) String questionCode) {
//    long startTime = System.currentTimeMillis();
//    QuestionsResponseDTO questionsResponseDTO = questionsMetadataService.fetchByQuestionCodeAndTCCID(tccTreeID, questionCode);
//    questionsResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
//    return questionsResponseDTO;
//  }
//
//  /**
//   * Create a new Questions Metadata with provided input details
//   *
//   * @param questionsRequest allowed object is {@link QuestionsRequestDTO }
//   * @return newly created Questions Metadata possible object is {@link QuestionsResponseDTO }
//   */
//  @PreAuthorize(WRITE_AUTHORITY_EXPRESSION)
//  @PostMapping
//  public QuestionsResponseDTO createQuestionsMetadata(@RequestBody QuestionsRequestDTO questionsRequest) {
//    long startTime = System.currentTimeMillis();
//    ValidationUtil.validateRequest(questionsRequest, POST);
//    QuestionsResponseDTO questionsResponseDTO = questionsMetadataService.addQuestion(questionsRequest);
//    questionsResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
//    return questionsResponseDTO;
//  }
//
//  /**
//   * Update a QuestionMetadata with provided input details.
//   *
//   * @param updateQuestionRequest allowed object is {@link QuestionsRequestDTO }
//   * @return updated Questions Metadata possible object is {@link QuestionsResponseDTO }
//   */
//  @PreAuthorize(WRITE_AUTHORITY_EXPRESSION)
//  @PutMapping
//  public QuestionsResponseDTO updateQuestionsMetadata(@RequestBody QuestionsRequestDTO updateQuestionRequest) {
//    long startTime = System.currentTimeMillis();
//    ValidationUtil.validateRequest(updateQuestionRequest, PUT);
//    QuestionsResponseDTO questionsResponseDTO = questionsMetadataService.updateQuestion(updateQuestionRequest);
//    questionsResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
//    return questionsResponseDTO;
//  }
//
//  /**
//   * Preview and Save a QuestionMetadata with provided input details.
//   *
//   * @param saveQuestionRequest allowed object is {@link QuestionsRequestDTO }
//   * @return saved Questions Metadata possible object is {@link QuestionsResponseDTO }
//   */
//  @PreAuthorize(WRITE_AUTHORITY_EXPRESSION)
//  @PostMapping("/saveQuestions")
//  public QuestionsResponseDTO previewAndSaveQuestionsMetadata(@RequestBody QuestionsRequestDTO saveQuestionRequest) {
//    long startTime = System.currentTimeMillis();
//    ValidationUtil.validateQuestionRequestForSave(saveQuestionRequest,POST);
//    QuestionsResponseDTO questionsResponseDTO = questionsMetadataService.previewAndSaveQuestions(saveQuestionRequest);
//    questionsResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
//    return questionsResponseDTO;
//  }
//
//  /**
//   * Soft Delete a QuestionMetadata for provided question id by changing its Active indicator.
//   *
//   * @param updateQuestionRequest allowed object is {@link QuestionsRequestDTO }
//   * @return updated Questions Metadata possible object is {@link QuestionsResponseDTO }
//   */
//  @PreAuthorize(WRITE_AUTHORITY_EXPRESSION)
//  @DeleteMapping("/{questionId}")
//  public QuestionsResponseDTO deleteQuestionsMetadata(@PathVariable @Min(value = 1, message = MIN_ID_VALIDATION_MESSAGE) Long questionId) {
//    long startTime = System.currentTimeMillis();
//    QuestionsResponseDTO questionsResponseDTO = questionsMetadataService.deleteQuestion(questionId);
//    questionsResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
//    return questionsResponseDTO;
//  }
//  
//  /**
//   * When user click on Delete, following method change the question status as MarkForDelete
//   */
//  @PreAuthorize(WRITE_AUTHORITY_EXPRESSION)
//  @PutMapping("/reset/tcc/{tccTreeID}/question/{questionId}")
//  public QuestionsResponseDTO resetMarkForDelQuestionsByTCCID(@PathVariable @Min(value = 1, message = MIN_ID_VALIDATION_MESSAGE) Long tccTreeID,@PathVariable @Min(value = 1, message = MIN_ID_VALIDATION_MESSAGE) Long questionId) {
//    long startTime = System.currentTimeMillis();
//    QuestionsResponseDTO questionsResponseDTO = questionsMetadataService.resetMarkForDelQuestionsByTCCID(tccTreeID,questionId);
//    questionsResponseDTO.setResponseHeader(ApplicationUtils.prepareResponseHeader(startTime));
//    return questionsResponseDTO;
//  }
//}
