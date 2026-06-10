package com.study.app.domains.meetingRooms;

import java.util.List;

public class RoomRsvnDTO {
	private Long rsvn_seq;
	private Long room_seq;
	private String users_id;
	private String title;
	private String start_dt;
	private String end_dt;
	private String created_at;
	
	private String name;
	private String sysname;
	private String room_name;
	private List<RoomRsvnMemberDTO> attendees;
	private List<RoomRsvnMemberDTO> removedAttendees;
	private List<RoomRsvnMemberDTO> addedAttendees;
	
	public RoomRsvnDTO() {}
	public RoomRsvnDTO(Long rsvn_seq, Long room_seq, String users_id, String title, String start_dt, String end_dt,
			String created_at, String name, String sysname, String room_name, List<RoomRsvnMemberDTO> attendees,
			List<RoomRsvnMemberDTO> removedAttendees, List<RoomRsvnMemberDTO> addedAttendees) {
		super();
		this.rsvn_seq = rsvn_seq;
		this.room_seq = room_seq;
		this.users_id = users_id;
		this.title = title;
		this.start_dt = start_dt;
		this.end_dt = end_dt;
		this.created_at = created_at;
		this.name = name;
		this.sysname = sysname;
		this.room_name = room_name;
		this.attendees = attendees;
		this.removedAttendees = removedAttendees;
		this.addedAttendees = addedAttendees;
	}
	public Long getRsvn_seq() {
		return rsvn_seq;
	}
	public void setRsvn_seq(Long rsvn_seq) {
		this.rsvn_seq = rsvn_seq;
	}
	public Long getRoom_seq() {
		return room_seq;
	}
	public void setRoom_seq(Long room_seq) {
		this.room_seq = room_seq;
	}
	public String getUsers_id() {
		return users_id;
	}
	public void setUsers_id(String users_id) {
		this.users_id = users_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart_dt() {
		return start_dt;
	}
	public void setStart_dt(String start_dt) {
		this.start_dt = start_dt;
	}
	public String getEnd_dt() {
		return end_dt;
	}
	public void setEnd_dt(String end_dt) {
		this.end_dt = end_dt;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSysname() {
		return sysname;
	}
	public void setSysname(String sysname) {
		this.sysname = sysname;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public List<RoomRsvnMemberDTO> getAttendees() {
		return attendees;
	}
	public void setAttendees(List<RoomRsvnMemberDTO> attendees) {
		this.attendees = attendees;
	}
	public List<RoomRsvnMemberDTO> getRemovedAttendees() {
		return removedAttendees;
	}
	public void setRemovedAttendees(List<RoomRsvnMemberDTO> removedAttendees) {
		this.removedAttendees = removedAttendees;
	}
	public List<RoomRsvnMemberDTO> getAddedAttendees() {
		return addedAttendees;
	}
	public void setAddedAttendees(List<RoomRsvnMemberDTO> addedAttendees) {
		this.addedAttendees = addedAttendees;
	}
}
