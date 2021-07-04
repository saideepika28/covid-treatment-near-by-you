import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin_Others {

	private JFrame frame;
	/**
	 * @wbp.nonvisual location=73,4
	 */
	private JTextField txtName;
	private JTextField txtSpecialization;
	private JTextField txtExperience;
	private JTextField txtFee;
	private JTextField txtHospital;
	private JTextField txtAddress;
	private JTextField txtID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Others window = new Admin_Others();
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
	public Admin_Others() {
		initialize();
		OracleJavaTest();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtPlace;
	
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
	


	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 908, 528);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Doctor name: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(27, 20, 166, 34);
		frame.getContentPane().add(lblNewLabel);
		
		txtName = new JTextField();
		txtName.setBounds(27, 57, 285, 45);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel lblSpecialization = new JLabel("Specialization: ");
		lblSpecialization.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSpecialization.setBounds(27, 112, 166, 34);
		frame.getContentPane().add(lblSpecialization);
		
		txtSpecialization = new JTextField();
		txtSpecialization.setColumns(10);
		txtSpecialization.setBounds(27, 156, 285, 45);
		frame.getContentPane().add(txtSpecialization);
		
		JLabel lblExperienceinYears = new JLabel("Experience (in years): ");
		lblExperienceinYears.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblExperienceinYears.setBounds(27, 216, 166, 34);
		frame.getContentPane().add(lblExperienceinYears);
		
		txtExperience = new JTextField();
		txtExperience.setBounds(27, 260, 158, 53);
		frame.getContentPane().add(txtExperience);
		txtExperience.setColumns(10);
		
		JLabel lblConsultancyFee = new JLabel("Consultancy fee: ");
		lblConsultancyFee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConsultancyFee.setBounds(340, 20, 166, 34);
		frame.getContentPane().add(lblConsultancyFee);
		
		txtFee = new JTextField();
		txtFee.setColumns(10);
		txtFee.setBounds(350, 53, 158, 53);
		frame.getContentPane().add(txtFee);
		
		JLabel lblHospitalTheyWork = new JLabel("Hospital they work in: ");
		lblHospitalTheyWork.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHospitalTheyWork.setBounds(340, 112, 166, 34);
		frame.getContentPane().add(lblHospitalTheyWork);
		
		txtHospital = new JTextField();
		txtHospital.setColumns(10);
		txtHospital.setBounds(350, 156, 285, 45);
		frame.getContentPane().add(txtHospital);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(23, 396, 612, 68);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblHospitalAddress = new JLabel("Hospital Address: ");
		lblHospitalAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblHospitalAddress.setBounds(27, 360, 166, 34);
		frame.getContentPane().add(lblHospitalAddress);
		
		JButton btnNewButton = new JButton("SUBMIT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name, id, specialization, address, hospital, place;
				String fee, experience;
				name=txtName.getText();
				id = txtID.getText();
				specialization = txtSpecialization.getText();
				address = txtAddress.getText();
				hospital = txtHospital.getText();
				place = txtPlace.getText();
				fee = txtFee.getText();
				experience = txtExperience.getText();
				try {
					pst= con.prepareStatement("INSERT INTO DOCTOR_DETAILS(DOCTOR_ID,DOCTOR_NAME,HOSPITAL_NAME,PLACE,ADDRESS,CONSULTANCY_FEE,SPECIALIZATION,EXPERIENCE)VALUES(?,?,?,?,?,?,?,?)");
					pst.setString(1, id);
					pst.setString(2, name);
					pst.setString(3,hospital);
					pst.setString(4, place);
					pst.setString(5, address);
					pst.setString(6, fee);
					pst.setString(7, specialization);
					pst.setString(8,  experience);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "RECORD ADDED");
					txtName.setText("");
					txtID.setText("");
					txtHospital.setText("");
					txtPlace.setText("");
					txtAddress.setText("");
					txtFee.setText("");
					txtSpecialization.setText("");
					txtExperience.setText("");
					txtName.grabFocus();
							}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
				

				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnNewButton.setBounds(712, 314, 138, 68);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblDoctorId = new JLabel("Doctor ID");
		lblDoctorId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDoctorId.setBounds(548, 20, 166, 34);
		frame.getContentPane().add(lblDoctorId);
		
		txtID = new JTextField();
		txtID.setColumns(10);
		txtID.setBounds(558, 53, 158, 53);
		frame.getContentPane().add(txtID);
		
		JButton btnUpdate = new JButton("MODIFY");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name, id, specialization, address, hospital, place;
				String fee, experience;
				name=txtName.getText();
				id = txtID.getText();
				specialization = txtSpecialization.getText();
				address = txtAddress.getText();
				hospital = txtHospital.getText();
				place = txtPlace.getText();
				fee = txtFee.getText();
				experience = txtExperience.getText();
				try {
					pst= con.prepareStatement("UPDATE DOCTOR_DETAILS set DOCTOR_NAME=?,HOSPITAL_NAME=?,PLACE=?,ADDRESS=?,CONSULTANCY_FEE=?,SPECIALIZATION=?,EXPERIENCE=? WHERE DOCTOR_ID=? ");
					pst.setString(1, id);
					pst.setString(2, name);
					pst.setString(3,hospital);
					pst.setString(4, place);
					pst.setString(5, address);
					pst.setString(6, fee);
					pst.setString(7, specialization);
					pst.setString(8,  experience);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "RECORD UPDATED");
					txtName.setText("");
					txtID.setText("");
					txtHospital.setText("");
					txtPlace.setText("");
					txtAddress.setText("");
					txtFee.setText("");
					txtSpecialization.setText("");
					txtExperience.setText("");
					txtName.grabFocus();
							}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
				

				
			}
		});
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnUpdate.setBounds(712, 236, 138, 68);
		frame.getContentPane().add(btnUpdate);
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String ID;
				
			
			ID=txtID.getText();
				try {
					pst= con.prepareStatement("DELETE FROM DOCTOR_DETAILS WHERE DOCTOR_ID=?");
					pst.setString(1, ID);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "RECORD DELETED");
					txtName.setText("");
					txtID.setText("");
					txtHospital.setText("");
					txtPlace.setText("");
					txtAddress.setText("");
					txtFee.setText("");
					txtSpecialization.setText("");
					txtExperience.setText("");
					txtName.grabFocus();
							}
				catch(SQLException e2)
				{
					e2.printStackTrace();
				}
				
				
				
				
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnDelete.setBounds(712, 158, 138, 68);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnExit.setBounds(712, 392, 138, 68);
		frame.getContentPane().add(btnExit);
		
		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlace.setBounds(350, 216, 166, 34);
		frame.getContentPane().add(lblPlace);
		
		txtPlace = new JTextField();
		txtPlace.setColumns(10);
		txtPlace.setBounds(340, 264, 285, 45);
		frame.getContentPane().add(txtPlace);
	}
}