package com.vms.resources;

import java.io.IOException;
import java.security.MessageDigest;
import java.sql.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import sun.misc.BASE64Encoder;
public class ConnectionManager {
	private static String JDBC_DRIVER   = null;
    private static String JDBC_URL      = null;
    private static String JDBC_USER     = null;
    private static String JDBC_PASSWORD = null;
    private static Driver driver = null;
    private static Connection conn;
    //public static List<String> LIST_USER_LOGIN = new ArrayList();
	/**
	 * getConnection Method
	 * @author : 
	 * Date : 11/15/2005 5:03:33 PM
	 */
   public static synchronized Connection getConnection() {
    	Connection connection = null;
		try{
			javax.naming.InitialContext ctx = new javax.naming.InitialContext();
			javax.sql.DataSource ds = 
			(javax.sql.DataSource)ctx.lookup("java:/ORACLE_VMS");
			connection = ds.getConnection(); 
			
		}catch(Exception ex){
			System.out.println( "Failed to initialize JDBC driver" ); 
			ex.printStackTrace();
		}
	return connection;
	}
   
   public static synchronized Connection getConnectionCenter() {
   	Connection connection = null;
		try{
			javax.naming.InitialContext ctx = new javax.naming.InitialContext();
			javax.sql.DataSource ds = 
			(javax.sql.DataSource)ctx.lookup("java:/CENTERDB");
			connection = ds.getConnection(); 
			
		}catch(Exception ex){
			System.out.println( "Failed to initialize JDBC driver from CENTERDB" ); 
			ex.printStackTrace();
		}
	return connection;
	}
 
    public static synchronized Connection connect() {
    	if(JDBC_URL==null)
    		parseXML();
		try {
			if (conn == null || conn.isClosed()) {
				//System.out.println("CREATE NEW CONNECTION...");
				Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
				driver = (Driver) jdbcDriverClass.newInstance();
				DriverManager.registerDriver(driver);
				conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
			} else {
				System.out.println("USING EXIST CONNECTION");
			}
		} catch (SQLException e) {
			System.out.println( "SQLException Error!" );
			e.printStackTrace();
		} catch(Exception ex){
			System.out.println( "Exception Error" );
			ex.printStackTrace();
		}
		return conn;
	}
	/**
	 * close Method 
	 * @author :  
	 * Date : 11/15/2005 5:03:33 PM
	 */
	public static void close(Connection conn) { 
		try {
			if (conn != null) conn.close();
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}

	/**
	 * close Method 
	 * @author :      
	 * Date : 11/15/2005 5:03:33 PM
	 */
	public static void close(PreparedStatement stmt){
		try {
			if (stmt != null) stmt.close();
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}

	/**
	 * close Method 
	 * @author :      
	 * Date : 11/15/2005 5:03:33 PM
	 */
	public static void close(ResultSet rs) { 
		try {
			if (rs != null) rs.close();
		}
		catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}
	public static synchronized String encryptSha(String inputStr) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(inputStr.getBytes("UTF-8"));
			byte digest[] = md.digest();
			return (new BASE64Encoder()).encode(digest);
		}
		catch (Exception e) {
			return null;
		}
	}
	private static void parseXML() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			String xmlfile = new java.io.File("..").getCanonicalPath();
			xmlfile += "\\server\\default\\deploy\\ORACLE_VMS-ds.xml";
			System.out.println("xmlfile="+xmlfile);
			builder = factory.newDocumentBuilder();
			String filename = xmlfile;
			org.w3c.dom.Document document = builder.parse(filename);
			NodeList nodes = document.getElementsByTagName("local-tx-datasource");
			nodes = nodes.item(0).getChildNodes();
			for(int ichannel = 0; ichannel < nodes.getLength(); ichannel++){
				Node temp = nodes.item(ichannel);
				if(temp.getNodeName().equals("connection-url"))
					JDBC_URL = temp.getTextContent();
				if(temp.getNodeName().equals("driver-class"))
					JDBC_DRIVER = temp.getTextContent();
				if(temp.getNodeName().equals("user-name"))
					JDBC_USER = temp.getTextContent();
				if(temp.getNodeName().equals("password"))
					JDBC_PASSWORD = temp.getTextContent();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(JDBC_URL==null)
				JDBC_URL = "jdbc:oracle:thin:@10.151.70.81:1521:dw2";
			if(JDBC_DRIVER == null)
				JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
			if(JDBC_USER == null)
				JDBC_USER = "feedback";
			if(JDBC_PASSWORD == null)
				JDBC_PASSWORD = "123";
		}
	}
	 public static void main(String[] arg){
		 
	 }
}