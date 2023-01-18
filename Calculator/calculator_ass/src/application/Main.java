package application;	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;

public class Main extends Application {
	private TextField textfield = new TextField();
	private boolean start=true;
	private long num1=0;
	private String op="";
	private long num2=0;

	@Override
	public void start(Stage primaryStage) throws Exception{
		
		textfield.setFont(Font.font(20));
		textfield.setPrefHeight(50);
		textfield.setEditable(false);
		textfield.setAlignment(Pos.CENTER_RIGHT);
	
		StackPane stackpane = new StackPane();
		stackpane.setPadding(new Insets(20));
		stackpane.getChildren().add(textfield);
		
		FlowPane pane = new FlowPane();
		pane.setHgap(10);
		pane.setVgap(10);
		pane.setAlignment(Pos.TOP_CENTER);
		
		BorderPane root = new BorderPane();
		root.setTop(stackpane);
		root.setCenter(pane);
		root.setStyle("-fx-background-color:grey");
		
		

		pane.getChildren().add(ButtonForClear("C"));
		pane.getChildren().add(ButtonForBack("B"));
		pane.getChildren().addAll(
				
				
				
				ButtonForNumber("7"),
				ButtonForNumber("8"),
				ButtonForNumber("9"),
				ButtonForOperators("/"),
				ButtonForNumber("4"),
				ButtonForNumber("5"),
				ButtonForNumber("6"),
				ButtonForOperators("X"),
				ButtonForNumber("1"),
				ButtonForNumber("2"),
				ButtonForNumber("3"),
				ButtonForOperators("-"),
				ButtonForNumber("0"),
				ButtonForOperators("+"),
				ButtonForOperators("%"),
				ButtonForEqual("=")
				);

		Scene scene = new Scene(root,270,370);
		primaryStage.setScene(scene);
		primaryStage.setTitle("My Calculator");
		primaryStage.show();
		primaryStage.setResizable(false);
	}
	
	private Button ButtonForNumber(String ch) {
		Button b1= new Button(ch);
		b1.setFont(Font.font(18));
		b1.setPrefSize(50,40);
		b1.setOnAction(this::processNumber);
		return b1;
	}
    private Button ButtonForOperators(String ch) {
    	Button b1= new Button(ch);
    	b1.setFont(Font.font(18));
		b1.setPrefSize(50,40);
		b1.setOnAction(this::processOperator);
		return b1;
	}
    
    private Button ButtonForEqual(String ch) {
		Button b1= new Button(ch);
		b1.setFont(Font.font(18));
		b1.setPrefSize(50,40);
		b1.setOnAction(this::processEqual);
		return b1;
	}
  private Button ButtonForClear(String ch) {
    	Button b1= new Button(ch);
    	b1.setFont(Font.font(18));
    	b1.setStyle("-fx-background-color:orange");
		b1.setPrefSize(110,50);
		b1.setOnAction(e-> {
			textfield.setText("");
			start=true;
			op="";
		});
		return b1;
   	}
  
  
  private Button ButtonForBack(String ch) {
  	Button b1= new Button(ch);
  	b1.setFont(Font.font(18));
		b1.setPrefSize(110,50);
		b1.setOnAction(this::processOperator);
		b1.setOnAction(e->{
			String text=textfield.getText();
			text=text.substring(0, text.length()-1);
			textfield.setText(text);
		});
		return b1;}
    private void processNumber(ActionEvent e) {
    	if(start) {
    		textfield.setText("");
    		start=false;
    	}
    	String value=((Button)e.getSource()).getText();
    	textfield.setText(textfield.getText()+value);	
    }
    
    
    private void processOperator(ActionEvent e){
    	String value=((Button)e.getSource()).getText();
    	    		if(!op.isEmpty())
    			return;
    		num1=Long.parseLong(textfield.getText());
    		op=value;
    	textfield.setText("");
    	
    	}
    
    	private void processEqual(ActionEvent e){
    		String value=((Button)e.getSource()).getText();
        num2=Long.parseLong(textfield.getText());
		calculate(num1,num2,op);
    	float result=calculate(num1,num2,op);
		textfield.setText(String.valueOf(result));
		start=true;
		op="";
      }
    
    
    private float calculate(long num1,long num2,String operator){
		switch (operator){
		case"+": return num1+num2;
		case"-":return num1-num2;
		case"/":
			if(num2==0)
				return 0;
			return num1/num2;
		case"X":return num1*num2;
		case"%":return (num1*100)/num2;
		default : return 0;
		}
	}	

	public static void main(String[] args) {
		launch(args);
	}
}
