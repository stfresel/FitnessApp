package com.example.fitnessapp;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Home implements Serializable {

     private Konto konto = new Konto();
     private Statistik statistik = new Statistik();
     private Tagebuch tagebuch = new Tagebuch();
// wichtig, da ansonst die Zuatenen nicht serialisiert werden
     private ArrayList<Zutat> gespeicherteZutaten = new ArrayList<>();

     /**
      * Die Methode ladet die Toolbar des HomeScreens.
      * Es wird festgelegt, dass beim schliessen des Fensters, die Daten automatisch gespeichert werden:
      */
     public void startHome(){
          Main.gespeicherteZutaten = gespeicherteZutaten;

          //Main.benutzer.datenSpeichern();
          Controller.datenSpeichern();
          Main.stage.setOnCloseRequest(new EventHandler<>(){
               @Override
               public void handle(WindowEvent windowEvent) {
                    System.out.println("closing");
                    Platform.exit();
                    //Main.benutzer.datenSpeichern();
                    Controller.datenSpeichern();

               }
          });

          addTage2Tagebuch();

          BorderPane borderPane = new BorderPane();
          borderPane.setPrefSize(Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
          //borderPane.setStyle("-fx-background-color: transparent;");

          //-------------------UI--------------------------------------------------------------------------------
          //Pane
          Pane pane = new Pane();

          UIstart uiBackground = new UIstart();
          uiBackground.setsize(600, 400);
          uiBackground.display(pane);

          //Bild
          ImageView statImg = loadImg("src/main/resources/com/example/fitnessapp/statsIcon.png");
          ImageView profileImg = loadImg("src/main/resources/com/example/fitnessapp/profilIcon.png");
          ImageView tagebuchImg = loadImg("src/main/resources/com/example/fitnessapp/tagebuchIcon.png");
          ImageView settingsImg = loadImg("src/main/resources/com/example/fitnessapp/settingsIcon.png");
          double iconsize = 50;
          profileImg.setFitHeight(iconsize);
          profileImg.setFitWidth(iconsize);
          tagebuchImg.setFitHeight(iconsize);
          tagebuchImg.setFitWidth(iconsize);
          statImg.setFitHeight(iconsize);
          statImg.setFitWidth(iconsize);
          settingsImg.setFitHeight(iconsize);
          settingsImg.setFitWidth(iconsize);
          borderPane.getStyleClass().add("button-login");

          VBox iconHolder = new VBox();
          HBox uispacer = new HBox();

          iconHolder.getChildren().add(profileImg);
          iconHolder.getChildren().add(tagebuchImg);
          iconHolder.getChildren().add(statImg);

          uispacer.getChildren().add(iconHolder);
          uispacer.getChildren().add(new Rectangle(100, 0));
          borderPane.setLeft(uispacer);


          ToolBar toolBar = new ToolBar();
          Button tagebuchButton = new Button("Tagebuch");
          tagebuchImg.setOnMouseClicked(new EventHandler<>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                    borderPane.setCenter(tagebuch.loadTagebuch());
               }
          });
          Button kontoButton = new Button("Konto");
          profileImg.setOnMouseClicked(new EventHandler<>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                    borderPane.setCenter(konto.loadKonto());
               }
          });



          //Button für Statistiken
          Button statButton = new Button("Statistik");
          statImg.setOnMouseClicked(new EventHandler<>() {
               @Override
               public void handle(MouseEvent mouseEvent) {
                    borderPane.setCenter(statistik.loadStat());
               }
          });
          toolBar.getItems().addAll(tagebuchButton, kontoButton, statButton);
          toolBar.setLayoutY(Main.stage.getHeight());
          //borderPane.setTop(toolBar);
          borderPane.setBottom(new Rectangle(0, 80));
          borderPane.setTop(new Rectangle(0 , 80));
          borderPane.getLeft().setStyle("-fx-row-valignment: center;");
          borderPane.setCenter(tagebuch.loadTagebuch());
          //Main.stage.setScene(new Scene(borderPane));

          uiBackground.setpos(70, 70);
          pane.setStyle(" -fx-background-color: #B6CC95;");
          pane.getChildren().add(borderPane);

          Main.switchScene(new Scene(pane));
     }

     /**
      * Die Methode fügt den heutigen Tag mit Datum zum Tagebuch hinzu, wenn er noch nicht erstellt wurde.
      */
     private void addTage2Tagebuch(){
          if (tagebuch.getAnzahlTage() < 1){
               Tag t1 = new Tag(LocalDate.now());
               //tagebuch.addTag(t1);        wieder einfügen

               //-----------
               tagebuch.addTag(new Tag(LocalDate.of(2023,5,3)));
               tagebuch.addTag(new Tag(LocalDate.of(2023,5,4)));
               tagebuch.addTag(t1);
               //-------
          } else if (!Objects.equals(tagebuch.getLastDay(), LocalDate.now())){
               Tag t1 = new Tag(LocalDate.now());
               tagebuch.addTag(t1);
          }
     }

     public ImageView loadImg(String src){
          //laden Hintergrund
          InputStream stream;
          try {
               stream = new FileInputStream(src);
          } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
          }
          Image image = new Image(stream);
          ImageView imageView = new ImageView();
          imageView.setImage(image);
          return imageView;
     }



     //---------------getter und setter--------------------------
     public Konto getKonto() {
          return konto;
     }

     public void setKonto(Konto konto) {
          this.konto = konto;
     }

     public Statistik getStatistik() {
          return statistik;
     }

     public void setStatistik(Statistik statistik) {
          this.statistik = statistik;
     }

     public Tagebuch getTagebuch() {
          return tagebuch;
     }

     public void setTagebuch(Tagebuch tagebuch) {
          this.tagebuch = tagebuch;
     }
}
