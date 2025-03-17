package controllers;

/* Import java, javafx */
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import controllers.food.BaseFoodController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import model.Day;
import model.Helper;
import model.Person;

public class SummaryTabController extends BaseFoodController implements Initializable {
	@FXML
	Label labelCalories;
	@FXML
	ProgressBar progressBarCalories;
	@FXML
	BarChart<String, Number> dailyProgress;
	@FXML
	CategoryAxis categoryAxisDate;
	@FXML
	NumberAxis numberAxisCalories;
	
	
	@SuppressWarnings("rawtypes")
	private ArrayList<XYChart.Series> charts = new ArrayList<XYChart.Series>();

	
	private double totalCalories = 3200;

	
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		setupDays();

		
		person = Person.getInstance();

		
		setupPieChart();

		
		setupBarChart();

		
		update();
	}
	
	
	private void updateCurrentGoalTF() {
		//tfCurrentGoal.setText(person.getCurrentGoal().getName());
	}

	
	private void setupBarChart() {
		dailyProgress.setBarGap(-30);
		dailyProgress.setLegendVisible(false);
	}

	
	private void setupDays() {
		
		LocalDate start = LocalDate.parse("2018-01-01"), end = LocalDate.parse("2018-12-31");
		LocalDate next = start.minusDays(1);
		while ((next = next.plusDays(1)).isBefore(end.plusDays(1))) {
			System.out.println(next);
			MainProgramController.days.add(new Day(next));
		}
	}

	
	private void updateProgressBar() {
		
		totalCalories = person.getGoalCalories();

		
		double percentage = calories / totalCalories;

		
		progressBarCalories.setProgress(percentage);

		
		labelCalories.setText("Calories remaining: " + Double.toString(Helper.round(calories, 2)) + " / "
				+ Double.toString(Helper.round(totalCalories, 2)));
	}

	
	public void update() {
		updatePieChart(LocalDate.now());
		updateGUIPieChart();
		updateBarChart();
		updateProgressBar();
		updateCurrentGoalTF();
	}

	
	private void updateBarChart() {
		
		Collections.sort(MainProgramController.days);

		
		charts.clear();
		dailyProgress.getData().clear();

		
		for (int i = 0; i < MainProgramController.days.size(); i++) {

			
			Day day = MainProgramController.days.get(i);

			
			Date date = Date.from(day.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant().plusSeconds(1));

			
			if (between(date, getFirstDayOfWeek(), getLastDayOfWeek())) {
				
				XYChart.Series<String, Number> series1 = new XYChart.Series<>();

				
				series1.setName(day.getDate().toString());

				
				DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM");
				series1.getData().add(new XYChart.Data<>(day.getDate().format(sdf), day.getTotalFoodCalories()));

				
				charts.add(series1);

				
				dailyProgress.getData().add(series1);
			}
		}
	}

	/**
	 * Checks if a date is between two dates
	 * 
	 * @param date
	 *            
	 * @param dateStart
	 *            
	 * @param dateEnd
	 *            
	 * @return true if the date specified is between the start and end dates
	 */
	public static boolean between(Date date, Date dateStart, Date dateEnd) {

		boolean valid = false;

		if (date != null && dateStart != null && dateEnd != null) {
			if (date.after(dateStart) && date.before(dateEnd)) {
				valid = true;
			} else if (date.equals(dateStart) || date.equals(dateEnd)) {
				valid = true;
			}
		}

		return valid;
	}

	/**
	 * Creates a Date object with time 00:00:00 of the 'Monday' of the current week
	 * 
	 * @return 
	 */
	public static Date getFirstDayOfWeek() {

		Calendar c = Calendar.getInstance(new Locale("en", "UK"));
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);

		return c.getTime();
	}

	/**
	 * Creates a Date object of the 'Sunday' of the current week with 23:59:59 time
	 * 
	 * @return 
	 */
	public Date getLastDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_YEAR);
		while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			cal.set(Calendar.DAY_OF_YEAR, ++day);
		}
		
		
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		
		return cal.getTime();
	}
}