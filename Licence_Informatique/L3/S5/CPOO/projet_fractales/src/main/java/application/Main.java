package application;

import java.text.ParseException;

import org.apache.commons.cli.*;

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import view.ViewManager;

public class Main extends Application {

	AutoCloseable controller;

	public static void main(String[] args) {

		if (args.length != 0) {

			Options options = new Options();

			Option complex = new Option("c", "complex", true, "value of c in z^2+c");
			complex.setRequired(false);
			options.addOption(complex);

			Option gap = new Option("g", "gap", true, "value of gap");
			gap.setRequired(true);
			options.addOption(gap);

			Option x0 = new Option("x0", true, "x value of bottom left corner");
			x0.setRequired(true);
			options.addOption(x0);

			Option x1 = new Option("x1", true, "x value of top right corner");
			x1.setRequired(true);
			options.addOption(x1);

			Option y0 = new Option("y0", true, "y value of bottom left corner");
			y0.setRequired(true);
			options.addOption(y0);

			Option y1 = new Option("y1", true, "y value of top right corner");
			y1.setRequired(false);
			options.addOption(y1);

			CommandLineParser parser = new DefaultParser();
			HelpFormatter formatter = new HelpFormatter();
			CommandLine cmd = null;

			try {
				cmd = parser.parse(options, args);

				Double gapArg = Double.parseDouble(cmd.getOptionValue("gap"));
				Double x0Arg = Double.parseDouble(cmd.getOptionValue("x0"));
				Double x1Arg = Double.parseDouble(cmd.getOptionValue("x1"));
				Double y0Arg = Double.parseDouble(cmd.getOptionValue("y0"));
				Double y1Arg = Double.parseDouble(cmd.getOptionValue("y1"));

				FractalDefinitionDomain d = new FractalDefinitionDomain(x0Arg, x1Arg, y0Arg, y1Arg);

				Fractal f = new FractalBuilder().setDefinitionDomain(d).setGap(gapArg).buildJulia();

				FractalDesigner fd = new FractalDesigner(f);
				fd.writeImage(fd.drawFractal());

			} catch (org.apache.commons.cli.ParseException e) {
				System.out.println(e.getMessage() + "\nsalut");
				formatter.printHelp("utility-name", options);

				System.exit(1);
			}

			System.exit(0);
		}

		TestLoadAverage.testCompute();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			new ViewManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stop() throws Exception {
		if (controller != null) {
			try {
				controller.close();
			} catch (Exception ex) {
				System.err.println("Problem : closing. " + ex.toString());
			}
		}
		super.stop();
	}

}
