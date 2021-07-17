package com.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import com.common.Student;


public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket cs = new Socket("Localhost",5000);
		
		DataOutputStream dout = new DataOutputStream(cs.getOutputStream());
		DataInputStream din =new DataInputStream(cs.getInputStream());
		
		
		
		int ch;
		int rollNo;
		String name;
		double marks;
		String msg;
		
		Student s;
		

		Scanner sc = new Scanner(System.in);
		
		do{
			System.out.println("1.Add student\n2.search student\n3.delete Student\n4.Failed students\n5.Exit");
			System.out.println("Enter Your Choice");
			ch = sc.nextInt();
		switch(ch) {
			case 1:
				
				System.out.println("Enter Your roll no");
				rollNo=sc.nextInt();
				
				System.out.println("Enter Your name");
				name=sc.next();
				
				System.out.println("Enter Your marks");
				marks=sc.nextDouble();
				
				dout.writeInt(ch);
				dout.writeInt(rollNo);
				dout.writeUTF(name);
				dout.writeDouble(marks);
			msg =	din.readUTF();
			System.out.println(msg);
				
				break;
			case 2:
				System.out.println("Enter roll no to be searched:");
				rollNo=sc.nextInt();
				dout.writeInt(ch);
				dout.writeInt(rollNo);
				
				msg =	din.readUTF();
				System.out.println(msg);
				break;
			case 3:
				System.out.println("Enter roll no to be deleted:");
					rollNo=sc.nextInt();
					dout.writeInt(ch);
					dout.writeInt(rollNo);
					msg =	din.readUTF();
					System.out.println(msg);
				break;
			case 4:
				System.out.println("Enter passing marks:");
			marks=sc.nextDouble();
			dout.writeInt(ch);
			dout.writeDouble(marks);
			msg =	din.readUTF();
			System.out.println(msg);
				break;
			case 5:
				break;
		}
		
			//dout.writeInt(ch);
	
		}while(ch!=5);

	}

}
