
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ShowInterface extends JFrame implements ActionListener {

    private final JButton settingsButton;

    private final JPanel panel;
    private final ImageShow show;
    private boolean stop = true;
    private ImageSlider slider;

    public ShowInterface() {
        setTitle("Image Slide Show");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       

        settingsButton = new JButton(new ImageIcon("/img/play.png"));

        settingsButton.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(null);
        panel.add(settingsButton);
        panel.setBackground(Color.BLACK);
        add(panel, BorderLayout.SOUTH);

        show = new ImageShow();
        show.setImages();
        add(show, BorderLayout.CENTER);
        setVisible(true);
       
      
        // switching to fullscre this.setUndecorated(true);en mode 
        GraphicsEnvironment.getLocalGraphicsEnvironment().
        getDefaultScreenDevice().setFullScreenWindow(this);
        startClick();
    }

    public void setConstraints(GridBagConstraints gbc, int gridx, int gridy, int gridw, int gridh, int ipadx, int ipady, int anchor, Insets ins, JComponent cmp) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.gridwidth = gridw;
        gbc.gridheight = gridh;
        gbc.ipadx = ipadx;
        gbc.ipady = ipady;
        gbc.anchor = anchor;
        gbc.insets = ins;
        getContentPane().add(cmp, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(settingsButton)) {
            stopClick();
            openLoginBox();
        }

    }

    private void startClick() {
        settingsButton.setIcon(new ImageIcon("/img/pause.png"));
        stop = false;
        slider = new ImageSlider();
        slider.start();
    }

    public void stopClick() {
        settingsButton.setIcon(new ImageIcon("/img/play.png"));
        stop = true;
        slider.stopShow();
    }

    public void openLoginBox() {
        LoginDialog ld = new LoginDialog(this);
        ld.setLocationRelativeTo(null);
        ld.setSize(400, 500);
        ld.pack();
        ld.setVisible(true);
    }

    class ImageSlider extends Thread {

        boolean started;
        int size;

        // map= getImages();
        ImageSlider() {
            started = true;
            size = show.getImgsSize();
        }

        @Override
        public void run() {
            int i;
            try {
                for (i = 0; i < size; i++) {
                    if (started != false) {
                        Thread.sleep(2000);
                        show.moveNext();
                        repaint();
                    }
                }
                stopClick();
                show.moveFirst();
                repaint();
            } catch (InterruptedException ie) {
                System.out.println("Interrupted slide show...");
            }
        }

        public void stopShow() {
            started = false;
        }

    }

    @Override
    public void paintComponents(Graphics g) {
        if (show != null) {
            show.paint(g);
        }

    }
}