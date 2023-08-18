import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class EditorWindow extends JFrame implements ActionListener {
    JButton selectImgBtn;
    JButton changeColorBtn;

    JLabel imgBox = new JLabel();
    JLabel label;


    public EditorWindow() {
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());

        selectImgBtn = new JButton("Select image");
        selectImgBtn.addActionListener(this);

        imgBox.setBounds(0, 0, 50, 50);

        changeColorBtn = new JButton("Change color");
        changeColorBtn.addActionListener(this);

        label = new JLabel();
        label.setBackground(Color.white);
        label.setOpaque(true);
        label.setFont(new Font("Arial", Font.PLAIN, 100));
        label.setText("TESTING LABEL");

        this.add(imgBox);
        this.add(selectImgBtn);
        this.add(label);
        this.add(changeColorBtn);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == selectImgBtn) {
//            PASSAR TUDO ISSO PARA UMA FUNCAO
            JFileChooser imgChooser = new JFileChooser();

            imgChooser.setCurrentDirectory(new File("user.dir"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("All Pic", "png", "jpg", "jpeg", "gif");

            imgChooser.addChoosableFileFilter(filter);

            int response = imgChooser.showSaveDialog(null);  // select file to open

            if(response == JFileChooser.APPROVE_OPTION) {
                String path = imgChooser.getSelectedFile().getAbsolutePath();
                ImageIcon icon =  new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(600, 500, Image.SCALE_DEFAULT));;
                imgBox.setIcon(icon);
            }
        }

        if(e.getSource() == changeColorBtn) {
            JColorChooser colorChooser = new JColorChooser();
            Color color = JColorChooser.showDialog(null, "Pick Color", Color.black);

            label.setForeground(color);
        }
    }
}
