package com.common;

import java.sql.Connection;

public class ConnectFactory {
	
	/*public Connection getConnection(){
		
	}*/
	
	
	public static class JdbcConf {
		private Integer id;
		private String url;
		private String userName;
		private String password;
		private boolean readOnly;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public boolean isReadOnly() {
			return readOnly;
		}
		public void setReadOnly(boolean readOnly) {
			this.readOnly = readOnly;
		}
		
		
	}
}
