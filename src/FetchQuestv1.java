import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.shape.*;

public class FetchQuestv1 extends Application {
	//there are so many goddamn things to be referenced im so sorry to anyone who reads this code after
	
	private Screen startScreen; //the start screen
	private BorderPane basePane; //the basepane of the overall game -- allows changing screens
	private final float WINDOW_WIDTH = 1000; //instanciates width and height so i dont need to remember it later
	private final float WINDOW_HEIGHT = 750;
	private Button btn; //idk why this needs to be here but im keeping it
	private static Button button1 = new Button(); //buttons for the bottom section of the pane
	private static Button button2 = new Button();
	private static Button button3 = new Button();
	private static Button button4 = new Button();
	private static Button petShopBtn = new Button("PET SHOP");
	private static Button apartmentBtn = new Button("APARTMENT");
	private static Button fishStoreBtn = new Button("FISH STORE");
	private static Button docksBtn = new Button("DOCKS");
	private static Button fleaMarketBtn = new Button("FLEA MARKET");
	private static BorderPane gameScreen = new BorderPane(); //make game screen and button pane screen
	private static HBox buttonPane = new HBox(); //buttonpane for bottom of gameScreen
	private VBox invAndMapPane = new VBox(); //vertical box for map and inventory
	private static Button mapButton = new Button("MAP"); //makes map and inventory
	private static Inventory inv = new Inventory(); //self explanatory
	private static Text invText = new Text();
	private static VBox textBox = new VBox(); //vertical box for ingame text
	private static Text location; //changes the location text up at the top of the screen
	private static Text textContent = new Text(); //typing out everything basically
	private static boolean isTutorial, mapIsOpen, mapClickable, textDone; //checks whether or not it's the tutorial & whether map is open
	private static MediaPlayer bgMusic; //overall background music
	private static ImageView imgView; //self explanatory
	private static Image map, img;
	private static GridPane mapPane = new GridPane(); //for the map
	private static boolean hasTalkedAboutFilters = false, hasTalkedFishFood = false, hasTalkedWorms = false, hasTalkedBox = false,
			takenMagazine = false, takenLettuce = false, takenWorms = false, taken20 = false,
			givenLettuce = false, givenWorms = false, givenMagazine = false, givenMacarons = false,
			knowsHalinasName = false; 
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception { //START SCREEN
		MediaPlayer bgMusic; //new mediaplayer	
		String dogsongFile = FetchQuestv1.class.getResource("/dogsong.mp3").toString();
		Media dogsong = new Media(dogsongFile);
		bgMusic = new MediaPlayer(dogsong);
		bgMusic.setCycleCount(MediaPlayer.INDEFINITE);
		bgMusic.play();
		
		primaryStage.setTitle("FETCH QUEST");
		Button btn = new Button("START");
		
		basePane = new BorderPane(); //makes the base pane
	    BorderPane startPane = new BorderPane(); //makes the start screen pane
	    basePane.setCenter(startPane); //puts start pane in the center of base pane
	    
	    btn.setScaleX(3); //resize button
	    btn.setScaleY(3);
	    
	    BorderPane.setAlignment(btn, Pos.CENTER); //add and align button in start pane
	    startPane.setBottom(btn);
	    startPane.setId("pane"); //set css stuff
	    BorderPane.setMargin(btn, new Insets(30,30,60,30)); //set margins for button
	    Scene scene = new Scene(basePane, WINDOW_WIDTH, WINDOW_HEIGHT, Color.BLACK);
	    primaryStage.setMaxHeight(WINDOW_HEIGHT);
	    primaryStage.setMaxWidth(WINDOW_WIDTH);
	    scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm()); //get css information
	    
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	bgMusic.stop();
            	basePane.getChildren().remove(startPane);
            	startGame();
            }
        });
	    
	    primaryStage.setScene(scene); //set scene
	    primaryStage.show();
	}
	
	
	
	public void startGame() { //ACTUALLY START GAME
		//ALL OF THIS STUFF UNDERNEATH IS JUST FORMATTING STUFF
		String apartmentFile = FetchQuestv1.class.getResource("/apartmentMusic.mp3").toString();
		Media apartment = new Media(apartmentFile);
		bgMusic = new MediaPlayer(apartment);
		bgMusic.setCycleCount(MediaPlayer.INDEFINITE);
		bgMusic.play();
		bgMusic.setVolume(0.5);
		
		isTutorial = true;
		mapClickable = false;
		
		mapButton.setScaleX(2);
		mapButton.setScaleY(2);
		invText.setText("INVENTORY");
		invText.setFont(Font.font("Courier New", 20));
		
		location = new Text(); //make location text
		BorderPane.setAlignment(location, Pos.CENTER);
		location.setText("APARTMENT"); //setting the text and stuff
		location.setFont(Font.font ("Courier New", FontWeight.BOLD, 25));
		location.setFill(Color.TEAL);
		BorderPane.setMargin(location, new Insets(10,10,10,10));
		
		//add buttons to hbox
		button1 = new Button(" CONTINUE ");
		button1.setScaleX(1.5);
		button1.setScaleY(1.5);
		button2.setScaleX(1.5);
		button2.setScaleY(1.5);
		button3.setScaleX(1.5);
		button3.setScaleY(1.5);
		button4.setScaleX(1.5);
		button4.setScaleY(1.5);
		buttonPane.getChildren().add(button1);
		
		buttonPane.setStyle("-fx-border-color: black"); //set border colors
		invAndMapPane.setStyle("-fx-border-color: black");
		invAndMapPane.setPrefWidth(200);
		buttonPane.setPrefHeight(150);
		
		basePane.setCenter(gameScreen); //place game screen in base pane
		gameScreen.setBottom(buttonPane); //place button pane in game screen
		gameScreen.setTop(location);
		gameScreen.setLeft(invAndMapPane); //place inv and map pane into the game screen
		gameScreen.setCenter(textBox);
		textBox.getChildren().add(textContent); //add textBox
		textContent.setFont(Font.font("Courier New", 17));
		
		VBox.setMargin(mapButton, new Insets(40,40,40,40)); //add padding to invtext and mapbutton
		VBox.setMargin(invText, new Insets(20,20,20,20));
		invAndMapPane.getChildren().addAll(mapButton, invText);
		VBox.setMargin(textContent, new Insets(20, 20, 20, 20));
		
		buttonPane.setAlignment(Pos.CENTER); //format button pane
		HBox.setMargin(button1, new Insets(40,40,40,40));
		HBox.setMargin(button2, new Insets(40,40,40,40));
		HBox.setMargin(button3, new Insets(40,40,40,40));
		
		//INSTANCIATE MAP PANE
		mapPane.setPrefWidth(550);
		mapPane.setPrefHeight(550);
		mapPane.setId("map");
	    mapPane.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); //get css information
	    
	    GridPane.setMargin(petShopBtn, new Insets(50, 10, 10, 200));
	    GridPane.setMargin(apartmentBtn, new Insets(50, 10, 10, 35));
	    GridPane.setMargin(fishStoreBtn, new Insets(50, 10, 10, 35));
	    GridPane.setMargin(docksBtn, new Insets(100, 10, 30, 40));
	    GridPane.setMargin(fleaMarketBtn, new Insets(50, 0, 0, 35));
	    
		petShopBtn.setScaleX(1.5);
		petShopBtn.setScaleY(1.5);
		apartmentBtn.setScaleX(1.5);
		apartmentBtn.setScaleY(1.5);
		docksBtn.setScaleX(1.5);
		docksBtn.setScaleY(1.5);
		fishStoreBtn.setScaleX(1.5);
		fishStoreBtn.setScaleY(1.5);
		fleaMarketBtn.setScaleX(1.5);
		fleaMarketBtn.setScaleY(1.5);
		
	    //SET POSITIONS FOR BUTTONS ON MAP PANE ***FIX THIS LATER****
	    mapPane.add(petShopBtn, 1, 0);
	    mapPane.add(apartmentBtn, 2, 1);
	    mapPane.add(fishStoreBtn, 4, 2);
	    mapPane.add(docksBtn, 5, 4);
	    mapPane.add(fleaMarketBtn, 2, 4);
			
		//print out the starting paragraph
		printText(textContent, "Welcome to Fetch Quest, a text adventure.\n\n"
        		+ "The game is played by solving various problems and going\n"
        		+ "through a bunch of...well, fetch quests.\n\n"
        		+ "Click on the corresponding button of your choice to interact\n"
        		+ "with various people/objects. Good luck!\n\n"
        		+ "(Click 'Continue' to proceed.)");
		
		button1.setOnAction(new EventHandler<ActionEvent>() { //start game
			@Override
			public void handle(ActionEvent event) {
                if (button1.getText().equals(" CONTINUE ") && mapIsOpen == false && textDone == true) { //ADD A THING TO CHECK IF THE ANIMATION IS PLAYING LATER
                	buttonPane.getChildren().remove(0);
                	printText(textContent, "You are a dog. You love getting head pets and playing\n"
                			+ "with your tennis ball. You also have a decent apartment in the city\n"
                			+ "and a 9 to 5 job chasing the mailman. You know, as all dogs do.\r\n\n"
                			+ "Today is a very special day, for today is your birthday!\n\n"
                			+ "After finishing your shift for the day, you decide to head on over\n"
                			+ "to the local PET SHOP to purchase a large quantity of dog treats.\n"
                			+ "You especially like the bacon-flavored ones.\r\n" + 
                			"\r\n" + 
                			"Click on the MAP button to bring up the map. Click on a corresponding\n"
                			+ "button to travel.");
                	mapClickable = true;
                }
            }
		});
		mapButton.setOnAction(new EventHandler<ActionEvent>() { //open map button
			@Override
			public void handle(ActionEvent event) {
				if(mapClickable == true) {
					mapHandler(); //EDIT LATER -- THIS IS JUST TO TEST THE PET SHOP LOCATION
				}
			}
		});
		
		petShopBtn.setOnAction(new EventHandler<ActionEvent>() { //click on pet shop button
			@Override
			public void handle(ActionEvent event) {
				if(textDone) {
					mapHandler();
					goPetShop();
				}
			}
		});
		
		docksBtn.setOnAction(new EventHandler<ActionEvent>() { //click on docks button
			@Override
			public void handle(ActionEvent event) {
				if(!isTutorial && textDone) {
					mapHandler();
					goDocks();
				}
			}
		});
		
		apartmentBtn.setOnAction(new EventHandler<ActionEvent>() { //click on apartment button
			@Override
			public void handle(ActionEvent event) {
				if(!isTutorial && textDone) {
					mapHandler();
					goApartment();
				}
			}
		});
		
		fishStoreBtn.setOnAction(new EventHandler<ActionEvent>() { //click on fish store button
			@Override
			public void handle(ActionEvent event) {
				if(!isTutorial && textDone) {
					mapHandler();
					goFishStore();
				}
			}
		});
		
		fleaMarketBtn.setOnAction(new EventHandler<ActionEvent>() { //click on flea market button
			@Override
			public void handle(ActionEvent event) {
				if(!isTutorial && textDone) {
					mapHandler();
					goFleaMarket();
				}
			}
		});
	}
	//END OF BASE CODE
	
	//PRINT OUT THE SPECIFIED STRING
	public static void printText(Text textContent, String textWriting) {
		textDone = false;
    	final IntegerProperty i = new SimpleIntegerProperty(0);
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.seconds(0.01),
                event -> {
                    if (i.get() > textWriting.length()) {
                        timeline.stop();
                        textDone = true;
                    } else {
                        textContent.setText(textWriting.substring(0, i.get()));
                        i.set(i.get() + 1);
                    }
                });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
	}
	
	//HANDLES THE MAP STUFF
	public static void mapHandler() {
		if(mapButton.getText().equals("MAP")) {
			gameScreen.getChildren().remove(textBox);
			mapIsOpen = true;
			
			//add image of map and all the buttons it needs
			gameScreen.setCenter(mapPane);
			mapButton.setText("CLOSE");
		}
		else if (mapButton.getText().equals("CLOSE")) {
			gameScreen.getChildren().add(textBox);
			mapIsOpen = false;
			
			//remove image of map and all the buttons it needs
			gameScreen.getChildren().remove(mapPane);
			mapButton.setText("MAP");
		}
	}
	
	public static void goPetShop() {
		bgMusic.stop(); //stop previous bg music
		//start playing new bg music
		String petShopFile = FetchQuestv1.class.getResource("/petShopMusic.mp3").toString();
		Media petShop = new Media(petShopFile);
		bgMusic = new MediaPlayer(petShop);
		bgMusic.setCycleCount(MediaPlayer.INDEFINITE);
		bgMusic.play();
		bgMusic.setVolume(0.5);
		
		if(!buttonPane.getChildren().contains(button1)) {
			buttonPane.getChildren().add(button1);
		}
		if(!buttonPane.getChildren().contains(button2)) {
			buttonPane.getChildren().add(button2);
		}
    	button1.setText("TALK TO KATZENBERG");
		button2.setText("PURCHASE TREATS");
		if(hasTalkedAboutFilters) {
			if(!buttonPane.getChildren().contains(button3)) {
				buttonPane.getChildren().add(button3);
				button3.setText("GIVE FILTERS");
    		}
		}
		if(buttonPane.getChildren().contains(button4)) {
			buttonPane.getChildren().remove(button4);
		}
		
		location.setText("PET SHOP");
		if(takenMagazine) {
			printText(textContent, "The bell over the door jingles as you enter the\n"
					+ "pet shop. The owner, Katzenberg, doesn’t actually sell any pets--\n"
					+ "just supplies. You trust him and his business practices.\n\n"
					+ "Katzenberg is sitting behind the counter, texting fervently on\n"
					+ "his phone. The jar of treats you desire to purchase sits on a\n"
					+ "high shelf, tantalizingly out of reach.");
		}
		else {
			printText(textContent, "The bell over the door jingles as you enter the\n"
					+ "pet shop. The owner, Katzenberg, doesn’t actually sell any pets--\n"
					+ "just supplies. You trust him and his business practices.\n\n"
					+ "Katzenberg is sitting behind the counter, reading a magazine about\n"
					+ "jellyfish. The jar of treats you desire to purchase sits on a\n"
					+ "high shelf, tantalizingly out of reach.");
		}

		
		//BUTTON 1 ACTIONS BELOW
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
                if (button1.getText().equals("TALK TO KATZENBERG")) { //ADD A THING TO CHECK IF THE ANIMATION IS PLAYING LATER
                	printText(textContent, "You bark to get his attention, and he glances up at you.\n"
                			+ "“Yo, dawg! It's great to see you again, man--you know,\n"
                			+ "you work waaay too much. A pup like you should spend some quality time\n"
                			+ "just relaxin’. What can I help you with today?”");
                	button1.setText("HOW WAS YOUR DAY?");
                	button2.setText("I WANT TO BUY TREATS");
                	if(!buttonPane.getChildren().contains(button3)) {
                		buttonPane.getChildren().add(button3);
                	}
                	button3.setText("NEVERMIND");
                	
                }
                
                else if (button1.getText().equals("HOW WAS YOUR DAY?")) {
                	if(!inv.hasItem("-Jellyfish\nMagazine"))
                	{
                	printText(textContent, "“Business has been sort of slow, lately. People these days aren’t as\n"
                			+ "interested in buying dog stuff anymore. Yo, didja know that jellyfish\n"
                			+ "keeping is the next big fad?”\n\n" + 
                			"You open your mouth to interject when he waves his hands at you.\n\n"
                			+ "“It’s awesome, man--unlike keeping normal fish, you need, like, a\n"
                			+ "low-power filter to make sure they’re not sucked into the current.\n"
                			+ "They’re really delicate, and once you figure out how to clean the tank\n"
                			+ "without being stung, they can be very relaxing to look at. Just\n"
                			+ "imagine, all those jellyfish, floating around so peacefully…”\n\n" + 
                			"He trails off, tapping his chin in a thoughtful manner.\n\n"
                			+ "“If only I had some of those filters to sell--I bet more people\n"
                			+ "would come in here to buy things.”\n\n" + 
                			"There’s a solid thirty seconds of awkward silence.");
                	} else {
                		printText(textContent, "“Business has been sort of slow, lately. People these days aren’t as\n"
                								+ "interested in buying dog stuff anymore. If only I had\n"
                								+ "some jellyfish filters....”");
                	}
            		if(!buttonPane.getChildren().contains(button1)) {
            			buttonPane.getChildren().add(button1);
            		}
        			if(!buttonPane.getChildren().contains(button2)) {
        				buttonPane.getChildren().add(button2);
        			}
                	
                	button1.setText("CAN I HAVE THE MAGAZINE?");
                	button2.setText("TELL ME A JELLYFISH FACT");
            		
            		if(takenMagazine){
            			buttonPane.getChildren().remove(button1); //you dont get to take TWO magazines
            			buttonPane.getChildren().remove(button2);
            		}
                	button3.setText("OKAY COOL I GUESS");
                }
                
                else if(button1.getText().equals("CAN I HAVE THE MAGAZINE?")) {
                	printText(textContent, "“Hmm? Oh, of course! Here you go, lil’ dude.\n"
                			+ "Just make sure to give it back once you’re done reading, okay?”\n\n"
                			+ "[MAGAZINE added to inventory.]\n\n"
                			+"“Anyways, enough about the jellyfish. You’re here to buy\n"
                			+ "something, right?”");
                	inv.addItem("-Jellyfish\nMagazine", 0); //GET MAGAZINE
                	invText.setText(inv.toString());
                	
                	takenMagazine = true;
            		
            		buttonPane.getChildren().remove(button3);

            		button2.setText("I WANT TO BUY TREATS");
            		button1.setText("NO THANKS");
                }
                
                else if(button1.getText().equals("EXPLAIN YOUR PLIGHT")) {
                	printText(textContent, "He nods sympathetically as you howl about not having any money.\n\n" 
                			+ "“Mhm. I hear you, man. Nothin’ much we can do about the economy except\n"
                			+ "complain about it and blame it on the big man.”\n\n"
                			+ "You’re about to ask who ‘the big man’ is when he continues talking.\n\n" 
                			+ "“Now, I can’t really help out with the money thing, but I’ve got an idea.\n"
                			+ "Y’know how the latest fad is jellyfish keeping? Well, those suckers need\n"
                			+ "special filters, and if you can somehow get your paws on those, I’ll be\n"
                			+ "more than glad to give you this jar of treats in exchange.”");
                	button1.setText("WHERE CAN I FIND FILTERS?");
                	hasTalkedAboutFilters = true;
                }
               
                else if(button1.getText().equals("WHERE CAN I FIND FILTERS?")) {
                	printText(textContent, "“Well, I guess it’s good to check out the fish store down\n"
                			+ "the street. I mean, they keep fish, they gotta have something, right?\n"
                			+ "You might also wanna look into the flea market, usually someone’s\n"
                			+ "selling some cool stuff. Maybe it’s worth visiting the docks?\n"
                			+ "I mean, they gotta have fish supplies there, it’s the ocean.”\n\n"
                			+ "You also note to yourself that you may have some useful items\n"
                			+ "back at your apartment. It’s worth stopping at home first to\n"
                			+ "see what you have.\n\n"
                			+ "[Fish Store, Flea Market, and Docks now available on MAP.]");
                	isTutorial = false;
                	button1.setText("THANK YOU");
                }
                
                else if(button1.getText().equals("THANK YOU") ||
                		button1.getText().equals("NEVERMIND, I'M GOOD") ||
                		button1.getText().equals("NO THANKS")) {
                	printText(textContent, "Katzenberg gives you a thumbs up, then turns away from you,\n"
                			+ "texting someone. Or maybe he's playing a game. You can't really tell.");
                	
            		if(!buttonPane.getChildren().contains(button1)) {
            			buttonPane.getChildren().add(button1);
            		}
        			if(!buttonPane.getChildren().contains(button2)) {
        				buttonPane.getChildren().add(button2);
        			}
        			if(!buttonPane.getChildren().contains(button3) && hasTalkedAboutFilters) {
        				buttonPane.getChildren().add(button3);
        			}
        			if(buttonPane.getChildren().contains(button3) && !hasTalkedAboutFilters) {
        				buttonPane.getChildren().remove(button3);
        			}
        			
                	button1.setText("TALK TO KATZENBERG");
                	button2.setText("PURCHASE TREATS");
                	button3.setText("GIVE FILTERS");
                }
                
                else if(button1.getText().equals("THE END")) {
                	endGame();
                }
            }
			}
		});
		
		//BUTTON2 ACTIONS BELOW
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
			
				if ((button2.getText().equals("PURCHASE TREATS")
						|| button2.getText().equals("I WANT TO BUY TREATS"))
						&& !hasTalkedAboutFilters
						&& mapIsOpen == false) {
					
            		if(buttonPane.getChildren().contains(button3)) {
            			buttonPane.getChildren().remove(button3);
            		}
					
					printText(textContent, "“Oh, classic choice, dude.” He turns to reach up and\n"
							+ "grab the treats, placing them on the counter. You can practically\n"
							+ "smell the meaty-flavored bits of goodness, and your mouth starts to water.\n\n"
							+ "“That’ll be thirty dollars.”\n\n"
							+ "You immediately stop drooling. You don’t have that kind of money.\n"
							+ "In fact, you don’t have any money at all. No one pays you when you chase\n"
							+ "after mailmen. It’s a sad, lonely existence being a dog.\n\n"
							+ "Katzenberg is looking at you expectantly.");
					
            		if(!buttonPane.getChildren().contains(button1)) {
            			buttonPane.getChildren().add(button1);
            		}
            		
					buttonPane.getChildren().remove(button2);
					button1.setText("EXPLAIN YOUR PLIGHT");
				}
				
				else if((button2.getText().equals("PURCHASE TREATS")
						|| button2.getText().equals("I WANT TO BUY TREATS"))
						&& hasTalkedAboutFilters
						&& mapIsOpen == false) {
					printText(textContent, "“Dude, I told you you can't have these treats until you get\n"
							+ "those filters for me. I gotta make a living, too, y'know!”");
				}
				
				else if(button2.getText().equals("TELL ME A JELLYFISH FACT")) {
					printText(textContent, "He flips through the magazine, skimming the pages for\n"
							+ "jellyfish facts.\n\n"
							+ "“Oh, here’s something interesting--jellyfish powder has been used\n"
							+ "to make salted caramel. It was invented by a couple of students in\n"
							+ "Japan.” He pauses. “Sounds totally weird, man. Dunno if I’d try it.”\n\n"
							+ "You’ve never tried salted caramel in your life, so you have no\n"
							+ "opinion on this.");
					
					buttonPane.getChildren().remove(button3);
					
					button1.setText("CAN I HAVE THE MAGAZINE?");
					button2.setText("COOL FACT");
					
				}
				
				else if(button2.getText().equals("COOL FACT")) {
					printText(textContent, "Katzenberg just sort of shrugs.\n\n"
							+ "“Anyways, enough about the jellyfish. How can I help you?”");
					
					button1.setText("NEVERMIND, I'M GOOD");
					button2.setText("I WANT TO BUY TREATS");
					
			}
		}
		}});
		
		//BUTTON3 ACTIONS BELOW
		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if (button3.getText().equals("GIVE FILTERS")) {
						if(inv.hasItem("-Jellyfish\nFilters")) {
							inv.removeItem(7);
							invText.setText(inv.toString());
							mapClickable = false;
							printText(textContent, "Katzenberg takes the filters from you, inspecting them. “Well,\n"
									+ "hey! You actually went and did it! I’m proud of you, dude.\n"
									+ "I hope it didn’t take too long. I believe that this is\n"
									+ "yours, now.”\n\n"
									+ "Your tails begins to wag furiously as he hand you the jar of treats.\n"
									+ "You barely waste a second to tear open the lid and start chowing down\n"
									+ "on the meaty goodness inside. In about five minutes, the jar is empty.\n"
									+ "After licking the crumbs clean from the glass, you bark thankfully at\n"
									+ "Katzenberg and return to your apartment for a well deserved rest.\n\n"
									+ "Today is the best birthday ever!");
							
							button1.setText("THE END");
							if(buttonPane.getChildren().contains(button2)) {
		            			buttonPane.getChildren().remove(button2);
		            		}
		            		if(buttonPane.getChildren().contains(button3)) {
		            			buttonPane.getChildren().remove(button3);
		            		}
						}
						else {
							printText(textContent, "“Dude, you don't have any filters to give to me. C'mon!\n"
									+ "I'm counting on you!”");
						}
					}
					
					else if (button3.getText().equals("OKAY COOL I GUESS")) {
						printText(textContent, "“Anyways, enough about the jellyfish. How can I help you?”");
						
						buttonPane.getChildren().remove(button3);
						
	            		if(!buttonPane.getChildren().contains(button1)) {
	            			buttonPane.getChildren().add(button1);
	            		}
	            		if(!buttonPane.getChildren().contains(button2)) {
	            			buttonPane.getChildren().add(button2);
	            		}
						
						button1.setText("NEVERMIND, I'M GOOD");
						button2.setText("I WANT TO BUY TREATS");
					} else if (button3.getText().equals("NEVERMIND")) {
	                	printText(textContent, "Katzenberg gives you a thumbs up, then turns away from you,\n"
	                			+ "texting someone. Or maybe he's playing a game. You can't really tell.");
	                	
	                	button1.setText("TALK TO KATZENBERG");
	                	button2.setText("PURCHASE TREATS");
	                	button2.setText("GIVE FILTERS");
	            		if(!hasTalkedAboutFilters) {
	            			if(buttonPane.getChildren().contains(button3)) {
	            				buttonPane.getChildren().remove(button3);
	                		}
	            		}
					}
				}
			}
		});
		
	}
	
	public static void goApartment() {
		bgMusic.stop(); //stop the previous bg music
		//Start playing background music
		String apartmentFile = FetchQuestv1.class.getResource("/apartmentMusic.mp3").toString();
		Media apartment = new Media(apartmentFile);
		bgMusic = new MediaPlayer(apartment);
		bgMusic.setCycleCount(MediaPlayer.INDEFINITE);
		bgMusic.play();
		bgMusic.setVolume(0.5);
		
		location.setText("APARTMENT");
		
		if(!buttonPane.getChildren().contains(button1)) {
			buttonPane.getChildren().add(button1);
		}
		if(!buttonPane.getChildren().contains(button2)) {
			buttonPane.getChildren().add(button2);
		}
		if(!buttonPane.getChildren().contains(button3)) {
			buttonPane.getChildren().add(button3);
		}
		if(buttonPane.getChildren().contains(button4)) {
			buttonPane.getChildren().remove(button4);
		}
    	button1.setText("BED");
		button2.setText("REFRIGERATOR");
		button3.setText("TOY BOX");

		printText(textContent, "You enter your apartment. It is surprisingly neat--mostly\n"
				+ "because there are only three pieces of furniture in this entire room.\n"
				+ "Being a dog is much less complicated than being a human.");
		
		//BUTTON 1 COMMANDS
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button1.getText().equals("BED")) {
						printText(textContent, "If you sleep now, you’ll miss out on your birthday, and then\n"
								+ "you’ll have to wait a whole year to celebrate again!");
					}
					
					else if(button1.getText().equals("TAKE CAN")) {
						if(inv.hasItem("-Empty Can")) {
							printText(textContent, "You already have a can--probably not a good idea to bring\n"
									+ "the contents of your entire refrigerator with you.");
							
						}
						else {
							printText(textContent, "You take one out of the refrigerator. There’s still a lot\n"
									+ "of empty cans in there. You note to yourself to take out the garbage\n"
									+ "later.\n\n"
									+ "[EMPTY CAN added to inventory.]");
						
							inv.addItem("-Empty Can", 2); //GET CAN
							invText.setText(inv.toString());
						}
					}
					
					else if(button1.getText().equals("PLAY WITH TENNIS BALL")) {
						printText(textContent, "You throw around the tennis ball for a little bit. It bounces\n"
								+ "off of the walls and you give chase, cornering it and sending it\n"
								+ "flying again. This is probably the most fun you’ve had since an\n"
								+ "hour ago!");
					}
				}
				
			}
		});
		
		//BUTTON 2 COMMANDS
		
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button2.getText().equals("REFRIGERATOR")) {
						if(takenLettuce) {
							printText(textContent, "You don’t really use this for much--just canned dog food and a few\n"
									+ "vegetables to pretend to be healthy. You open the refrigerator to see\n"
									+ "empty cans. Maybe you should stop by the supermarket tomorrow to look\n"
									+ "for more food. To steal it. Because you have no money.");
							buttonPane.getChildren().remove(button2);
						}
						else {
							printText(textContent, "You don’t really use this for much--just canned dog food and a few\n"
									+ "vegetables to pretend to be healthy. You open the refrigerator to see\n"
									+ "empty cans and a...head of lettuce? Maybe you should stop by the\n"
									+ "supermarket tomorrow for more food. To steal it. Because you\n"
									+ "have no money.");
						}
						button1.setText("TAKE CAN");
						button2.setText("TAKE LETTUCE");
						button3.setText("BACK");
					}
					
					else if(button2.getText().equals("TAKE LETTUCE")) {
						takenLettuce = true;
						printText(textContent, "You take the lettuce. You don’t even like vegetables--\n"
								+ "why do you have this?\n\n"
								+ "[LETTUCE added to inventory.]");
						
	                	inv.addItem("-Lettuce", 1); //GET LETTUCE
	                	invText.setText(inv.toString());
	                	
	                	buttonPane.getChildren().remove(button2);
					}
					
					else if(button2.getText().equals("PLAY WITH SQUEAKY TOY")) {
						printText(textContent, "You viciously bite down on a duck-shaped squeaky toy, which gives\n"
								+ "a satisfying squeak of despair. You chew at it and throw it around, making\n"
								+ "it squeak even more. You could probably do this all day.");
					}
				}
			}
		});
		
		//BUTTON 3 COMMANDS
		
		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button3.getText().equals("TOY BOX")) {
						printText(textContent, "The box is filled to the brim with dog toys. Tennis balls, chew\n"
								+ "toys, rubber balls, squeaky toys...any pup would be pleased with this\n"
								+ "extensive collection. You don’t really want to take any of these toys\n"
								+ "with you. However, you could take a quick break to play with them--\n"
								+ "after all, it is your birthday!");
						
						button1.setText("PLAY WITH TENNIS BALL");
						button2.setText("PLAY WITH SQUEAKY TOY");
						button3.setText("BACK");
					}
					
					else if(button3.getText().equals("BACK")) {
						printText(textContent, "You abandon whatever you were doing to turn your attention\n"
								+ "to something else.");
						
						if(!buttonPane.getChildren().contains(button2)) {
							buttonPane.getChildren().removeAll(button1, button3);
							buttonPane.getChildren().addAll(button1, button2, button3);
						}
						
				    	button1.setText("BED");
						button2.setText("REFRIGERATOR");
						button3.setText("TOY BOX");
					}
				}
			}
		});
	}
	
	public static void goFishStore() {
		bgMusic.stop(); //stop previous bg music
		//start playing new bg music
		String fishStoreFile = FetchQuestv1.class.getResource("/fishStoreMusic.mp3").toString();
		Media fishStore = new Media(fishStoreFile);
		bgMusic = new MediaPlayer(fishStore);
		bgMusic.setCycleCount(MediaPlayer.INDEFINITE);
		bgMusic.play();
		bgMusic.setVolume(0.5);
		
		location.setText("FISH STORE");
		
		printText(textContent, "The walls of the shop are lined with rows upon rows of colorful\n"
				+ "and vibrant fish tanks. There is a poster up that advertises jellyfish\n"
				+ "filters. A man with a medium-length haircut is busy cleaning out tanks.\n"
				+ "His name tag reads “MARTIN”, and, in slightly smaller letters underneath,\n"
				+ "“Manager”. He doesn’t seem to be particularly busy right now.");
		
		if(buttonPane.getChildren().contains(button1)) {
			buttonPane.getChildren().remove(button1);
		}
		if(buttonPane.getChildren().contains(button2)) {
			buttonPane.getChildren().remove(button2);
		}
		if(!buttonPane.getChildren().contains(button1)) {
			buttonPane.getChildren().add(button1);
		}
		if(!buttonPane.getChildren().contains(button2)) {
			buttonPane.getChildren().add(button2);
		}
		if(buttonPane.getChildren().contains(button3)) {
			buttonPane.getChildren().remove(button3);
		}
		if(buttonPane.getChildren().contains(button4)) {
			buttonPane.getChildren().remove(button4);
		}
		
    	button1.setText("TALK TO MARTIN");
		button2.setText("EXAMINE POSTER");
		
		//BUTTON 1 COMMANDS
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button1.getText().equals("TALK TO MARTIN")) {
						printText(textContent, "You bark to get his attention, and he glances down at\n"
								+ "you, raising an eyebrow. “Yes? Can I help you?”");
						
						buttonPane.getChildren().add(button3);
						
						if(!hasTalkedFishFood)
							button1.setText("DO YOU HAVE JELLYFISH FILTERS?");
						else
							button1.setText("FISH FOOD");
						
						button2.setText("WHAT'S UP?");
						button3.setText("NEVERMIND");
					}
					
					else if(button1.getText().equals("DO YOU HAVE JELLYFISH FILTERS?")) {
						printText(textContent, "“Ah, you’d like to purchase a jellyfish filter, I presume?\n"
								+ "That’ll be $99.99.”\n\n" 
								+ "You bark to say that you’re a dog and, thus, have no money. Martin\n"
								+ "shrugs, seemingly indifferent.\n\n"
								+ "“Well, then, no filter. I’ve got to make a living, too, you know.”");
						
						buttonPane.getChildren().remove(button3);
						
						button1.setText("CAN'T I BARTER WITH YOU?");
						button2.setText("THANKS ANYWAYS");
					}
					
					else if(button1.getText().equals("CAN'T I BARTER WITH YOU?")) {
						printText(textContent, "“No. What do you think this is, some sort of video game\n"
								+ "where you can jump through an elaborate amount of fetch quests\n"
								+ "to get the one thing you can’t pay for?”\n\n"
								+ "He sighs, wiping his hands on a nearby towel. “I apologize,\n"
								+ "these filters are the one thing keeping my business afloat\n"
								+ "nowadays. No one was really interested in fishkeeping up until\n"
								+ "a few months ago. I’ll tell you what--if you can get me some\n"
								+ "fish food for my African Cichlids, I can give you twenty\n"
								+ "dollars for your trouble. How’s that sound?”");
						
						button1.setText("SOUNDS GOOD");
						button2.setText("SOUNDS BAD");
					}
					
					else if(button1.getText().equals("SOUNDS GOOD")) {
						printText(textContent, "Martin nods. “Good. I’ll need two things to feed these\n"
								+ "guys until my next shipment of fish food comes in. One: some\n"
								+ "sort of leafy vegetable. Possibly lettuce. Really, anything\n"
								+ "works. Two: worms. I’d prefer if those were in some sort of\n"
								+ "container--I’d rather not deal with escaped worms in the shop.\n"
								+ "Once you have these things, come back and talk to me, alright?”\n\n"
								+ "He turns back to his fish tanks and starts cleaning again.");
						
						hasTalkedFishFood = true;
						if(!buttonPane.getChildren().contains(button1)) {
							buttonPane.getChildren().add(button1);
						}
						if(!buttonPane.getChildren().contains(button2)) {
							buttonPane.getChildren().add(button2);
						}
						if(buttonPane.getChildren().contains(button3)) {
							buttonPane.getChildren().remove(button3);
						}
						
				    	button1.setText("TALK TO MARTIN");
						button2.setText("EXAMINE POSTER");
					}
					
					else if(button1.getText().equals("FISH FOOD")) {
						if(!taken20) {
							printText(textContent, "“Yes? Do you have what I need, yet?”");
						
							buttonPane.getChildren().add(button4);
						
							if(!inv.hasItem("-Lettuce")) {
							buttonPane.getChildren().remove(button2);
							}
						
							if(!inv.hasItem("-Can of Worms")) { //removes the buttons if the player does not have the items
								buttonPane.getChildren().remove(button3);
							}
						
							button1.setText("WHAT DID YOU NEED?");
							button2.setText("GIVE LETTUCE");
							button3.setText("GIVE WORMS");
							button4.setText("BACK");
						}
						else {
							printText(textContent, "“You've already given me what I needed. The fish are\n"
									+ "doing well, thanks to you. Now, if you'll excuse me, I need to\n"
									+ "get back to work.”");
							
							button4.setText("OKAY");
							
							buttonPane.getChildren().remove(button1);
							buttonPane.getChildren().remove(button2);
							buttonPane.getChildren().remove(button3);
							buttonPane.getChildren().add(button4);
						}
					}
					
					else if(button1.getText().equals("WHAT DID YOU NEED?")) {
						printText(textContent, "“You need some sort of leafy vegetable and some worms, preferably\n"
								+ "in some sort of container. Now, get to it, these fish won’t\n"
								+ "feed themselves.”");
					}
				}
			}
		});
		
		//BUTTON 2 COMMANDS
		
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button2.getText().equals("EXAMINE POSTER")) {
						printText(textContent, "You examine the poster carefully. It says the following:\n\n"
								+ "!!SALE!! JELLYFISH FILTERS - ONLY $99.99 EACH!\n" + 
								"(0% off of our regularly marked price!)\n" + 
								"Ever wanted to be just like everyone else and follow the fad wave\n"
								+ "until it dies? Well, now you can be hip with all the cool kids\n"
								+ "and have your very own pet jellyfish! These filters are guaranteed\n"
								+ "to keep your tank clean while not damaging your precious jellyfish\n"
								+ "in the process!* Safe, quiet, and mess-free!\n\n" + 
								"*Store is not liable for any other kind of jellyfish death. You are\n"
								+ "responsible for your own pets’ wellbeing.");
					}
					
					else if(button2.getText().equals("WHAT'S UP?")) {
						printText(textContent, "“Hmm.” He stops cleaning for a minute. “Today a couple came in,\n"
								+ "asking to buy two betta fish for a five gallon tank. I tried to\n"
								+ "patiently explain to them that betta fish cannot be kept together,\n"
								+ "and that five gallons is far too small for two of them, anyways.\n"
								+ "They insisted on purchasing them, however, and eventually,\n"
								+ "I told them to leave.”\n\n"
								+ "Martin continues to clean again. “At least the income from\n"
								+ "the jellyfish filters let me make slightly more humane\n"
								+ "decisions when it comes to my customers.”");
					}
					
					else if(button2.getText().equals("THANKS ANYWAYS")) {
						printText(textContent, "He nods, then turns back to his fish tanks and\n"
								+ "starts cleaning again.");
						
						if(!buttonPane.getChildren().contains(button1)) {
							buttonPane.getChildren().add(button1);
						}
						if(!buttonPane.getChildren().contains(button2)) {
							buttonPane.getChildren().add(button2);
						}
							buttonPane.getChildren().remove(button3);
					
				    	button1.setText("TALK TO MARTIN");
						button2.setText("EXAMINE POSTER");
					}
					
					else if(button2.getText().equals("SOUNDS BAD")) {
						printText(textContent, "“Well, if you’re ever looking to make some money,\n"
								+ "just come back and talk to me again.” He turns back to his\n"
								+ "fish tanks and starts cleaning again.");
						
						if(!buttonPane.getChildren().contains(button1)) {
							buttonPane.getChildren().add(button1);
						}
						if(!buttonPane.getChildren().contains(button2)) {
							buttonPane.getChildren().add(button2);
						}
						if(buttonPane.getChildren().contains(button3)) {
							buttonPane.getChildren().remove(button3);
						}
						
				    	button1.setText("TALK TO MARTIN");
						button2.setText("EXAMINE POSTER");	
					}
					
					else if(button2.getText().equals("GIVE LETTUCE")) {
						inv.removeItem(1);
						invText.setText(inv.toString());
						givenLettuce = true;
						
						if(!givenWorms) {
							printText(textContent, "“This’ll work nicely, thank you kindly.”\n\n"
									+ "He puts away the lettuce. “You still need to get me some worms.”");
						}
						else {
							printText(textContent, "“Oh, that’s the last thing I need! Thank you for helping\n"
									+ "me out. Here’s your payment.”\n\n"
									+ "Martin hands you a wrinkled twenty dollar bill. You tuck it into\n"
									+ "your collar and feel proud. Too bad it’s not thirty dollars--otherwise,\n"
									+ "you’d be able to buy the treats all by yourself.\n\n"
									+ "[TWENTY DOLLAR BILL added to inventory.]\n\n"
									+ "“I'll feed them as soon as I finish cleaning. Thank you.”");
							
							inv.addItem("-$20", 4);
							invText.setText(inv.toString());
							taken20 = true;
							buttonPane.getChildren().remove(button1);
						}
						
						buttonPane.getChildren().remove(button2);
					}
				}
			}
		});
		
		//BUTTON 3 COMMANDS
		
		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button3.getText().equals("NEVERMIND")) {
						printText(textContent, "He nods, mutters something under his breath about\n"
								+ "needing to restock guppies, then turns back to his fish tanks and\n"
								+ "starts cleaning again.");
						
						if(!buttonPane.getChildren().contains(button1)) {
							buttonPane.getChildren().add(button1);
						}
						if(!buttonPane.getChildren().contains(button2)) {
							buttonPane.getChildren().add(button2);
						}
							buttonPane.getChildren().remove(button3);
					
				    	button1.setText("TALK TO MARTIN");
						button2.setText("EXAMINE POSTER");
					}
					
					else if(button3.getText().equals("GIVE WORMS")) {
						inv.removeItem(3);
						invText.setText(inv.toString());
						givenWorms = true;
						if(!givenLettuce) {
							
							printText(textContent, "“Hmm, these look to be okay. Thanks for putting them\n"
									+ "in a can, too.”\n\n"
									+ "He puts away the can of worms. “You still need to get me some\n"
									+ "sort of vegetable.”");
						}
						else {
							printText(textContent, "“Oh, that’s the last thing I need! Thank you for helping\n"
									+ "me out. Here’s your payment.”\n\n"
									+ "Martin hands you a wrinkled twenty dollar bill. You tuck it into\n"
									+ "your collar and feel proud. Too bad it’s not thirty dollars--otherwise,\n"
									+ "you’d be able to buy the treats all by yourself.\n\n"
									+ "[TWENTY DOLLAR BILL added to inventory.]\n\n"
									+ "“I'll feed them as soon as I finish cleaning. Thank you.”");
							
							inv.addItem("-$20", 4);
							invText.setText(inv.toString());
							taken20 = true;
							
							buttonPane.getChildren().remove(button1);
						}
						buttonPane.getChildren().remove(button3);
					}
				}
			}
		});
		
		button4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button4.getText().equals("BACK") || button4.getText().equals("OKAY")) {
						
						printText(textContent, "“Alright. Is there anything else you wanted?”");
						
						button1.setText("FISH FOOD");
						button2.setText("WHAT'S UP?");
						button3.setText("NEVERMIND");
						
						if(!buttonPane.getChildren().contains(button1)) {
							buttonPane.getChildren().add(button1);
						}
						
						if(!buttonPane.getChildren().contains(button2)) {
							buttonPane.getChildren().add(button2);
						}
						
						if(!buttonPane.getChildren().contains(button3)) {
							buttonPane.getChildren().add(button3);
						}
						
						buttonPane.getChildren().remove(button4);
					}
				}
			}
		});
	}
	
	public static void goDocks() {
		bgMusic.stop();
		String docksFile = FetchQuestv1.class.getResource("/docksMusic.wav").toString();
		Media docks = new Media(docksFile);
		bgMusic = new MediaPlayer(docks);
		bgMusic.setCycleCount(MediaPlayer.INDEFINITE);
		bgMusic.play();
		bgMusic.setVolume(0.5);
		
		location.setText("DOCKS");
		
		if(!buttonPane.getChildren().contains(button1)) {
			buttonPane.getChildren().add(button1);
		}
		
		if(!buttonPane.getChildren().contains(button2)) {
			buttonPane.getChildren().add(button2);
		}
		
		if(buttonPane.getChildren().contains(button3)) {
			buttonPane.getChildren().remove(button3);
		}
		
		if(buttonPane.getChildren().contains(button4)) {
			buttonPane.getChildren().remove(button4);
		}
		
		button1.setText("BAIT SHOP");
		button2.setText("TALK TO FISHERMAN");
		
		printText(textContent, "You arrive at the docks. You don’t really come here too often--\n"
				+ "it’s a long walk from your apartment. The soothing waves lapping up\n"
				+ "against the pier makes you feel relaxed. It’s a quiet day, today--\n"
				+ "only one person is out here fishing. There’s a bait and tackle shop\n"
				+ "off to the side.");
		
		//BUTTON 1 COMMANDS
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button1.getText().equals("BAIT SHOP")) {
						printText(textContent, "As you approach the bait shop, you realize that a sign\n"
								+ "has been posted on the door. It reads:\n\n"
								+ "“WE ARE CLOSED\n"
								+ "(OUT OF WORMS - SORRY!)”\n\n"
								+ "What kind of bait shop runs out of worms?");
					}
					
					else if(button1.getText().equals("CAN I HAVE SOME WORMS?")) {
						printText(textContent, "He raises an eyebrow, quizzical. “Sure, but...if I\n"
								+ "don’t have worms to fish, what else am I going to do? I can’t\n"
								+ "just give you my bait for free, you know.\n\n"
								+ "...Tell you what. If you get something for me to read while\n"
								+ "I wait for the fish to bite, I’ll give you some of my worms.\n"
								+ "Might wanna get something to carry them in, too. Is that a deal?”");
						
						button1.setText("SURE");
						button2.setText("NAH");
						buttonPane.getChildren().remove(button3);
					}
					
					else if(button1.getText().equals("GIVE MAGAZINE")) {
						if(inv.hasItem("-Jellyfish\nMagazine") && !inv.hasItem("-Empty Can")) {
							printText(textContent, "The fisherman grunts, eyeing the magazine. “It’ll probably\n"
									+ "be easier for you to get a can or something to hold the worms.\n"
									+ "Easier to code, too. Hold onto your magazine for now, you can\n"
									+ "give me both at the same time.”\n\n"
									+ "You don’t know what he means by ‘easier to code’.");
						}
						else if(inv.hasItem("-Jellyfish\nMagazine") && inv.hasItem("-Empty Can")) {
							inv.removeItem(2);
							inv.removeItem(0);
							givenMagazine = true;
							
							inv.addItem("-Can of Worms", 3);
							invText.setText(inv.toString());
							printText(textContent, "The fisherman graciously takes the magazine from you.\n"
									+ "“Jellyfish, huh? Don’t know how interesting this’ll be, but at\n"
									+ "least it’s better than sitting around doing nothing. A deal’s\n"
									+ "a deal--let me fill up this can for you.”\n\n"
									+ "He opens his tackle box, takes a fistful of wriggling worms,\n"
									+ "and dumps it into your can. You wrinkle your nose a little bit--\n"
									+ "it smells pretty bad. The fisherman flips open the magazine and\n"
									+ "starts to read.\n\n"
									+ "[CAN OF WORMS added to inventory.]");
							
							buttonPane.getChildren().remove(button1);
						}
						else {
							printText(textContent, "Huh? What magazine?");
						}
					}
					
					else if(button1.getText().equals("SURE")) {
						hasTalkedWorms = true;
						
						printText(textContent, "“Alright. Just come and talk to me when you’ve got what\n"
								+ "I need, alright?”\n\n"
								+ "He turns back to look out at the ocean, humming a quiet\n"
								+ "tune to himself.");
						
						button1.setText("BAIT SHOP");
						button2.setText("TALK TO FISHERMAN");
					}
				}
				
			}
		});
		
		//BUTTON 2 COMMANDS
		
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button2.getText().equals("TALK TO FISHERMAN")) {
						
						printText(textContent, "The fisherman looks up as you approach, sitting with his\n"
								+ "fishing pole in hand. Next to him sits a rather impressive-looking\n"
								+ "tackle box. He looks surprised to see a dog coming up to greet him.\n"
								+ "He smiles, and pats you on the head. Your tail begins to wag.\n\n"
								+ "“Hello, puppy. What’re you doing out here?”");
						
						if(!hasTalkedWorms) { //hasn't talked about worms yet
							button1.setText("CAN I HAVE SOME WORMS?");
						} else if(hasTalkedWorms && !givenMagazine) { //talked about worms, haven't given magazine
							button1.setText("GIVE MAGAZINE");
						}
						else { //given magazine or whatever
							buttonPane.getChildren().remove(button1);
						}
						
						button2.setText("HOW ARE YOU?");
						buttonPane.getChildren().add(button3);
						button3.setText("NEVERMIND");
					}
					
					else if(button2.getText().equals("HOW ARE YOU?")) {
						printText(textContent, "He scratches his beard, thinking. “Alright. The fish\n"
								+ "aren’t really biting today.\n\n"
								+ "There’s a few seconds of silence.\n\n"
								+ "“Shame. I was looking forward to having a fish dinner today.”\n\n"
								+ "He falls quiet again. This guy doesn’t seem to like talking\n"
								+ "very much.");
					}
					
					else if(button2.getText().equals("NAH")) {
						
					}
				}
			}
		});
		
		//BUTTON 3 COMMANDS
		
		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button3.getText().equals("NEVERMIND")) {
						printText(textContent, "He gives you one last head pat before turning his\n"
								+ "attention back towards his fishing pole.");
						
						button1.setText("BAIT SHOP");
						button2.setText("TALK TO FISHERMAN");
						
						if(!buttonPane.getChildren().contains(button1)) {
							buttonPane.getChildren().removeAll(button2, button3);
							buttonPane.getChildren().addAll(button1, button2);
						}
						
						if(buttonPane.getChildren().contains(button3)) {
							buttonPane.getChildren().remove(button3);
						}
					}
				}
			}
		});
	}
	
	public static void goFleaMarket() {
		bgMusic.stop();
		String fleaMarketFile = FetchQuestv1.class.getResource("/fleaMarketMusic.mp3").toString();
		Media fleaMarket = new Media(fleaMarketFile);
		bgMusic = new MediaPlayer(fleaMarket);
		bgMusic.setCycleCount(MediaPlayer.INDEFINITE);
		bgMusic.play();
		bgMusic.setVolume(0.5);
		
		location.setText("FLEA MARKET");
		
		if(!buttonPane.getChildren().contains(button1)) {
			buttonPane.getChildren().add(button1);
		}
		
		if(!buttonPane.getChildren().contains(button2)) {
			buttonPane.getChildren().add(button2);
		}
		
		if(!buttonPane.getChildren().contains(button3)) {
			buttonPane.getChildren().add(button3);
		}
		
		if(buttonPane.getChildren().contains(button4)) {
			buttonPane.getChildren().remove(button4);
		}
		
		button1.setText("JELLYFISH FILTER STAND");
		button2.setText("MACARON STAND");
		
		if(!givenMacarons && !knowsHalinasName) {
			button3.setText("TALK TO NERVOUS LADY");
		}
		else if(!givenMacarons && knowsHalinasName) {
			button3.setText("TALK TO HALINA");
		}
		else {
			buttonPane.getChildren().remove(button3);
		}
		
		if(givenMacarons) {
			printText(textContent, "The flea market seems to be winding down for the day, though\n"
					+ "there are still a few stalls open for you to peruse. There is a\n"
					+ "frizzy-haired woman sitting behind a plastic table. The sign for her stand\n"
					+ "reads: “VIC'S FISH SUPPLIES: REFURBISHED & CHEAP! GET THEM WHILE THEY’RE HOT”.\n"
					+ "She's chatting with Halina over some macarons. There is also a macaron\n"
					+ "stand, which is having an end-of-day sale.");
		}
		else {
			printText(textContent, "The flea market seems to be winding down for the day, though\n"
					+ "there are still a few stalls open for you to peruse. There is a\n"
					+ "frizzy-haired woman sitting behind a plastic table covered with jellyfish\n"
					+ "filters. The sign for her stand reads: “VIC'S FISH SUPPLIES: REFURBISHED\n"
					+ "& CHEAP! GET THEM WHILE THEY’RE HOT”. She looks rather forlorn. There is\n"
					+ "also a macaron stand, which is having an end-of-day sale. Near the\n"
					+ "end of the long row of stalls, a nervous-looking woman is cowering behind\n"
					+ "a sign, trying to remain hidden.");
		}
		
		
		//BUTTON 1 COMMANDS
		
		button1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button1.getText().equals("JELLYFISH FILTER STAND")) {
						if(!givenMacarons) {
							printText(textContent, "You approach Vic and bark to get her attention\n"
									+ "She barely looks in your direction.");
							
							button1.setText("CAN I HAVE A FILTER?");
							
							if(inv.hasItem("-Macaron Box\nw/ Letter") || inv.hasItem("-Macaron Box")) {
								button2.setText("GIVE MACARONS");
							}
							else {
								button2.setText("HOW ARE YOU?");
							}
							button3.setText("NEVERMIND");
							
						}
						else {
							printText(textContent, "You approach Vic and bark to get her attention.\n"
									+ "She looks up and grins.\n\n"
									+ "“Hi! Thanks for helping me out. I'm kinda busy right now, but you\n"
									+ "can always come back later.”\n\n"
									+ "She turns away and continues to chat with Hal.");
						}
					}
					
					else if(button1.getText().equals("CAN I HAVE A FILTER?")) {
						printText(textContent, "“Wanna buy a filter? I refurbished them, myself. Didn’t\n"
								+ "think anyone would need them until the new jellyfish craze.”\n"
								+ "She sighs. “They’re $45 each.”\n\n"
								+ "You explain that you don’t have that kind of money. She shrugs.\n\n"
								+ "“I can’t lower the price any more than that. It’s already half\n"
								+ "off the normal cost.”");
					}
					
					else if(button1.getText().equals("SURE")) {
						if(!inv.hasItem("-$20")) {
							printText(textContent, "Fodor laughs. “You can’t buy macarons without money,\n"
									+ "you know! Come back once you can buy something, alright?”");
						}
						else {
							inv.removeItem(4); //remove 20 dollars
							inv.addItem("-Macaron Box", 5);
							invText.setText(inv.toString());
							printText(textContent, "“Here you go!” He takes the 20 dollars from your collar\n"
									+ "and slides you a box of macarons. They smell delicious, but\n"
									+ "most of them are made of chocolate. Alas! A trip to the vet\n"
									+ "is not worth the instant gratification.\n\n"
									+ "[MACARON BOX added to inventory.]\n\n"
									+ "You walk on back to the rest of the flea market.");
							
							if(!buttonPane.getChildren().contains(button1)) {
								buttonPane.getChildren().add(button1);
							}
							
							if(!buttonPane.getChildren().contains(button2)) {
								buttonPane.getChildren().add(button2);
							}
							
							if(!buttonPane.getChildren().contains(button3)) {
								buttonPane.getChildren().add(button3);
							}
							
							button1.setText("JELLYFISH FILTER STAND");
							button2.setText("MACARON STAND");
							
							if(!givenMacarons && !knowsHalinasName) {
								button3.setText("TALK TO NERVOUS LADY");
							}
							else if(!givenMacarons && knowsHalinasName) {
								button3.setText("TALK TO HALINA");
							}
							else {
								buttonPane.getChildren().remove(button3);
							}
						}
					}
					
					else if(button1.getText().equals("WHAT'S WRONG?")) {
						if(!hasTalkedBox) {
							printText(textContent, "“What’s wrong? Uh, nothing’s wrong. Hah. Why would you\n"
									+ "think anything is wrong?”\n\n"
									+ "She mutters to herself, “Gee, Halina, I can’t believe you’re so\n"
									+ "lonely that you’re talking to a dog.”\n\n"
									+ "You sort of just stare at her until she sighs. “Fine, fine. I...I\n"
									+ "want to be friends with Vic, but she’s just so much cooler than me!\n"
									+ "She always acts so aloof, like she can’t be bothered with anything,\n"
									+ "and I don’t even know if she’ll like me, I’m a mess compared to her,\n"
									+ "and--”\n\n"
									+ "She keeps talking and talking until you bark, trying to calm her\n"
									+ "down. She sighs.\n\n"
									+ "“I-I’m sorry, I just too shy to go up and just...talk to her.”");
							
							knowsHalinasName = true;
							
							button1.setText("CAN I HELP?");
							button2.setText("SORRY, I CAN'T HELP YOU");
						}
						else {
							printText(textContent, "“O-Oh, you know. Vic. Talking. Can’t do it.” Hal laughs\n"
									+ "nervously. “God, I’m terrible at doing this.”");
						}
					}
					
					else if(button1.getText().equals("CAN I HELP?")) {
						hasTalkedBox = true;
						printText(textContent, "“Um…” Hal chews nervously on her nails. “Oh! I have an\n"
								+ "idea! Um, can you run down to the macaron stand and buy a box of\n"
								+ "macarons? You can give it to me, and I’ll w-write a note or\n"
								+ "something. It’s easier for me to write stuff th-than to...\n"
								+ "y’know, say things.”\n\n"
								+ "You bark, complaining that you don’t have any money to buy\n"
								+ "things. She winces.\n\n"
								+ "“I’m s-sorry. I-I’d give you money, but, um...I forgot my wallet\n"
								+ "at home. Maybe you could f-find someone else to give some to you?\n"
								+ "A-Anyways, if you do get your p-paws on some macarons, please give\n"
								+ "it back to m-me.\n\n"
								+ "I-Is there anything else you wanted t-to talk to me about?”");
						
						if(inv.hasItem("-Macaron Box"))
							button1.setText("GIVE BOX");
						else
							button1.setText("WHAT'S WRONG?");
						button2.setText("NEVERMIND");
						buttonPane.getChildren().remove(button3);
					}
					
					else if(button1.getText().equals("GIVE BOX")) {
						printText(textContent, "“O-Oh! You got it!” She snatches it from your paws, takes\n"
								+ "a pencil and a crumpled piece of paper out of her pocket, and\n"
								+ "starts scribbling fervently. After a few minutes, she hands\n"
								+ "back the letter with the box. The letter has been folded up several\n"
								+ "times, and you don’t exactly have the thumbs to open it.\n\n"
								+ "[MACARON BOX W/ LETTER added to inventory.]\n\n"
								+ "“C-Can you p-please give this to Vic? T-Thank you so much\n"
								+ "for everything. Is there anything else you wanted to t-talk\n"
								+ "to me about?”");
						
						inv.addItem("-Macaron Box\nw/ Letter", 6);
						inv.removeItem(5);
						invText.setText(inv.toString());
						
						if(!inv.hasItem("-Macaron Box"))
							button1.setText("WHAT'S WRONG?");
						else
							button1.setText("GIVE BOX");
						button2.setText("NEVERMIND");
						buttonPane.getChildren().remove(button3);
					}
				}
			}
		});
		
		//BUTTON 2 COMMANDS
		
		button2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button2.getText().equals("MACARON STAND")) {
						printText(textContent, "You walk over to the macaron stand, where a man with\n"
								+ "the nametag “CHEF FODOR” is cooking up some delicious\n"
								+ "macarons. He greets you with an air of generosity and\n"
								+ "kindness.\n\n"
								+ "“Hello! We are selling our macarons at a discounted price\n"
								+ "for an end-of-day sale. Twenty dollars for a box! Would\n"
								+ "you like to purchase some?”");
						
						button1.setText("SURE");
						button2.setText("NO THANKS");
						
						if (buttonPane.getChildren().contains(button3)){
							buttonPane.getChildren().remove(button3);
						}
					}
					
					else if(button2.getText().equals("HOW ARE YOU?")) {
						printText(textContent, "Vic glances briefly towards the woman trying to hide\n"
								+ "behind a stall. “Hmm. This woman has been visiting the flea\n"
								+ "market for the past week. I think she wants to talk to me,\n"
								+ "but I can’t really leave my stand.”\n\n"
								+ "She shrugs. “If she wants to talk, she’ll have to come to\n"
								+ "me. It’s a shame--she looks like she’d have some interesting\n"
								+ "things to say.”");
					}
					
					else if(button2.getText().equals("NO THANKS")) {
						printText(textContent, "“Alright, but don’t forget--this sale won’t last forever!”\n\n"
								+ "You head on back to the rest of the flea market.");
						
						if(!buttonPane.getChildren().contains(button1)) {
							buttonPane.getChildren().add(button1);
						}
						
						if(!buttonPane.getChildren().contains(button2)) {
							buttonPane.getChildren().add(button2);
						}
						
						if(!buttonPane.getChildren().contains(button3)) {
							buttonPane.getChildren().add(button3);
						}
						
						button1.setText("JELLYFISH FILTER STAND");
						button2.setText("MACARON STAND");
						
						if(!givenMacarons && !knowsHalinasName) {
							button3.setText("TALK TO NERVOUS LADY");
						}
						else if(!givenMacarons && knowsHalinasName) {
							button3.setText("TALK TO HALINA");
						}
						else {
							buttonPane.getChildren().remove(button3);
						}
						
					}
					
					else if(button2.getText().equals("GIVE MACARONS")) {
						if(inv.hasItem("-Macaron Box\nw/ Letter")) {
							inv.removeItem(6);
							inv.addItem("-Jellyfish\nFilters", 7);
							invText.setText(inv.toString());
							givenMacarons = true;

							printText(textContent, "Vic’s eyes widen as you slide the box onto the table.\n"
									+ "“For me?” She picks up the letter and starts reading it. When\n"
									+ "she finishes, she glances over towards Hal, who just nervously\n"
									+ "waves at her. Hal is visibly sweating.\n\n"
									+ "“Excuse me for a second.” Vic gets up from her chair and starts\n"
									+ "walking towards Hal. The two talk for a few minutes before walking\n"
									+ "back to the stand. Vic smiles at you.\n\n"
									+ "“Hey, I’m feeling a little generous today--if you want, feel free\n"
									+ "to take a few jellyfish filters. I’m serious. You’ve introduced\n"
									+ "me to an interesting and wonderful person.”\n\n"
									+ "Hal laughs, scratching the back of her neck. “Turns out, we both\n"
									+ "really like Star Wars.”\n\n"
									+ "You gladly take some filters from the table. Score! Now you can\n"
									+ "get those treats at the pet shop! You walk away from the stand\n"
									+ "as Vic and Hal continue to talk.\n\n"
									+ "[JELLYFISH FILTERS added to inventory]");
							
							button1.setText("JELLYFISH FILTER STAND");
							button2.setText("MACARON STAND");
							if(!givenMacarons && !knowsHalinasName) {
								button3.setText("TALK TO NERVOUS LADY");
							}
							else if(!givenMacarons && knowsHalinasName) {
								button3.setText("TALK TO HALINA");
							}
							else {
								buttonPane.getChildren().remove(button3);
							}
							
						}
						else if(inv.hasItem("-Macaron Box")) {
							printText(textContent, "Not now--you need Hal to write a letter with the macarons!");
						}
						
					}
					
					else if(button2.getText().equals("SORRY, I CAN'T HELP YOU")) {
						
						printText(textContent, "“O-Oh, that’s alright. I-I don’t even know if I can be\n"
								+ "helped. I-Is there anything else you wanted to talk to\n"
								+ "me about?”");
						
						if(!inv.hasItem("-Macaron Box"))
							button1.setText("WHAT'S WRONG?");
						else
							button1.setText("GIVE BOX");
						button2.setText("NEVERMIND");
					}
					
					else if(button2.getText().equals("NEVERMIND")) {
						printText(textContent, "You walk away to go do something else in the flea market.");
						
						if(!givenMacarons) {
								buttonPane.getChildren().add(button3);
							}
						button1.setText("JELLYFISH FILTER STAND");
						button2.setText("MACARON STAND");
						if(!givenMacarons && !knowsHalinasName) {
							button3.setText("TALK TO NERVOUS LADY");
						}
						else if(!givenMacarons && knowsHalinasName) {
							button3.setText("TALK TO HALINA");
						}
						else {
							buttonPane.getChildren().remove(button3);
						}
					}
				}
			}
		});
		
		//BUTTON 3 COMMANDS
		
		button3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!mapIsOpen && textDone) {
					if(button3.getText().equals("TALK TO NERVOUS LADY")) {
						printText(textContent, "The lady looks at you as you approach. “H-Hi. Um. What’s\n"
								+ "up? Er, don’t mind me, I’m just...hanging out. You know.”\n\n"
								+ "She laughs nervously, wringing her hands together. She keeps\n"
								+ "glancing over at Vic’s stand.");
						
						if(!inv.hasItem("-Macaron Box"))
							button1.setText("WHAT'S WRONG?");
						else
							button1.setText("GIVE BOX");
						button2.setText("NEVERMIND");
						buttonPane.getChildren().remove(button3);
					}
					
					else if(button3.getText().equals("TALK TO HALINA")) {
						printText(textContent, "Halina looks at you as you approach. “H-Hi. Um. What’s\n"
								+ "up? Er, don’t mind me, I’m just...hanging out. You know.”\n\n"
								+ "She laughs nervously, wringing her hands together. She keeps\n"
								+ "glancing over at Vic’s stand.");
						
						if(!inv.hasItem("-Macaron Box"))
							button1.setText("WHAT'S WRONG?");
						else
							button1.setText("GIVE BOX");
						button2.setText("NEVERMIND");
						buttonPane.getChildren().remove(button3);
					}
					
					else if(button3.getText().equals("NEVERMIND")) {
						printText(textContent, "You walk away to go do something else in the flea market.");
						
						if(!buttonPane.getChildren().contains(button2)) {
							buttonPane.getChildren().remove(button3);
							buttonPane.getChildren().add(button2);
							if(!givenMacarons) {
								buttonPane.getChildren().add(button3);
							}
						}
						button1.setText("JELLYFISH FILTER STAND");
						button2.setText("MACARON STAND");
						if(!givenMacarons && !knowsHalinasName) {
							button3.setText("TALK TO NERVOUS LADY");
						}
						else if(!givenMacarons && knowsHalinasName) {
							button3.setText("TALK TO HALINA");
						}
						else {
							buttonPane.getChildren().remove(button3);
						}
					}
				}
			}
		});
	}
	
	public static void endGame() { //finish the game
		System.exit(1);
	}
}