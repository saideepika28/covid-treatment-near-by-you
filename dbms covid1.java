import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Admin_covid {

	private JFrame frame;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtPlace;
	private JTextField txtAddress;
	private JTextField txtFee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_covid window = new Admin_covid();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	private void OracleJavaTest() {
		String dburl = "jdbc:oracle:thin:@218.248.0.7:1521:rdbms";
		String us = "it197370305";
		String pas = "vasavi";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(dburl,us,pas);
			System.out.println("Server Connected");
		}
		catch (SQLException connectException) {
			System.out.println(connectException.getMessage());
			System.out.println(connectException.getSQLState());
			System.out.println(connectException.getErrorCode());
			System.exit(1);
			}
			catch (Exception e)
			{
			System.err.println("Unable to find and load driver");
			System.exit(1);
			}
	}
	public Admin_covid() {
		initialize_adminOthers();
		OracleJavaTest();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize_adminOthers() {
		frame = new JFrame();
		frame.setBounds(100, 100, 906, 661);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("HOSPITAL_ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(45, 41, 182, 45);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblHospitalname = new JLabel("HOSPITAL_NAME:");
		lblHospitalname.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHospitalname.setBounds(45, 175, 182, 45);
		frame.getContentPane().add(lblHospitalname);
		
		JLabel lblPlace = new JLabel("PLACE:");
		lblPlace.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPlace.setBounds(45, 311, 182, 45);
		frame.getContentPane().add(lblPlace);
		
		JLabel lblAddress = new JLabel("ADDRESS: ");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAddress.setBounds(45, 420, 182, 45);
		frame.getContentPane().add(lblAddress);
		
		JLabel lblFeePerDay = new JLabel("FEE PER DAY:");
		lblFeePerDay.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFeePerDay.setBounds(480, 41, 182, 45);
		frame.getContentPane().add(lblFeePerDay);
		
		txtID = new JTextField();
		txtID.setBounds(45, 84, 293, 55);
		frame.getContentPane().add(txtID);
		txtID.setColumns(10);
		
		txtName = new JTextField();
		txtName.setBounds(45, 230, 377, 55);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtPlace = new JTextField();
		txtPlace.setBounds(45, 355, 308, 55);
		frame.getContentPane().add(txtPlace);
		txtPlace.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(45, 475, 538, 75);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		txtFee = new JTextField();
		txtFee.setBounds(473, 84, 189, 55);
		frame.getContentPane().add(txtFee);
		txtFee.setColumns(10);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name, id, address, place, fee;
				name=txtName.getText();
				id = txtID.getText();
				address = txtAddress.getText();
				place = txtPlace.getText();
				fee = txtFee.getText();
				try {
					pst= con.prepareStatement("INSERT INTO COIVD_HOSPITALS(HOSPITAL_ID,HOSPITAL_NAME,PLACE,ADDRESS,FEE_PERDAY)VALUES(?,?,?,?,?)");
					pst.setString(1, id);
					pst.setString(2, name);
					pst.setString(3, place);
					pst.setString(4, address);
					pst.setString(5, fee);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "RECORD ADDED");
					txtName.setText("");
					txtID.setText("");
					txtPlace.setText("");
					txtAddress.setText("");
					txtFee.setText("");
					txtName.grabFocus();
							}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
				

				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(702, 208, 141, 63);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnModify = new JButton("MODIFY");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name, id, address, place, fee;
				name=txtName.getText();
				id = txtID.getText();
				address = txtAddress.getText();
				place = txtPlace.getText();
				fee = txtFee.getText();
				try {
					pst= con.prepareStatement("UPDATE COIVD_HOSPITALS set HOSPITAL_NAME=?,PLACE=?,ADDRESS=?, FEE_PERDAY=? WHERE HOSPITAL_ID=? ");
					pst.setString(1, id);
					pst.setString(2, name);
					pst.setString(4, place);
					pst.setString(5, address);
					pst.setString(6, fee);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "RECORD UPDATED");
					txtName.setText("");
					txtID.setText("");
					txtPlace.setText("");
					txtAddress.setText("");
					txtFee.setText("");
					txtName.grabFocus();
							}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
				

				
			}
		});
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnModify.setBounds(702, 296, 141, 63);
		frame.getContentPane().add(btnModify);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ID;
				
				
				ID=txtID.getText();
					try {
						pst= con.prepareStatement("DELETE COIVD_DETAILS WHERE HOSPITAL_ID=?");
						pst.setString(1, ID);
						pst.executeUpdate();
						JOptionPane.showMessageDialog(null, "RECORD DELETED");
						txtName.setText("");
						txtID.setText("");
						txtPlace.setText("");
						txtAddress.setText("");
						txtFee.setText("");
						txtName.grabFocus();
								}
					catch(SQLException e2)
					{
						e2.printStackTrace();
					}
					
					
					
					
					
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnDelete.setBounds(702, 389, 141, 63);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(702, 487, 141, 63);
		frame.getContentPane().add(btnExit);
	}
}
