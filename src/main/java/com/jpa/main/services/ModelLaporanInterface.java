package com.jpa.main.services;

import java.util.List;

import com.jpa.main.entity.Laporan;

public interface ModelLaporanInterface {
public List<Laporan> getAllLaporan();
	
	
	public Laporan addLaporan(Laporan laporan);
	
	public void deleteLaporan(String id);
	
	public Laporan cariLaporan(String id);
}
