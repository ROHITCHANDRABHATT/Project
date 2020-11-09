package com.cg.fms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name = "Feedback")
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "Feedback_ID")
	private int feedbackId;
	@OneToOne
	private Employee participant;
	@OneToOne
	private Program program;
	@Column(length = 2, name = "fc1")
	private int feedbackCriteria1;
	@Column(length = 2, name = "fc2")
	private int feedbackCriteria2;
	@Column(length = 2, name = "fc3")
	private int feedbackCriteria3;
	@Column(length = 2, name = "fc4")
	private int feedbackCriteria4;
	@Column(length = 2, name = "fc5")
	private int feedbackCriteria5;
	@Column(length = 50, name = "comments")
	private String comments;
	@Column(length = 90, name="suggestions")
	private String suggestions;
	
	
	
	public Feedback() {
		super();
	}
	
	public Feedback(int feedbackId, Employee participant, Program program, int feedbackCriteria1, int feedbackCriteria2,
			int feedbackCriteria3, int feedbackCriteria4, int feedbackCriteria5, String comments, String suggestions) {
		super();
		this.feedbackId = feedbackId;
		this.participant = participant;
		this.program = program;
		this.feedbackCriteria1 = feedbackCriteria1;
		this.feedbackCriteria2 = feedbackCriteria2;
		this.feedbackCriteria3 = feedbackCriteria3;
		this.feedbackCriteria4 = feedbackCriteria4;
		this.feedbackCriteria5 = feedbackCriteria5;
		this.comments = comments;
		this.suggestions = suggestions;
	}

	public int getFeedbackId() {
		return feedbackId;
	}
	public Employee getParticipant() {
		return participant;
	}
	public void setParticipant(Employee participant) {
		this.participant = participant;
	}
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
	public int getFeedbackCriteria1() {
		return feedbackCriteria1;
	}
	public void setFeedbackCriteria1(int feedbackCriteria1) {
		this.feedbackCriteria1 = feedbackCriteria1;
	}
	public int getFeedbackCriteria2() {
		return feedbackCriteria2;
	}
	public void setFeedbackCriteria2(int feedbackCriteria2) {
		this.feedbackCriteria2 = feedbackCriteria2;
	}
	public int getFeedbackCriteria3() {
		return feedbackCriteria3;
	}
	public void setFeedbackCriteria3(int feedbackCriteria3) {
		this.feedbackCriteria3 = feedbackCriteria3;
	}
	public int getFeedbackCriteria4() {
		return feedbackCriteria4;
	}
	public void setFeedbackCriteria4(int feedbackCriteria4) {
		this.feedbackCriteria4 = feedbackCriteria4;
	}
	public int getFeedbackCriteria5() {
		return feedbackCriteria5;
	}
	public void setFeedbackCriteria5(int feedbackCriteria5) {
		this.feedbackCriteria5 = feedbackCriteria5;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getSuggestions() {
		return suggestions;
	}
	public void setSuggestions(String suggestions) {
		this.suggestions = suggestions;
	}
			
}
