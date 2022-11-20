import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MetricConverterWGUI extends Application {
  private TextField conversion = new TextField();
  private TextField conversionTotal = new TextField();
  private Button btCalculate = new Button("Convert");
  
  @Override // Override the start method in the Application class
  public void start(Stage primaryStage) {
    
    GridPane gridPane = new GridPane();
    gridPane.setHgap(6);
    gridPane.setVgap(6);
    gridPane.add(new Label("Enter your conversion:"), 0, 0);
    gridPane.add(conversion, 1, 0);
    gridPane.add(new Label("Conversion rate:"), 0, 1);
    gridPane.add(new Label("Enter your conversion in this format: 12 km = m"), 0, 3);
    gridPane.add(conversionTotal, 1, 1);
    gridPane.add(btCalculate, 1, 6);
    conversionTotal.setEditable(false);

    
    gridPane.setAlignment(Pos.CENTER);
    conversion.setAlignment(Pos.BOTTOM_RIGHT);
    conversionTotal.setAlignment(Pos.BOTTOM_RIGHT);
    GridPane.setHalignment(btCalculate, HPos.RIGHT);

    
    btCalculate.setOnAction(e -> caculateConversion());

    
    Scene scene = new Scene(gridPane, 600, 300);
    primaryStage.setTitle("Metric Converter"); 
    primaryStage.setScene(scene); 
    primaryStage.show(); 
  }
  
  private void caculateConversion() {
    String input = conversion.getText();
    String[] strLength = input.split(" ");
    float sourceNum = 0;
    String fromMetric = " ";
    String toMetric = " ";
    try {
      if (strLength.length < 3 || strLength.length > 4) {
         System.out.printf("Your input cannot be handled%n Please try again", input);
      } else if (strLength.length == 3) {
        sourceNum = Float.parseFloat(strLength[0]);
            fromMetric = strLength[1];
            toMetric = strLength[2];
      }  else {
            sourceNum = Float.parseFloat(strLength[0]);
            fromMetric = strLength[1];
            toMetric = strLength[3];
          }
          convert(sourceNum, fromMetric, toMetric);
    } catch (Exception e) {
      System.out.println("ERROR. Incorrect input please try again.");
    }
  }
  

  public static void main(String[] args) {
    launch(args);
  }

  public float convert(float sourceNum, String fromMetric, String toMetric) {
		float targetNum = 0;
		switch (fromMetric) {
		case "km":
			switch (toMetric) {
			case "m": 
				targetNum = sourceNum * 1000;
        break;
        case "mi":
        targetNum = sourceNum / (float) 1.6;
        break;
        case "ly":
        targetNum = sourceNum * (float) 0.00000000000010570;
        break;
    case "ft":
        targetNum = sourceNum * (float) 3280.8;
        break;
    case "cm":
        targetNum = sourceNum * (float) 100000;
        break;
    case "mm":
        targetNum = sourceNum * (float) 1000000;
        break;
      }
			break;
		}
    switch(fromMetric){
      case "kg":
      switch(toMetric){
        case "g":
        targetNum = sourceNum * 1000;
        break;
        case "lbs":
        targetNum = sourceNum * (float) 2.2046;
        break;
        case "oz":
        targetNum = sourceNum * (float) 35.274;
        break;
        case "mg":
        targetNum = sourceNum * 1000000;
        break;
      }
      break;
    }
    switch(fromMetric){
      case "C":
    
    switch(toMetric){
      case "F":
      targetNum = (float) ((sourceNum * 1.8) + 32);
      break;
      case "K":
      targetNum = sourceNum + (float) 273.15;
      break;
    }
    break;
  }
  switch(fromMetric){
    case "L":
    switch(toMetric){
      case "gal":
      targetNum = sourceNum * (float) 0.26417;
      break;
      case "floz":
      targetNum = sourceNum * (float) 33.814;
      break;
    }
    break;
  }
  switch(fromMetric){
    case "kph":
    switch(toMetric){
      case "mph":
      targetNum = sourceNum / (float) 1.6094;
      break;
    }
    break;
  }
		conversionTotal.setText(sourceNum + fromMetric + " = " + targetNum + toMetric);
		return targetNum;
	}
}