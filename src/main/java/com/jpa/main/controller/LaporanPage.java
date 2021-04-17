package com.jpa.main.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jpa.main.services.ModelLaporan;
import com.jpa.main.entity.Laporan;
import com.jpa.main.utility.FileUtility;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class LaporanPage {

	@Autowired
	ModelLaporan modelLaporan;
	
	 private final String UPLOAD_DIR = "./src/main/resources/static/uploads/";

		@GetMapping("/laporan/view")
		public String viewIndexLaporan(Model model) {
			model.addAttribute("listLaporan",modelLaporan.getAllLaporan());
			model.addAttribute("active",2);
			return "view_laporan";
		}
		
		@GetMapping("/laporan/add")
		public String viewAddLaporan(Model model) {
			model.addAttribute("laporan",new Laporan());
			return "add_laporan";
		}
		
		
		
		@PostMapping("/laporan/vieew")
		public String addLaporan(@RequestParam(value = "file")MultipartFile file,@ModelAttribute Laporan laporan, Model model) throws IOException { {
			   String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			  
		         
		        String uploadDir = "user-photos/" ;
		
		        FileUtility.saveFile(uploadDir, fileName, file);
		 
	          laporan.setStatusGambar("/"+uploadDir + fileName);
	           this.modelLaporan.addLaporan(laporan);

			model.addAttribute("listLaporan",modelLaporan.getAllLaporan());
			
			
			return "redirect:/dashboard";
		  }
		}
		
		@GetMapping("/laporan/print")
		public String viewReportLaporan(Model model) {
			
			exportPDF();
			
			return "redirect:/laporan/view";
		}
		
		@GetMapping("/laporan/report/pdf")
		public String exportPDF() {
			try {
			File file = ResourceUtils.getFile("classpath:laporan.jrxml");
			JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
			
			List<Laporan> lstLaporan = modelLaporan.getAllLaporan();
	        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lstLaporan);
	        
	        Map<String, Object> parameters = new HashMap<>();
	        parameters.put("createdBy","Juaracoding");
	        
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
	        String path = "D:\\Laporan.pdf";
	        JasperExportManager.exportReportToPdfFile(jasperPrint,path);
	        
	       
			
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
			
			
			return "redirect:/laporan/view";
					
		}
		
		@GetMapping("/laporan/delete/{id}")
		public String deleteLaporan(@PathVariable String id, Model model) {
			
			this.modelLaporan.deleteLaporan(id);
			
			
			return "redirect:/laporan/view";
		}
		
		@GetMapping("/laporan/update/{id}")
		public String viewUpdateLaporan(@PathVariable String id, Model model) {
			
			Laporan laporan = modelLaporan.cariLaporan(id);
			// buat penampung data laporan di halaman htmlnya
			model.addAttribute("laporan",laporan);
			
			return "add_laporan";
		}
}
