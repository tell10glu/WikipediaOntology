package com.wiki.structure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {
	private static String connectionQuery = null;
	private static String userName = null;
	private static String password = null;

	public static void setUserName(String name) {
		if (userName == null)
			userName = name;
	}

	public static void setPassword(String password) {
		if (Database.password == null)
			Database.password = password;
	}

	public static void setConnectionQuery(String connection) {
		if (connectionQuery == null) {
			connectionQuery = connection;
		}
	}

	private Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Connection");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = (Connection) DriverManager.getConnection(connectionQuery, userName, password);
		return con;
	}

	private Database() {

	}

	public static Database table(String table) {
		Database database = new Database();
		return database;
	}

	public Database type(QueryType type) {
		this.type = type;
		return this;
	}

	public Object run() throws SQLException, UnknownParameterException {
		checkParameters();
		if (type == QueryType.SELECT) {
			PreparedStatement statement = prepareStatement(query, values);
			List<Object[]> rows = new ArrayList<>();
			ResultSet set = statement.executeQuery();
			ResultSetMetaData resultSetMetaData = set.getMetaData();
			int columnCount = resultSetMetaData.getColumnCount();
			while (set.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = set.getObject(i);
				}
				rows.add(row);
			}
			return rows;
		} else if (type == QueryType.INSERT) {
			PreparedStatement statement = prepareStatement(query, Statement.RETURN_GENERATED_KEYS, values);
			int updatedValue = statement.executeUpdate();
			return updatedValue;
		} else {
			return null;
		}

	}

	public void clear() {
		try {
			if (getConnection() != null) {
				getConnection().close();
			}
		} catch (SQLException e) {
				
		}
	}

	public Database query(String query) {
		this.query = query;
		return this;
	}

	private PreparedStatement prepareStatement(String sql, Object... values) throws SQLException {
		PreparedStatement stmt = getConnection().prepareStatement(sql);
		if (values != null)
			for (int i = 0; i < values.length; i++) {
				stmt.setObject(i + 1, values[i]);
			}
		return stmt;
	}

	private PreparedStatement prepareStatement(String sql, int statementArg, Object... values) throws SQLException {
		PreparedStatement stmt = getConnection().prepareStatement(sql, statementArg);
		if (values != null)
			for (int i = 0; i < values.length; i++) {
				stmt.setObject(i + 1, values[i]);
			}
		return stmt;
	}

	private void checkParameters() throws UnknownParameterException {
		if (connectionQuery == null)
			throw new UnknownParameterException("Connection Query", "setConnectionQuery");
		if(userName == null)
			throw new UnknownParameterException("User Name", "setUserName");
		if(password == null)
			throw new UnknownParameterException("Password", "setPassword");
		
	}

	private String query;
	private Object[] values;
	private QueryType type = QueryType.SELECT;

	public enum QueryType {
		INSERT, SELECT, DELETE, UPDATE
	}

	public class UnknownParameterException extends Exception {
		private static final long serialVersionUID = 1L;
		private String param;
		private String methodName;

		public UnknownParameterException(String parameterName, String methodName) {
			this.param = parameterName;
			this.methodName = methodName;
		}

		@Override
		public String getMessage() {
			return param + " must be set before the run function!(use " + methodName + ")";
		}
	}
}
