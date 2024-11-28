package es.ulpgc.dis.kata2.view;

import es.ulpgc.dis.kata2.model.Histogram;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class JFreeChartHistogramDisplay extends JPanel implements HistogramDisplay {
	@Override
	public void display(Histogram histogram) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (String key : histogram.keys())
			dataset.addValue(histogram.valueOf(key), "Frequency", key);
		JFreeChart barChart = ChartFactory.createBarChart(
				histogram.title(),
				"Categories",
				"Frequency",
				dataset);
		ChartPanel comp = new ChartPanel(barChart);
		comp.setSize(600,400);
		add(comp);

	}
}
