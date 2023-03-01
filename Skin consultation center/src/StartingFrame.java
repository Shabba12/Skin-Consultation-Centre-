import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class StartingFrame {

    private WestminsterSkinConsultationManager manager;
    public StartingFrame(WestminsterSkinConsultationManager manager) throws IOException {
        this.manager = manager;

        JFrame frame1 = new JFrame("Welcome");
        frame1.setSize(500,600);
        JPanel panel = new JPanel();

        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JLabel picLabel = new JLabel();
        picLabel.setBounds(100,50,300,200);
        ImageIcon image = new ImageIcon("skinCare.png");
        Image img = image.getImage();
        img = img.getScaledInstance(picLabel.getWidth(), picLabel.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon finalImage = new ImageIcon(img);
        picLabel.setIcon(finalImage);
        frame1.add(picLabel);

        BufferedImage createImg = null;
        createImg = ImageIO.read(new File("create2.png"));
        JButton startBtn = new JButton();
        startBtn.setBounds(160,300,150,40);
        startBtn.setBackground(new Color(241, 255, 231));
        startBtn.setIcon(new ImageIcon(createImg.getScaledInstance(40, 30, java.awt.Image.SCALE_SMOOTH)));
        startBtn.addActionListener(e -> {
            frame1.setVisible(false);
            ConsultationGUI consGui = new ConsultationGUI(this.manager);
        });
        frame1.add(startBtn);

        BufferedImage viewImg = null;
        viewImg = ImageIO.read(new File("view.png"));
        JButton  viewConsultaion= new JButton();
        viewConsultaion.setBackground(new Color(241, 255, 231));
        viewConsultaion.setBounds(160,370,150,40);
        viewConsultaion.setIcon(new ImageIcon(viewImg.getScaledInstance(40, 30, java.awt.Image.SCALE_SMOOTH)));
        viewConsultaion.addActionListener(e -> {
            frame1.setVisible(false);
            manager.printConsultationListGui(manager);
        });
        frame1.add(viewConsultaion);

        BufferedImage exitImg = null;
        exitImg = ImageIO.read(new File("cancel.png"));
        JButton  exitBtn= new JButton();
        exitBtn.setBackground(new Color(241, 255, 231));
        exitBtn.setBounds(160,440,150,40);
        exitBtn.setIcon(new ImageIcon(exitImg.getScaledInstance(40, 30, java.awt.Image.SCALE_SMOOTH)));

        exitBtn.addActionListener(e -> {
            DeleteConsultation delConsult = new DeleteConsultation(manager);
            frame1.setVisible(false);
        });
        frame1.add(exitBtn);


        frame1.setVisible(true);
        frame1.getContentPane().setBackground(new Color(50, 162, 135));
    }
}
