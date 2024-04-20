package com.rjl.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="cd_student")
public class Student { //想要用学号连接另一个表的学号，必须有一个表的学号是外键,物理外键不好，所以不用

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer sno;
	
	@Column(length=20)
	private String sname;

	@Column(length=20)
	private int sage;
	@Column(length=20)
	private String sex;
	@Column(length=20)
	private String dept;
	@Column(length=20)
	private String classes;
	
	@OneToOne(targetEntity=HSCheckResult.class,cascade=CascadeType.PERSIST,fetch=FetchType.LAZY,mappedBy="student")
	private HSCheckResult checkResult;
	
	@OneToOne(targetEntity=PrepareData.class,cascade=CascadeType.PERSIST,mappedBy="student")
	private PrepareData prepareData;
	
	@OneToOne(targetEntity=SubmitData.class,cascade=CascadeType.PERSIST,mappedBy="student")
	private SubmitData submitData;

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}


	public Integer getSno() {
		return sno;
	}

	public void setSno(Integer sno) {
		this.sno = sno;
	}

	public int getSage() {
		return sage;
	}

	public void setSage(int sage) {
		this.sage = sage;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public HSCheckResult getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(HSCheckResult checkResult) {
		this.checkResult = checkResult;
	}

	public PrepareData getPrepareData() {
		return prepareData;
	}

	
	@Override
	public String toString() {
		return "Student [sno=" + sno + ", sname=" + sname + ", sage=" + sage + ", sex=" + sex + ", dept=" + dept
				+ ", classes=" + classes + ", checkResult=" + checkResult + ", prepareData=" + prepareData
				+ ", submitData=" + submitData + "]";
	}

	public void setPrepareData(PrepareData prepareData) {
		this.prepareData = prepareData;
	}

	public SubmitData getSubmitData() {
		return submitData;
	}

	public void setSubmitData(SubmitData submitData) {
		this.submitData = submitData;
	}
	
	
	
}
