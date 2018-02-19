//Ryan Betts
//CIS 2212 GUI Controls Calculator 
//November 26, 2017
//The GUI for the Calculator created using JavaFX
//with functionality using lambda functions for the buttons

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class CalcWindow extends Application {
	@Override //override the start method in the Application class
	public void start(Stage primaryStage) {
		
		//Create an instance of MemoryCalc
		MemoryCalc calc = new MemoryCalc();
		
		//CONSTANT INT VARIABLES FOR WIDTH AND HEIGHT OF WINDOW
		final int WIDTH = 250;
		final int HEIGHT = 250;		
		//GridPane for calculator buttons and text field
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.TOP_CENTER);
		grid.setPadding(new Insets( 10, 10, 10, 10));	//10px padding around grid
		grid.setVgap(10);	//vertical spacing between individual grid cells
		grid.setHgap(10);	//horizontal spacing between individual grid cells
		
		
		//TextField to show the Calculator's input and output
		TextField calcText = new TextField("0.0");
		calcText.setEditable(false);
		calcText.setFont(Font.font("Courier", FontWeight.BOLD, 20));
		grid.add(calcText, 0, 0);
		GridPane.setColumnSpan(calcText, 4);	
		
		
		//Create buttons for calculator and add to grid using loop
		String[] buttonList = {"1", "2", "3", "+", "4", "5", "6", "-",
								"7", "8", "9", "*", "0", ".", "C", "/"};
		
		int btnIndex = 0;
			for(int row = 1; row < 5; row++){
				for(int col = 0; col < 4; col++){
					Button newButton = new Button(buttonList[btnIndex]);
					newButton.setPrefWidth(WIDTH);
					newButton.setFont(Font.font("Courier", 14));
					
					//What happens when one of the buttons are pressed
					newButton.setOnAction(e -> {
						
						//First get the text of the button pressed
						String txt = newButton.getText();
						
						//If the text of the button is an operator, set the current calc operator to
						//that operator
						if(txt.equals("+") || txt.equals("-") || txt.equals("*") || txt.equals("/")){
							calc.setCurrentOperator(txt);
						}
						
						//If the text of the button is "C", then use calc.clear 
						//to clear the currentValue and input
						//and then set the GUI's calcText to "0.0"
						else if(txt.equals("C")){
							calc.clear();
							calcText.setText("0.0");
						}
						
						//If the text of the button is a digit or "."
						else{	
							
							//If the current operator stored in the calc object is not empty/null
							//then allow the user to enter numbers
							if(calc.getCurrentOperator() != null){
								calc.setCurrentInput(calc.getCurrentInput()+txt);
								calcText.setText(calc.getCurrentInput());
							}
						}
						
					});				
					
					//add the new button to the grid
					grid.add(newButton, col, row);
					btnIndex++;
				}
			}
		
		//Create equals button at bottom of grid
		Button equalBtn = new Button("=");	
		equalBtn.setFont(Font.font("Courier", 14));
		equalBtn.setPrefWidth(WIDTH);
		GridPane.setColumnSpan(equalBtn, 4);
		grid.add(equalBtn, 0, 5);
		
		//When the equals button is pressed
		equalBtn.setOnAction(e -> {
			
			String op = calc.getCurrentOperator();
			String inputStr = calc.getCurrentInput();
			double inputDbl = 0;
			
			//Try to convert the user input into a double
			//Catch invalid input/exceptions and display error message
			try {
				inputDbl = Double.parseDouble(inputStr);
			} catch (NumberFormatException | NullPointerException e1){
				Alert alert = new Alert(AlertType.INFORMATION, "Invalid number.");
				alert.setHeaderText(null);
				alert.showAndWait();
				inputStr = null;
			}
			
			//Perform the appropriate calculation depending on operator
			if( op != null && inputStr != null){
				if(op.equals("+")){
					calc.add(inputDbl);
				}
				
				else if(op.equals("-")){
					calc.subtract(inputDbl);
				}
				
				else if(op.equals("*")){
					calc.multiply(inputDbl);
				}			
				
				else if(op.equals("/")){
					calc.divide(inputDbl);
				}
			}
			
			//Finally set calcText to currentValue of calc object after calculation
			calcText.setText(Double.toString(calc.getCurrentValue()));
			calc.setCurrentInput("");  //clear the current input in calc object
			
		});
		
		//Create scene and display window
		Scene scene = new Scene(grid, WIDTH, HEIGHT);
		primaryStage.setTitle("Calculator");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	//Main method is only needed for IDE with limited JavaFX support
	public static void main(String[] args){
		Application.launch(args);
	}
}