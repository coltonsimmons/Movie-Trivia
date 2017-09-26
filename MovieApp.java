import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import AddOns.Actor;
import AddOns.AudioRoom;
import AddOns.Background;
import AddOns.BackstageScreenshots;
import AddOns.Countdown;
import AddOns.GameOver;
import AddOns.Lives;
import AddOns.Quote;
import AddOns.Winner;
import app.*;
import multimedia.AbstractMultimediaApp;


/**
 * Class used to create and run the main movie application.
 * 
 * @author Colton Simmons
 *
 */
public class MovieApp extends AbstractMultimediaApp implements MouseListener{
  private int act = 1, mov = 1, sound = 1, quo = 1, screen = 1;
  private int random = 0;
  private int lives = 3;
  private int countdown = 9;
  private String mapped;
  private boolean painted = false;
  
  //Questions and Answers
  private ArrayList<String> screenlevel1;
  private ArrayList<String> quotelevel1;
  private ArrayList<String> soundlevel1;
  private ArrayList<String> actorlevel1;
  
  private ArrayList<String> quotelevel2;
  private ArrayList<String> soundlevel2;
  private ArrayList<String> screenlevel2;
  private ArrayList<String> actorlevel2;
  
  private ArrayList<String> quotelevel3;
  private ArrayList<String> screenlevel3;
  private ArrayList<String> soundlevel3;
  private ArrayList<String> actorlevel3;


  private ArrayList<String> quotelevel4;
  private ArrayList<String> soundlevel4;
  private ArrayList<String> screenlevel4;
  private ArrayList<String> actorlevel4;

  
  private ArrayList<String> randomanswers;
  private ArrayList<String> randomactors;
  
  private HashMap<String, String> actoranswers;
  private HashMap<String, String> screenanswers;
  private HashMap<String, String> soundanswers;
  private HashMap<String, String> quoteanswers;
  private HashMap<String, String> actorsmovies;
  
  private ArrayList<JLabel> order;
  
  JPanel theatre;
  JPanel backstage;
  JPanel soundroom;
  JPanel quote;
  JPanel lifecount;
  JPanel actor;
  JPanel counter;
  JPanel bg;
  JPanel live;
  JPanel gameover;
  JPanel winner;
  JLabel actor1;
  JLabel actor2;
  JLabel actor3;
  JLabel actor4;
  JLabel movie1;
  JLabel movie2;
  JLabel movie3;
  JLabel movie4;
  JLabel sound1;
  JLabel sound2;
  JLabel sound3;
  JLabel sound4;
  JLabel quote1;
  JLabel quote2;
  JLabel quote3;
  JLabel quote4;
  JLabel screen1;
  JLabel screen2;
  JLabel screen3;
  JLabel screen4;

  JLabel answer1;
  JLabel answer2;
  JLabel answer3;
  JLabel answer4;

  
  JLabel currentlabel;
  
  JTextArea quotedmovie;
  JTextArea actorhelp;
  
