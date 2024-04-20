package com.rjl.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="cd_check")
public class HSCheckResult {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String sname;
//	@Column
//	private String sno;
	
	@Column
	private String wname;
//	@Column
//	private String wno;
	
	@Column
	private String result;
	
	@OneToOne(targetEntity=Student.class)
	@JoinColumn(name="sno")
	private Student student;
	
	@OneToOne(targetEntity=SubmitData.class,cascade=CascadeType.PERSIST,mappedBy="checkResult")
	private SubmitData submitData;
	
	@ManyToOne(targetEntity=Worker.class,cascade=CascadeType.PERSIST,fetch=FetchType.LAZY)
	@JoinColumn(name="worker_no")
	private Worker worker;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public SubmitData getSubmitData() {
		return submitData;
	}

	public void setSubmitData(SubmitData submitData) {
		this.submitData = submitData;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	
	
}
