package es.ulpgc.dis.kata2.control;

import es.ulpgc.dis.kata2.model.Title;

public interface TitleDeserializer {
	 Title deserialize(String content);
}
