package com.jpa.main.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.jpa.main.entity.UserAdmin;
import com.jpa.main.entity.Laporan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="user_admin")
public class UserAdmin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idUserAdmin;
	private String username;
	private String password;
	private String role;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="id_user", referencedColumnName = "idUserAdmin")
    private List<Laporan> lstLaporan = new ArrayList<Laporan>();
}
