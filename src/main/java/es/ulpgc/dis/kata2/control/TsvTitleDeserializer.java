package es.ulpgc.dis.kata2.control;

import es.ulpgc.dis.kata2.model.Title;

public class TsvTitleDeserializer implements TitleDeserializer {
	@Override
	public Title deserialize(String content) {
		String[] fields = content.split("\t");
		return new Title(fields[0], Title.TitleType.valueOf(capitalize(fields[1])), fields[2], fields[3]);
	}

	private String capitalize(String value) {
		return value.substring(0,1).toUpperCase() + value.substring(1);
	}
}
