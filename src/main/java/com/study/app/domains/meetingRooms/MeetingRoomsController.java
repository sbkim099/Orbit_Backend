package com.study.app.domains.meetingRooms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meetingRooms")
public class MeetingRoomsController {
	
	@Autowired
	private MeetingRoomsService roomServ;
	
	@GetMapping("getAllRooms")
	public ResponseEntity<List<MeetingRoomsDTO>> getAllRooms(){
		List<MeetingRoomsDTO> list = roomServ.getAllRooms();
		return ResponseEntity.ok(list);
	}
}
