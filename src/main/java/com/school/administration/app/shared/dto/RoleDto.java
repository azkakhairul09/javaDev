package com.school.administration.app.shared.dto;

public class RoleDto{
	private long id;
	private String roleId;
	private String roleName;
	private UserDto users;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public UserDto getUsers() {
		return users;
	}
	public void setUsers(UserDto users) {
		this.users = users;
	}
}
