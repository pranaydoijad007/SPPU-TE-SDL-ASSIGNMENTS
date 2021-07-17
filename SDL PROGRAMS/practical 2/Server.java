package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import com.common.Student;

public class Server {
	public static ArrayList<Student> list = new ArrayList<Student>();

	public static void main(String[] args) throws IOException {
		
		
		
		ServerSocket ss = new ServerSocket(5000);
		
		Socket cs =	ss.accept();
		DataOutputStream dout = new DataOutputStream(cs.getOutputStream());
		DataInputStream din =new DataInputStream(cs.getInputStream());
		int ch;
		int rollNo;
		String name;
		double marks;
		Scanner sc = new Scanner(System.in);
		Student s;
		Operation obj =new Operation();
		while(true) {
			ch = din.readInt();
			switch(ch) {
			case 1:
				
				
				 s = new Student();
				 rollNo=din.readInt();
				s.rollNo=rollNo;
				name=din.readUTF();
				s.name=name;
				marks=din.readDouble();
				s.marks=marks;
				
				obj.addstudent(s);
				//System.out.println("student in list->"+list);
			dout.writeUTF("student in list->"+list);	
				break;
			case 2:
				 rollNo=din.readInt();
				 s = obj.search(rollNo);
				 if (s==null) {
				//	 System.out.println("not found:");
					 dout.writeUTF("not found:");
					 
				 }else {
			//	 System.out.println(s);
				 dout.writeUTF(" found:"+s);
				 }
				break;
			case 3:
				 rollNo=din.readInt();
				int t = obj.delete(rollNo);
				if(t==-1) {
					 //System.out.println("not found:");
					 dout.writeUTF("not found:\nstudent in list->"+list);
					
				}else {
					// System.out.println("no is deleted:");
					 dout.writeUTF("no is deleted\nstudent in list->"+list);
						
				}
			//	System.out.println("student in list->"+list);
				break;
			
			case 4:
			marks=din.readDouble();
			ArrayList<Student> v  =	obj.failedstudent(marks);
			//System.out.println("failed student:"+v);
			 dout.writeUTF("failed student:"+v);
				break;
			case 5:
				break;
			}
			}
		
		
		
	}

}
