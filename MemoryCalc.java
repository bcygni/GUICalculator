public class MemoryCalc {

	private double currentValue;  //the current value of the calculations performed
	private String currentOperator = null;	//the operation about to be performed in the calc (+, -, *, or /)
	private String currentInput = "";	//the current numbers inputed into the calc as a String
	
	public double getCurrentValue() {
		return currentValue;
	}
	
	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}
	
	public String getCurrentOperator() {
		return currentOperator;
	}
	
	public void setCurrentOperator(String currentOperator) {
		this.currentOperator = currentOperator;
	}	
	
	public String getCurrentInput() {
		return currentInput;
	}

	public void setCurrentInput(String currentInput) {
		this.currentInput = currentInput;
	}

	public void add(double op2) {
		currentValue += op2;
	}

	public void subtract(double op2) {
		currentValue -= op2;
	}

	public void multiply(double op2) {
		currentValue *= op2;
	}

	public void divide(double op2) {
		if (op2 == 0) {
			currentValue = Double.NaN;
		} else {
			currentValue /= op2;
		}
	}
	
	public void clear() {
		currentValue = 0;
		currentInput = "";
		currentOperator = null;
	}

}
