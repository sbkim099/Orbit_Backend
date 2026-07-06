package com.study.app.domains.certType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/certType")
public class CertTypeController {
	
	@Autowired
	private CertTypeService certServ;
	
	@GetMapping("getCertInfo")
	public ResponseEntity<List<CertTypeDTO>> getCertInfo(){
		List<CertTypeDTO> list = certServ.getCertInfo();
		return ResponseEntity.ok(list);
	}
}
