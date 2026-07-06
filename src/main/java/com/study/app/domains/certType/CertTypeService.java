package com.study.app.domains.certType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CertTypeService {
	
	@Autowired
	private CertTypeDAO dao ;
	
	public List<CertTypeDTO> getCertInfo(){
		return dao.getCertInfo();
	}
}
