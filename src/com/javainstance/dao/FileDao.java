package com.javainstance.dao;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import com.javainstance.model.FileDetail;

/**
 * @author javainstance
 *
 */
public class FileDao {

	// To save the file

	public int saveFile(String fileName, InputStream inputStream) {
		int result = 0;

		ConnectionDao dao = new ConnectionDao();
		Connection conn = dao.getConnection();

		try {
			String sql = "INSERT INTO file_upload (image_name,image) values (?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, fileName);
			statement.setBlob(2, inputStream);
			result = statement.executeUpdate();
			statement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * @return list of files
	 */
	public List<FileDetail> getAllFiles() {

		List<FileDetail> list = new ArrayList<>();

		try {
			ConnectionDao dao = new ConnectionDao();
			Connection conn = dao.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from file_upload");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FileDetail fileDetailt = new FileDetail();
				fileDetailt.setFileId(rs.getInt(1));
				fileDetailt.setFileName(rs.getString(2));
				list.add(fileDetailt);

			}
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * @param fileId
	 * @return file
	 */
	public FileDetail downloadFile(int fileId) {

		// queries the database
		Blob blob = null;
		ConnectionDao dao = new ConnectionDao();
		Connection conn = dao.getConnection();
		FileDetail det = null;
		String sql = "SELECT * FROM file_upload WHERE image_id = ?";
		PreparedStatement statement;
		try {
			statement = conn.prepareStatement(sql);
			statement.setInt(1, fileId);

			ResultSet result = statement.executeQuery();
			if (result.next()) {
				// gets file name and file blob data
				int fId = result.getInt("image_id");
				String fileName = result.getString("image_name");
				blob = result.getBlob("image");

				det = new FileDetail(fId, fileName, blob);

			}
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return det;
	}
}
