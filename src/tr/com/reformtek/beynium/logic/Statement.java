package tr.com.reformtek.beynium.logic;

import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.util.adt.align.HorizontalAlign;

import tr.com.reformtek.beynium.util.ResourcesManager;

public class Statement {
	public int MAX = 49;
	public int MIN = 1;
	
	public Font			font;
	public int 			operand1,operand2,correctValue, validOperators;
	public String 		statement, pureStatement, stQuestion, answerString, operatorString = OPERATION.PLUS.toString();
	public Text 		statementText, pureStatementText, stQuestionText,firstOperandText, secondOperandText, operatorText, answerText;
	public OPERATION 	operation;
	
//	public Statement(){
//		this.validOperators = 3;
//		this.font = ResourcesManager.getInstance().fontPencil;
//		generateStatement();
//	}
	
	public Statement(Font font, int validOperators){
		this.validOperators = validOperators;
		this.font = font;
		statementOperation();
	}
	
	public Statement(Font font, int validOperators, int max, int min){
		this(font, validOperators);
		MAX = max;
		MIN = min;
		statementOperation();
	}
//
//	public synchronized void generateStatement(){
//		operand1 = (int)(Math.random() * MAX + MIN);
//		operand2 = (int)(Math.random() * MAX + MIN);
//		answerString = "?";
//		
//		if(operand1 < operand2){
//			int temp = operand1;
//			operand1 = operand2;
//			operand2 = temp;
//		}
//		int operator = (int)(Math.random() * 3 + 1);
//		
//		switch(operator)
//		{
//			case 1: 
//				correctValue 	= operand1 + operand2;
//				this.operation 	= OPERATION.PLUS;
//				operatorString 	= OPERATION.PLUS.value;
//				break;
//				
//			case 2:
//				correctValue	= operand1 - operand2;
//				this.operation 	= OPERATION.MINUS;
//				operatorString 	= OPERATION.MINUS.value;
//				break;
//				
//			case 3: 
//				operand1 		= (int)(Math.random()*10+1);
//				operand2 		= (int)(Math.random()*10+1);
//				correctValue 	= operand1 * operand2;
//				this.operation 	= OPERATION.MULTIPLY;
//				operatorString 	= OPERATION.MULTIPLY.value;
//				break;
//				
//			case 4: 
//				operand1 		= operand1 - (operand1%operand2);
//				correctValue 	= operand1 / operand2;
//				this.operation 	= OPERATION.DIVIDE;
//				operatorString 	= OPERATION.DIVIDE.value;
//				break;
//				
//			default: 
//				correctValue 	= operand1 + operand2;
//				this.operation 	= OPERATION.PLUS;
//				operatorString 	= OPERATION.PLUS.value;
//			break;
//		}
//		
//    	pureStatement = Integer.toString(operand1) + " " + operatorString + " " + Integer.toString(operand2);
//    	statement = pureStatement + " = " + answerString;
//    	statementText = new Text(70,50, ResourcesManager.getInstance().fontPencil, statement, 32,
//				new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
//    	
//    	pureStatementText = new Text(140,120, ResourcesManager.getInstance().fontPencil, pureStatement, 
//    			new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
//    	
//    	pureStatementText.setAlpha(1f);
//	}
	
	public void statementOperation(){
		operand1 = (int)(Math.random() * MAX + MIN);
		operand2 = (int)(Math.random() * MAX + MIN);
		answerString = "?";
		
		if(operand1 < operand2){
			int temp = operand1;
			operand1 = operand2;
			operand2 = temp;
		}
		int operator = (int)(Math.random()*validOperators+1);
		
		switch(operator)
		{
			case 1: 
				operatorString 	= OPERATION.PLUS.value;
				correctValue 	= operand1 + operand2;
				this.operation 	= OPERATION.PLUS;
				break;
				
			case 2: 
				operatorString 	= OPERATION.MINUS.value;
				correctValue 	= operand1 - operand2;
				this.operation	= OPERATION.MINUS;
				break;
				
			case 3: 
				operatorString 	= OPERATION.MULTIPLY.value;
				operand1 		= (int)(Math.random() * 10 + 1);
				operand2 		= (int)(Math.random() * 10 + 1);
				correctValue 	= operand1 * operand2;
				this.operation 	= OPERATION.MULTIPLY;
				break;
				
			case 4: 
				operatorString 	= OPERATION.DIVIDE.value;
				operand1 		= operand1 - (operand1%operand2);
				correctValue 	= operand1 / operand2;
				this.operation 	= OPERATION.DIVIDE;
				break;
				
			default: 
				operatorString 	= OPERATION.PLUS.toString();
				correctValue 	= operand1 + operand2;
				this.operation	= OPERATION.PLUS;
			break;
		}
		statement 			= Integer.toString(operand1) + " "+operatorString+" " + Integer.toString(operand2) + "=" + Integer.toString(correctValue);
		stQuestion 			= Integer.toString(operand1) + " "+operatorString+" " + Integer.toString(operand2) + "=" + OPERATION.QUESTION.value;
		pureStatement 		= Integer.toString(operand1) + "" + operatorString + "" + Integer.toString(operand2);
		
    	statementText 		= new Text(120,120, this.font, statement, 32,
    									new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
    	
    	stQuestionText		= new Text(120,120, this.font, stQuestion, 16,
										new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
    	
    	firstOperandText 	= new Text(120, 120, this.font, Integer.toString(operand1), 3, 
    									new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
    	
    	secondOperandText 	= new Text(120, 120, this.font, Integer.toString(operand2), 3, 
    									new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
    	
    	operatorText 		= new Text(120, 120, this.font, this.operation.value, 3, 
    									new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
    	
    	pureStatementText 	= new Text(140,120, this.font, pureStatement, 
    									new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
    	
    	answerText			= new Text(140,120, this.font, Integer.toString(correctValue), 
										new TextOptions(HorizontalAlign.CENTER), ResourcesManager.getInstance().vbom);
	}
	
	public final int getFirstOperand() {
		return operand1;
	}

	public final void setFirstOperand(int operand1) {
		this.operand1 = operand1;
		firstOperandText.setText(Integer.toString(this.operand1));
	}

	public final int getSecondOperand() {
		return operand2;
	}

	public final void setSecondOperand(int operand2) {
		this.operand2 = operand2;
		secondOperandText.setText(Integer.toString(this.operand2));
	}

	public final String getAnswerString() {
		return answerString;
	}

	public final void setAnswerString(String answerString) {
		this.answerString = answerString;
	}
	
	public void setFont(Font f){
		this.font = f;
	}
	
	public static enum OPERATION{
		PLUS     ("+"),
		MINUS    ("-"),
		MULTIPLY ("x"),
		DIVIDE   ("/"),
		BIGGER   (">"),
		SMALLER  ("<"),
		EQUAL    ("="),
		QUESTION ("?"),
		BLANK    (" ");
		
		private String value;
		private OPERATION(String s){
			value = s;
		}
		
		public String toString(){
			return value;
		}
	}
}
