package homework1;

import sun.java2d.pipe.ShapeSpanIterator;

import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * Main application class for exercise #1.
 * This application allows the user to add shapes to a graphical window and
 * to animate them.
 */
@SuppressWarnings("serial")
public class Animator extends JFrame implements ActionListener {

    // preferred frame width and height.
    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 400;

    // graphical components
    private JMenuBar menuBar;
    private JMenu fileMenu, insertMenu, helpMenu;
    private JMenuItem newItem, exitItem,
                        triangleItem, ovalItem,
                        numberedOvalItem, sectorItem, aboutItem;
    private JCheckBoxMenuItem animationCheckItem;
    private JPanel mainPanel;

    // shapes that have been added to this

    // TODO (BOM): Add and initialize a container of shapes called shapes.
    private Set<Shape> shapes = new HashSet<>();
    //FIXME: Added, not sure the data structure is the correct one


    /**
     * @modifies this
     * @effects Initializes the GUI and enables a timer that steps animation
     *          of all shapes in this 25 times per second while animation
     *          checkbox is selected.
     */
    public Animator() {
        super("Animator");

        // create main panel and menubar
        mainPanel = (JPanel)createMainPanel();
        getContentPane().add(mainPanel);
        menuBar = (JMenuBar)createMenuBar();
        setJMenuBar(menuBar);

        // enable animation timer (ticks 25 times per second)
        Timer timer = new Timer(40, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if (animationCheckItem.isSelected()) {
                    // TODO (BOM): Add code for making one animation step for all
                    //       shapes in this
                    Iterator<Shape> shapeI = shapes.iterator();
                    while(shapeI.hasNext()) {
                        //Shape curr = shapeI.next();
                        Rectangle bounds = new Rectangle(0 , 0,WINDOW_WIDTH, WINDOW_HEIGHT);
                        Animatable currAnimatable = (Animatable)shapeI.next();
                        currAnimatable.step(bounds);
                    }

                    repaint();  // make sure that the shapes are redrawn
                }
            }
        });
        timer.start();
    }


    /**
     * @return main GUI panel.
     */
    private JComponent createMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(
                new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        mainPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        mainPanel.setBackground(Color.WHITE);

        return mainPanel;
    }


    /**
     * @return main GUI menubar.
     */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        newItem.addActionListener(this);
        fileMenu.add(newItem);
        animationCheckItem = new JCheckBoxMenuItem("Animation");
        fileMenu.add(animationCheckItem);
        exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        insertMenu = new JMenu("Insert");
        triangleItem = new JMenuItem("Triangle");
        triangleItem.addActionListener(this);
        insertMenu.add(triangleItem);
        ovalItem = new JMenuItem("Oval");
        ovalItem.addActionListener(this);
        insertMenu.add(ovalItem);
        numberedOvalItem = new JMenuItem("Numbered Oval");
        numberedOvalItem.addActionListener(this);
        insertMenu.add(numberedOvalItem);
        sectorItem = new JMenuItem("Sector");
        sectorItem.addActionListener(this);
        insertMenu.add(sectorItem);
        menuBar.add(insertMenu);

        helpMenu = new JMenu("Help");
        aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(this);
        helpMenu.add(aboutItem);
        menuBar.add(helpMenu);

        return menuBar;
    }


    /**
     * @modifies g
     * @effects Paint this including all its shapes to g. This method is
     *          invoked by Swing to draw components. It should not be invoked
     *          directly, but the repaint method should be used instead in
     *          order to schedule the component for redrawing.
     */
    public void paint(Graphics g) {
        super.paint(g);
        //TODO (BOM): Add code for drawing all shapes in this
        Graphics panel = getContentPane().getGraphics();
        Iterator<Shape> iterator = shapes.iterator();
        while(iterator.hasNext()) {
            Shape curr = iterator.next();
            curr.draw(panel);
        }

    }


    /**
     * @modifies this
     * @effects Invoked when the user selects an action from the menubar
     *          and performs the appropriate operation.
     */
    public void actionPerformed(ActionEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());

        // File->New : clear all shapes
        if (source.equals(newItem)) {
            shapes.clear();
            repaint();

            //TODO (BOM):  Add code for number of LocationChangingNumerOval = 0
        }

        // File->Exit: close application
        else if (source.equals(exitItem)) {
            dispose();
        }

        // Insert a shape
        else if ((source.equals(triangleItem)) ||
                 (source.equals(ovalItem)) ||
                 (source.equals(numberedOvalItem)) ||
                 (source.equals(sectorItem))) {

            // TODO (BOM): Add code for creating the appropriate shape such that:
            //       it is completely inside the window's bounds &&
            //       its location, size and color are randomly selected &&
            //       1/10*WINDOW_WIDTH <= shape.width < 3/10*WINDOW_WIDTH &&
            //       1/10*WINDOW_HEIGHT <= shape.height < 3/10*WINDOW_HEIGHT

            Dimension size = randomizeDimension();
            Point location = randomizeLocation(size);
            Color color = randomizeColor();

            Shape s = null;
            if(source.equals(triangleItem)) {
                s = new LocationAndColorChangingTriangle(location, color, size);
            } else if(source.equals(ovalItem)) {
                s = new LocationChangingOval(location, color, size);
            } else if(source.equals(numberedOvalItem)) {
                s = new LocationChangingNumberedOval(location, color, size);
            } else if(source.equals(sectorItem)) {
                s = new AngleChangingSector(location, color, size, randomizeAngle(), randomizeAngle());
            }
            shapes.add(s);

            repaint();
        }

        // Help->About : show about message dialog
        else if (source.equals(aboutItem)){
            JOptionPane.showMessageDialog(
                    this,
                    "Animator - 1st" +
                    " homework assignment",
                    "About",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //TODO: spec
    private Color randomizeColor() {
        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        Color rand = new Color(r, g, b);
        return rand;
    }

    //TODO: Add spec and maybe change 359 and 0 into static members of the class(make them const)
    private int randomizeAngle() {
        Random random = new Random();
        return random.nextInt(359 - 0 );
    }

    //TODO: spec
    private Dimension randomizeDimension() {
        Random random = new Random();
        Dimension d = new Dimension();
        d.width = random.nextInt(WINDOW_WIDTH *3/10 - WINDOW_WIDTH *1/10 ) +WINDOW_WIDTH *1/10;
        d.height = random.nextInt(WINDOW_HEIGHT *3/10 - WINDOW_HEIGHT *1/10 ) +WINDOW_HEIGHT *1/10;
        return d;
    }

    //TODO: spec
    private Point randomizeLocation(Dimension size) {
        Random random = new Random();
        Point p = new Point();
        p.x = random.nextInt(WINDOW_WIDTH - size.width - 0 + 1);
        p.y = random.nextInt(WINDOW_HEIGHT - size.height - 0 + 1);
        return p;
    }

    /**
     * @effects Animator application.
     */
    public static void main(String[] args) {
        Animator application = new Animator();

        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setResizable(false);
        application.pack();
        application.setVisible(true);
    }
}
