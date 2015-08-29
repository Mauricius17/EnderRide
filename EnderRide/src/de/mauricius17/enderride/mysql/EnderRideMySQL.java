package de.mauricius17.enderride.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public enum EnderRideMySQL {

	ON,
	OFF;
	
	public static void setEnderRide(String uuid, EnderRideMySQL enderRide) {
		MySQL.getExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				getEnderRideStats(uuid, new Consumer<String>() {

					@Override
					public void accept(String result) {
						try {
							if(result.equals("wrong")) {
								String qry  = "INSERT INTO " + MySQL.getTABLE() + " (playeruuid, " + MySQL.getENDERRIDE() + ") VALUES (?,?)";
								PreparedStatement stmt = MySQL.getConnection().prepareStatement(qry);
								stmt.setString(1, uuid);
								stmt.setString(2, enderRide.toString());
								stmt.executeUpdate();
								stmt.close();
							} else {
								String qry = "UPDATE " + MySQL.getTABLE() + " SET " + MySQL.getENDERRIDE() + " = ? WHERE playeruuid = ?";
								PreparedStatement stmt = MySQL.getConnection().prepareStatement(qry);
								stmt.setString(1, enderRide.toString());
								stmt.setString(2, uuid);
								stmt.executeUpdate();
								stmt.close();
							}
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}
	
	public static void getEnderRideStats(String uuid, Consumer<String> consumer)  {
		MySQL.getExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				try {
					String qry = "SELECT " + MySQL.getENDERRIDE() + " FROM " + MySQL.getTABLE() + " WHERE playeruuid = ?";
					PreparedStatement stmt = MySQL.getConnection().prepareStatement(qry);
					stmt.setString(1, uuid);
					
					ResultSet rs = stmt.executeQuery();
					
					if(rs.next()) {
						consumer.accept(rs.getString(MySQL.getENDERRIDE()));
					} else {
						consumer.accept("wrong");
					}
					
					rs.close();
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
}