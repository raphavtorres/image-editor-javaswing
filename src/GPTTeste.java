//import javax.imageio.ImageIO;
//import javax.swing.*;
//import javax.swing.filechooser.FileNameExtensionFilter;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//public abstract class GPTTeste extends JFrame implements ActionListener {
//    JButton selectImgBtn;
//    JLabel backgroundImgLb = new JLabel();
//    JButton changeColorBtn;
//    JLabel labelTxt = new JLabel();
//    JLayeredPane layeredPane;
//
//    // ... other variables ...
//
//    public void DownloadImage() {
//        // Check if there's an image displayed in backgroundImgLb
//        if (backgroundImgLb.getIcon() != null) {
//            // Create a buffered image of the displayed image
//            BufferedImage image = new BufferedImage(
//                    backgroundImgLb.getWidth(),
//                    backgroundImgLb.getHeight(),
//                    BufferedImage.TYPE_INT_RGB
//            );
//            Graphics2D g2d = image.createGraphics();
//            backgroundImgLb.paint(g2d);
//            g2d.dispose();
//
//            // Create a file chooser to specify where to save the image
//            JFileChooser fileChooser = new JFileChooser();
//            fileChooser.setCurrentDirectory(new File("user.dir"));
//            fileChooser.setDialogTitle("Save Image");
//
//            FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG Images", "png");
//            fileChooser.setFileFilter(filter);
//
//            // Display the file chooser dialog
//            int userSelection = fileChooser.showSaveDialog(null);
//
//            if (userSelection == JFileChooser.APPROVE_OPTION) {
//                // Get the selected file and try to save the image
//                File fileToSave = fileChooser.getSelectedFile();
//                try {
//                    ImageIO.write(image, "png", fileToSave);
//                    JOptionPane.showMessageDialog(
//                            null,
//                            "Image saved successfully!",
//                            "Success",
//                            JOptionPane.INFORMATION_MESSAGE
//                    );
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                    JOptionPane.showMessageDialog(
//                            null,
//                            "Error saving image",
//                            "Error",
//                            JOptionPane.ERROR_MESSAGE
//                    );
//                }
//            }
//        } else {
//            JOptionPane.showMessageDialog(
//                    null,
//                    "No image selected",
//                    "Error",
//                    JOptionPane.ERROR_MESSAGE
//            );
//        }
//    }
//
//    // ... other methods ...
//
//    public EditorWindow() {
//        // ... initialization code ...
//
//        // Create and add a button for downloading the image
//        JButton downloadImgBtn = new JButton("Download image");
//        downloadImgBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                DownloadImage();
//            }
//        });
//        this.add(downloadImgBtn);
//
//        // ... rest of your initialization code ...
//    }
//
//    // ... rest of your code ...
//
//
//    }
//}
//
//public static void main(String[] args) {
//    SwingUtilities.invokeLater(() -> new EditorWindow());
//}
