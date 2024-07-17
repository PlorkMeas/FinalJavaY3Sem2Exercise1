import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Exercise2 implements ActionListener {
    private final String url = "jdbc:mysql://localhost/javaexamsem2";
    private final String username = "rith";
    private final String password = "@Rith123";
    private final String selectQuery = "Select * from tbCustomer";
    private JFrame f;
    private JLabel lid, lLastname, lFirstName, lPhone;
    private JLabel tid, tLastName, tFirstName, tPhone;
    private JButton bPrevious, bNext;
    private Connection con;
    private Statement stm;
    private ResultSet rs;

    public Exercise2(){
        initialize();
        initializeDatabase();
    }

    private void initialize(){
        f = new JFrame("Exercise2");
        lid = new JLabel("ID:");
        lLastname = new JLabel("Last Name:");
        lFirstName = new JLabel("First Name:");
        lPhone = new JLabel("Phone:");
        tid = new JLabel();
        tFirstName = new JLabel();
        tLastName = new JLabel();
        tPhone = new JLabel();
        bPrevious = new JButton("Previous");
        bNext = new JButton("Next");


        bPrevious.addActionListener(this);
        bNext.addActionListener(this);

        f.setLayout(new GridLayout(5, 2));
        f.add(lid);
        f.add(tid);
        f.add(lLastname);
        f.add(tLastName);
        f.add(lFirstName);
        f.add(tFirstName);
        f.add(lPhone);
        f.add(tPhone);
        f.add(bPrevious);
        f.add(bNext);

        f.setSize(new Dimension(350, 250));
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private void initializeDatabase(){
        try{
            con = DriverManager.getConnection(url, username, password);
            stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(selectQuery);
            rs.first();
            moveChange();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null ,e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(e.getSource()==bPrevious){
                movePrevious();
            }else if(e.getSource()==bNext){
                moveNext();
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }
    private void clear(){
        tid.setText(null);
        tLastName.setText(null);
        tFirstName.setText(null);
        tPhone.setText(null);
    }
    private void moveChange()throws Exception{
        if(rs!=null){
            clear();

            tid.setText(String.valueOf(rs.getInt("customer_id")));
            tLastName.setText(rs.getString("customer_last_name"));
            tFirstName.setText(rs.getString("customer_first_name"));
            tPhone.setText(rs.getString("customer_phone"));

            bPrevious.setEnabled(!rs.isFirst());
            bNext.setEnabled(!rs.isLast());
        }
    }
    private void movePrevious()throws Exception{
        rs.previous();
        moveChange();
    }

    private void moveNext()throws Exception{
        rs.next();
        moveChange();
    }

}
