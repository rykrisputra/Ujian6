package com.jpa.main.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="laporan")
public class Laporan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idLaporan;
	private String nama;
	private String kejadian;
	private String alamat;
	private String keterangan;
	private String statusGambar;
}
