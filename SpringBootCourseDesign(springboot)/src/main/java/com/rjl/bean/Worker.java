package com.rjl.bean;

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
@Table(name="cd_worker")
public class Worker {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=20)
	private String wname;
	
	@Column(length=20)
	private String wno;
	
	@Column(length=20)
	private int wage;
	@Column(length=20)
	private String wsex;
	@Column(length=20)
	private String wtype;
	
	//外键设在多的一方
	@OneToMany(targetEntity=HSCheckResult.class,mappedBy="worker",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Set<HSCheckResult> hsCheckResults;
	
	@OneToMany(targetEntity=SubmitData.class,mappedBy="worker",cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	private Set<SubmitData> submitDatas;

	
	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWno() {
		return wno;
	}

	public void setWno(String wno) {
		this.wno = wno;
	}

	public int getWage() {
		return wage;
	}

	public void setWage(int wage) {
		this.wage = wage;
	}

	public String getWsex() {
		return wsex;
	}

	public void setWsex(String wsex) {
		this.wsex = wsex;
	}

	public String getWtype() {
		return wtype;
	}

	public void setWtype(String wtype) {
		this.wtype = wtype;
	}

	public Set<HSCheckResult> getHsCheckResults() {
		return hsCheckResults;
	}

	public void setHsCheckResults(Set<HSCheckResult> hsCheckResults) {
		this.hsCheckResults = hsCheckResults;
	}

	public Set<SubmitData> getSubmitDatas() {
		return submitDatas;
	}

	public void setSubmitDatas(Set<SubmitData> submitDatas) {
		this.submitDatas = submitDatas;
	}


	
	
}

