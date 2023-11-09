package com.empresa.conexion;
 
import java.sql.Connection;
import java.sql.SQLException;
 
import javax.sql.DataSource;
 
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 * Clase encargada de la conexion con BD
 */
public class Conexion {
 private static BasicDataSource dataSource = null;
 
 /**
  * metodo para leer los datos de login a la BD
  * @return
  */
 @SuppressWarnings("deprecation")
private static DataSource getDataSource() {
  if (dataSource == null) {
   dataSource = new BasicDataSource();
   dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
   dataSource.setUsername("root");
   dataSource.setPassword("root");
   dataSource.setUrl("jdbc:mysql://localhost:3306/pruebanominas");
   dataSource.setInitialSize(20);
   dataSource.setMaxIdle(15);
   dataSource.setMaxTotal(20);
   dataSource.setMaxWaitMillis(5000);
  }
  return dataSource;
 }
 
 /**
  * metodo para completar la conexion a BD
  * @return
  * @throws SQLException
  */
 public static Connection getConnection() throws SQLException {
  return getDataSource().getConnection();
 }
}