package de.mauricius17.enderride.utils;

public enum Permissions {

	ENDERRIDEHELP("enderride.help"),
	ENDERRIDESET("enderride.set"),
	ENDERRIDESWITCH("enderride.switch"),
	ENDERRIDEUSE("enderride.use");
	
	String permission;
	
	private Permissions(String permission) {
		this.permission = permission;
	}
	
	public String getPermission() {
		return permission;
	}
}