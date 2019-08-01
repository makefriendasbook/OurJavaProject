package twb2019_07_28;

import twb2019_07_27.Sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class UIDistinguish extends Component implements ActionListener {
    private String appId = "16899604";
    private String apiKey = "lOpoP6z355lkPuVBRtByu8sq";
    private String secretKey = "AUPo2yjONqwn27HPB7xkTgaQRlRCDoCa";
    private Sample sample;
    private String distinguish_Contest;
    private JTextPane displayDescription;
    private JPanel leftPanel;
    private ImageIcon modify_Image,modify_img,small_img1,small_img2,small_img3,small_img4,small_img5;
    private String FilePath ="E:\\图片测试\\动物图片测试\\cat.jpg" ;

    public static void main(String[] args){
        UIDistinguish dis = new UIDistinguish();
        dis.UIinit();
    }

    private void UIinit() {
        jf = new JFrame();
        jf.setTitle("物体识别_v0.4");
        jf.setSize(1000,1000);
        jf.setDefaultCloseOperation(3);
        jf.setLocationRelativeTo(null);

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.blue);
        rightPanel.setPreferredSize(new Dimension(250,0));
        rightPanel.setLayout(new FlowLayout());
        jf.add(rightPanel,BorderLayout.EAST);

        leftPanel = new JPanel();
        leftPanel.setBackground(Color.pink);
        leftPanel.setPreferredSize(new Dimension(750,0));
        leftPanel.setLayout(new FlowLayout());
        jf.add(leftPanel,BorderLayout.WEST);
        /**
         *给左边的面板添加相应的组件
         */
        //更改图片大小为指定高度和宽度.
        modify_Image = ModifyImageSize(FilePath,700,400);
        imageLabel = new JLabel();
        imageLabel.setIcon(modify_Image);

        imageLabel.setPreferredSize(new Dimension(700,400));

        leftPanel.add(imageLabel);

        //创建识别内容的识别框
        displayDescription = new JTextPane();
        //将识别框设置为滚动
        JScrollPane jsp = new JScrollPane(displayDescription); //添加一个滚动面板,并将文本域添加到滚动面板中。
        jsp.setPreferredSize(new Dimension(700,500));
        leftPanel.add(jsp);

        sample = new Sample();

        distinguish_Contest = sample.DistinguishImage(FilePath,appId,apiKey,secretKey);
        displayDescription.setText(distinguish_Contest); //将测试后的结果返回给识别框

        uplaodButton = new JButton("上传");
        leftPanel.add(uplaodButton);
        uplaodButton.addActionListener(this);

        /**
         * 给右边的面板添加相应的组件
         */
        small_imgLabel1 = new JLabel();

        //继续更改图片的大小,将小图片插到右边的面板之中
        small_img1 = ModifyImageSize("E:\\图片测试\\动物图片测试\\cat (2).jpg",250,180);
        small_imgLabel1.setIcon(small_img1);
        //更改小图片标签的大小
        small_imgLabel1.setPreferredSize(new Dimension(250,180));

        //将小图片标签添加监听.
        small_imgLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getSource() == small_imgLabel1)
                {
                    FilePath = "E:\\图片测试\\动物图片测试\\cat (2).jpg";
                    modify_img = ModifyImageSize("E:\\图片测试\\动物图片测试\\cat (2).jpg",700,400);
                    addImage_Label(modify_img);

                    distinguish_Contest = sample.DistinguishImage(FilePath,appId,apiKey,secretKey);
                    displayDescription.setText(distinguish_Contest); //将测试后的结果返回给识别框
                }
            }
        });

        rightPanel.add(small_imgLabel1);

        small_imgLabel2 = new JLabel();
        //继续更改图片的大小,将小图片插到右边的面板之中
        small_img2 = ModifyImageSize("E:\\图片测试\\动物图片测试\\dog.jpg",250,180);
        small_imgLabel2.setIcon(small_img2);
        //更改小图片标签的大小
        small_imgLabel2.setPreferredSize(new Dimension(250,180));

        //将小图片标签添加监听.
        small_imgLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getSource() == small_imgLabel2)
                {
                    FilePath = "E:\\图片测试\\动物图片测试\\dog.jpg";
                    modify_img = ModifyImageSize("E:\\图片测试\\动物图片测试\\dog.jpg",700,400);
                    addImage_Label(modify_img);
                    distinguish_Contest = sample.DistinguishImage(FilePath,appId,apiKey,secretKey);
                    displayDescription.setText(distinguish_Contest); //将测试后的结果返回给识别框
                }
            }
        });

        rightPanel.add(small_imgLabel2);

        small_imgLabel3 = new JLabel();
        //继续更改图片的大小,将小图片插到右边的面板之中
        small_img3 = ModifyImageSize("E:\\图片测试\\动物图片测试\\dogs.jpg",250,180);
        small_imgLabel3.setIcon(small_img3);
        //更改小图片标签的大
        small_imgLabel3.setPreferredSize(new Dimension(250,180));

        //将小图片标签添加监听.
        small_imgLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getSource() == small_imgLabel3)
                {
                    FilePath = "E:\\图片测试\\动物图片测试\\dogs.jpg";
                    modify_img =  ModifyImageSize("E:\\图片测试\\动物图片测试\\dogs.jpg",700,400);
                    addImage_Label(modify_img);
                    distinguish_Contest = sample.DistinguishImage(FilePath,appId,apiKey,secretKey);
                    displayDescription.setText(distinguish_Contest); //将测试后的结果返回给识别框
                }
            }
        });

        rightPanel.add(small_imgLabel3);

        small_imgLabel4 = new JLabel();
        //继续更改图片的大小,将小图片插到右边的面板之中
        small_img4 = ModifyImageSize("E:\\图片测试\\动物图片测试\\rabbit.jpg",250,180);
        small_imgLabel4.setIcon(small_img4);
        //更改小图片标签的大小
        small_imgLabel4.setPreferredSize(new Dimension(250,180));

        //将小图片标签添加监听.
        small_imgLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getSource() == small_imgLabel4)
                {
                    FilePath = "E:\\图片测试\\动物图片测试\\rabbit.jpg";
                    modify_img = ModifyImageSize("E:\\图片测试\\动物图片测试\\rabbit.jpg",700,400);
                    addImage_Label(modify_img);
                    distinguish_Contest = sample.DistinguishImage(FilePath,appId,apiKey,secretKey);
                    displayDescription.setText(distinguish_Contest); //将测试后的结果返回给识别框
                }
            }
        });

        rightPanel.add(small_imgLabel4);

        small_imgLabel5 = new JLabel();
        //继续更改图片的大小,将小图片插到右边的面板之中
        small_img5 = ModifyImageSize("E:\\图片测试\\动物图片测试\\rabbits.jpg",250,180);
        small_imgLabel5.setIcon(small_img5);
        //更改小图片标签的大小
        small_imgLabel5.setPreferredSize(new Dimension(250,180));

        //将小图片标签添加监听.
        small_imgLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(e.getSource() == small_imgLabel5)
                {
                    FilePath = "E:\\图片测试\\动物图片测试\\rabbits.jpg";
                    modify_img = ModifyImageSize("E:\\图片测试\\动物图片测试\\rabbits.jpg",700,400);
                    addImage_Label(modify_img);
                    distinguish_Contest = sample.DistinguishImage(FilePath,appId,apiKey,secretKey);
                    displayDescription.setText(distinguish_Contest); //将测试后的结果返回给识别框
                }
            }
        });

        rightPanel.add(small_imgLabel5);

        jf.setVisible(true);
    }

    private JButton uplaodButton;
    private JFrame jf;
    private InputStream is;
    private BufferedImage iamge;
    private File file;
    private JLabel imageLabel,small_imgLabel1,small_imgLabel2,small_imgLabel3,small_imgLabel4,small_imgLabel5;
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("上传"))
        {
            System.out.println("点击我啦~~");
            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true); //设置只能选择文件夹
            //设置可选择文件的类型
            FileNameExtensionFilter filter = new FileNameExtensionFilter("文本文件(*.jpg,*png)",
                    "png", "jpg");
            chooser.setFileFilter(filter);
            chooser.showOpenDialog(null); //打开先选择文件对话框,null表示当前的窗口JFrame或者frame
            file = chooser.getSelectedFile(); // file为用户选择的图片文件

            try {
                is = new FileInputStream(file);
                iamge = ImageIO.read(is);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //点击换区图片后的路径.
            FilePath = file.getPath();

            //更改图片的大小
            ImageIcon modify_img = ModifyImageSize(FilePath,700,400);

            //将上传后的图片添加到图片标签中.
            addImage_Label(modify_img);

            distinguish_Contest = sample.DistinguishImage(FilePath,appId,apiKey,secretKey);
            displayDescription.setText(distinguish_Contest); //将测试后的结果返回给识别框.

        }
    }

    private ImageIcon ModifyImageSize(String filename, int modify_width, int modify_height){
        ImageIcon img = new ImageIcon(filename);
        img.setImage(img.getImage().getScaledInstance(modify_width,modify_height,Image.SCALE_DEFAULT));
        return img;
    }

    private void addImage_Label(ImageIcon iamge){
        imageLabel.setIcon(iamge);
        imageLabel.setPreferredSize(new Dimension(700,400));
    }

}
