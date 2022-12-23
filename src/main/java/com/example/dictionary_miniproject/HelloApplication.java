package com.example.dictionary_miniproject;

import javafx.application.Application;
import javafx.css.converter.EffectConverter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.css.*;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class HelloApplication extends Application {
    Pane createContent()
    {
        //creating pane to add all elements such as textbox,label etc.
        Pane root=new Pane();
        root.setPrefSize(500,500);

        styling_elements styEle=new styling_elements();
        //label to display "enter word".
        Label label1=new Label("Enter word:");
        label1.setTranslateX(25);
        label1.setTranslateY(55);
        label1.setStyle(styEle.labelStyle());

        TextFlow t=new TextFlow();
        
        //textbox to get word or input from the user.
        TextField textField1=new TextField();
        textField1.setTranslateX(120);
        textField1.setTranslateY(50);
        textField1.hoverProperty();
        textField1.setStyle(styEle.TextBoxStyling());
        textField1.setFont(Font.font("Gadugi",30));

        //button to search for the button.
        Button button1=new Button("Search");
        button1.setTranslateX(320);
        button1.setTranslateY(50);
        button1.setStyle(styEle.buttonStyling());
        //button to reset previously entered part and to enter new word

        Button reset=new Button("Reset");
        reset.setTranslateX(395);
        reset.setTranslateY(50);
        reset.setStyle(styEle.buttonStyling());


        //label to display searched word
        Label searchedWord = new Label();
        searchedWord.setTranslateX(15);
        searchedWord.setTranslateY(190);
        searchedWord.setText("Meaning:");

        //label to display the meaning of the given word.
        TextArea result=new TextArea();
        result.setTranslateX(10);
        result.setTranslateY(210);
        result.setEditable(false);
        result.setWrapText(true);
        result.setMaxWidth(500);
        result.setFont(Font.font("Gadugi",13));
        DictionaryClass WordSearch=new DictionaryClass();


        Label suggestions=new Label();
        suggestions.setText("Suggestions for you:");
        suggestions.setTranslateX(10);
        suggestions.setTranslateY(110);


        ListView<String> suggestionList =new ListView<>();
        //suggestionList.setEditable(false);
        suggestionList.setOrientation(Orientation.HORIZONTAL);
        suggestionList.setTranslateX(10);
        suggestionList.setTranslateY(130);
        suggestionList.setMinSize(330,50);
        suggestionList.setMaxSize(300,50);
        textField1.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                suggestionList.getItems().clear();

                ArrayList<String> word=WordSearch.getPrefix(textField1.getText());
                suggestionList.getItems().addAll(word);
            }
        });

        suggestionList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String selectedItem=suggestionList.getSelectionModel().getSelectedItem();
                textField1.setText(selectedItem);
            }
        });
        //Event handler for button on click event.
        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                //input from the user.
                String textReceived=textField1.getText();

               searchedWord.setText("Meaning for "+textReceived);
                //check if enter word is empty or not.
                if(textReceived.equals(""))
                {
                    //if given input is empty display as "please enter a word" in red color.
                    result.setText("Please enter a word");

                }
                else
                {
                    //if text is not empty

                    //convert to lower case to avoid difference in uppercase and lowercase
                    //ex: Apple is difference from apple due to case Sensitivity.
                    textReceived=textReceived.toLowerCase();

                    //wrap the text, so that the text doesn't disappear after exceeding pane length.

                    //object creation to access hashmap and get meaning which located in "DictionaryClass" class

                    //store the meaning to display it to user.
                    String meaning=WordSearch.getMeaning(textReceived);


                    result.setText(meaning);
                }
            }
        });
        reset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                textField1.clear();
                result.clear();
                searchedWord.setText("Meaning:");
                suggestionList.getItems().clear();
            }
        });
        // adding all the elements to the pane.
        root.getChildren().addAll(label1,textField1,button1,result,searchedWord,reset,suggestionList,suggestions);
        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {
      // FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        //name of the application.
        stage.setTitle("Dictionary...");
        //setting up the scene to make it representable to user.
        stage.setScene(scene);
        //showing the UI to user.


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}