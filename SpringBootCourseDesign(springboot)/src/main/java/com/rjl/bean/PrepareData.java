package com.rjl.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="cd_prepare")
public class PrepareData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length=20)
	private String sname;
//	@Column
//	private String sno;
	@Column(length=20)
	private String status;
	
	@Column(length=20)
	private String jkm;
	@Column(length=20)
	private String address;  //不能使用数据库关键字
	
	@OneToOne(targetEntity=Student.class,cascade=CascadeType.PERSIST)
	@JoinColumn(name="sno")
	private Student student;



	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getJkm() {
		return jkm;
	}

	public void setJkm(String jkm) {
		this.jkm = jkm;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	
}
