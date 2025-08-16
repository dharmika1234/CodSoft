import java.util.Scanner;

public class StudentGradeCalculator {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of subjects");
		int noofsubs=sc.nextInt();
		int[] marks=new int[noofsubs];
		int totalmarks = 0;
		System.out.println("Enter the marks for subjects out of 100");
		for(int i=0;i<noofsubs;i++)
		{
			marks[i] = sc.nextInt();
			if(marks[i]<0 || marks[i]>100) {
				System.out.println("Invalid! please enter the marks in range of 1-100");
				marks[i] = sc.nextInt();
			}
			totalmarks += marks[i];
		}
		
		int averagepercentage = totalmarks/noofsubs;
		System.out.println("Total Marks for all subjects are: "+totalmarks);
		System.out.println("Average Percentage for all subjects are: "+averagepercentage);
		
		if(averagepercentage>=90)
			System.out.println("Grade A");
		else if(averagepercentage>=80 && averagepercentage<90)
			System.out.println("Grade B");
		else if(averagepercentage>=70 && averagepercentage<80)
			System.out.println("Grade c");
		else if(averagepercentage>=60 && averagepercentage<70)
			System.out.println("Grade D");
		else if(averagepercentage>=50 && averagepercentage<60)
			System.out.println("pass");
		else if(averagepercentage<50)
			System.out.println("Fail");
		
sc.close();
	}

}
