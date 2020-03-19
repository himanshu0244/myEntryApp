package org.myEntryApp.server.domain;

import static org.myEntryApp.server.constants.CommonConstants.CREATED_BY;
import static org.myEntryApp.server.constants.CommonConstants.CREATED_DATE;
import static org.myEntryApp.server.constants.CommonConstants.MODIFIED_BY;
import static org.myEntryApp.server.constants.CommonConstants.MODIFIED_DATE;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {
  @Column(name = CREATED_DATE)
  private LocalDateTime createDate;

  @Column(name = MODIFIED_DATE)
  private LocalDateTime modifiedDate;

  @Column(name = CREATED_BY)
  private String createdBy;

  @Column(name = MODIFIED_BY)
  private String modifiedBy;

  /**
   * Stores created date in DB for new record
   *
   */
  @PrePersist
  public void prePersist() {
    this.createDate = LocalDateTime.now();
    this.createdBy = "SYS_ADMIN";
  }

  /**
   * updates modified date by modified date value for modified record
   *
   */

  @PreUpdate
  public void preUpdate() {
    this.modifiedDate = LocalDateTime.now();
    this.modifiedBy = "SYS_ADMIN";
  }
}
