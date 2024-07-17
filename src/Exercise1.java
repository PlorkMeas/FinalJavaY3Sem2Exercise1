import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise1 implements ActionListener {
    JFrame f;
    JPanel pMain;
    JLabel lFirst, lSecond, lResult;
    JTextField txtFirst, txtSecond, txtResult;
    JButton bPlus, bSub, bMul, bDiv, bRem, bClear;
    public Exercise1(){
        initialize();
    }
    private void initialize(){
        f = new JFrame("Exercise1");
        pMain = new JPanel(new GridLayout(6, 2));
        lFirst = new JLabel("First Number:");
        lSecond = new JLabel("Second Number:");
        lResult = new JLabel("Result:");
        txtFirst = new JTextField();
        txtSecond = new JTextField();
        txtResult = new JTextField();
        txtResult.setEditable(false);
        bPlus = new JButton("+");
        bSub = new JButton("-");
        bMul = new JButton("*");
        bDiv = new JButton("/");
        bRem = new JButton("%");
        bClear = new JButton("Clear");

        pMain.add(lFirst);
        pMain.add(txtFirst);
        pMain.add(lSecond);
        pMain.add(txtSecond);
        pMain.add(lResult);
        pMain.add(txtResult);
        pMain.add(bPlus);
        pMain.add(bSub);
        pMain.add(bMul);
        pMain.add(bDiv);
        pMain.add(bRem);
        pMain.add(bClear);

        bPlus.addActionListener(this);
        bSub.addActionListener(this);
        bMul.addActionListener(this);
        bDiv.addActionListener(this);
        bRem.addActionListener(this);
        bClear.addActionListener(this);

        f.setLayout(new BorderLayout());
        f.add(pMain, BorderLayout.CENTER);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(new Dimension(400, 300));
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bClear){
            clearText();
        }

        try{
            if(e.getSource()==bPlus){
                txtResult.setText(String.valueOf(getFirst()+getSecond()));
            }else if(e.getSource()==bSub){
                txtResult.setText(String.valueOf(getFirst()-getSecond()));
            }else if(e.getSource()==bMul){
                txtResult.setText(String.valueOf(getFirst()*getSecond()));
            }else if(e.getSource()==bDiv){
                txtResult.setText(String.valueOf(getFirst()/getSecond()));
            }else if(e.getSource()==bRem){
                txtResult.setText(String.valueOf(getFirst()%getSecond()));
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
    }

    private double getFirst() throws Exception{
        return Double.parseDouble(txtFirst.getText().trim());
    }
    private double getSecond() throws Exception{
        return Double.parseDouble(txtSecond.getText().trim());
    }
    private void clearText(){
        txtFirst.setText(null);
        txtSecond.setText(null);
        txtResult.setText(null);
    }
}
