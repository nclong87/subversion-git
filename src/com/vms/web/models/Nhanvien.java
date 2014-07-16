package com.vms.web.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.QueryHint;

import com.vms.db.impl.NhanvienServiceImpl;


/**
 * The persistent class for the NHANVIEN database table.
 * 
 */
@NamedNativeQueries( {	
	@NamedNativeQuery(
		name = NhanvienServiceImpl.FN_FIND_NHANVIEN_BY_BOPHAN, 
		query = NhanvienServiceImpl.SQL_FN_FIND_NHANVIEN_BY_BOPHAN ,
		resultClass = Nhanvien.class,
		hints = @QueryHint(name = "org.hibernate.callable", value = "true") 
	),
	@NamedNativeQuery(
		name = NhanvienServiceImpl.FN_FIND_NHANVIEN_BY_EVENT, 
		query = NhanvienServiceImpl.SQL_FN_FIND_NHANVIEN_BY_EVENT ,
		resultClass = Nhanvien.class,
		hints = @QueryHint(name = "org.hibernate.callable", value = "true") 
	)
})
@Entity
public class Nhanvien implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer active;

	private String dienthoaidd;

	private String email;

	private Integer gioitinh;

	private String honhanvien;

	private String manhanvien;

	private Integer msbophan;
	
	@Id
	private Integer msnhanvien;

	private String ngaysinh;

	private Integer stt;

	private String tennhanvien;

    public Nhanvien() {
    }

	public Integer getActive() {
	
		return active;
	}

	public void setActive(Integer active) {
	
		this.active = active;
	}

	public String getDienthoaidd() {
	
		return dienthoaidd;
	}

	public void setDienthoaidd(String dienthoaidd) {
	
		this.dienthoaidd = dienthoaidd;
	}

	public String getEmail() {
	
		return email;
	}

	public void setEmail(String email) {
	
		this.email = email;
	}

	public Integer getGioitinh() {
	
		return gioitinh;
	}

	public void setGioitinh(Integer gioitinh) {
	
		this.gioitinh = gioitinh;
	}

	public String getHonhanvien() {
	
		return honhanvien;
	}

	public void setHonhanvien(String honhanvien) {
	
		this.honhanvien = honhanvien;
	}

	public String getManhanvien() {
	
		return manhanvien;
	}

	public void setManhanvien(String manhanvien) {
	
		this.manhanvien = manhanvien;
	}

	public Integer getMsbophan() {
	
		return msbophan;
	}

	public void setMsbophan(Integer msbophan) {
	
		this.msbophan = msbophan;
	}

	public Integer getMsnhanvien() {
	
		return msnhanvien;
	}

	public void setMsnhanvien(Integer msnhanvien) {
	
		this.msnhanvien = msnhanvien;
	}

	public String getNgaysinh() {
	
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
	
		this.ngaysinh = ngaysinh;
	}

	public Integer getStt() {
	
		return stt;
	}

	public void setStt(Integer stt) {
	
		this.stt = stt;
	}

	public String getTennhanvien() {
	
		return tennhanvien;
	}

	public void setTennhanvien(String tennhanvien) {
	
		this.tennhanvien = tennhanvien;
	}

	

}