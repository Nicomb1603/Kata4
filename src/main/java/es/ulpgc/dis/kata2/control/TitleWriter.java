package es.ulpgc.dis.kata2.control;

import es.ulpgc.dis.kata2.model.Title;

import java.io.IOException;

public interface TitleWriter {
	void write(Title title) throws IOException;
}
