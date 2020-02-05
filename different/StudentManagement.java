package different;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;

public class StudentManagement {
	private Vector<Student> students = new Vector<Student>();

	public class Student {
		private String name;
		private int id;
		private float gpa;

		private Student(int id, String name, float gpa) {
			if (id < 0)
				this.id = 0;
			else
				this.id = id;
			if (name.isEmpty() || name == null)
				this.name = "There is no name";
			else
				this.name = name;
			if (gpa < 0)
				this.gpa = 0;
			else
				this.gpa = gpa;
		}

		@Override
		public String toString() {
			return "Student [name=" + name + ", id=" + id + ", gpa=" + gpa + "]";
		}

	}

	public StudentManagement(int id, String name, float gpa) {
		students.add(new Student(id, name, gpa));
	}

	public void inputStudent() {
		// just create 5 random... easy
	}

	public void showStudents() {
		for (Student x : students)
			System.out.println(x);
	}

	public void sortByGpa() {
		Collections.sort(students, new Comparator<Student>() {
			public int compare(Student o1, Student o2) {
				return (int) (o1.gpa - o2.gpa);
			}
		});
	}

	public void sortByName() {
		Collections.sort(students, new Comparator<Student>() {
			public int compare(Student o1, Student o2) {
				return o1.name.compareTo(o2.name);
			}
		});
	}

	public void deleteStudent(int Id) {
		Iterator<Student> i = students.iterator();
		while (i.hasNext())
			if (i.next().id == Id)
				i.remove();
	}

	public boolean searchStudent(String name) {
		for (Student x : students)
			if (x.name == name)
				return true;
		return false;
	}

}
