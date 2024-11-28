package es.ulpgc.dis.kata2.control;

import es.ulpgc.dis.kata2.model.Title;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;




public class SQLiteTitleReader implements TitleReader {
	private final Connection connection;
	private final PreparedStatement selectStatement;
	private final PreparedStatement randomStatement;

	public SQLiteTitleReader(File dbFile) throws IOException {
		try {
			this.connection = openConnection(dbFile);
			this.randomStatement = connection.prepareStatement("SELECT * FROM titles ORDER BY RANDOM() LIMIT 1");
			this.selectStatement = connection.prepareStatement("SELECT * FROM titles");
			selectStatement.execute();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}

	@Override
	public Iterator<Title> read() throws IOException {
		return new Iterator<>() {
			final ResultSet resultSet = executeQuery();

			@Override
			public boolean hasNext() {
				try {
					return resultSet.next();
				} catch (SQLException e) {
					return false;
				}
			}

			@Override
			public Title next() {
				try {
					return new Title(resultSet.getString(1),
							Title.TitleType.valueOf(resultSet.getString(2)),
							resultSet.getString(3),
							resultSet.getString(4));
				} catch (SQLException e) {
					return null;
				}
			}
		};
	}

	private ResultSet executeQuery() throws IOException {
		try {
			return selectStatement.executeQuery();
		} catch (SQLException e) {
			throw new IOException(e);
		}
	}


	private Connection openConnection(File file) throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:" + file.getAbsolutePath());

	}

	public Title getRandomTitle(){
        try {
            ResultSet resultSet = randomStatement.executeQuery();
			if(resultSet.next()) {
				TsvTitleDeserializer deserializer = new TsvTitleDeserializer();
				StringBuilder result = new StringBuilder();
				int columnCount = resultSet.getMetaData().getColumnCount();
				for (int i = 1; i <= columnCount; i++) {
					result.append(resultSet.getString(i));
					if (i < columnCount) {
						result.append("\t");
					}
				}
				return deserializer.deserialize(result.toString());
			}
			return null;
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
