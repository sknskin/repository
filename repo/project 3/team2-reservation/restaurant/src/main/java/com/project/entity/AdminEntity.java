package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.project.dto.AdminDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_admin")
public class AdminEntity {
	
	public AdminEntity(AdminDto admin) {
		this.id = admin.getId();
		this.passwd = admin.getPasswd();
	}
	
	public AdminDto exportAdminDto() {
		AdminDto admin = new AdminDto();
		admin.setId(id);
		admin.setPasswd(passwd);
		
		return admin;
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int adminCode;
	
	@Column(length = 100, nullable = false)
	private String id;
	
	@Column(length = 100, nullable = false)
	private String passwd;
	
}







