package by.htp.ex.dao.pool;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

import javax.annotation.Nullable;

import jakarta.resource.cci.ResultSet;

import java.sql.Statement;
import java.sql.Struct;

public final class ConnectionPool {
	
	private final static ConnectionPool instance = new ConnectionPool();
	private String driver;
	private String url;
	private String user;
	private String password;
	private int countPool;
	
	private BlockingQueue<Connection> queueAvailableConnection;
	private BlockingQueue<Connection> queueTakenConnection;
	
	
	private ConnectionPool() {
		DBParameterManager parameters = DBParameterManager.getInstance();
		this.driver = parameters.getValue(DBParameters.DB_DRIVER);
		this.url = parameters.getValue(DBParameters.DB_URL);
		this.user = parameters.getValue(DBParameters.DB_USER);
		this.password = parameters.getValue(DBParameters.DB_PASSWORD);
		try {
			this.countPool = Integer.parseInt(parameters.getValue(DBParameters.DB_COUNT_POOL));
		}
		catch (NumberFormatException e) {
			this.countPool = 5;
		}
	}
	
	public static ConnectionPool getInstance() throws ConnectionPoolException{
		return instance;
	}	
	
	public void initConnectionPool() throws ConnectionPoolException {
		
		try {
			Class.forName(driver);
			queueAvailableConnection = new ArrayBlockingQueue<>(countPool);
			queueTakenConnection = new ArrayBlockingQueue<>(countPool);
			
			for (int i = 0; i < countPool; i++) {
				Connection connection = DriverManager.getConnection(url, user, password);
				queueAvailableConnection.add(connection);
			}
		}
		catch(ClassNotFoundException e) {
			throw new ConnectionPoolException("Class dtiver can`t found", e);
		}
		catch(SQLException e) {
			throw new ConnectionPoolException("SQLException in ConnectionPool", e);
		}
	}
	
	@Nullable
	public Connection takeConnection() throws ConnectionPoolException {
		Connection connection = null;
		
		try {
			connection = queueAvailableConnection.take();
			PooledConnection pooledConnection = new PooledConnection(connection);
			queueTakenConnection.add(pooledConnection);
		}
		catch(SQLException e) {
			throw new ConnectionPoolException(e);
		}
		catch(InterruptedException e) {
			throw new ConnectionPoolException("Error with taken connection from queueAvailableConnection", e);
		}
		
		return connection;
	}
	
	public void closeConnection(Connection connection, Statement statement) {
		
		try {
			statement.close();
		}
		catch(SQLException e) {
			
		}
		
		try {
			connection.close();
		}
		catch(SQLException e) {
			
		}
	}
	
	public void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {

		try {
			statement.close();
		}
		catch(SQLException e) {
			
		}
		
		try {
			connection.close();
		}
		catch(SQLException e) {
			
		}
		
		try{
			resultSet.close();
		}
		catch(SQLException e) {
			
		}
	}
	
	//TODO Этот метод вызывается, когда пользователь перестает отправлять запросы. Нужно вызвать в методе destroy нашего сервлета
	public void dispose() {
		clearConnectionQueue();
	}
	
	private void clearConnectionQueue() {
		closeConnectionQueue(queueAvailableConnection);
		closeConnectionQueue(queueTakenConnection);
	}
	
	private void closeConnectionQueue(BlockingQueue <Connection> queue) {
		
	}
	
	private class PooledConnection implements Connection{
		
		private Connection connection;
		
		public PooledConnection(Connection connection) throws SQLException {
			this.connection = connection;
			connection.setAutoCommit(true);
		}
		
		@Override
		public void close() throws SQLException {
			connection.close();
		}

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			return connection.unwrap(iface);
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			return connection.isWrapperFor(iface);
		}

		@Override
		public Statement createStatement() throws SQLException {
			return connection.createStatement();
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			return connection.prepareStatement(sql);
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			return connection.prepareCall(sql);
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			return connection.nativeSQL(sql);
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			connection.setAutoCommit(autoCommit);
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			return connection.getAutoCommit();
		}

		@Override
		public void commit() throws SQLException {
			connection.commit();
		}

		@Override
		public void rollback() throws SQLException {
			connection.rollback();
		}

		@Override
		public boolean isClosed() throws SQLException {
			return connection.isClosed();
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			return connection.getMetaData();
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			connection.setReadOnly(readOnly);
			
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			return connection.isReadOnly();
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			connection.setCatalog(catalog);
		}

		@Override
		public String getCatalog() throws SQLException {
			return connection.getCatalog();
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			connection.setTransactionIsolation(level);
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			return connection.getTransactionIsolation();
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			return connection.getWarnings();
		}

		@Override
		public void clearWarnings() throws SQLException {
			connection.clearWarnings();
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			return connection.createStatement(resultSetType, resultSetConcurrency);
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Clob createClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Blob createBlob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public NClob createNClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getSchema() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}
		
		
	}
}
