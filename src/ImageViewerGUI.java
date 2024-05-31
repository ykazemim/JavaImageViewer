/**
 * Some parts of this code is taken from "stackoverflow.com"
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class ImageViewerGUI extends JFrame implements ActionListener {
    private final JLabel introLabel = new JLabel("Image Viewer");
    private final JButton selectFileButton = new JButton("Choose Image");
    private final JButton showImageButton = new JButton("Show Image");
    private final JButton resizeButton = new JButton("Resize");
    private final JButton grayscaleButton = new JButton("Gray Scale");
    private final JButton brightnessButton = new JButton("Brightness");
    private final JButton closeButton = new JButton("Exit");
    private final JButton showResizeButton = new JButton("Show Result");
    private final JButton showBrightnessButton = new JButton("Result");
    private final JButton backButton = new JButton("Back");
    private final JTextField widthTextField = new JTextField();
    private final JTextField heightTextField = new JTextField();
    private final JTextField brightnessTextField = new JTextField();

    String filePath = ".\\";
    File file;
    JFileChooser fileChooser = new JFileChooser(filePath);
    ImageIcon imageIcon;
    int h = 900;
    int w = 1200;
    float brightenFactor = 1;


    ImageViewerGUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Image Viewer");
        this.setSize(700, 300);
        this.setVisible(true);
        this.setResizable(true);
        this.setLocationRelativeTo(null);

        introLabel.setOpaque(true);
        introLabel.setForeground(Color.BLUE);
        introLabel.setBackground(Color.lightGray);
        introLabel.setFont(new Font(introLabel.getFont().getFontName(), Font.BOLD, 25));

        //Adding buttons to action listener
        selectFileButton.addActionListener(this);
        showImageButton.addActionListener(this);
        resizeButton.addActionListener(this);
        grayscaleButton.addActionListener(this);
        brightnessButton.addActionListener(this);
        closeButton.addActionListener(this);
        showResizeButton.addActionListener(this);
        showBrightnessButton.addActionListener(this);
        backButton.addActionListener(this);

        // Allowing only images in file chooser
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes()));

        mainPanel();

        revalidate();
        repaint();
    }

    public void mainPanel() {
        // Create main panel for adding to Frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Create Grid panel for adding buttons to it, then add it all to main panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(3, 2));

        // Adding all buttons to Grid panel
        buttonsPanel.add(this.selectFileButton);
        buttonsPanel.add(this.showImageButton);
        buttonsPanel.add(this.brightnessButton);
        buttonsPanel.add(this.grayscaleButton);
        buttonsPanel.add(this.resizeButton);
        buttonsPanel.add(this.closeButton);

        // add Grid panel that contains 6 buttons to main panel
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(this.introLabel, gbc);
        gbc.gridx++;
        mainPanel.add(buttonsPanel, gbc);

        // add main panel to our frame
        this.add(mainPanel);
    }

    public void resizePanel() {
        JPanel resizePanel = new JPanel();
        JLabel resizeLabel = new JLabel("Resize Section");
        JLabel widthLabel = new JLabel("Width");
        JLabel heightLabel = new JLabel("Height");

        resizeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        resizePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.gridwidth = 2;
        resizePanel.add(resizeLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        resizePanel.add(widthLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        resizePanel.add(widthTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        resizePanel.add(heightLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        resizePanel.add(heightTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        resizePanel.add(backButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        resizePanel.add(showResizeButton, gbc);

        this.getContentPane().removeAll();
        this.add(resizePanel);
        this.revalidate();
        this.repaint();

    }

    public void brightnessPanel() {
        JPanel brightnessPanel = new JPanel();
        JLabel brightnessLabel = new JLabel("Enter brightness factor (between 0 and 1)");

        brightnessPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        brightnessPanel.add(brightnessLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // fill remaining horizontal space
        gbc.gridwidth = GridBagConstraints.REMAINDER; // fill remaining horizontal space
        brightnessPanel.add(brightnessTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        brightnessPanel.add(backButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        brightnessPanel.add(showBrightnessButton, gbc);

        this.getContentPane().removeAll();
        this.add(brightnessPanel);
        this.revalidate();
        this.repaint();
    }

    public void chooseFileImage() {
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
    }

    public void showOriginalImage() {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel label = new JLabel();

        boolean isReadyToShow = true;

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IllegalArgumentException e) {
            isReadyToShow = false;
            label.setForeground(Color.RED);
            label.setText("Error: No image input");
        } catch (IOException e) {
            isReadyToShow = false;
            label.setForeground(Color.RED);
            label.setText("Error: Something went wrong during reading image");
        } finally {
            if (bufferedImage == null) {
                isReadyToShow = false;
                label.setForeground(Color.RED);
                label.setText("Error: No image input");
            }
        }

        if (isReadyToShow) {
            imageIcon = new ImageIcon(bufferedImage);
            label.setIcon(imageIcon);
        }

        tempPanel.add(label);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void grayScaleImage() {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel label = new JLabel();

        boolean isReadyToShow = true;

        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(file);
        } catch (IllegalArgumentException e) {
            isReadyToShow = false;
            label.setForeground(Color.RED);
            label.setText("Error: No image input");
        } catch (IOException e) {
            isReadyToShow = false;
            label.setForeground(Color.RED);
            label.setText("Error: Something went wrong during reading image");
        } finally {
            if (bufferedImage == null) {
                isReadyToShow = false;
                label.setForeground(Color.RED);
                label.setText("Error: No image input");
            }
        }

        if (isReadyToShow) {
            ImageFilter filter = new GrayFilter(true, 10);
            ImageProducer producer = new FilteredImageSource(bufferedImage.getSource(), filter);
            Image image = Toolkit.getDefaultToolkit().createImage(producer);
            imageIcon = new ImageIcon(image);
            label.setIcon(imageIcon);
        }

        tempPanel.add(label);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void showResizeImage(int w, int h) {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();
        JLabel label = new JLabel();

        boolean isReadyToShow = true;

        if (w == -1 || h == -1) {
            isReadyToShow = false;
            label.setForeground(Color.RED);
            label.setText("Error: Bad number input");
        } else {
            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (IllegalArgumentException e) {
                isReadyToShow = false;
                label.setForeground(Color.RED);
                label.setText("Error: No image input");
            } catch (IOException e) {
                isReadyToShow = false;
                label.setForeground(Color.RED);
                label.setText("Error: Something went wrong during reading image");
            } finally {
                if (bufferedImage == null) {
                    isReadyToShow = false;
                    label.setForeground(Color.RED);
                    label.setText("Error: No image input");
                }
            }
            if (isReadyToShow) {
                Image image = bufferedImage.getScaledInstance(w, h, Image.SCALE_DEFAULT);
                imageIcon = new ImageIcon(image);
                label.setIcon(imageIcon);
            }
        }

        tempPanel.add(label);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    public void showBrightnessImage(float f) {
        JFrame tempFrame = new JFrame();
        JPanel tempPanel = new JPanel();

        JLabel label = new JLabel();

        boolean isReadyToShow = true;

        if (f == -1) {
            isReadyToShow = false;
            label.setForeground(Color.RED);
            label.setText("Error: Bad number input");
        } else {

            BufferedImage bufferedImage = null;
            try {
                bufferedImage = ImageIO.read(file);
            } catch (IllegalArgumentException e) {
                isReadyToShow = false;
                label.setForeground(Color.RED);
                label.setText("Error: No image input");
            } catch (IOException e) {
                isReadyToShow = false;
                label.setForeground(Color.RED);
                label.setText("Error: Something went wrong during reading image");
            } finally {
                if (bufferedImage == null) {
                    isReadyToShow = false;
                    label.setForeground(Color.RED);
                    label.setText("Error: No image input");
                }
            }
            if (isReadyToShow) {
                RescaleOp op = new RescaleOp(f, 0, null);
                bufferedImage = op.filter(bufferedImage, bufferedImage);
                imageIcon = new ImageIcon(bufferedImage);
                label.setIcon(imageIcon);
            }
        }

        tempPanel.add(label);

        tempPanel.setSize(1800, 1000);
        tempFrame.setTitle("Image Viewer");
        tempFrame.setSize(1800, 1000);
        tempFrame.setVisible(true);
        tempFrame.setResizable(true);
        tempFrame.add(tempPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == resizeButton) {
            this.resizePanel();
        } else if (e.getSource() == showImageButton) {
            this.showOriginalImage();
        } else if (e.getSource() == grayscaleButton) {
            this.grayScaleImage();
        } else if (e.getSource() == showResizeButton) {
            try {
                int width = Integer.parseInt(widthTextField.getText());
                int height = Integer.parseInt(heightTextField.getText());
                if (width > 0 && height > 0) this.showResizeImage(width, height);
                else this.showResizeImage(-1, -1); // -1,-1 indicate an error
            } catch (NumberFormatException ex) {
                this.showResizeImage(-1, -1); // -1,-1 indicate an error
            }
        } else if (e.getSource() == brightnessButton) {
            this.brightnessPanel();
        } else if (e.getSource() == showBrightnessButton) {
            try {
                float factor = Float.parseFloat(brightnessTextField.getText());
                if (factor >= 0 && factor <= 1) this.showBrightnessImage(factor);
                else this.showBrightnessImage(-1); // -1 indicates an error
            } catch (NullPointerException | NumberFormatException ex) {
                this.showBrightnessImage(-1); // -1 indicates an error
            }
        } else if (e.getSource() == selectFileButton) {
            this.chooseFileImage();
        } else if (e.getSource() == closeButton) {
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        } else if (e.getSource() == backButton) {
            this.getContentPane().removeAll();
            this.mainPanel();
            this.revalidate();
            this.repaint();
        }
    }
}