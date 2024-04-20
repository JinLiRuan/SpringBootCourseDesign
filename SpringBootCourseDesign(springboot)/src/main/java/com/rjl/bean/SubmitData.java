package com.rjl.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="cd_submit")
public class SubmitData {

	@Id
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
	private String jkm;
	
	@Column
	private String status;
	
	@Column
	private String comment;
	
	@Column
	private String data;
	
	@OneToOne(targetEntity=Student.class,fetch=FetchType.EAGER)
	@JoinColumn(name="stu_sno")
	private Student student;
	
	@ManyToOne(targetEntity=Worker.class,fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	@JoinColumn(name="worker_no")
	private Worker worker;
	
	@OneToOne(targetEntity=HSCheckResult.class)
	@JoinColumn(name="check_sno")
	private HSCheckResult checkResult;
	
	public HSCheckResult getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(HSCheckResult checkResult) {
		this.checkResult = checkResult;
	}

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

//	public String getSno() {
//		return sno;
//	}
//
//	public void setSno(String sno) {
//		this.sno = sno;
//	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

//	public String getWno() {
//		return wno;
//	}
//
//	public void setWno(String wno) {
//		this.wno = wno;
//	}

	public String getJkm() {
		return jkm;
	}

	public void setJkm(String jkm) {
		this.jkm = jkm;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}



	
	
	
}