  //Stuff I'll sort later
  String currentOp;
  BufferedImage currentPic;
  String currentSound;
  Random rand = new Random();
  Random list = new Random();
  URL url;
  AudioInputStream audioIn;
  Clip clip;
  Clip backgroundmusic;
  Timer t;

  
  /**
   * Method used to instantiate and begin the execution of the movie trivia program.
   */
  public void init()
  {    
    //What to do when the timer is done
    ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if(clip != null && clip.isRunning()) return;
        if(countdown == 0){
          if(lives-1 == 0){
            gameOver();
            ((Lives) live).setImage(3);
            return;
          }
          lives--;
          ((Lives) live).setImage(lives);
          theatre.removeAll();
          theatre.add(bg);
          theatre.repaint();
          countdown = 9;
          ((Countdown) counter).setImage(9);
        }
        else{
          countdown--;
          ((Countdown) counter).setImage(countdown);
          counter.repaint();
        }
        
        }
      };
      t = new Timer(1000, taskPerformer);
      
      
    InputStream stream;   
    JPanel contentPane;
 
    //Initialize arraylists and populate the random answers
    randomanswers = new ArrayList<String>();
    screenanswers = new HashMap<String, String>();
    soundanswers = new HashMap<String, String>();
    quoteanswers = new HashMap<String, String>();
    order = new ArrayList<JLabel>();
    
    quotelevel1 = new ArrayList<String>();
    quotelevel2 = new ArrayList<String>();
    quotelevel3 = new ArrayList<String>();
    quotelevel4 = new ArrayList<String>();

    soundlevel1 = new ArrayList<String>();
    soundlevel2 = new ArrayList<String>();
    soundlevel3 = new ArrayList<String>();
    soundlevel4 = new ArrayList<String>();
    
    
    actorlevel1 = new ArrayList<String>();
    actorlevel2 = new ArrayList<String>();
    actorlevel3 = new ArrayList<String>();
    actorlevel4 = new ArrayList<String>();

    
    screenlevel1 = new ArrayList<String>();
    screenlevel2 = new ArrayList<String>();
    screenlevel3 = new ArrayList<String>();
    screenlevel4 = new ArrayList<String>();
    
    actoranswers = new HashMap<String, String>();
    randomactors = new ArrayList<String>();
    actorsmovies = new HashMap<String, String>();

    populate();

    //Set up answers for later
    answer1 = new JLabel();
    answer1.setBounds(165, 550, 200, 50);
    answer1.setBackground(Color.GRAY);
    answer1.setOpaque(true);
    answer1.addMouseListener(this);

    answer2 = new JLabel();
    answer2.setBounds(390,550, 200, 50);
    answer2.setBackground(Color.GRAY);
    answer2.setOpaque(true);
    answer2.addMouseListener(this);

    answer3 = new JLabel();
    answer3.setBounds(615, 550, 200, 50);
    answer3.setBackground(Color.GRAY);
    answer3.setOpaque(true);
    answer3.addMouseListener(this);

    answer4 = new JLabel();
    answer4.setBounds(840, 550, 200, 50);
    answer4.setBackground(Color.GRAY);
    answer4.setOpaque(true);
    answer4.addMouseListener(this);

    //Add to array to randomize later
    order.add(answer1);
    order.add(answer2);
    order.add(answer3);
    order.add(answer4);
    
    //Set up actor labels
    actor1 = new JLabel("Actor 1", SwingConstants.CENTER);
    actor1.setBounds(175, 550, 120, 50);
    actor1.setBackground(Color.GRAY);
    actor1.setOpaque(true);
    actor1.addMouseListener(this);
    
    actor2 = new JLabel("Actor 2", SwingConstants.CENTER);
    actor2.setBounds(175,470, 120, 50);
    actor2.setBackground(Color.GRAY);
    actor2.setOpaque(true);
    actor2.addMouseListener(this);
    
    actor3 = new JLabel("Actor 3", SwingConstants.CENTER);
    actor3.setBounds(175, 390, 120, 50);
    actor3.setBackground(Color.GRAY);
    actor3.setOpaque(true);
    actor3.addMouseListener(this);
    
    actor4 = new JLabel("Actor 4", SwingConstants.CENTER);
    actor4.setBounds(175, 310, 120, 50);
    actor4.setBackground(Color.GRAY);
    actor4.setOpaque(true);
    actor4.addMouseListener(this);
    
    //JTextArea for quotes
    quotedmovie = new JTextArea();
    quotedmovie.setBounds(620, 100, 500, 300);
    quotedmovie.setFont(quotedmovie.getFont().deriveFont(24f));
    quotedmovie.setOpaque(false);
    quotedmovie.setLineWrap(true);
    quotedmovie.setWrapStyleWord(true);
    
    actorhelp = new JTextArea();
    actorhelp.setBounds(630, 300, 400, 100);
    actorhelp.setFont(actorhelp.getFont().deriveFont(24f));
    actorhelp.setWrapStyleWord(true);
    actorhelp.setLineWrap(true);
    actorhelp.setOpaque(false);

    
    

    
    //Set up Sound Bites
    sound1 = new JLabel("Sound 1", SwingConstants.CENTER);
    sound1.setBounds(380, 550, 120, 50);
    sound1.setBackground(Color.GRAY);
    sound1.setOpaque(true);
    sound1.addMouseListener(this);
    
    sound2 = new JLabel("Sound 2", SwingConstants.CENTER);
    sound2.setBounds(380,470, 120, 50);
    sound2.setBackground(Color.GRAY);
    sound2.setOpaque(true);
    sound2.addMouseListener(this);
    
    sound3 = new JLabel("Sound 3", SwingConstants.CENTER);
    sound3.setBounds(380, 390, 120, 50);
    sound3.setBackground(Color.GRAY);
    sound3.setOpaque(true);
    sound3.addMouseListener(this);
    
    sound4 = new JLabel("Sound 4", SwingConstants.CENTER);
    sound4.setBounds(380, 310, 120, 50);
    sound4.setBackground(Color.GRAY);
    sound4.setOpaque(true);
    sound4.addMouseListener(this);
    
    //Set up Quotes
    quote1 = new JLabel("Quote 1", SwingConstants.CENTER);
    quote1.setBounds(630, 550, 120, 50);
    quote1.setBackground(Color.GRAY);
    quote1.addMouseListener(this);
    quote1.setOpaque(true);
    
    quote2 = new JLabel("Quote 2", SwingConstants.CENTER);
    quote2.setBounds(630,470, 120, 50);
    quote2.setBackground(Color.GRAY);
    quote2.setOpaque(true);
    quote2.addMouseListener(this);
    
    quote3 = new JLabel("Quote 3", SwingConstants.CENTER);
    quote3.setBounds(630, 390, 120, 50);
    quote3.setBackground(Color.GRAY);
    quote3.setOpaque(true);
    quote3.addMouseListener(this);
    
    quote4 = new JLabel("Quote 4", SwingConstants.CENTER);
    quote4.setBounds(630, 310, 120, 50);
    quote4.setBackground(Color.GRAY);
    quote4.setOpaque(true);
    quote4.addMouseListener(this);
    
  //Set up screenshots
    screen1 = new JLabel("Screenshot 1", SwingConstants.CENTER);
    screen1.setBounds(850, 550, 120, 50);
    screen1.setBackground(Color.GRAY);
    screen1.setOpaque(true);
    screen1.addMouseListener(this);
    
    screen2 = new JLabel("Screenshot 2", SwingConstants.CENTER);
    screen2.setBounds(850,470, 120, 50);
    screen2.setBackground(Color.GRAY);
    screen2.setOpaque(true);
    screen2.addMouseListener(this);
    
    screen3 = new JLabel("Screenshot 3", SwingConstants.CENTER);
    screen3.setBounds(850, 390, 120, 50);
    screen3.setBackground(Color.GRAY);
    screen3.setOpaque(true);
    screen3.addMouseListener(this);
    
    screen4 = new JLabel("Screenshot 4", SwingConstants.CENTER);
    screen4.setBounds(850, 310, 120, 50);
    screen4.setBackground(Color.GRAY);
    screen4.setOpaque(true);
    screen4.addMouseListener(this);
    
    counter = new Countdown();
    counter.setBounds(20, 20, 100, 100);
    counter.setOpaque(false);
    
    contentPane = (JPanel)rootPaneContainer.getContentPane();
    contentPane.setSize(1200, 750);
    theatre = new JPanel(null);
    theatre.setSize(1200, 750);
    
    

      
    
   
    bg = new Background();
    bg.setBounds(0, 0, 1200, 750);
    bg.add(actor1);
    bg.add(actor2);
    bg.add(actor3);
    bg.add(actor4);
    bg.add(sound1);
    bg.add(sound2);
    bg.add(sound3);
    bg.add(sound4);
    bg.add(quote1);
    bg.add(quote2);
    bg.add(quote3);
    bg.add(quote4);
    bg.add(screen1);
    bg.add(screen2);
    bg.add(screen3);
    bg.add(screen4);

    backstage = new BackstageScreenshots();
    backstage.setBounds(0,0, 1200, 750);
    
    soundroom = new AudioRoom();
    soundroom.setBounds(0, 0, 1200, 750);
    
    quote = new Quote();
    quote.setBounds(0, 0, 1200, 750);
    quote.add(quotedmovie);
    
    actor = new Actor();
    actor.setBounds(0, 0, 1200, 750);
    actor.add(actorhelp);
    
    counter = new Countdown();
    counter.setBounds(0, 0, 100, 100);
    
    live = new Lives();
    live.setBounds(1030, 30, 80, 80);
    
    gameover = new GameOver();
    gameover.setBounds(0, 0, 1200, 750);
    
    winner = new Winner();
    winner.setBounds(0,0,1200,750);
    
    bg.add(live);
    theatre.add(bg);
 
    contentPane.add(theatre);
    contentPane.setVisible(true);
    
    try {
      // Open an audio input stream.
      url = this.getClass().getClassLoader().getResource("Random/litegroove.wav");
      audioIn = AudioSystem.getAudioInputStream(url);
      // Get a sound clip resource.
      backgroundmusic = AudioSystem.getClip(null);
      // Open audio clip and load samples from the audio input stream.
      backgroundmusic.open(audioIn);
      backgroundmusic.start();
    }
    catch (UnsupportedAudioFileException ex) {
      ex.printStackTrace();
   } catch (IOException ex) {
      ex.printStackTrace();
   } catch (LineUnavailableException ex) {
      ex.printStackTrace();
   }
    
    
    
    backgroundmusic.loop(Clip.LOOP_CONTINUOUSLY);

    
  }

  @Override
  /**
   * Method used to handle question and answer clicks appropriately. 
   */
  public void mouseClicked(MouseEvent e)
  {
    ArrayList<String> ans = new ArrayList<String>();
    
    if(e.getSource() == screen1){
      if(screen != 1){
        return;
      }
      currentOp = "screen";
      currentlabel = screen1;
      theatre.remove(bg);
      
     int next = list.nextInt(screenlevel1.size());
      //In this case, set it to element 0
      ((BackstageScreenshots) backstage).setImage(screenlevel1.get(next));
      
      mapped = screenlevel1.get(next);
      random = rand.nextInt(4);

      
      order.get(random).setText(screenanswers.get(screenlevel1.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(screenanswers.get(screenlevel1.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      
      
      backstage.add(answer1);
      backstage.add(answer2);
      backstage.add(answer3);
      backstage.add(answer4);
      backstage.add(counter);
      theatre.add(backstage);
      theatre.repaint();
      t.start();
  }
    
    if(e.getSource() == screen2){
      if(screen != 2){
        return;
      }
      currentOp = "screen";
      currentlabel = screen2;
      theatre.remove(bg);
      
     int next = list.nextInt(screenlevel2.size());
      //In this case, set it to element 0
      ((BackstageScreenshots) backstage).setImage(screenlevel2.get(next));
      
      mapped = screenlevel2.get(next);
      random = rand.nextInt(4);

      
      order.get(random).setText(screenanswers.get(screenlevel2.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(screenanswers.get(screenlevel2.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      
      
      backstage.add(answer1);
      backstage.add(answer2);
      backstage.add(answer3);
      backstage.add(answer4);
      backstage.add(counter);
      theatre.add(backstage);
      theatre.repaint();
      t.start();
  }
    
    if(e.getSource() == screen3){
      if(screen != 3){
        return;
      }
      currentOp = "screen";
      currentlabel = screen3;
      theatre.remove(bg);
      
     int next = list.nextInt(screenlevel2.size());
      //In this case, set it to element 0
      ((BackstageScreenshots) backstage).setImage(screenlevel3.get(next));
      
      mapped = screenlevel3.get(next);
      random = rand.nextInt(4);

      
      order.get(random).setText(screenanswers.get(screenlevel3.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(screenanswers.get(screenlevel3.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      
      
      backstage.add(answer1);
      backstage.add(answer2);
      backstage.add(answer3);
      backstage.add(answer4);
      backstage.add(counter);
      theatre.add(backstage);
      theatre.repaint();
      t.start();
  }
    
    
    if(e.getSource() == screen4){
      if(screen != 4){
        return;
      }
      currentOp = "screen";
      currentlabel = screen4;
      theatre.remove(bg);
      
     int next = list.nextInt(screenlevel2.size());
      //In this case, set it to element 0
      ((BackstageScreenshots) backstage).setImage(screenlevel4.get(next));
      
      mapped = screenlevel4.get(next);
      random = rand.nextInt(4);

      
      order.get(random).setText(screenanswers.get(screenlevel4.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(screenanswers.get(screenlevel4.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      
      
      backstage.add(answer1);
      backstage.add(answer2);
      backstage.add(answer3);
      backstage.add(answer4);
      backstage.add(counter);
      theatre.add(backstage);
      theatre.repaint();
      t.start();
  }
   
    
    if(e.getSource() == sound1){
      if(sound != 1){
        return;
      }
      int next = list.nextInt(soundlevel1.size());

      currentOp = "sound";
      currentlabel = sound1;
      currentSound = soundlevel1.get(next);
     
      random = rand.nextInt(4);
      order.get(random).setText(soundanswers.get(soundlevel1.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(soundanswers.get(soundlevel1.get(next)));
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      
      soundroom.add(answer1);
      soundroom.add(answer2);
      soundroom.add(answer3);
      soundroom.add(answer4);
      soundroom.add(counter);
      theatre.remove(bg);
      theatre.add(soundroom);
      theatre.repaint();   
      painted = true;
      
      try {
        // Open an audio input stream.
        backgroundmusic.stop();
        System.out.println("Sounds/" + soundlevel1.get(next));
        url = this.getClass().getClassLoader().getResource("Sounds/" + soundlevel1.get(next));
        audioIn = AudioSystem.getAudioInputStream(url);
        // Get a sound clip resource.
        clip = AudioSystem.getClip(null);
        // Open audio clip and load samples from the audio input stream.
        clip.open(audioIn);
        clip.start();
        while(!clip.isRunning()){
          
        }
        
       
        
     } catch (UnsupportedAudioFileException ex) {
        ex.printStackTrace();
     } catch (IOException ex) {
        ex.printStackTrace();
     } catch (LineUnavailableException ex) {
        ex.printStackTrace();
     }
      
     t.start();
      
      
    }
    
    if(e.getSource() == sound2){
      if(sound != 2){
        return;
      }
      int next = list.nextInt(soundlevel2.size());

      currentOp = "sound";
      currentlabel = sound2;
      currentSound = soundlevel2.get(next);
     
      random = rand.nextInt(4);
      order.get(random).setText(soundanswers.get(soundlevel2.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(soundanswers.get(soundlevel2.get(next)));
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      
      soundroom.add(answer1);
      soundroom.add(answer2);
      soundroom.add(answer3);
      soundroom.add(answer4);
      soundroom.add(counter);
      theatre.remove(bg);
      theatre.add(soundroom);
      theatre.repaint();   
      
      try {
        // Open an audio input stream.
        backgroundmusic.stop();
        System.out.println("Sounds/" + soundlevel2.get(next));
        url = this.getClass().getClassLoader().getResource("Sounds/" + soundlevel2.get(next));
        audioIn = AudioSystem.getAudioInputStream(url);
        // Get a sound clip resource.
        clip = AudioSystem.getClip(null);
        // Open audio clip and load samples from the audio input stream.
        clip.open(audioIn);
        clip.start();
        while(clip.isActive()){
          t.restart();
        }
     } catch (UnsupportedAudioFileException ex) {
        ex.printStackTrace();
     } catch (IOException ex) {
        ex.printStackTrace();
     } catch (LineUnavailableException ex) {
        ex.printStackTrace();
     }
     
      
      t.start();
      
    }
    
    if(e.getSource() == sound3){
      if(sound != 3){
        return;
      }
      int next = list.nextInt(soundlevel3.size());

      currentOp = "sound";
      currentlabel = sound3;
      currentSound = soundlevel3.get(next);
     
      random = rand.nextInt(4);
      order.get(random).setText(soundanswers.get(soundlevel3.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(soundanswers.get(soundlevel3.get(next)));
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      
      soundroom.add(answer1);
      soundroom.add(answer2);
      soundroom.add(answer3);
      soundroom.add(answer4);
      soundroom.add(counter);
      theatre.remove(bg);
      theatre.add(soundroom);
      theatre.repaint();   
      
      try {
        // Open an audio input stream.
        backgroundmusic.stop();
        url = this.getClass().getClassLoader().getResource("Sounds/" + soundlevel3.get(next));
        audioIn = AudioSystem.getAudioInputStream(url);
        // Get a sound clip resource.
        clip = AudioSystem.getClip(null);
        // Open audio clip and load samples from the audio input stream.
        clip.open(audioIn);
        clip.start();
        while(clip.isActive()){
          t.restart();
        }
     } catch (UnsupportedAudioFileException ex) {
        ex.printStackTrace();
     } catch (IOException ex) {
        ex.printStackTrace();
     } catch (LineUnavailableException ex) {
        ex.printStackTrace();
     }
     
      
      t.start();
    }
    
    if(e.getSource() == sound4){
      if(sound != 4){
        return;
      }
      int next = list.nextInt(soundlevel4.size());

      currentOp = "sound";
      currentlabel = sound4;
      currentSound = soundlevel4.get(next);
     
      random = rand.nextInt(4);
      order.get(random).setText(soundanswers.get(soundlevel4.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(soundanswers.get(soundlevel4.get(next)));
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      
      soundroom.add(answer1);
      soundroom.add(answer2);
      soundroom.add(answer3);
      soundroom.add(answer4);
      soundroom.add(counter);
      theatre.remove(bg);
      theatre.add(soundroom);
      theatre.repaint();   
      
      try {
        // Open an audio input stream.
        backgroundmusic.stop();
        System.out.println("Sounds/" + soundlevel3.get(next));
        url = this.getClass().getClassLoader().getResource("Sounds/" + soundlevel4.get(next));
        audioIn = AudioSystem.getAudioInputStream(url);
        // Get a sound clip resource.
        clip = AudioSystem.getClip(null);
        // Open audio clip and load samples from the audio input stream.
        clip.open(audioIn);
        clip.start();
        while(clip.isActive()){
          t.restart();
        }
     } catch (UnsupportedAudioFileException ex) {
        ex.printStackTrace();
     } catch (IOException ex) {
        ex.printStackTrace();
     } catch (LineUnavailableException ex) {
        ex.printStackTrace();
     }
     
      
      t.start();
    }
    
    //Quote questions
    if(e.getSource() == quote1){
      if(quo != 1){
        return;
      }
      int next = list.nextInt(quotelevel1.size());

      currentOp = "quote";
      currentlabel = quote1;
      mapped = quotelevel1.get(next);
      random = rand.nextInt(4);
      

      order.get(random).setText(quoteanswers.get(quotelevel1.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(quoteanswers.get(quotelevel1.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size());
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      theatre.remove(bg);
      quotedmovie.setText(quotelevel1.get(next));
      quote.add(answer1);
      quote.add(answer2);
      quote.add(answer3);
      quote.add(answer4);
      quote.add(counter);
      theatre.add(quote);
      theatre.repaint();
      
      t.start();
    }
    
    if(e.getSource() == quote2){
      if(quo != 2){
        return;
      }
      int next = list.nextInt(quotelevel2.size());

      currentOp = "quote";
      currentlabel = quote2;
      mapped = quotelevel2.get(next);
      random = rand.nextInt(4);
      

      order.get(random).setText(quoteanswers.get(quotelevel2.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(quoteanswers.get(quotelevel2.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      theatre.remove(bg);
      quotedmovie.setText(quotelevel2.get(next));
      quote.add(answer1);
      quote.add(answer2);
      quote.add(answer3);
      quote.add(answer4);
      quote.add(counter);
      theatre.add(quote);
      theatre.repaint();
      
      t.start();

    }
    
    if(e.getSource() == quote3){
      if(quo != 3){
        return;
      }
      int next = list.nextInt(quotelevel3.size());

      currentOp = "quote";
      currentlabel = quote3;
      mapped = quotelevel3.get(next);
      random = rand.nextInt(4);
      

      order.get(random).setText(quoteanswers.get(quotelevel3.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(quoteanswers.get(quotelevel3.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      theatre.remove(bg);
      quotedmovie.setText(quotelevel3.get(next));
      quote.add(answer1);
      quote.add(answer2);
      quote.add(answer3);
      quote.add(answer4);
      quote.add(counter);
      theatre.add(quote);
      theatre.repaint();
      t.start();

    }
    
    if(e.getSource() == quote4){
      if(quo != 4){
        return;
      }
      int next = list.nextInt(quotelevel4.size());

      currentOp = "quote";
      currentlabel = quote4;
      mapped = quotelevel4.get(next);
      random = rand.nextInt(4);
      

      order.get(random).setText(quoteanswers.get(quotelevel4.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(quoteanswers.get(quotelevel4.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomanswers.size());
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomanswers.get(x)) && order.get(random).getText() != randomanswers.get(x)){
            ans.add(randomanswers.get(x));
            order.get(i).setText(randomanswers.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
      theatre.remove(bg);
      quotedmovie.setText(quotelevel4.get(next));
      quote.add(answer1);
      quote.add(answer2);
      quote.add(answer3);
      quote.add(answer4);
      quote.add(counter);
      theatre.add(quote);
      theatre.repaint();
      t.start();

      

    }
    

    if(e.getSource() == actor1){
      if(act != 1){
        return;
      }
      theatre.remove(bg);
  
      currentlabel = actor1;
      currentOp = "actor";
      
      int next = list.nextInt(actorlevel1.size());

      mapped = actorlevel1.get(next);
      ((Actor) actor).setImage(actorlevel1.get(next)); 
    //  quotedmovie.setText(quotelevel1.get(0));
      
     
      actorhelp.setText(actorsmovies.get(actorlevel1.get(next)));
      actor.add(answer1);
      actor.add(answer2);
      actor.add(answer3);
      actor.add(answer4);
      actor.add(counter);
      
       
      random = rand.nextInt(4);
      order.get(random).setText(actoranswers.get(actorlevel1.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(actoranswers.get(actorlevel1.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomactors.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomactors.get(x)) && order.get(random).getText() != randomactors.get(x)){
            ans.add(randomactors.get(x));
            order.get(i).setText(randomactors.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
  
      theatre.add(actor);
      theatre.repaint();
      t.start();
      
    }
    
    if(e.getSource() == actor2){
      if(act != 2){
        return;
      }
      theatre.remove(bg);
      currentlabel = actor2;
      currentOp = "actor";
      
      int next = list.nextInt(actorlevel2.size());

      mapped = actorlevel2.get(next);
      ((Actor) actor).setImage(actorlevel2.get(next)); 
      
     
      actorhelp.setText(actorsmovies.get(actorlevel2.get(next)));
      actor.add(answer1);
      actor.add(answer2);
      actor.add(answer3);
      actor.add(answer4);
      actor.add(counter);
      
       
      random = rand.nextInt(4);
      order.get(random).setText(actoranswers.get(actorlevel2.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(actoranswers.get(actorlevel2.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomactors.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomactors.get(x)) && order.get(random).getText() != randomactors.get(x)){
            ans.add(randomactors.get(x));
            order.get(i).setText(randomactors.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
  
      theatre.add(actor);
      theatre.repaint();
      t.start();
      
    }
    
    if(e.getSource() == actor3){
      if(act != 3){
        return;
      }
      theatre.remove(bg);
      currentlabel = actor3;
      currentOp = "actor";
      
      int next = list.nextInt(actorlevel3.size());

      mapped = actorlevel3.get(next);
      ((Actor) actor).setImage(actorlevel3.get(next)); 
    //  quotedmovie.setText(quotelevel1.get(0));
      
     
      actorhelp.setText(actorsmovies.get(actorlevel3.get(next)));
      actor.add(answer1);
      actor.add(answer2);
      actor.add(answer3);
      actor.add(answer4);
      actor.add(counter);
      
       
      random = rand.nextInt(4);
      order.get(random).setText(actoranswers.get(actorlevel3.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(actoranswers.get(actorlevel3.get(next)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomactors.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomactors.get(x)) && order.get(random).getText() != randomactors.get(x)){
            ans.add(randomactors.get(x));
            order.get(i).setText(randomactors.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
  
      theatre.add(actor);
      theatre.repaint();
      t.start();
      
    }
    
    if(e.getSource() == actor4){
      if(act != 4){
        return;
      }
      theatre.remove(bg);
      currentlabel = actor4;
      currentOp = "actor";
      
      int next = list.nextInt(actorlevel4.size());

      mapped = actorlevel4.get(next);
      ((Actor) actor).setImage(actorlevel4.get(next)); 
    //  quotedmovie.setText(quotelevel1.get(0));
      
     
      actorhelp.setText(actorsmovies.get(actorlevel4.get(next)));
      actor.add(answer1);
      actor.add(answer2);
      actor.add(answer3);
      actor.add(answer4);
      actor.add(counter);
      
       
      random = rand.nextInt(4);
      order.get(random).setText(actoranswers.get(actorlevel4.get(next)));
      order.get(random).setHorizontalAlignment(SwingConstants.CENTER);
      ans.add(actoranswers.get(actorlevel4.get(0)));
      
      //Set the labels to random movie answers
      for(int i = 0; i < 4; i++){
        int x = rand.nextInt(randomactors.size() - 0) + 0;
        if(order.get(i).getText() == ""){
          if(!ans.contains(randomactors.get(x)) && order.get(random).getText() != randomactors.get(x)){
            ans.add(randomactors.get(x));
            order.get(i).setText(randomactors.get(x));
            order.get(i).setHorizontalAlignment(SwingConstants.CENTER);
          }
          else{
            i--;
          }
        }        
      }
  
      theatre.add(actor);
      theatre.repaint();
      t.start();
      
    }
    
    
    
    //Answers
    if(e.getSource() == answer1){
      t.stop();
      ((Countdown) counter).setImage(9);
      countdown = 9;
      
      if(clip != null && clip.isActive()){
        clip.stop();
      }
      if(currentOp == "screen"){
         
        if(screenanswers.get(mapped).equals(answer1.getText())){
          currentlabel.setBackground(Color.GREEN);
          screen++;

        }
        else{
          if(lives - 1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);
        }
        clearAnswers();
        
        theatre.remove(backstage);
        theatre.add(bg);
        theatre.repaint();
        
      }
      
      if(currentOp == "sound"){
        if(soundanswers.get(currentSound).equals(answer1.getText())){
          if(clip.isActive()){
            clip.stop();
          }
          currentlabel.setBackground(Color.GREEN);
          sound++;
        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          
          lives--;
          ((Lives) live).setImage(lives);

        }
        clearAnswers();

        theatre.remove(soundroom);
        theatre.add(bg);
        theatre.repaint();
      }
      
     
      
      if(currentOp == "actor"){
        if(actoranswers.get(mapped).equals(answer1.getText())){
          currentlabel.setBackground(Color.GREEN);
          act++;
        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);
        }
        theatre.remove(actor);
        theatre.add(bg);
        theatre.repaint();
      
      }
      
      if(currentOp == "quote"){
        if(quoteanswers.get(mapped).equals(answer1.getText())){
          currentlabel.setBackground(Color.GREEN);
          quo++;
        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        theatre.remove(quote);
        theatre.add(bg);
        theatre.repaint();
        
      }
     
      clearAnswers();
      checkWinner();
      backgroundmusic.start();

    }
    
    if(e.getSource() == answer2){
      t.stop();
      ((Countdown) counter).setImage(9);
      countdown = 9;

      if(clip != null && clip.isActive()){
        clip.stop();
      }
      if(currentOp == "screen"){
         
        if(screenanswers.get(mapped).equals(answer2.getText())){
          currentlabel.setBackground(Color.GREEN);
          screen++;

        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        clearAnswers();

        
        theatre.remove(backstage);
        theatre.add(bg);
        theatre.repaint();
        
      }
      
      if(currentOp == "sound"){
        if(soundanswers.get(currentSound).equals(answer2.getText())){
          if(clip.isActive()){
            clip.stop();
          }
          currentlabel.setBackground(Color.GREEN);
          sound++;
        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        clearAnswers();

        theatre.remove(soundroom);
        theatre.add(bg);
        theatre.repaint();
      }
      
     
      
      if(currentOp == "actor"){
        if(actoranswers.get(mapped).equals(answer2.getText())){
          currentlabel.setBackground(Color.GREEN);
          act++;
        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        theatre.remove(actor);
        theatre.add(bg);
        theatre.repaint();
      
      }
      
      if(currentOp == "quote"){
        if(quoteanswers.get(mapped).equals(answer2.getText())){
          currentlabel.setBackground(Color.GREEN);
          quo++;
        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        theatre.remove(quote);
        theatre.add(bg);
        theatre.repaint();
        
      }

      clearAnswers();
      checkWinner();
      backgroundmusic.start();

    }
    
    if(e.getSource() == answer3){
      t.stop();
      ((Countdown) counter).setImage(9);
      countdown = 9;

      if(clip != null && clip.isActive()){
        clip.stop();
      }
      if(currentOp == "screen"){
         
        if(screenanswers.get(mapped).equals(answer3.getText())){
          currentlabel.setBackground(Color.GREEN);
          screen++;

        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        clearAnswers();


        theatre.remove(backstage);
        theatre.add(bg);
        theatre.repaint();
        
      }
      
      if(currentOp == "sound"){
        if(soundanswers.get(currentSound).equals(answer3.getText())){
          if(clip.isActive()){
            clip.stop();
          }
          currentlabel.setBackground(Color.GREEN);
          sound++;
        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        clearAnswers();

        theatre.remove(soundroom);
        theatre.add(bg);
        theatre.repaint();
      }
      
     
      
      if(currentOp == "actor"){
        if(actoranswers.get(mapped).equals(answer3.getText())){
          currentlabel.setBackground(Color.GREEN);
          act++;
        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        theatre.remove(actor);
        theatre.add(bg);
        theatre.repaint();
      
      }
      
      if(currentOp == "quote"){
        if(quoteanswers.get(mapped).equals(answer3.getText())){
          currentlabel.setBackground(Color.GREEN);
          quo++;
        }
        else{
          if(lives -1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        theatre.remove(quote);
        theatre.add(bg);
        theatre.repaint();
        
      }
  
      clearAnswers();
      checkWinner();
      backgroundmusic.start();

    }
    
    if(e.getSource() == answer4){
      t.stop();
      ((Countdown) counter).setImage(9);
      countdown = 9;

      if(clip != null && clip.isActive()){
        clip.stop();
      }
      if(currentOp == "screen"){
         
        if(screenanswers.get(mapped).equals(answer4.getText())){
          currentlabel.setBackground(Color.GREEN);
          screen++;

        }
        else{
          if(lives-1 == 0){
            gameOver();
            return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        clearAnswers();

        
        theatre.remove(backstage);
        theatre.add(bg);
        theatre.repaint();
        
      }
      
      if(currentOp == "sound"){
        if(soundanswers.get(currentSound).equals(answer4.getText())){
          if(clip.isActive()){
            clip.stop();
          }
          currentlabel.setBackground(Color.GREEN);
          sound++;
        }
        else{
          if(lives -1 == 0){
              gameOver();
              return;

          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        clearAnswers();

        theatre.remove(soundroom);
        theatre.add(bg);
        theatre.repaint();
      }
      
     
      
      if(currentOp == "actor"){
        if(actoranswers.get(mapped).equals(answer4.getText())){
          currentlabel.setBackground(Color.GREEN);
          act++;
        }
        else{
          if(lives -1 ==0){
            gameOver();
            return;
          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        theatre.remove(actor);
        theatre.add(bg);
        theatre.repaint();
      
      }
      
      if(currentOp == "quote"){
        if(quoteanswers.get(mapped).equals(answer4.getText())){
          currentlabel.setBackground(Color.GREEN);
          quo++;
        }
        else{
          if(lives-1 == 0){
            gameOver();
            return;
          }
          lives--;
          ((Lives) live).setImage(lives);

        }
        theatre.remove(quote);
        theatre.add(bg);
        theatre.repaint();
        
      }
   
      clearAnswers();
      checkWinner();
      backgroundmusic.start();

    }
      
     
  }

  @Override
  public void mousePressed(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseReleased(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseEntered(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void mouseExited(MouseEvent e)
  {
    // TODO Auto-generated method stub
    
  }
  
  /**
   * Method used to populate every list. Generates the arraylists used and the random answers.
   */
  public void populate(){
    randomanswers.add("Pulp Fiction");
    randomanswers.add("Lion King");
    randomanswers.add("Meet the Parents");
    randomanswers.add("Ted");
    randomanswers.add("King Kong");
    randomanswers.add("Bee Movie");
    randomanswers.add("Titanic");
    randomanswers.add("Elf");
    randomanswers.add("Grease");
    randomanswers.add("Remember the Titans");
    randomanswers.add("Wizard of Oz");
    randomanswers.add("ET");
    randomanswers.add("Terminator");
    randomanswers.add("Django Unchained");
    randomanswers.add("The Big Lebowski");
    randomanswers.add("Predator");
    randomanswers.add("National Treasure");
    randomanswers.add("Taken");
    randomanswers.add("Inception");
    randomanswers.add("Interstellar");
    randomanswers.add("Back to the Future");
    randomanswers.add("Good Will Hunting");
    randomanswers.add("Full Metal Jacket");
    randomanswers.add("True Lies");
    randomanswers.add("Pulp Fiction");
    randomanswers.add("Trading Places");
    randomanswers.add("Die Hard");
    randomanswers.add("Inglorious Basterds");
    randomanswers.add("Donnie Darko");
    randomanswers.add("Dallas Buyers Club");
    randomanswers.add("Drive");
    randomanswers.add("Leon: The Professional");
    randomanswers.add("Snatch");
    randomanswers.add("Boondock Saints");
    randomanswers.add("The Birds");
    randomanswers.add("Se7en");
    randomanswers.add("Blazing Saddles");
    randomanswers.add("Eraserhead");
    randomanswers.add("Pale Rider");
    randomanswers.add("Taxi Driver");
    randomanswers.add("Tusk");
    randomanswers.add("Saw");
    randomanswers.add("The Godfather");
    randomanswers.add("Dirty Harry");
    randomanswers.add("The Sandlot");
    randomanswers.add("Forrest Gump");
    randomanswers.add("Jerry Maguire");
    randomanswers.add("Field of Dreams");
    randomanswers.add("Planet of the Apes");
    randomanswers.add("The Shining");
    randomanswers.add("Dirty Dancing");
    randomanswers.add("Gone with the Wind");
    randomanswers.add("Sixth Sense");
    randomanswers.add("Jaws");
    randomanswers.add("Top Gun");
    randomanswers.add("Goldfinger");
    randomanswers.add("Airplane");
    randomanswers.add("2001: A Space Odyssey");
    randomanswers.add("Poltergeist");
    randomanswers.add("Ash: Evil Dead");
    randomanswers.add("No Country for Old Men");
    randomanswers.add("A Few Good Men");
    randomanswers.add("Midnight Cowboy");
    randomanswers.add("The Graduate");
    randomanswers.add("Willy Wonka");
    randomanswers.add("Commando");
    randomanswers.add("Action Jackson");
    randomanswers.add("There Will Be Blood");
    randomanswers.add("The Usual Suspects");
    randomanswers.add("Aliens");
    randomanswers.add("Zoolander");
    randomanswers.add("Ferris Bueller's Day Off");
    randomanswers.add("The Princess Bride");
    randomanswers.add("Lord of the Rings");
    randomanswers.add("Toy Story");
    randomanswers.add("Braveheart");
    randomanswers.add("Rocky");
    randomanswers.add("Kill Bill");
    randomanswers.add("RoboCop");
    randomanswers.add("Rush Hour");
    randomanswers.add("Anchorman");
    randomanswers.add("They Live!");
    randomanswers.add("Cool Hand Luke");
    randomanswers.add("The Good, The Bad, The Ugly");
    randomanswers.add("Man on Fire");
    randomanswers.add("Apocalypse Now");
    randomanswers.add("Dead Poets Society");
    randomanswers.add("GoodFellas");







    


    
    randomactors.add("Lee Van Clief");
    randomactors.add("Clint Eastwood");
    randomactors.add("Michael Kaine");
    randomactors.add("Tom Sizemore");
    randomactors.add("Ving Rhames");
    randomactors.add("Tim Roth");
    randomactors.add("Guy Pearce");
    randomactors.add("J.K. Simmons");
    randomactors.add("Jared Leto");
    randomactors.add("Jean Reno");
    randomactors.add("Vinnie Jones");
    randomactors.add("Joe Pesci");
    randomactors.add("Edward James Olmos");
    randomactors.add("Richard Dreyfuss");
    randomactors.add("Ralph Fiennes");
    randomactors.add("Val Kilmer");
    randomactors.add("Christopher Lloyd");
    randomactors.add("Alan Arkin");
    randomactors.add("Michael Pena");



   

    /**
     * NOTE: From this point on, this is where you will add your own
     * quotes/sound bites/actor images/and movie stills. Do this at your own discretion
     * and make sure to read up and the Fair Use act before-hand. Educational and private use only.
     */
    
    //Quote levels
    quotelevel1.add("What is this?! A center for ants!?");
    quotelevel1.add("Life moves pretty fast. If you don’t stop and look around once in awhile, you could miss it.");
    quotelevel1.add("My name is Inigo Montoya. You killed my father, prepare to die.");
    quotelevel1.add("Why, you stuck up... half-witted... scruffy-looking... Nerf-herder!");
    quotelevel1.add("You shall not pass!");
    quotelevel1.add("Say hello to my little friend!");
    quotelevel1.add("You're a sad, strange little man.");
    quotelevel1.add("They may take our lives, but they'll never take our freedom!");
    quotelevel1.add("And you know what they call a quarter pounder with cheese? They call it a Royale with Cheese.");
    quotelevel1.add("Yo, Adrian!");
    
    quotelevel2.add("That'll do, pig.");
    quotelevel2.add("However, leave the limbs you've lost. They belong to me now.");
    quotelevel2.add("Dead or Alive. You're coming with me.");
    quotelevel2.add("I can see! I can see! I have...I have legs! Jesus, praise Jesus!");
    quotelevel2.add("Tonight, we dine in Hell!");
    quotelevel2.add("I break wind in your general direction.");
    quotelevel2.add("Do you understand the words that are coming out of my mouth?");
    quotelevel2.add("Momma says that alligators are so ornery because they got all them teeth but no toothbrush.");
    quotelevel2.add("Why don't you make like a tree and get out of here.");
    quotelevel2.add("60% of the time...it works every time.");
    
    quotelevel3.add("Forgiveness is between them and God. It's my job to arrange the meeting.");
    quotelevel3.add("I have nipples Greg. Could you milk me?");
    quotelevel3.add("I like to picture Jesus in a tuxedo T-shirt because it says I want to be formal, but I'm here to party.");
    quotelevel3.add("I love the smell of napalm in the morning.");
    quotelevel3.add("You had me at hello.");
    quotelevel3.add("Carpe Diem! Sieze the day, boys!");
    quotelevel3.add("What we've got here is failure to communicate.");
    quotelevel3.add("Play it, Sam. Play, 'As Time Goes By.'");
    quotelevel3.add("Funny? Funny how? I mean, funny like a clown? I amuse you?");
    quotelevel3.add("I wish I knew how to quit you.");
    
    quotelevel4.add("Remember, Sully, when I promised to kill you last? I lied.");
    quotelevel4.add("You see, in this world there's two kinds of people, my friend: Those with loaded guns and those who dig. You dig.");
    quotelevel4.add("Larry's not white. Larry's clear.");
    quotelevel4.add("I cam to chew bubblegum and kick some ass...and I'm all out of bubblegum.");
    quotelevel4.add("They call me Mr. Tibbs!");
    quotelevel4.add("Only one thing counts in this life - get them to sign on that dotted line.");
    quotelevel4.add("Well, if this is it old boy, I hope you don't mind if I go out speaking the King's.");
    quotelevel4.add("By protecting others, you save yourselves.");
    quotelevel4.add("Calling it your job doesn't make it right, boss.");
    quotelevel4.add("I'll have what she's having.");
    
    quoteanswers.put("What is this?! A center for ants!?", "Zoolander");
    quoteanswers.put("Life moves pretty fast. If you don’t stop and look around once in awhile, you could miss it.", "Ferris Bueller's Day Off");
    quoteanswers.put("My name is Inigo Montoya. You killed my father, prepare to die.", "The Princess Bride");
    quoteanswers.put("Why, you stuck up... half-witted... scruffy-looking... Nerf-herder!", "Star Wars");
    quoteanswers.put("You shall not pass!", "Lord of the Rings");
    quoteanswers.put("Say hello to my little friend!", "Scarface");
    quoteanswers.put("Only one thing counts in this life - get them to sign on that dotted line.", "Glengarry Glen Ross");
    quoteanswers.put("You're a sad, strange little man.", "Toy Story");
    quoteanswers.put("They may take our lives, but they'll never take our freedom!", "Braveheart");
    quoteanswers.put("And you know what they call a quarter pounder with cheese? They call it a Royale with Cheese.", "Pulp Fiction");
    quoteanswers.put("Yo, Adrian!", "Rocky");
    quoteanswers.put("That'll do, pig.", "Babe");
    quoteanswers.put("However, leave the limbs you've lost. They belong to me now.", "Kill Bill");
    quoteanswers.put("Dead or Alive. You're coming with me.", "RoboCop");
    quoteanswers.put("I can see! I can see! I have...I have legs! Jesus, praise Jesus!", "Trading Places");
    quoteanswers.put("Tonight, we dine in Hell!", "300");
    quoteanswers.put("I break wind in your general direction.", "Monty Python");
    quoteanswers.put("Do you understand the words that are coming out of my mouth?", "Rush Hour");
    quoteanswers.put("Momma says that alligators are so ornery because they got all them teeth but no toothbrush.", "The Waterboy");
    quoteanswers.put("Why don't you make like a tree and get out of here.", "Back to the Future");
    quoteanswers.put("60% of the time...it works every time.", "Anchorman");
    quoteanswers.put("Forgiveness is between them and God. It's my job to arrange the meeting.", "Man on Fire");
    quoteanswers.put("I have nipples Greg. Could you milk me?", "Meet the Parents");
    quoteanswers.put("I like to picture Jesus in a tuxedo T-shirt because it says I want to be formal, but I'm here to party.", "Talladega Nights");
    quoteanswers.put("I love the smell of napalm in the morning.", "Apocalypse Now");
    quoteanswers.put("You had me at hello.", "Jerry Maguire");
    quoteanswers.put("Carpe Diem! Sieze the day, boys!", "Dead Poets Society");
    quoteanswers.put("What we've got here is failure to communicate.", "Cool Hand Luke");
    quoteanswers.put("Play it, Sam. Play, 'As Time Goes By.'", "Casablanca");
    quoteanswers.put("Funny? Funny how? I mean, funny like a clown? I amuse you?", "GoodFellas");
    quoteanswers.put("I wish I knew how to quit you.", "Brokeback Mountain");
    quoteanswers.put("Remember, Sully, when I promised to kill you last? I lied.", "Commando");
    quoteanswers.put("You see, in this world there's two kinds of people, my friend: Those with loaded guns and those who dig. You dig.", "The Good, The Bad, The Ugly");
    quoteanswers.put("Larry's not white. Larry's clear.", "Space Jam");
    quoteanswers.put("I cam to chew bubblegum and kick some ass...and I'm all out of bubblegum.", "They Live!");
    quoteanswers.put("Well, if this is it old boy, I hope you don't mind if I go out speaking the King's.", "Inglorious Basterds");
    quoteanswers.put("By protecting others, you save yourselves.", "Seven Samurai");
    quoteanswers.put("Calling it your job doesn't make it right, boss.", "Cool Hand Luke");
    quoteanswers.put("I'll have what she's having.", "When Harry Met Sally");
    quoteanswers.put("They call me Mr. Tibbs!", "In the Heat of the Night");
    
    //Sound levels
    soundlevel1.add("father.wav");
    soundlevel1.add("play.wav");
    soundlevel1.add("findyou.wav");
    soundlevel1.add("offer.wav");
    soundlevel1.add("punk.wav");
    soundlevel1.add("likeagirl.wav");
    soundlevel1.add("choppa.wav");
    soundlevel1.add("kansas.wav");
    soundlevel1.add("et.wav");
    soundlevel1.add("icecream.wav");
    
    soundlevel2.add("money.wav");
    soundlevel2.add("come.wav");
    soundlevel2.add("ape.wav");
    soundlevel2.add("johnny.wav");
    soundlevel2.add("baby.wav");
    soundlevel2.add("damn.wav");
    soundlevel2.add("dead.wav");
    soundlevel2.add("boat.wav");
    soundlevel2.add("speed.wav");
    soundlevel2.add("vodka.wav");
    
    soundlevel3.add("shirley.wav");
    soundlevel3.add("twas.wav");
    soundlevel3.add("hal.wav");
    soundlevel3.add("king.wav");
    soundlevel3.add("here.wav");
    soundlevel3.add("boomstick.wav");
    soundlevel3.add("steers.wav");
    soundlevel3.add("talking.wav");
    soundlevel3.add("coin.wav");
    soundlevel3.add("handle.wav");
    
    soundlevel4.add("walking.wav");
    soundlevel4.add("robinson.wav");
    soundlevel4.add("goodday.wav");
    soundlevel4.add("gentlemen.wav");
    soundlevel4.add("sally.wav");
    soundlevel4.add("ribs.wav");
    soundlevel4.add("bleed.wav");
    soundlevel4.add("milkshake.wav");
    soundlevel4.add("devil.wav");
    soundlevel4.add("man.wav");

    soundanswers.put("father.wav", "Star Wars");
    soundanswers.put("play.wav", "Saw");
    soundanswers.put("findyou.wav", "Taken");
    soundanswers.put("offer.wav", "The Godfather");
    soundanswers.put("punk.wav", "Dirty Harry");
    soundanswers.put("likeagirl.wav", "The Sandlot");
    soundanswers.put("choppa.wav", "Predator");
    soundanswers.put("kansas.wav", "Wizard of Oz");
    soundanswers.put("et.wav", "ET");
    soundanswers.put("icecream.wav", "Forrest Gump");
    soundanswers.put("money.wav", "Jerry Maguire");
    soundanswers.put("come.wav", "Field of Dreams");
    soundanswers.put("ape.wav", "Planet of the Apes");
    soundanswers.put("johnny.wav", "The Shining");
    soundanswers.put("baby.wav", "Dirty Dancing");
    soundanswers.put("damn.wav", "Gone with the Wind");
    soundanswers.put("dead.wav", "The Sixth Sense");
    soundanswers.put("boat.wav", "Jaws");
    soundanswers.put("speed.wav", "Top Gun");
    soundanswers.put("vodka.wav", "Goldeneye");
    soundanswers.put("shirley.wav", "Airplane");
    soundanswers.put("twas.wav", "King Kong");
    soundanswers.put("hal.wav", "2001: A Space Odyssey");
    soundanswers.put("king.wav", "Titanic");
    soundanswers.put("here.wav", "Poltergeist");
    soundanswers.put("boomstick.wav", "Ash: Evil Dead");
    soundanswers.put("steers.wav", "Full Metal Jacket");
    soundanswers.put("talking.wav", "Taxi Driver");
    soundanswers.put("coin.wav", "No Country for Old Men");
    soundanswers.put("handle.wav","A Few Good Men");
    soundanswers.put("walking.wav", "Midnight Cowboy");
    soundanswers.put("robinson.wav", "The Graduate");
    soundanswers.put("goodday.wav", "Willy Wonka");
    soundanswers.put("gentlemen.wav", "Dr. Strangelove");
    soundanswers.put("sally.wav", "Commando");
    soundanswers.put("ribs.wav", "Action Jackson");
    soundanswers.put("bleed.wav", "Predator");
    soundanswers.put("milkshake.wav", "There Will Be Blood");
    soundanswers.put("devil.wav", "The Usual Suspects");
    soundanswers.put("man.wav", "Aliens");








   
    

    //Actor levels
    actorlevel1.add("brad.jpg");
    actorlevel1.add("denzel.jpg");
    actorlevel1.add("ben.jpeg");
    actorlevel1.add("matt.jpg");
    actorlevel1.add("leo.jpg");
    actorlevel1.add("clint.jpg");
    actorlevel1.add("tom.jpg");
    actorlevel1.add("harrison.jpg");
    actorlevel1.add("hanks.jpg");
    actorlevel1.add("mark.jpg");
    
    actorlevel2.add("christopher.jpg");
    actorlevel2.add("michael.jpg");
    actorlevel2.add("pena.jpg");
    actorlevel2.add("patrick.jpg");
    actorlevel2.add("kaine.jpg");
    actorlevel2.add("arkin.jpg");
    actorlevel2.add("neeson.jpg");
    actorlevel2.add("norton.jpg");
    actorlevel2.add("nicholson.jpg");
    actorlevel2.add("sizemore.jpg");

    actorlevel3.add("rhames.jpg");
    actorlevel3.add("roth.jpg");
    actorlevel3.add("duncan.jpg");
    actorlevel3.add("ermey.jpg");
    actorlevel3.add("teller.jpg");
    actorlevel3.add("vincent.jpg");
    actorlevel3.add("toro.jpg");
    actorlevel3.add("pearce.jpg");
    actorlevel3.add("simmons.jpg");
    actorlevel3.add("leto.jpg");

    actorlevel4.add("reno.jpg");
    actorlevel4.add("olmos.jpg");
    actorlevel4.add("jones.jpg");
    actorlevel4.add("pesci.jpg");
    actorlevel4.add("kilmer.jpg");
    actorlevel4.add("beatty.jpg");
    actorlevel4.add("dreyfuss.jpg");
    actorlevel4.add("kelly.jpg");
    actorlevel4.add("grant.jpg");
    actorlevel4.add("fiennes.jpg");


    
    actoranswers.put("brad.jpg", "Brad Pitt");
    actoranswers.put("denzel.jpg", "Denzel Washington");
    actoranswers.put("ben.jpeg", "Ben Affleck");
    actoranswers.put("matt.jpg", "Matt Damon");
    actoranswers.put("leo.jpg", "Leonardo Dicaprio");
    actoranswers.put("clint.jpg", "Clint Eastwood");
    actoranswers.put("tom.jpg", "Tom Cruise");
    actoranswers.put("harrison.jpg", "Harrison Ford");
    actoranswers.put("hanks.jpg", "Tom Hanks");
    actoranswers.put("mark.jpg", "Mark Wahlberg");
    actoranswers.put("christopher.jpg", "Christopher Lloyd");
    actoranswers.put("michael.jpg", "Michael J. Fox");
    actoranswers.put("pena.jpg", "Michael Pena");
    actoranswers.put("patrick.jpg", "Patrick Stewart");
    actoranswers.put("kaine.jpg", "Michael Kaine");
    actoranswers.put("arkin.jpg", "Alan Arkin");
    actoranswers.put("neeson.jpg", "Liam Neeson");
    actoranswers.put("norton.jpg", "Edward Norton");
    actoranswers.put("nicholson.jpg", "Jack Nicholson");
    actoranswers.put("sizemore.jpg", "Tom Sizemore");
    actoranswers.put("rhames.jpg", "Ving Rhames");
    actoranswers.put("ermey.jpg", "R. Lee Ermey");
    actoranswers.put("roth.jpg", "Tim Roth");
    actoranswers.put("toro.jpg", "Benecio Del Toro");
    actoranswers.put("duncan.jpg", "Michael Clarke Duncan");
    actoranswers.put("vincent.jpg", "Vincent D'Onofrio");
    actoranswers.put("teller.jpg", "Miles Teller");
    actoranswers.put("pearce.jpg", "Guy Pearce");
    actoranswers.put("simmons.jpg", "J.K. Simmons");
    actoranswers.put("leto.jpg", "Jared Leto");
    actoranswers.put("reno.jpg", "Jean Reno");
    actoranswers.put("olmos.jpg", "Edward James Olmos");
    actoranswers.put("jones.jpg", "Vinnie Jones");
    actoranswers.put("pesci.jpg", "Joe Pesci");
    actoranswers.put("kilmer.jpg", "Val Kilmer");
    actoranswers.put("beatty.jpg", "Ned Beatty");
    actoranswers.put("dreyfuss.jpg", "Richard Dreyfuss");
    actoranswers.put("kelly.jpg", "Gene Kelly");
    actoranswers.put("grant.jpg", "Cary Grant");
    actoranswers.put("fiennes.jpg", "Ralph Fiennes");



    actorsmovies.put("brad.jpg", "Mr. and Mrs. Smith (2005)");
    actorsmovies.put("denzel.jpg", "Fences (2016)");
    actorsmovies.put("ben.jpeg", "Argo (2012)");
    actorsmovies.put("matt.jpg", "The Bourne Supremacy (2004)");
    actorsmovies.put("leo.jpg", "Inception (2010)");
    actorsmovies.put("clint.jpg", "Gran Torino (2008)");
    actorsmovies.put("tom.jpg", "Mission: Impossible-Ghost Protocol (2011)");
    actorsmovies.put("harrison.jpg", "Star Wars - A New Hope (1977)");
    actorsmovies.put("hanks.jpg", "Cast Away (2000)");
    actorsmovies.put("mark.jpg", "Four Brothers (2004)");
    actorsmovies.put("christopher.jpg", "Back to the Future (1985)");
    actorsmovies.put("michael.jpg", "Back to the Future (1985)");
    actorsmovies.put("pena.jpg", "End of Watch (2012)");
    actorsmovies.put("patrick.jpg", "Logan (2017)");
    actorsmovies.put("kaine.jpg", "Batman Begins (2005)");
    actorsmovies.put("arkin.jpg", "Little Miss Sunshine (2006)");
    actorsmovies.put("neeson.jpg", "Schindler's List (1993)");
    actorsmovies.put("norton.jpg", "Fight Club (1999)");
    actorsmovies.put("nicholson.jpg", "The Departed (2006)");
    actorsmovies.put("sizemore.jpg", "Saving Private Ryan (1998)");
    actorsmovies.put("rhames.jpg", "Pulp Fiction (1994)");
    actorsmovies.put("ermey.jpg", "Full Metal Jacket (1987)");
    actorsmovies.put("roth.jpg", "Reservoir Dogs (1992)");
    actorsmovies.put("duncan.jpg", "The Green Mile (1999)");
    actorsmovies.put("teller.jpg", "War Dogs (2016)");
    actorsmovies.put("vincent.jpg", "Men in Black (1997)");
    actorsmovies.put("pearce.jpg", "Memento (2000)");
    actorsmovies.put("simmons.jpg", "Whiplash (2014)");
    actorsmovies.put("toro.jpg", "Snatch (2000)");
    actorsmovies.put("leto.jpg", "Requiem for a Dream (2000)");
    actorsmovies.put("reno.jpg", "Leon: The Professional (1994)");
    actorsmovies.put("olmos.jpg", "Blade Runner (1982)");
    actorsmovies.put("jones.jpg", "Lock, Stock, and Two Smoking Barrels (1998)");
    actorsmovies.put("pesci.jpg", "Casino (1995)");
    actorsmovies.put("kilmer.jpg", "Batman Forever (1995)");
    actorsmovies.put("beatty.jpg", "Deliverance (1972)");
    actorsmovies.put("dreyfuss.jpg", "Jaws (1975)");
    actorsmovies.put("kelly.jpg", "Singin' in the Rain (1952)");
    actorsmovies.put("grant.jpg", "North by Northwest (1959)");
    actorsmovies.put("fiennes.jpg", "The Hurt Locker (2008)");

    
    //Screen levels
    screenlevel1.add("lionking.jpg");
    screenlevel1.add("ted.jpg");
    screenlevel1.add("kingkong.jpg");
    screenlevel1.add("beemovie.jpg");
    screenlevel1.add("titanic.jpg");
    screenlevel1.add("elf.jpeg");
    screenlevel1.add("grease.jpg");
    screenlevel1.add("remember.jpg");
    screenlevel1.add("wizard.jpg");
    screenlevel1.add("et.jpg");
 
    screenlevel2.add("terminator.jpg");
    screenlevel2.add("django.jpg");
    screenlevel2.add("lebowski.jpg");
    screenlevel2.add("predator.jpg");
    screenlevel2.add("national.jpg");
    screenlevel2.add("taken.jpg");
    screenlevel2.add("inception.jpg");
    screenlevel2.add("interstellar.jpg");
    screenlevel2.add("scarface.jpg");
    screenlevel2.add("future.jpg");
    
    screenlevel3.add("hunting.jpg");
    screenlevel3.add("fmj.jpg");
    screenlevel3.add("truelies.jpg");
    screenlevel3.add("fiction.jpg");
    screenlevel3.add("trading.jpg");
    screenlevel3.add("diehard.jpg");
    screenlevel3.add("basterds.jpg");
    screenlevel3.add("darko.jpg");
    screenlevel3.add("dallas.jpg");
    screenlevel3.add("drive.png");
    
    screenlevel4.add("leon.jpg");
    screenlevel4.add("snatch.jpg");
    screenlevel4.add("saints.jpg");
    screenlevel4.add("birds.jpg");
    screenlevel4.add("se7en.jpg");
    screenlevel4.add("saddles.jpg");
    screenlevel4.add("eraserhead.jpg");
    screenlevel4.add("pale.jpg");
    screenlevel4.add("taxi.jpg");
    screenlevel4.add("tusk.jpg");
    
    
    screenanswers.put("lionking.jpg", "Lion King");
    screenanswers.put("ted.jpg", "Ted");
    screenanswers.put("kingkong.jpg", "King Kong");
    screenanswers.put("beemovie.jpg", "Bee Movie");
    screenanswers.put("titanic.jpg", "Titanic");
    screenanswers.put("elf.jpeg", "Elf");
    screenanswers.put("grease.jpg", "Grease");
    screenanswers.put("remember.jpg", "Remember the Titans");
    screenanswers.put("wizard.jpg", "Wizard of Oz");
    screenanswers.put("et.jpg", "ET");
    screenanswers.put("terminator.jpg", "Terminator");
    screenanswers.put("django.jpg", "Django Unchained");
    screenanswers.put("lebowski.jpg", "The Big Lebowski");
    screenanswers.put("predator.jpg", "Predator");
    screenanswers.put("national.jpg", "National Treasure");
    screenanswers.put("taken.jpg", "Taken");
    screenanswers.put("inception.jpg", "Inception");
    screenanswers.put("interstellar.jpg", "Interstellar");
    screenanswers.put("scarface.jpg", "Scarface");
    screenanswers.put("future.jpg", "Back to the Future");
    screenanswers.put("hunting.jpg", "Good Will Hunting");
    screenanswers.put("fmj.jpg", "Full Metal Jacket");
    screenanswers.put("truelies.jpg", "True Lies");
    screenanswers.put("fiction.jpg", "Pulp Fiction");
    screenanswers.put("trading.jpg", "Trading Places");
    screenanswers.put("diehard.jpg", "Die Hard");
    screenanswers.put("basterds.jpg", "Inglorious Basterds");
    screenanswers.put("darko.jpg", "Donnie Darko"); 
    screenanswers.put("dallas.jpg", "Dallas Buyers Club");
    screenanswers.put("drive.png", "Drive");
    screenanswers.put("leon.jpg", "Leon: The Professional");
    screenanswers.put("snatch.jpg", "Snatch");
    screenanswers.put("saints.jpg", "Boondock Saints");
    screenanswers.put("birds.jpg", "The Birds");
    screenanswers.put("se7en.jpg", "Se7en");
    screenanswers.put("saddles.jpg", "Blazing Saddles");
    screenanswers.put("eraserhead.jpg", "Eraserhead");
    screenanswers.put("pale.jpg", "Pale Rider");
    screenanswers.put("taxi.jpg", "Taxi Driver");
    screenanswers.put("tusk.jpg", "Tusk");
  }
  
  /**
   * Method used to clear the labels
   */
  public void clearAnswers(){
    for(JLabel l: order){
      l.setText("");
    }
  }
  
  /**
   * Method used to paint the game over screen and ask if they want to go again.
   */
  public void gameOver(){
    backgroundmusic.stop();
    theatre.removeAll();
    theatre.add(gameover);
    theatre.repaint();
    try {
      // Open an audio input stream.
      backgroundmusic.stop();
      url = this.getClass().getClassLoader().getResource("Sounds/gameover.wav");
      audioIn = AudioSystem.getAudioInputStream(url);
      // Get a sound clip resource.
      clip = AudioSystem.getClip(null);
      // Open audio clip and load samples from the audio input stream.
      clip.open(audioIn);
      clip.start();
    
  } catch (UnsupportedAudioFileException ex) {
    ex.printStackTrace();
 } catch (IOException ex) {
    ex.printStackTrace();
 } catch (LineUnavailableException ex) {
    ex.printStackTrace();
 }
    while(clip.isRunning()){
      
    }
    int test = JOptionPane.showConfirmDialog(theatre, "Would you like to restart?");
    if(test == 0){
      if(clip != null && clip.isRunning()){
        clip.stop();
      }
      reset();
    }
    if(test == 1){
      System.exit(0);
    }
  }
  
  /**
   * Method used to reset the game and start over if the user chooses to.
   * 
   */
  public void reset(){
    sound1.setBackground(Color.GRAY);
    sound2.setBackground(Color.GRAY);
    sound3.setBackground(Color.GRAY);
    sound4.setBackground(Color.GRAY);

    quote1.setBackground(Color.GRAY);
    quote2.setBackground(Color.GRAY);
    quote3.setBackground(Color.GRAY);
    quote4.setBackground(Color.GRAY);
    
    actor1.setBackground(Color.GRAY);
    actor2.setBackground(Color.GRAY);
    actor3.setBackground(Color.GRAY);
    actor4.setBackground(Color.GRAY);
    
    screen1.setBackground(Color.GRAY);
    screen2.setBackground(Color.GRAY);
    screen3.setBackground(Color.GRAY);
    screen4.setBackground(Color.GRAY);
    
    theatre.removeAll();
    theatre.add(bg);
    ((Lives) live).setImage(3);
    theatre.repaint();
    lives = 3;
    quo = 1;
    screen = 1;
    act = 1;
    sound = 1; 
    backgroundmusic.start();
    backgroundmusic.loop(Clip.LOOP_CONTINUOUSLY);



  }
  
  /**
   * See if they have won by checking the status of the four integers.
   */
  public void checkWinner(){
    if(quo > 4 && screen > 4 && act > 4 && sound >4){
      backgroundmusic.stop();
      theatre.removeAll();
      theatre.add(winner);
      theatre.repaint();
      try {
        // Open an audio input stream.
        backgroundmusic.stop();
        url = this.getClass().getClassLoader().getResource("Sounds/winner.wav");
        audioIn = AudioSystem.getAudioInputStream(url);
        // Get a sound clip resource.
        clip = AudioSystem.getClip(null);
        // Open audio clip and load samples from the audio input stream.
        clip.open(audioIn);
        clip.start();
      
    } catch (UnsupportedAudioFileException ex) {
      ex.printStackTrace();
   } catch (IOException ex) {
      ex.printStackTrace();
   } catch (LineUnavailableException ex) {
      ex.printStackTrace();
   }
      
      int test = JOptionPane.showConfirmDialog(theatre, "Would you like to restart?");
      if(test == 0){
        if(clip != null && clip.isRunning()){
          clip.stop();
        }
        reset();
      }
      if(test == 1){
        System.exit(0);
      }
    
    }
  }
  
  
  
}