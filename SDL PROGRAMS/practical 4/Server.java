package com.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.common.Student;

public class Server {
	public static ArrayList<Student> list = new ArrayList<Student>();

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		
		
		ServerSocket ss = new ServerSocket(5000);
		while(true) {
		Socket cs =	ss.accept();
		threadbody obj = new threadbody(cs);
		obj.start();
		}
		
		
	}

}
class threadbody extends Thread{
	Socket cs =null;
	public threadbody(Socket cs) {
		// TODO Auto-generated constructor stub
		this.cs=cs;
	}
	public void run() {
		try {
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
				
				int result =obj.addstudent(s);
				if(result==0)

				
				
				
				
				
				
				
				
				{
					dout.writeUTF("student not added succeessfully");	
				}
				else {
				//System.out.println("student in list->"+list);
			dout.writeUTF("student in list->");
				}
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
				if(t==0) {
					 //System.out.println("not found:");
					 dout.writeUTF("not found:\nstudent in list->");
					
				}else {
					// System.out.println("no is deleted:");
					 dout.writeUTF("no is deleted\nstudent in list->");
						
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
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}