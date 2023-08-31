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


    public void ChooseImg() {
        JFileChooser imgChooser = new JFileChooser();

        imgChooser.setCurrentDirectory(new File("user.dir"));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("All Pic", "png", "jpg", "jpeg", "gif");

        imgChooser.addChoosableFileFilter(filter);

        int response = imgChooser.showSaveDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            String path = imgChooser.getSelectedFile().getAbsolutePath();
            ImageIcon img = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT));
            backgroundImgLb.setIcon(img);
        }
    }

    public void ChangeTextColor(JLabel labelTxt) {
        JColorChooser colorChooser = new JColorChooser();
        Color color = JColorChooser.showDialog(null, "Pick Color", Color.black);
        labelTxt.setForeground(color);
    }

    public void DownloadImage() {
        if (backgroundImgLb != null) {
            // Image saved in buffer (can access each pixel individually)
            BufferedImage bufferedImg = new BufferedImage(600, 500, BufferedImage.TYPE_INT_RGB);

            Graphics2D g2d = bufferedImg.createGraphics();
            backgroundImgLb.paint(g2d);

            // Render the text onto the image
            g2d.setFont(labelTxt.getFont());
            g2d.setColor(labelTxt.getForeground());
            g2d.drawString(labelTxt.getText(), labelTxt.getX(), labelTxt.getY() + labelTxt.getHeight());

            g2d.dispose();

            // Create a file chooser to specify where to save the image
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("user.dir"));
            fileChooser.setDialogTitle("Save Image");

            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
            fileChooser.setFileFilter(filter);

            // Display the file chooser dialog
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
        JButton downloadImgBtn = new JButton("Download image");
        downloadImgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DownloadImage();
            }
        });

        inputText = new JTextField(20); // Create a text field with 20 columns
        JButton updateTextBtn = new JButton("Update Label");
        updateTextBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelTxt.setText(inputText.getText()); // Update label text
            }
        });
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

        if (e.getSource() == changeColorBtn) {
            ChangeTextColor(labelTxt);
        }
    }
}
