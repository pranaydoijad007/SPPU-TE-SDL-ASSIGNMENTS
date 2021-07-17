package com.server;

import java.util.ArrayList;
import java.util.Iterator;

import com.common.Student;


public class Operation {
	public void addstudent(Student s){
		Server.list.add(s);
		
		
	
	}
	
	public Student search(int rollNo){
		Student s =null;
		Iterator<Student> e = Server.list.iterator();
		while(e.hasNext()) {
			s= e.next();
			if(s.rollNo==rollNo) {
				break;
			}
			else {
				s=null;
			}
			
		}
		
		return s;
	}
	public int delete(int rollNo) {
		Student s =null;
		int index = -1;
		Iterator<Student> e = Server.list.iterator();
		while(e.hasNext()) {
			s= e.next();
			if(s.rollNo==rollNo) {
			index =	Server.list.indexOf(s);
			Server.list.remove(index);
				break;
			}
			
		}
		return index;
		
		
	}
	public ArrayList<Student> failedstudent(double marks){
		ArrayList<Student> failedlist= new ArrayList<Student>();
		Student s =null;
		Iterator<Student> e = Server.list.iterator();
		while(e.hasNext()) {
			s=(Student) e.next();
			if(s.marks<marks) {
				failedlist.add(s);
			}
		
		
	}
		return failedlist;

}
}
