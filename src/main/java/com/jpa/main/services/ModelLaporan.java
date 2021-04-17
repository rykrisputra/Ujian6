package com.jpa.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jpa.main.entity.Laporan;
import com.jpa.main.repository.LaporanRepository;


@Service
public class ModelLaporan implements ModelLaporanInterface{

	@Autowired
	LaporanRepository laporanRepo;
	
	@Override
	public List<Laporan> getAllLaporan() {
		// TODO Auto-generated method stub
		return (List<Laporan>) this.laporanRepo.findAll();
	}

	@Override
	public Laporan addLaporan(Laporan laporan) {
		// TODO Auto-generated method stub
		return this.laporanRepo.save(laporan);
	}

	@Override
	public void deleteLaporan(String id) {
		// TODO Auto-generated method stub
		this.laporanRepo.deleteById(Long.parseLong(id));
	}

	@Override
	public Laporan cariLaporan(String id) {
		// TODO Auto-generated method stub
		return this.laporanRepo.findById(Long.parseLong(id)).get();
	}

}
