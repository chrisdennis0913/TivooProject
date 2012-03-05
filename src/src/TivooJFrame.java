package src;
import input.Event;
import input.InputParser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;
import output.GenerateCalendar;
import output.Output;
import processor.EndTimeSorter;
import processor.KeyWordFinder;
import processor.NameSorter;
import processor.StartTimeSorter;
import processor.TVEventFinder;
import processor.TimeFrameFinder;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class TivooJFrame extends javax.swing.JFrame {
    private JButton jButton4;
    private JButton jButton3;
    private JTextField jTextField1;
    private JButton jButton2;
    private AbstractAction loadAction;
    private JMenuItem jMenuItem2;
    private JMenu jMenu2;
    private JMenuBar jMenuBar1;
    private JButton jButton8;
    private JTextField jTextField4;
    private JTextField jTextField5;
    private AbstractAction omitKeywordAction;
    private AbstractAction startDescendAction;
    private AbstractAction endDescendAction;
    private AbstractAction endSortAction;
    private AbstractAction startSortAction;
    private AbstractAction nameDescendSortAction;
    private AbstractAction nameAscendSortAction;
    private AbstractAction omitCategoryAction;
    private AbstractAction byCategoryAction;
    private AbstractAction byEndAction;
    private AbstractAction byStartAction;
    private AbstractAction searchKeyword;
    private AbstractAction previewAction;
    private AbstractAction startOverAction;
    private AbstractAction exitGUIAction;
    private JButton jButton15;
    private JButton jButton14;
    private JButton jButton13;
    private JButton jButton12;
    private JButton jButton11;
    private JButton jButton10;
    private JPanel jPanel5;
    private JButton jButton9;
    private JPanel jPanel4;
    private JButton jButton7;
    private JButton jButton6;
    private JTextField jTextField3;
    private JPanel jPanel3;
    private JButton jButton5;
    private JTextField jTextField2;
    private JPanel jPanel2;
    private JTabbedPane jTabbedPane1;
    private JPanel jPanel1;
    private JMenu jMenu1;
    private List<Event> eventList;
    private GregorianCalendar start;
    private GregorianCalendar end;
    JEditorPane pane;
    private String latestURL;
    private File latestXMLFile;

    /**
    * Auto-generated main method to display this JFrame
    */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TivooJFrame inst = new TivooJFrame();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }
    
    public TivooJFrame() {
        super();
        //output
        int year = 2011;
        int month = 7;
        int date = 1;
        start = new GregorianCalendar(year, month, date);
        int eYear = 2012;
        int eMonth = 3;
        int eDate = 30;
        end = new GregorianCalendar(eYear, eMonth, eDate);
        initGUI();
    }
    
    private void initGUI() {
        try {
            GroupLayout thisLayout = new GroupLayout((JComponent)getContentPane());
            getContentPane().setLayout(thisLayout);
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            {
                jMenuBar1 = new JMenuBar();
                setJMenuBar(jMenuBar1);
                {
                    jMenu2 = new JMenu();
                    jMenuBar1.add(jMenu2);
                    jMenu2.setText("File");
                    {
                        jMenuItem2 = new JMenuItem();
                        jMenu2.add(jMenuItem2);
                        jMenuItem2.setText("jMenuItem2");
                        jMenuItem2.setAction(getLoadAction());
                    }
                }
            }
            {
                jMenu1 = new JMenu();
                jMenu1.setText("File");
            }
            thisLayout.setVerticalGroup(thisLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jMenu1, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                .addGap(31)
                .addComponent(getJTabbedPane1(), 0, 165, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(thisLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(getJButton4(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(getJButton2(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE))
                .addContainerGap());
            thisLayout.setHorizontalGroup(thisLayout.createSequentialGroup()
                .addContainerGap(17, 17)
                .addGroup(thisLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
                        .addComponent(jMenu1, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 190, Short.MAX_VALUE)
                        .addComponent(getJButton4(), GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
                        .addGap(22)
                        .addComponent(getJButton2(), GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, thisLayout.createSequentialGroup()
                        .addComponent(getJTabbedPane1(), 0, 379, Short.MAX_VALUE)
                        .addGap(43)))
                .addContainerGap(15, 15));
            thisLayout.linkSize(SwingConstants.VERTICAL, new Component[] {getJButton4(), getJButton2()});
            thisLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {getJButton4(), getJButton2()});
            pack();
            this.setSize(454, 300);
        } catch (Exception e) {
            //add your error handling code here
            e.printStackTrace();
        }
    }

    private AbstractAction getLoadAction() {
        if(loadAction == null) {
            loadAction = new AbstractAction("Load New XML", null) {
                public void actionPerformed(ActionEvent evt) {

                    JFileChooser fc = new JFileChooser();
                    int returnVal = fc.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        latestXMLFile = fc.getSelectedFile();
                        InputParser input= InputParser.ParserFactory.generate(latestXMLFile.getName());
                        if (eventList==null){
                            eventList= new ArrayList<Event>();
                            }
                        eventList.addAll(input.getListOfEvents());
                        Collections.sort(eventList);
                        }
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                    latestURL="Calendar.html";
                }
            };
        }
        return loadAction;
    }
    
    private JButton getJButton2() {
        if(jButton2 == null) {
            jButton2 = new JButton();
            jButton2.setText("Preview");
            jButton2.setAction(getPreviewAction());
        }
        return jButton2;
    }
    
    private JTextField getJTextField1() {
        if(jTextField1 == null) {
            jTextField1 = new JTextField();
            jTextField1.setText("Enter Keyword");
        }
        return jTextField1;
    }
    
    private JButton getJButton3() {
        if(jButton3 == null) {
            jButton3 = new JButton();
            jButton3.setText("Search");
            jButton3.setAction(getSearchKeyword());
        }
        return jButton3;
    }
    
    private JButton getJButton4() {
        if(jButton4 == null) {
            jButton4 = new JButton();
            jButton4.setText("Start Over");
            jButton4.setAction(getStartOverAction());
        }
        return jButton4;
    }
    
    private JPanel getJPanel1() {
        if(jPanel1 == null) {
            jPanel1 = new JPanel();
            GroupLayout jPanel1Layout = new GroupLayout((JComponent)jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1.setPreferredSize(new java.awt.Dimension(157, 117));
            jPanel1.setBorder(BorderFactory.createTitledBorder("By Keyword"));
            jPanel1Layout.setHorizontalGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, 25)
                .addGroup(jPanel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(getJButton3(), GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(getJButton7(), GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(8)
                        .addComponent(getJTextField1(), 0, 196, Short.MAX_VALUE)))
                .addContainerGap());
            jPanel1Layout.setVerticalGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(getJTextField1(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(getJButton3(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                    .addComponent(getJButton7(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addContainerGap());
        }
        return jPanel1;
    }
    
    private JTabbedPane getJTabbedPane1() {
        if(jTabbedPane1 == null) {
            jTabbedPane1 = new JTabbedPane();
            jTabbedPane1.addTab("Title", null, getJPanel1(), null);
            jTabbedPane1.addTab("Start", null, getJPanel2(), null);
            jTabbedPane1.addTab("End", null, getJPanel3(), null);
            jTabbedPane1.addTab("Category", null, getJPanel4(), null);
            jTabbedPane1.addTab("Sort", null, getJPanel5(), null);
        }
        return jTabbedPane1;
    }
    
    private JPanel getJPanel2() {
        if(jPanel2 == null) {
            jPanel2 = new JPanel();
            GroupLayout jPanel2Layout = new GroupLayout((JComponent)jPanel2);
            jPanel2.setPreferredSize(new java.awt.Dimension(157, 117));
            jPanel2.setBorder(BorderFactory.createTitledBorder("By Start Time"));
            jPanel2.setLayout(jPanel2Layout);
            jPanel2Layout.setHorizontalGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, 31)
                .addGroup(jPanel2Layout.createParallelGroup()
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(getJTextField2(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(53)
                        .addComponent(getJButton5(), GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 58, Short.MAX_VALUE)))
                .addContainerGap(24, 24));
            jPanel2Layout.setVerticalGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(getJTextField2(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(getJButton5(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE));
        }
        return jPanel2;
    }
    
    private JTextField getJTextField2() {
        if(jTextField2 == null) {
            jTextField2 = new JTextField();
            jTextField2.setText("Enter Start Time (mm/dd/yyyy hh:mm)");
        }
        return jTextField2;
    }
    
    private JButton getJButton5() {
        if(jButton5 == null) {
            jButton5 = new JButton();
            jButton5.setText("Search");
            jButton5.setAction(getByStartAction());
        }
        return jButton5;
    }
    
    private JPanel getJPanel3() {
        if(jPanel3 == null) {
            jPanel3 = new JPanel();
            GroupLayout jPanel3Layout = new GroupLayout((JComponent)jPanel3);
            jPanel3.setPreferredSize(new java.awt.Dimension(157, 117));
            jPanel3.setBorder(BorderFactory.createTitledBorder("By End Time"));
            jPanel3.setLayout(jPanel3Layout);
            jPanel3Layout.setHorizontalGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(33, 33)
                .addGroup(jPanel3Layout.createParallelGroup()
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(getJTextField3(), GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(52)
                        .addComponent(getJButton6(), GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 89, Short.MAX_VALUE)))
                .addContainerGap(84, 84));
            jPanel3Layout.setVerticalGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(getJTextField3(), GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                .addGap(20)
                .addComponent(getJButton6(), GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE));
        }
        return jPanel3;
    }
    
    private JTextField getJTextField3() {
        if(jTextField3 == null) {
            jTextField3 = new JTextField();
            jTextField3.setText("Enter End Time (mm/dd/yyyy hh:mm)");
        }
        return jTextField3;
    }
    
    private JButton getJButton6() {
        if(jButton6 == null) {
            jButton6 = new JButton();
            jButton6.setText("Search");
            jButton6.setAction(getByEndAction());
        }
        return jButton6;
    }
    
    private JButton getJButton7() {
        if(jButton7 == null) {
            jButton7 = new JButton();
            jButton7.setText("Omit");
            jButton7.setAction(getOmitKeywordAction());
        }
        return jButton7;
    }
    
    private JPanel getJPanel4() {
        if(jPanel4 == null) {
            jPanel4 = new JPanel();
            GroupLayout jPanel4Layout = new GroupLayout((JComponent)jPanel4);
            jPanel4.setPreferredSize(new java.awt.Dimension(157,117));
            jPanel4.setBorder(BorderFactory.createTitledBorder("By Characteristic"));
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(getJButton8(), GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 56, Short.MAX_VALUE)
                        .addComponent(getJButton9(), GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(getJTextField5(), 0, 216, Short.MAX_VALUE)
                        .addGap(51))
                    .addGroup(GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getJTextField4(), 0, 216, Short.MAX_VALUE)
                        .addGap(45))));
            jPanel4Layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {getJTextField4(), getJTextField5()});
            jPanel4Layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {getJButton8(), getJButton9()});
            jPanel4Layout.setVerticalGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(getJTextField5(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, GroupLayout.PREFERRED_SIZE)
                .addComponent(getJTextField4(), GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(getJButton8(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                    .addComponent(getJButton9(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                .addContainerGap());
            jPanel4Layout.linkSize(SwingConstants.VERTICAL, new Component[] {getJTextField4(), getJTextField5()});
            jPanel4Layout.linkSize(SwingConstants.VERTICAL, new Component[] {getJButton8(), getJButton9()});
        }
        return jPanel4;
    }
    
    private JTextField getJTextField4() {
        if(jTextField4 == null) {
            jTextField4 = new JTextField();
            jTextField4.setText("Enter Keywords");
        }
        return jTextField4;
    }
    
    private JButton getJButton8() {
        if(jButton8 == null) {
            jButton8 = new JButton();
            jButton8.setText("Search");
            jButton8.setAction(getByCategoryAction());
        }
        return jButton8;
    }
    
    private JButton getJButton9() {
        if(jButton9 == null) {
            jButton9 = new JButton();
            jButton9.setText("Omit");
            jButton9.setAction(getOmitCategoryAction());
        }
        return jButton9;
    }
    
    private JTextField getJTextField5() {
        if(jTextField5 == null) {
            jTextField5 = new JTextField();
            jTextField5.setText("Enter Category");
        }
        return jTextField5;
    }
    
    private JPanel getJPanel5() {
        if(jPanel5 == null) {
            jPanel5 = new JPanel();
            GroupLayout jPanel5Layout = new GroupLayout((JComponent)jPanel5);
            jPanel5.setPreferredSize(new java.awt.Dimension(157,117));
            jPanel5.setLayout(jPanel5Layout);
            jPanel5.setBorder(BorderFactory.createTitledBorder(null, "Sort By", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION));
            jPanel5Layout.setHorizontalGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup()
                    .addComponent(getJButton10(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                    .addComponent(getJButton14(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
                    .addComponent(getJButton12(), GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup()
                    .addComponent(getJButton11(), GroupLayout.Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getJButton15(), GroupLayout.Alignment.LEADING, 0, 132, Short.MAX_VALUE)
                    .addComponent(getJButton13(), GroupLayout.Alignment.LEADING, 0, 132, Short.MAX_VALUE))
                .addContainerGap());
            jPanel5Layout.linkSize(SwingConstants.HORIZONTAL, new Component[] {getJButton10(), getJButton12(), getJButton14()});
            jPanel5Layout.setVerticalGroup(jPanel5Layout.createSequentialGroup()
                .addGap(6)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(getJButton12(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                    .addComponent(getJButton13(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(getJButton14(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                    .addComponent(getJButton15(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(getJButton10(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
                    .addComponent(getJButton11(), GroupLayout.Alignment.BASELINE, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
                .addContainerGap());
            jPanel5Layout.linkSize(SwingConstants.VERTICAL, new Component[] {getJButton10(), getJButton11(), getJButton12(), getJButton14()});
        }
        return jPanel5;
    }

    private JButton getJButton10() {
        if(jButton10 == null) {
            jButton10 = new JButton();
            jButton10.setText("End Time");
            jButton10.setAction(getEndSortAction());
        }
        return jButton10;
    }
    
    private JButton getJButton11() {
        if(jButton11 == null) {
            jButton11 = new JButton();
            jButton11.setText("End (reversed)");
            jButton11.setAction(getEndDescendAction());
        }
        return jButton11;
    }
    
    private JButton getJButton12() {
        if(jButton12 == null) {
            jButton12 = new JButton();
            jButton12.setText("Name");
            jButton12.setAction(getNameAscendSortAction());
        }
        return jButton12;
    }
    
    private JButton getJButton13() {
        if(jButton13 == null) {
            jButton13 = new JButton();
            jButton13.setText("Name (reversed)");
            jButton13.setAction(getNameDescendSortAction());
        }
        return jButton13;
    }
    
    private JButton getJButton14() {
        if(jButton14 == null) {
            jButton14 = new JButton();
            jButton14.setText("Start Time");
            jButton14.setAction(getStartSortAction());
        }
        return jButton14;
    }
    
    private JButton getJButton15() {
        if(jButton15 == null) {
            jButton15 = new JButton();
            jButton15.setText("Start (reversed)");
            jButton15.setAction(getStartDescendAction());
        }
        return jButton15;
    }
    
    private AbstractAction getExitGUIAction() {
        if(exitGUIAction == null) {
            exitGUIAction = new AbstractAction("Exit", null) {
                public void actionPerformed(ActionEvent evt) {
                }
            };
        }
        return exitGUIAction;
    }
    
    private AbstractAction getStartOverAction() {
        if(startOverAction == null) {
            startOverAction = new AbstractAction("Start Over", null) {
                public void actionPerformed(ActionEvent evt) {
                    eventList.clear();
                }
            };
        }
        return startOverAction;
    }
    
    private AbstractAction getPreviewAction() {
        if(previewAction == null) {
            previewAction = new AbstractAction("Preview", null) {
                public void actionPerformed(ActionEvent evt) {
                    // user clicked a link, load it and show it
                    pane = new JEditorPane();
                    getContentPane().setLayout(new BorderLayout());
                    getContentPane().add(pane, BorderLayout.CENTER);
                    pane.setEditable(false);
                    pane.setPreferredSize(new Dimension(900, 800));
                    pack();
                    try
                    {
                        pane.setPage("file:/Users/chrisdennis0913/Documents/workspace/TivooProject/"+latestURL);
                    }
                    catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(TivooJFrame.this,
                                                      "loading problem for " + latestURL +
                                                              " " + e,
                                                      "Load Problem",
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                    setVisible(true);
                }
            };
        }
        return previewAction;
    }
    
    private AbstractAction getSearchKeyword() {
        if(searchKeyword == null) {
            searchKeyword = new AbstractAction("Search", null) {
                public void actionPerformed(ActionEvent evt) {
                    String[] keyWordArray= jTextField1.getText().split(" ");
                    List<String> keyWordList=new ArrayList<String>();
                    for (int n=0; n<keyWordArray.length; n++){
                        keyWordList.add(keyWordArray[n]);
                    }
                    KeyWordFinder kwf= new KeyWordFinder(keyWordList,true);
                    eventList=kwf.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return searchKeyword;
    }
    
    private AbstractAction getOmitKeywordAction() {
        if(omitKeywordAction == null) {
            omitKeywordAction = new AbstractAction("Omit", null) {
                public void actionPerformed(ActionEvent evt) {
                    String[] keyWordArray= jTextField1.getText().split(" ");
                    List<String> keyWordList=new ArrayList<String>();
                    for (int n=0; n<keyWordArray.length; n++){
                        keyWordList.add(keyWordArray[n]);
                    }
                    KeyWordFinder kwf= new KeyWordFinder(keyWordList,false);
                    eventList=kwf.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return omitKeywordAction;
    }
    
    private AbstractAction getByStartAction() {
        if(byStartAction == null) {
            byStartAction = new AbstractAction("Search", null) {
                public void actionPerformed(ActionEvent evt) {
//                    (mm/dd/yyyy hh:mm)
                    String[] searchArray1=jTextField2.getText().split(" ");
                    String[] searchArrayDay=searchArray1[0].split("/");
                    String[] searchArrayTime=searchArray1[1].split(":");
                    GregorianCalendar searchStart = new GregorianCalendar(Integer.parseInt(searchArrayDay[2]),Integer.parseInt(searchArrayDay[0]),Integer.parseInt(searchArrayDay[1])-1,Integer.parseInt(searchArrayTime[0]),Integer.parseInt(searchArrayTime[1]));
                    TimeFrameFinder tff =new TimeFrameFinder(searchStart,end,true);
                    eventList=tff.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return byStartAction;
    }
    
    private AbstractAction getByEndAction() {
        if(byEndAction == null) {
            byEndAction = new AbstractAction("Search", null) {
                public void actionPerformed(ActionEvent evt) {
                    String[] searchArray1=jTextField3.getText().split(" ");
                    String[] searchArrayDay=searchArray1[0].split("/");
                    String[] searchArrayTime=searchArray1[1].split(":");
                    GregorianCalendar searchEnd = new GregorianCalendar(Integer.parseInt(searchArrayDay[2]),Integer.parseInt(searchArrayDay[0]),Integer.parseInt(searchArrayDay[1])-1,Integer.parseInt(searchArrayTime[0]),Integer.parseInt(searchArrayTime[1]));
                    TimeFrameFinder tff =new TimeFrameFinder(start,searchEnd,true);
                    eventList=tff.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return byEndAction;
    }
    
    private AbstractAction getByCategoryAction() {
        if(byCategoryAction == null) {
            byCategoryAction = new AbstractAction("Search", null) {
                public void actionPerformed(ActionEvent evt) {
                    String[] myKeywordArray=jTextField4.getText().split(" ");
                    List<String> keyWordList=new ArrayList<String>();
                    for (int n=0; n<myKeywordArray.length; n++){
                        keyWordList.add(myKeywordArray[n]);
                    }
                    TVEventFinder tvef= new TVEventFinder(keyWordList,true);
                    eventList=tvef.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return byCategoryAction;
    }
    
    private AbstractAction getOmitCategoryAction() {
        if(omitCategoryAction == null) {
            omitCategoryAction = new AbstractAction("Omit", null) {
                public void actionPerformed(ActionEvent evt) {
                    String[] myKeywordArray=jTextField4.getText().split(" ");
                    List<String> keyWordList=new ArrayList<String>();
                    for (int n=0; n<myKeywordArray.length; n++){
                        keyWordList.add(myKeywordArray[n]);
                    }
                    TVEventFinder tvef= new TVEventFinder(keyWordList,false);
                    eventList=tvef.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return omitCategoryAction;
    }
    
    private AbstractAction getNameAscendSortAction() {
        if(nameAscendSortAction == null) {
            nameAscendSortAction = new AbstractAction("Name", null) {
                public void actionPerformed(ActionEvent evt) {
                    NameSorter ns=new NameSorter(true);
                    eventList=ns.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return nameAscendSortAction;
    }
    
    private AbstractAction getNameDescendSortAction() {
        if(nameDescendSortAction == null) {
            nameDescendSortAction = new AbstractAction("Name (reversed)", null) {
                public void actionPerformed(ActionEvent evt) {
                    NameSorter ns=new NameSorter(false);
                    eventList=ns.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return nameDescendSortAction;
    }
    
    private AbstractAction getStartSortAction() {
        if(startSortAction == null) {
            startSortAction = new AbstractAction("Start Time", null) {
                public void actionPerformed(ActionEvent evt) {
                    StartTimeSorter sts=new StartTimeSorter(true);
                    eventList=sts.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return startSortAction;
    }
    
    private AbstractAction getStartDescendAction() {
        if(startDescendAction == null) {
            startDescendAction = new AbstractAction("Start (reversed)", null) {
                public void actionPerformed(ActionEvent evt) {
                    StartTimeSorter sts=new StartTimeSorter(false);
                    eventList=sts.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return startDescendAction;
    }
    
    private AbstractAction getEndSortAction() {
        if(endSortAction == null) {
            endSortAction = new AbstractAction("End Time", null) {
                public void actionPerformed(ActionEvent evt) {
                    EndTimeSorter ets=new EndTimeSorter(true);
                    eventList=ets.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return endSortAction;
    }
    
    private AbstractAction getEndDescendAction() {
        if(endDescendAction == null) {
            endDescendAction = new AbstractAction("End (reversed)", null) {
                public void actionPerformed(ActionEvent evt) {
                    EndTimeSorter ets=new EndTimeSorter(false);
                    eventList=ets.search(eventList);
                    Output o = new GenerateCalendar(eventList);
                    o.generate(start, end);
                }
            };
        }
        return endDescendAction;
    }

}
