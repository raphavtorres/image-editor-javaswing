import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class EditorWindow extends JFrame implements ActionListener {
    JButton selectImgBtn;
    JLayeredPane layeredPane;
    JLabel backgroundImgLb;
    JLabel labelTxt;
    JButton changeColorBtn;
    JTextField inputText;
    JButton downloadImgBtn;
    JButton updateTextBtn;


    public void ChooseImg() {
        JFileChooser imgChooser = new JFileChooser();
        imgChooser.setCurrentDirectory(new File("user.dir"));

        // Creating filter for img chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("All Pic", "png", "jpg", "jpeg", "gif");
        imgChooser.addChoosableFileFilter(filter);

        // shows the choose screen, passing null to indicate that the component has no parent elem (center screen)
        int response = imgChooser.showSaveDialog(null);

        // if user chose a file
        if (response == JFileChooser.APPROVE_OPTION) {
            String path = imgChooser.getSelectedFile().getAbsolutePath();
            ImageIcon img = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT));
            backgroundImgLb.setIcon(img);
        }
    }

    public void ChangeTextColor(JLabel labelTxt) {
        JColorChooser colorChooser = new JColorChooser();
        Color color = JColorChooser.showDialog(null, "Change Text Color", Color.black);
        labelTxt.setForeground(color);
    }

    public void DownloadImage() {
        if (backgroundImgLb != null) {
            // Image saved in buffer (can access each pixel individually)
            BufferedImage bufferedImg = new BufferedImage(600, 500, BufferedImage.TYPE_INT_RGB);

            // rendering img to g2d
            Graphics2D imgGraphic = bufferedImg.createGraphics();
            backgroundImgLb.paint(imgGraphic);

            // Render the text onto the image
            imgGraphic.setFont(labelTxt.getFont());
            imgGraphic.setColor(labelTxt.getForeground());
            imgGraphic.drawString(labelTxt.getText(), labelTxt.getX(), labelTxt.getY() + labelTxt.getHeight());

            // free memory
            imgGraphic.dispose();

            // Create a file chooser to specify where to save the image
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("user.dir"));
            fileChooser.setDialogTitle("Download Image");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            fileChooser.setFileFilter(filter);

            // Display the file chooser
            int userSelection = fileChooser.showSaveDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                // Get the selected file and try to save the image
                File fileToSave = fileChooser.getSelectedFile();
                try {
                    ImageIO.write(bufferedImg, "png", fileToSave);
                    JOptionPane.showMessageDialog(
                        null,
                        "Image saved successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                        null,
                        "Error saving image",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "No image selected",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    public EditorWindow() {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.setResizable(false);

        getContentPane().setBackground(Color.DARK_GRAY);
        this.setLayout(new FlowLayout());

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 500));

        selectImgBtn = new JButton("Select image");
        selectImgBtn.addActionListener(this);

        backgroundImgLb = new JLabel();
        backgroundImgLb.setBounds(0, 0, 600, 500);
        layeredPane.add(backgroundImgLb, Integer.valueOf(0));

        labelTxt = new JLabel();
        labelTxt.setBounds(100, 400, 400, 100);
        labelTxt.setBackground(Color.WHITE);
        labelTxt.setOpaque(false);
        labelTxt.setFont(new Font("Arial", Font.PLAIN, 50));
        labelTxt.setText("You're Text Here");
        layeredPane.add(labelTxt, Integer.valueOf(1));

        changeColorBtn = new JButton("Change color");
        changeColorBtn.addActionListener(this);

        // Create and add a button for downloading the image
        downloadImgBtn = new JButton("Download image");
        downloadImgBtn.addActionListener(this);
//        downloadImgBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DownloadImage();
//            }
//        });

        inputText = new JTextField(20); // Create a text field with 20 columns
        updateTextBtn = new JButton("Update Label");
        updateTextBtn.addActionListener(this);
//        updateTextBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                labelTxt.setText(inputText.getText()); // Update label text
//            }
//        });
        this.add(downloadImgBtn);

        this.add(selectImgBtn);
        this.add(changeColorBtn);
        this.add(layeredPane);
        this.add(inputText);
        this.add(updateTextBtn);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == selectImgBtn) {
            ChooseImg();
        }

        else if (e.getSource() == changeColorBtn) {
            ChangeTextColor(labelTxt);
        }

        else if (e.getSource() == downloadImgBtn) {
            DownloadImage();
        }

        else if (e.getSource() == updateTextBtn) {
            labelTxt.setText(inputText.getText());
        }
    }
}
