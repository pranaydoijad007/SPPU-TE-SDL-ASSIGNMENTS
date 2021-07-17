package com.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.*;

import com.common.Student;

public class ClientUI extends JFrame implements ActionListener {
	
	JPanel main,addpanel,deletepanel,seachpanel,failpanel;
	JButton main_add,main_del,main_search,main_fail,add;
	JTextField rollNoT,nameT,marksT;
	JLabel rollNoL,nameL,marksL;
	Socket cs;
	
	DataOutputStream dout;
	DataInputStream 
	din;
	
	int ch;
	int rollNo;
	String name;
	double marks;
	String msg;
	
	Student s;
	

	Scanner sc = new Scanner(System.in);
	void initialise() throws UnknownHostException, IOException {
		cs = new Socket("localhost",5000);
		dout = new DataOutputStream(cs.getOutputStream());
		din =new DataInputStream(cs.getInputStream());
		
		
	}
	

	
	public ClientUI() throws UnknownHostException, IOException {
		initialise();
		setSize(500,500);
		setLocationRelativeTo(null);
		setLayout(null);
		main= new JPanel();
		main.setSize(500,500);
		main.setLayout(null);
		
		main_add=new JButton("ADD");
		main_add.setBounds(20,20,100,100);
		main_add.addActionListener(this);
		
		main.add(main_add);
		
		main_del=new JButton("DELETE");
		main_del.setBounds(140,20,100,100);
		main_del.addActionListener(this);
		
		main.add(main_del);
		
		main_search=new JButton("SEARCH");
		main_search.setBounds(260,20,100,100);
		main_search.addActionListener(this);
		
		main.add(main_search);
		
		main_fail=new JButton("FAIL");
		main_fail.setBounds(380,20,100,100);
		main_fail.addActionListener(this);
		
		main.add(main_fail);
		
		main.setVisible(true);
		add(main);
		
		addpanel=new JPanel();
		addpanel.setSize(500,500);
		addpanel.setLayout(null);
		
		rollNoL=new JLabel("ROLL NO");
		rollNoL.setBounds(20,20,200,30);
		
		addpanel.add(rollNoL);
		rollNoT=new JTextField();
		rollNoT.setBounds(220,20,200,30);
		
		addpanel.add(rollNoT);
		
		nameL=new JLabel("NAME");
		nameL.setBounds(20,70,200,30);
		
		addpanel.add(nameL);
		nameT=new JTextField();
		nameT.setBounds(220,70,200,30);
		
		
		addpanel.add(nameT);
		
		marksL=new JLabel("MARKS");
		marksL.setBounds(20,120,200,30);
		
		addpanel.add(marksL);
		marksT=new JTextField();
		marksT.setBounds(220,120,200,30);
		
		
		addpanel.add(marksT);
		
		add=new JButton("Add");
		add.setBounds(250,170,100,100);
		add.addActionListener(this);
		
		addpanel.add(add);
		addpanel.setVisible(false);
		add(addpanel);
		
		setVisible(true);
	}
	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		new ClientUI();

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==main_add)
		{
			System.out.println("clicked on add button");
			main.setVisible(false);
			addpanel.setVisible(true);
			
		}else if(e.getSource()==main_del)
		{
			System.out.println("clicked on delete button");
			
		}else if(e.getSource()==main_search)
		{
			System.out.println("clicked on search button");
			
		}else if(e.getSource()==main_fail)
		{
			System.out.println("clicked on fail button");
			
		}else if(e.getSource()==add)
		{
		try {
			rollNo=Integer.parseInt(rollNoT.getText());
			
			
			name=nameT.getText();
			
			marks=Double.parseDouble(marksT.getText());
			
			dout.writeInt(1);
			dout.writeInt(rollNo);
			dout.writeUTF(name);
			dout.writeDouble(marks);
		msg =	din.readUTF();
		//System.out.println(msg);
		JOptionPane.showMessageDialog(this, msg);
		}catch(Exception p) {
			p.printStackTrace();
		}
		
		
	}


	}
}
