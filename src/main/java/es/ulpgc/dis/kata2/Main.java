package es.ulpgc.dis.kata2;

import es.ulpgc.dis.kata2.control.*;
import es.ulpgc.dis.kata2.model.Histogram;
import es.ulpgc.dis.kata2.view.MainFrame;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		File tsvFile = new File("title.basics.tsv");
		File dbFile = new File("titles.db");
		new TitleLoader().loadTitles(tsvFile, dbFile);
		MainFrame mainFrame = new MainFrame();
		SQLiteTitleReader titleReader = new SQLiteTitleReader(dbFile);
		Histogram histogram = new TitleTypeHistogram(titleReader);
		mainFrame.displayHistogram(histogram);
		mainFrame.setTitleReader(titleReader);
		mainFrame.setVisible(true);

	}

//TARDA UN POQUITO EN EJECUTAR (ALREDEDOR DE 1 MINUTO) PORQUE TIENE QUE INSERTAR TODOS LOS TITULOS EN LA BASE DE DATOS :)

}

