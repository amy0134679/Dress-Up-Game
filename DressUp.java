import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.* ;
    /*
     * Name: <Amy Chen>
     * Student ID: <501406845>
     *  
     ******** Project Description ********
     * 
     * Describe in plain English the overall program/program in a paragraph or 2.
     * 
     * My program is a dress up game of myself that I made to entertain myself simillar to the games
     * I played as a child. The UI of the game is simple, composed of two panels, one of a display
     * of the character to dress up and one with buttons and instructions.
     * To change outfits or add a boba cup, the user can click the appropriate button in the bottom
     * panel. Additionally, to change the background of the display, the user can click anywhere
     * in the display panel. The aim of the program is to create a fun and simple dress-up game
     *  that is customizable and easy to play.
     * 
     ******** Swing Requirement ********
     * 
     * Describe in 1 paragraph how your program satisfies the requirement that
     * there is at least 3 unique components. Be clear to identify in what
     * files and the lines number (just the starting line is fine) that the
     * components are defined on.
     * 
     * To meet the requirements I included a RadioButton (Line 331) adds or deleted a 
     * boba cup on my character's hand. I also included a Label (Line 329) to tell the user instructions on 
     * how to change the background color. Lastly, I included a Button (Line 333) that
     * changes the outfit on the upper panel when clicked.
     *    
     * 
     ******** 2D Graphics Requirement ********
     *
     * Describe in 1 paragraph how your program satisfies the requirement that
     * there is at least 1 JPanel used for drawing something. Be clear to
     * identify in what files and the line numbers that this panel is defined on.
     * 
     * I used 2 JPanels, one for the display part of the game, and another for the bottom 
     * panel to display the buttons and information. The upper Panel draws and displays the 
     * character and her outfits.
     * 
     ******** Event Listener Requirement ********
     *
     * Describe in 1 paragraph how your program satisfies the requirement that
     * there is at least one ActionListener, and there is additionally at least
     * one MouseListener or ActionListener. Be clear to identify in what file
     * and the line numbers that these listeners are defined in.
     * 
     * I added an ActionListener (Line 343) for the button to listen
     *  for a click to change the outfits of my character. Additionally I included an 
     *  MouseListener (Line 321) that listens for a click on the panel to change the background color.
     * 
     */

public class DressUp extends JPanel {

    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 500;

    public static int GIRL_HEIGHT = 350;
    public static int GIRL_WIDTH = 200;

    private int girlX = FRAME_WIDTH / 2 - GIRL_WIDTH / 2;
    private int girlY = FRAME_HEIGHT - GIRL_HEIGHT - 50;

    private int outfitCount = 0;
    private boolean hasBoba = false;
    private boolean hasBubbles = false;


    private Color skyColor = new Color(148, 228, 247);
    private Color hairColor = new Color(77, 54, 41);
    private Color blush = (Color.PINK);
    private Color skin = new Color(255, 209, 191);

    // Constructor defines the size of the frame and sets background color
    public DressUp() {
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        setBackground(skyColor); 
    }

