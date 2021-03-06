/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.Properties;

import javax.swing.JOptionPane;

import data.AuthenticationMethodEnum;
import data.ConnectionSecurityEnum;

/**
 * Dialog for editing the server email settings
 * 
 * @author Frank Weber
 */
public class EmailSettingsDialog extends javax.swing.JDialog {

	private boolean pressedOK = false;
	private Properties props = new Properties();

	/**
	 * Creates new form EmailSettingsDialog
	 */
	public EmailSettingsDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		setLocationRelativeTo(null);

		cbSecurity.removeAllItems();
		for (ConnectionSecurityEnum element : ConnectionSecurityEnum.values())
			cbSecurity.addItem(element.toString());

		cbAuth.removeAllItems();
		for (AuthenticationMethodEnum element : AuthenticationMethodEnum.values())
			cbAuth.addItem(element.toString());
	}

	public boolean pressedOK() {
		return pressedOK;
	}

	public Properties getEmailProperties() {
		return props;
	}

	public void setProperties(Properties emailProps) {
		props = emailProps;
		tfServer.setText(props.getProperty("mailHost"));
		tfUsername.setText(props.getProperty("user"));
		tfPassword.setText(props.getProperty("password"));
		if (props.containsKey("connectionSecurity")) {
			cbSecurity.setSelectedItem(props.get("connectionSecurity"));
		}
		if (props.containsKey("authenticationMethod")) {
			cbAuth.setSelectedItem(props.get("authenticationMethod"));
		}
		if (props.containsKey("mailPort")) {
			spPort.setValue(props.get("mailPort"));
		}
	}

	private void initComponents() {
		java.awt.GridBagConstraints gridBagConstraints;

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Email settings");

		pMain.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 8, 8, 8));
		pMain.setLayout(new java.awt.GridBagLayout());

		lbServer.setText("Server:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(lbServer, gridBagConstraints);

		lbPort.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lbPort.setText("Port:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(lbPort, gridBagConstraints);

		tfServer.setColumns(20);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(tfServer, gridBagConstraints);

		lbSecurity.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lbSecurity.setText("Connection security:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(lbSecurity, gridBagConstraints);

		lbAuth.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		lbAuth.setText("Authentication method:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(lbAuth, gridBagConstraints);

		lbUsername.setText("Username:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(lbUsername, gridBagConstraints);

		cbSecurity.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onSecurityChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 2;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(cbSecurity, gridBagConstraints);

		cbAuth.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onAuthChanged(evt);
			}
		});
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 3;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(cbAuth, gridBagConstraints);

		lbPassword.setText("Password:");
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(lbPassword, gridBagConstraints);

		tfUsername.setColumns(15);
		tfUsername.setEnabled(false);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 4;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(tfUsername, gridBagConstraints);

		tfPassword.setColumns(15);
		tfPassword.setEnabled(false);
		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 5;
		gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
		pMain.add(tfPassword, gridBagConstraints);

		spPort.setModel(new javax.swing.SpinnerNumberModel(587, 0, 65535, 1));
		pPort.add(spPort);

		lbStandardPort.setText("Standardport:");
		pPort.add(lbStandardPort);

		lbPortNum.setText("587");
		pPort.add(lbPortNum);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 1;
		gridBagConstraints.gridy = 1;
		pMain.add(pPort, gridBagConstraints);

		btOkay.setText("Okay");
		btOkay.setFocusPainted(false);
		btOkay.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onOkay(evt);
			}
		});
		pButtons.add(btOkay);

		btCancel.setText("Cancel");
		btCancel.setFocusPainted(false);
		btCancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				onCancel(evt);
			}
		});
		pButtons.add(btCancel);

		gridBagConstraints = new java.awt.GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 6;
		gridBagConstraints.gridwidth = 2;
		pMain.add(pButtons, gridBagConstraints);

		getContentPane().add(pMain, java.awt.BorderLayout.CENTER);

		pack();
	}

	private void onSecurityChanged(java.awt.event.ActionEvent evt) {
		switch (cbSecurity.getSelectedIndex()) {
		case 0:
		case 1:
			spPort.setValue(587);
			lbPortNum.setText("587");
			break;
		case 2:
			spPort.setValue(465);
			lbPortNum.setText("465");
			break;
		}
	}

	private void onAuthChanged(java.awt.event.ActionEvent evt) {
		switch (cbAuth.getSelectedIndex()) {
		case 0:
			tfUsername.setEnabled(false);
			tfPassword.setEnabled(false);
			break;
		case 1:
			tfUsername.setEnabled(true);
			tfPassword.setEnabled(true);
			break;
		}
	}

	private void onOkay(java.awt.event.ActionEvent evt) {
		try {
			if (tfServer.getText().isEmpty()) {
				throw new Exception("Server is empty");
			} else {
				props.put("mailHost", tfServer.getText());
				if (cbAuth.getSelectedIndex() == 1) {
					if (tfUsername.getText().isEmpty()) {
						throw new Exception("Username is empty");
					} else {
						props.put("user", tfUsername.getText());
						if (tfPassword.getPassword().length == 0) {
							throw new Exception("Password is empty");
						} else {
							props.put("password", tfPassword.getPassword().toString());
						}
					}
				}
				props.put("mailPort", spPort.getValue());
				props.put("connectionSecurity", cbSecurity.getSelectedItem());
				props.put("authenticationMethod", cbAuth.getSelectedItem());
			}
			pressedOK = true;
			dispose();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void onCancel(java.awt.event.ActionEvent evt) {
		dispose();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(EmailSettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(EmailSettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(EmailSettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(EmailSettingsDialog.class.getName()).log(java.util.logging.Level.SEVERE,
					null, ex);
		}

		/* Create and display the dialog */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				EmailSettingsDialog dialog = new EmailSettingsDialog(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	// GUI Variables declaration
	private javax.swing.JButton btCancel = new javax.swing.JButton();
	private javax.swing.JButton btOkay = new javax.swing.JButton();
	private javax.swing.JComboBox<String> cbAuth = new javax.swing.JComboBox<>();
	private javax.swing.JComboBox<String> cbSecurity = new javax.swing.JComboBox<>();
	private javax.swing.JLabel lbAuth = new javax.swing.JLabel();
	private javax.swing.JLabel lbPassword = new javax.swing.JLabel();
	private javax.swing.JLabel lbPort = new javax.swing.JLabel();
	private javax.swing.JLabel lbPortNum = new javax.swing.JLabel();
	private javax.swing.JLabel lbSecurity = new javax.swing.JLabel();
	private javax.swing.JLabel lbServer = new javax.swing.JLabel();
	private javax.swing.JLabel lbStandardPort = new javax.swing.JLabel();
	private javax.swing.JLabel lbUsername = new javax.swing.JLabel();
	private javax.swing.JPanel pButtons = new javax.swing.JPanel();
	private javax.swing.JPanel pMain = new javax.swing.JPanel();
	private javax.swing.JPanel pPort = new javax.swing.JPanel();
	private javax.swing.JSpinner spPort = new javax.swing.JSpinner();
	private javax.swing.JPasswordField tfPassword = new javax.swing.JPasswordField();
	private javax.swing.JTextField tfServer = new javax.swing.JTextField();
	private javax.swing.JTextField tfUsername = new javax.swing.JTextField();
	// End of variables declaration
}
