package com.github.maybeec.jackson2545;

import java.io.Serializable;

public class JMSEmailTo implements Serializable {

  private static final long serialVersionUID = -1057450145588422140L;

  private String from;

  private String to;

  private String cc;

  private String bcc;

  private String replyTo;

  private String sentDate;

  private String subject;

  private String text;

  private String operatorName;

  public String getOperatorName() {

    return this.operatorName;
  }

  public void setOperatorName(String operatorName) {

    this.operatorName = operatorName;
  }

  public String getNotificationCode() {

    return this.notificationCode;
  }

  public void setNotificationCode(String notificationCode) {

    this.notificationCode = notificationCode;
  }

  private String notificationCode;

  /**
   * @return from
   */
  public String getFrom() {

    return this.from;
  }

  /**
   * @param from new value of {@link #getFrom}.
   */
  public void setFrom(String from) {

    this.from = from;
  }

  /**
   * @return to
   */
  public String getTo() {

    return this.to;
  }

  /**
   * @param to new value of {@link #getTo}.
   */
  public void setTo(String to) {

    this.to = to;
  }

  /**
   * @return cc
   */
  public String getCc() {

    return this.cc;
  }

  /**
   * @param cc new value of {@link #getCc}.
   */
  public void setCc(String cc) {

    this.cc = cc;
  }

  /**
   * @return bcc
   */
  public String getBcc() {

    return this.bcc;
  }

  /**
   * @param bcc new value of {@link #getBcc}.
   */
  public void setBcc(String bcc) {

    this.bcc = bcc;
  }

  /**
   * @return replyTo
   */
  public String getReplyTo() {

    return this.replyTo;
  }

  /**
   * @param replyTo new value of {@link #getReplyTo}.
   */
  public void setReplyTo(String replyTo) {

    this.replyTo = replyTo;
  }

  /**
   * @return sentDate
   */
  public String getSentDate() {

    return this.sentDate;
  }

  /**
   * @param sentDate new value of {@link #getSentDate}.
   */
  public void setSentDate(String sentDate) {

    this.sentDate = sentDate;
  }

  /**
   * @return subject
   */
  public String getSubject() {

    return this.subject;
  }

  /**
   * @param subject new value of {@link #getSubject}.
   */
  public void setSubject(String subject) {

    this.subject = subject;
  }

  /**
   * @return text
   */
  public String getText() {

    return this.text;
  }

  /**
   * @param text new value of {@link #getText}.
   */
  public void setText(String text) {

    this.text = text;
  }

  @Override
  public String toString() {

    return "JMSEmailTo [from=" + this.from + ", to=" + this.to + ", cc=" + this.cc + ", bcc=" + this.bcc + ", replyTo="
        + this.replyTo + ", sentDate=" + this.sentDate + ", subject=" + this.subject + ", text=" + this.text + "]";
  }

}
