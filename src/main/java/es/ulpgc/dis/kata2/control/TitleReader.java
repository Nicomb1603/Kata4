package es.ulpgc.dis.kata2.control;

import es.ulpgc.dis.kata2.model.Title;

import java.io.IOException;
import java.util.Iterator;

public interface TitleReader {
	Iterator<Title> read() throws IOException;
}