    //  draws everything on the panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGirl(g);
    }

    // Cycles through outfit options by updating the outfit index.
    // If the index goes past the last outfit, it resets back to 0.
    // Calls repaint() so the new outfit is shown on the screen.
    // This method is triggered by a JButton ActionListener.
    private void changeOutfit() {
        outfitCount++;
        if (outfitCount>2) {
            outfitCount=0;
        }
        repaint();
    }

    
    // Changes the background color when the panel is clicked.
    // Randomly selects a color from a preset list.
    // Updates the background and repaints the panel.
    // This method is triggered by a MouseListener.
    public void changeBackground() {
        Color[] colors = {
                new Color(148, 228, 247), // blue
                new Color(229, 217, 250), // lavender
                new Color(208, 230, 193), // green
                new Color(255, 235, 240)  // pink
            };

            int index = (int) (Math.random() * colors.length);
            skyColor = colors[index];
            setBackground(skyColor); 
            repaint();
    }

    // Draws elements of the character to dress up
    private void drawGirl(  Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // SHADOW
        g2.setColor(new Color(0, 0, 0, 50));
        g2.fillOval(girlX, girlY+290, 200, 50);

        // HAIR BACK
        g2.setColor(hairColor);
        g2.fillArc(girlX+10, girlY-20, 180, 200, 0, 180); 
        g2.fillRect(girlX+10, girlY+65, 180, 180);


        // BOBA CUP
        if (hasBoba==true) {
            g2.setColor(new Color(159, 197, 227));
            g2.drawRect( girlX+190, girlY+220, 30, 40); // plastic cup
            g2.setColor(new Color(186, 140, 104));
            g2.fillRect( girlX+190, girlY+225, 30, 35); // milk tea inside

            // change brush for straw
            g2.setStroke(new BasicStroke( 3.0f));
            g2.setColor(new Color(209, 153, 240)); 
            g2.drawLine(girlX+205, girlY+210, girlX+205, girlY+230); // straw

            // pearls
            g2.setColor(hairColor);
            g2.fillOval(girlX+193, girlY+243, 5, 5);
            g2.fillOval(girlX+210, girlY+250, 5, 5);
            g2.fillOval(girlX+208, girlY+240, 5, 5);
            g2.fillOval(girlX+202, girlY+245, 5, 5);
            g2.fillOval(girlX+192, girlY+253, 5, 5);

        }

        // ARMS
        g2.setStroke(new BasicStroke(
            25.0f,                
            BasicStroke.CAP_ROUND,  
            BasicStroke.JOIN_ROUND  
        ));      

        g2.setColor(skin);
        g2.drawLine(girlX+50, girlY+160, girlX+30, girlY+230); 
        g2.drawLine(girlX+150, girlY+160, girlX+180, girlY+230);

        // Body
         g2.fillRect(girlX + 50, girlY + 140, 100, 110); // torso

         //CHANGE OUTFIT
         switch (outfitCount) {
            case 0: 
                g2.setStroke(new BasicStroke(
                    25.0f,                
                    BasicStroke.CAP_ROUND,  
                    BasicStroke.JOIN_ROUND  
                ));      

                g2.setColor(Color.WHITE);
                g2.drawLine(girlX+50, girlY+154, girlX+30, girlY+230); // left sleeve
                g2.drawLine(girlX+150, girlY+154, girlX+180, girlY+230);// right sleeve
                g2.fillRect(girlX + 50, girlY + 140, 100, 110); // torso
                g2.setStroke(new BasicStroke(
                    3.0f,                
                    BasicStroke.CAP_ROUND,  
                    BasicStroke.JOIN_ROUND  
                ));      
                g2.setColor(new Color(196, 183, 173));
                g2.drawLine(girlX+100, girlY + 140, girlX+100, girlY + 250);
                g2.drawOval(girlX+90, girlY+190, 5, 5);
                g2.drawOval(girlX+90, girlY+210, 5, 5);
                g2.drawOval(girlX+90, girlY+170, 5, 5);
                g2.setColor(skin);
                g2.fillArc(girlX+16, girlY+220, 25, 25, 155, 180); // left hand
                g2.fillArc(girlX+170, girlY+220, 25, 25, 200, 180); // right hand

                g2.fillRect(girlX + 50, girlY + 250, 40, 60); // left leg
                g2.fillRect(girlX + 110, girlY + 250, 40, 60); // right leg
                g2.setColor(Color.BLACK); // make skirt
                g2.fillRect(girlX + 50, girlY + 230, 100, 35); // make skirt
                g2.setColor(Color.WHITE);
                g2.fillRect(girlX + 50, girlY + 290, 40, 20); // left sock
                g2.fillRect(girlX + 110, girlY + 290, 40, 20); // right sock
                break;
             case 1: 
                g2.setColor(Color.BLACK);
                g2.fillRect(girlX + 50, girlY + 140, 100, 110); // torso
                g2.setColor(skin);
                g2.fillRect(girlX + 55, girlY + 140, 90, 20);
                g2.setColor(new Color(41, 56, 30));
                g2.fillRect(girlX+50, girlY+230, 100, 20);
                g2.fillRect(girlX + 50, girlY + 250, 40, 60); // left leg
                g2.fillRect(girlX + 110, girlY + 250, 40, 60); // right leg
                break;
            case 2:
                g2.setColor(skin);
                g2.fillRect(girlX + 50, girlY + 250, 40, 60); // left leg
                g2.fillRect(girlX + 110, girlY + 250, 40, 60); // right leg
                g2.setColor(new Color(209, 153, 240)); // purple
                g2.fillRect(girlX + 50, girlY + 140, 100, 110); // torso
                g2.fillRect(girlX + 50, girlY + 230, 100, 35); //dress
                g2.setColor(skin);
                g2.fillRect(girlX + 55, girlY + 140, 90, 20); // neckline
                g2.setStroke(new BasicStroke(3.0f));      
                g2.setColor(new Color(116, 92, 158)); // dark purple for stitches
                g2.drawLine(girlX + 55, girlY + 195, girlX + 65, girlY + 195); // stitches
                g2.drawLine(girlX + 75, girlY + 195, girlX + 85, girlY + 195); // stitches
                g2.drawLine(girlX + 95, girlY + 195, girlX + 105, girlY + 195); // stitches
                g2.drawLine(girlX + 115, girlY + 195, girlX + 125, girlY + 195); // stitches
                g2.drawLine(girlX + 135, girlY + 195, girlX + 145, girlY + 195); // stitches
                g2.drawOval(girlX+45, girlY+160, 10, 7); // bow 1
                g2.drawOval(girlX+55, girlY+160, 10, 7);
                g2.drawLine(girlX+55, girlY+163, girlX+36, girlY+175);
                g2.drawLine(girlX+55, girlY+163, girlX+67, girlY+175);
                g2.drawOval(girlX+135, girlY+160, 10, 7); // bow 2
                g2.drawOval(girlX+145, girlY+160, 10, 7);
                g2.drawLine(girlX+145, girlY+163, girlX+126, girlY+175);
                g2.drawLine(girlX+145, girlY+163, girlX+156, girlY+175);
                break;
         }

        // HEAD
        g2.setColor(skin);
        g2.fillOval(girlX + 30, girlY + 10, 140, 140); // face

        g2.setColor(Color.PINK);
        g2.fillOval(girlX + 35, girlY + 95, 30, 25); // blush
        g2.fillOval(girlX + 135, girlY + 95, 30, 25);

        // BANGS
        g2.setColor(hairColor);
        g2.fillArc(girlX + 20, girlY, 100, 100, 45, 180);
        g2.fillArc(girlX + 80, girlY, 100, 100, 325, 180);

        // NOSE
        g2.setStroke(new BasicStroke(
            5.0f,                
            BasicStroke.CAP_ROUND,  
            BasicStroke.JOIN_ROUND  
        )); 

        g2.setColor(new Color(143, 104, 89));
        g2.drawLine(girlX+100, girlY+100,girlX+100, girlY+100);

        // MOUTH
        g2.setStroke(new BasicStroke(
            2.0f,                
            BasicStroke.CAP_ROUND,  
            BasicStroke.JOIN_ROUND  
        )); 
        g2.drawLine(girlX + 90, girlY + 120, girlX + 110, girlY + 120);

        // EYES
        g2.setColor(hairColor);
        g2.fillOval(girlX + 65, girlY + 90, 7, 7);
        g2.fillOval(girlX + 130, girlY + 90, 7, 7); 
        
        // EYEBROWS
        g2.drawLine(girlX+60, girlY+80, girlX + 70, girlY + 80);
        g2.drawLine(girlX + 130, girlY+80, girlX + 140, girlY + 80);


        // SHOES
        g2.setColor(Color.BLACK);
        g2.fillOval(girlX + 50, girlY + 300, 40, 15);
        g2.fillOval(girlX + 110, girlY + 300, 40, 15);    
    
    }

    /*My program is a dress-up game.
     It shows a character drawn on a JPanel, where the user can change 
     her outfit and some features. The character is drawn using 
     shapes like rectangles, ovals, and lines in the drawGirl method.
     It updates every time repaint() is called.

    To make the program interactive, I used some Swing components.
    A JButton uses an ActionListener to change the outfit when clicked.
    Additionally, I also used JRadioButton components with ActionListeners
    to let the user choose whether the character has boba.
    A MouseListener is added to the panel so when the user 
    clicks on the screen, the background color changes randomly.

    The program uses several Swing components
    like JButton, JRadioButton, JLabel, and JPanel.
    The button is for changing outfits, the radio
    buttons are for selecting the boba option,
    and the labels display the title and instructions.
    The main panel is used for drawing the character,
    which meets the graphics requirement.

    To use the program, run the file, and click the button
    to switch outfits, select a radio button to add or 
    remove the boba, and click on the screen to change the background color. */

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dress-Up Game");
        DressUp panel = new DressUp();

        panel.addMouseListener(new MouseAdapter() {
        @Override
            public void mouseClicked(MouseEvent e) {
                panel.changeBackground(); // this will change the background when clicked
            }
        });

        JLabel label = new JLabel("DRESS UP GAME");
        JLabel info = new JLabel("Click the Panel to Change Background Color!");
        JButton button = new JButton("Change Outfit");
        JRadioButton boba = new JRadioButton("Thirsty?");
        JRadioButton  noBoba = new JRadioButton("Not Thirsty");
        ButtonGroup group = new ButtonGroup();
        group.add(boba);
        group.add(noBoba);
        button.setText("Change outfit XD");
        button.setBackground(Color.WHITE);
        button.setBounds(600, 100, 50, 30);
        button.addActionListener(e -> panel.changeOutfit());
        info.setBackground(Color.WHITE);
        info.setBounds(600, 100, 50, 30);

        boba.addActionListener(e -> {
            panel.hasBoba = true;
            panel.repaint();
        });

        noBoba.addActionListener(e -> {
            panel.hasBoba = false;
            panel.repaint();
        });


        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(500, 100));
        bottomPanel.setBackground(new Color(240, 240, 255)); 
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(label);
        bottomPanel.add(button);
        bottomPanel.add(info);
        bottomPanel.add(boba);
        bottomPanel.add(noBoba);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}