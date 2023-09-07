package com.sist.temp;
import java.util.*;
import java.sql.*;

public class MusicDAO {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:xe";

	public MusicDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public void musicInsert(Music m) {
		try {
			conn=DriverManager.getConnection(URL, "hr", "happy");
			String sql="INSERT INTO genie_music "
					 + "VALUES((SELECT NVL(MAX(mno)+1,1) FROM genie_music),"
					 + "?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, m.getCno());
			ps.setString(2, m.getTitle());
			ps.setString(3, m.getSinger());
			ps.setString(4, m.getAlbum());
			ps.setString(5, m.getPoster());
			ps.setInt(6, m.getIdcrement());
			ps.setString(7, m.getState());
			ps.setString(8, m.getKey());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null)
					ps.close();
				if(conn!=null)
					conn.close();
			} catch (Exception e2) {}
		}
	}
}
