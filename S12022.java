import java.util.*;
import java.awt.*;
public class S12022 {


    public static String reverse_substring(String word, int startIndex, int endIndex){

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(word.substring(0, startIndex));
        
        sb.append(word.substring(startIndex,endIndex + 1));
        sb.reverse();
        sb2.append(sb.toString());
        sb2.append(word.substring(endIndex+1));
        return sb2.toString();
    }
    public static int getNumber() {
        Scanner input = new Scanner(System.in);
        int number = 0;
        while (true){
            System.out.println("Enter a number which is divisible by 3 but not 5:");
            number = input.nextInt();
            if (number % 3== 0 && number % 5 !=0){
                return number;
            }
        }
        
        
    }

    public static int[] getSquared(int[] numbers){

        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = numbers[i] * numbers[i];
    }
    return result;

    }

    public static void removeWordEndingWithe(ArrayList<String> words) {
        for (int i = words.size() - 1; i >= 0; i--) {
            String word = words.get(i);
            if (word.endsWith("e")) {
                words.remove(i);
            }
        }
    }

    enum Month {
        JAN(31), FEB(29), MAR(31), APR(30), MAY(31), 
        JUN(30), JUL(31),AUG(31), SEP(30), 
        OCT(31), NOV(30), DEC(31);
        private int days;
        private Month(int days){
            this.days = days;
        }

        public int getNumberOfDays() {
            return days;
        }

    }
    public static void main(String[] args) {
        TriangleSequence ds = new TriangleSequence(5);
        Iterator<Integer> iterator  = ds.iterator();
        for (int i: ds)
        System.out.print(i + " ");
            }

}

class Rugby{

    private String countryName;
    private int points;
    public Rugby() {
        this("N/A", 0);
    }
    public Rugby(String country) {
        this(country, 0);
    }
    public Rugby(String country, int points) {
        this.countryName = country;
        this.points = points;
    }
    
    public String getCountryName() {
        return this.countryName;
    }
    public void setCountryName(String country) {
        this.countryName = country instanceof String && country.length() > 0? country:this.countryName;
    }

    public int getPoints() {
        return this.points;
    }
    public void setPoints(int points) {
        this.points = points >=0 ? points:this.points;
    }
    public String toString() {
        return String.format("Country: %s(%d)", this.countryName, this.points);
    }
}

class MyCircle {
    private Point centre;
    private int radius;
    public MyCircle() {
        this.centre = new Point(0,0);
        this.radius = 1;
    }
    public MyCircle(int x, int y, int radius) {
        this.centre = new Point(x, y);
        this.radius = radius;
    }
    public boolean contains(Point other){
        double distance = centre.distance(other);
        return distance < radius;
    }
    @Override
    public String toString() {
        return String.format("Circle at (%d, %d), radius=%d",this.centre.x, this.centre.y, this.radius);
    }
}

class MySubCircle extends Point {
    private int radius;

    public MySubCircle() {   
        this.radius = 1;
    }
    public MySubCircle(int x, int y, int radius) {
        super(x, y);
        this.radius = radius;
        
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("Circle at (%d, %d), radius=%d", super.x, super.y, this.radius);
    }
    public boolean contains(Point other){
        double distance = super.distance(other);
        return distance < radius;
    }
}

abstract class Employee {
    protected String name;
    protected int baseSalary = 800; //base salary per week
    public Employee(String name) { this.name = name; }
    public Employee(String name, int base) { this.name = name; baseSalary = base; }
    public String toString() { return String.format("%s gets $%.2f per week.", name, calculateSalary()); }
    public abstract double calculateSalary();
   }

class Secretary extends Employee{
    private double hourlyRate = 21.20;
    private int numberOfHours = 40;

    public Secretary(String name) { super(name); }
    public Secretary(String name, double rate, int hours) { 
        super(name);
        this.hourlyRate = rate;
        this.numberOfHours = hours;
    }
    public double getHourlyRate() {
        return hourlyRate;
    }
    @Override
    public double calculateSalary() {
        // TODO Auto-generated method stub
        return hourlyRate * numberOfHours;
    }
}
class Manager extends Employee{
    private int bonus = 100;
    public Manager(String name) { super(name); }
    public Manager(String name, int bonus) {
        super(name);
        this.bonus = bonus;
    }

    @Override
    public double calculateSalary() {
        // TODO Auto-generated method stub
        return baseSalary+ this.bonus;
    }
}

interface Measurable {
    public double getMeasure();
  }

  class MyLine implements Measurable{
    private Point pt1, pt2 ;
    public MyLine() {this(0,0,0,0);}
    public MyLine(int x1, int y1, int x2, int y2) {
      pt1 = new Point(x1, y1);  // Construct the instances declared
      pt2 = new Point(x2, y2);
    }
    public double getLength(){
        return pt1.distance(pt2);
    }
    public double getMeasure() {
        // TODO Auto-generated method stub
        return getLength();
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("(%d, %d) to (%d, %d)" ,this.pt1.x, this.pt1.y, this.pt2.x, this.pt2.y);
    }
    
  }

  class Country implements Measurable{
    private String countryName;
    private double landArea;
    private int population;
    public Country(String name, int pop, double area) { countryName = name; landArea = area; population = pop; }
    
    public double getPopulationDensity() {
        return population/landArea;
    }
    
    public double getMeasure(){
        return this.getPopulationDensity();
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.format("%s(%.2f)", countryName, getPopulationDensity());
    }
  }

  
  class TriangleSequence implements Iterable<Integer>{
    private int n;
    public TriangleSequence(int n) { this.n = n; }
    public Iterator<Integer> iterator() { return new TriangleIterator(); }
    //complete the inner class here
  
    class TriangleIterator implements Iterator<Integer>{
        private int index;
        private int value;
        public boolean hasNext(){
            return index < n;
        };
        public Integer next(){
            this.value += ++index;
            return value;
        };
        public void remove(){
            throw new UnsupportedOperationException();
        }

    }
    
}

