package com.medassi.pacmanfx;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class PrimaryController implements Initializable {

    @FXML
    private Canvas canvas;
    private Image imgFantome;
    private Image imgMur;
    private Image imgVide;
    private Image imgPacman;
    private final Position pPacman = new Position();//Stocke la position du Pacman de la matrice
    private final Position[] pFantomes = new Position[Config.NBFANTOMES];//Stocke la position des fantômes de la matrice
    private final Image[][] map = new Image[Config.HAUTEUR][Config.LARGEUR];//La matrice d'image
    boolean start = false; //est-ce que la partie a commencé ?

    @Override
    public void initialize(URL url, ResourceBundle rb) {//Terminé
        imgFantome = new Image(getClass().getResourceAsStream("img/fantome.png"));
        imgMur = new Image(getClass().getResourceAsStream("img/mur.png"));
        imgVide = new Image(getClass().getResourceAsStream("img/vide.png"));
        imgPacman = new Image(getClass().getResourceAsStream("img/pacman.png"));
        loadMapFromTextFile(); //extrait le fichier texte pour valoriser la map et les positions du Pacman et des fantômes
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.fillText("Mega PACMAN SIO1 😥", 300, 400);

        printMap();// affiche la map dans le canvas
    }

    private void loadMapFromTextFile() {//A Faire
        Scanner sc = new Scanner(getClass().getResourceAsStream("map.txt"));
        int y = 0;
        int nbF = 0;
        while (sc.hasNext()) {//parcours de chaque ligne
            String ligne = sc.nextLine();
            for (int x = 0; x < ligne.length(); x++) {//parcours de chaque caractère de la ligne
                if (x < Config.LARGEUR && y < Config.HAUTEUR) {
                    switch (ligne.charAt(x)) {
                        case 'M':
                            //A Faire : On positionne l'image du mur dans la map
                            map[x][y] = imgMur;
                            break;
                        case 'F':
                            //A Faire : On positionne l'image du fantome dans la map
                            map[x][y] = imgFantome;
                            //A Faire : on stocke la position du nouveau fantome
                            Position p = new Position();
                            p.x = x;
                            p.y = y;
                            pFantomes[nbF] = p;
                            nbF++;
                            break;
                        case 'P':
                            //A Faire : On positionne l'image du Pacman dans la map
                            map[x][y] = imgPacman;
                            //A Faire : on stocke la position du Pacman
                            pPacman.x = x ;
                            pPacman.y = y ;
                            break;
                        case ' ':
                            //A Faire : On positionne l'image du vide dans la map
                            map[x][y] = imgVide;
                            break;
                    }
                }
            }
            y++;
        }
    }

    @FXML
    private void onKeyPressed(KeyEvent event) {//A faire
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //A Faire : Si la partie n'est pas encore lancée, alors on la lance et on demarre les fantomes
        switch (event.getCode()) {
            case UP:
                //A Faire : on deplace le Pacman vers le haut si c'est possible
                map[pPacman.x][pPacman.y-1] = imgPacman ;
                map[pPacman.x][pPacman.y] = imgVide ;
                gc.drawImage(imgPacman, pPacman.x*Config.IMAGELARGEUR, (pPacman.y-1)*Config.IMAGEHAUTEUR,Config.IMAGELARGEUR ,Config.IMAGEHAUTEUR);
                pPacman.y = pPacman.y -1 ;
                break;
            case DOWN:
                //A Faire : on deplace le Pacman vers le bas si c'est possible
                break;
            case LEFT:
                //A Faire : on deplace le Pacman vers la gauche si c'est possible
                break;
            case RIGHT:
                //A Faire : on deplace le Pacman vers la droite si c'est possible
                break;
        }
        //A Faire : On redessine les images qui ont changé (gauche ou droite ou haut ou bas.
    }

    private void printMap() {//A Faire
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //A Faire : on dessine les 800 images de la map sur le canvas (gc)
        for( int y=0 ; y<40 ; y ++ ){
            for( int x=0 ; x<40 ; x++ ){
                Image imageADessiner = map[x][y] ;
                gc.drawImage(imageADessiner, x*Config.IMAGELARGEUR, y*Config.IMAGEHAUTEUR,Config.IMAGELARGEUR , Config.IMAGEHAUTEUR);
                
            }
        }
    }

    private void startTimerFantomes() {//Terminé
        //demarrage des fantomes. La procédure moveFantomes sera appelée toutes les 200ms
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    moveFantomes();
                });
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 200);
    }

    private void moveFantomes() {//A Faire
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //A Faire : On parcourt chaque fantome et on le deplace aleéatoirement 
        //d'une case à gauche, ou à droite ou en haut ou en bas. 
        //On le déplace forcément car il ne peut pas être coincé (en théorie)
        //Une fois que le déplacement est effectué, on redessine les cases modifiées
    }
}
