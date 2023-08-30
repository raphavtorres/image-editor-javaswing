import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class EditorWindow extends JFrame implements ActionListener {
    JButton selectImgBtn;
    JLabel backgroundImgLb = new JLabel();
    JButton changeColorBtn;
    JLabel labelTxt = new JLabel();
    JLayeredPane layeredPane;

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

    public EditorWindow() {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(600, 500));

        selectImgBtn = new JButton("Select image");
        selectImgBtn.addActionListener(this);

        backgroundImgLb.setBounds(0, 0, 600, 500);
        layeredPane.add(backgroundImgLb, Integer.valueOf(0));

        labelTxt.setBounds(100, 100, 400, 100);
        labelTxt.setBackground(Color.WHITE);
        labelTxt.setOpaque(false);
        labelTxt.setFont(new Font("Arial", Font.PLAIN, 50));
        labelTxt.setText("TESTING LABEL");
        layeredPane.add(labelTxt, Integer.valueOf(1));

        changeColorBtn = new JButton("Change color");
        changeColorBtn.addActionListener(this);

        this.add(selectImgBtn);
        this.add(layeredPane);
        this.add(changeColorBtn);

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
