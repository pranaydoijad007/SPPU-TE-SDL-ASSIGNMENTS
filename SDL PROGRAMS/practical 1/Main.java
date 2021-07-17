package com.main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

import com.datatype.Student;
import com.operation.Operation;

public class Main {
	public static ArrayList<Student> list = new ArrayList<Student>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Operation obj = new Operation();
		Scanner sc = new Scanner(System.in);
		int ch;
		int rollNo;
		String name;
		double marks;
		Student s;
		do{
			System.out.println("1.Add student\n2.search student\n3.delete Student\n4.Failed students\n5.Exit");
			System.out.println("Enter Your Choice");
			ch = sc.nextInt();
		
		switch(ch){
		case 1:
			
			System.out.println("Enter Your roll no");
			rollNo=sc.nextInt();
			System.out.println("Enter Your name");
			name=sc.next();
			System.out.println("Enter Your marks");
			marks=sc.nextDouble();
			
			 s = new Student();
			
			s.rollNo=rollNo;
			s.name=name;
			s.marks=marks;
			
			obj.addstudent(s);
			System.out.println("student in list->"+list);
			
			break;
		case 2:
			System.out.println("Enter roll no to be searched:");
			rollNo=sc.nextInt();
			 s = obj.search(rollNo);
			 if (s==null) {
				 System.out.println("not found:");
			 }
			 System.out.println(s);
			break;
		case 3:
			System.out.println("Enter roll no to be deleted:");
			rollNo=sc.nextInt();
			int t = obj.delete(rollNo);
			if(t==-1) {
				 System.out.println("not found:");
				
			}else {
				 System.out.println("no is deleted:");
			}
			System.out.println("student in list->"+list);
			break;
		case 4:
			System.out.println("Enter passing marks:");
			marks=sc.nextDouble();
			System.out.println("Enter roll no to be deleted:");
			ArrayList<Student> v  =	obj.failedstudent(marks);
			System.out.println("failed student:"+v);
			break;
		case 5:
			break;
		default:
			System.out.println("Invalid choice!"); 
		}
		}while(ch!=5);

	}

}
