import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.*;

enum Material { WOOD, GRASS; }

public class Midsemester {
    public static void main(String[] args) {
        Base b = new Derived(10);
        System.out.println(b.x);
        System.out.println(((Derived)b).x);
        // BasicUniqueEven numbers = new BasicUniqueEven();
        // numbers.addAll(new int[]{5, 2, 63, 46, 67, 24, 72, 2, 63});
        // for (int i: numbers) System.out.println(i);
    }
    public static String getFireRisk(double rainfall, Material item) {

        if (rainfall >=20){
            if (item == Material.GRASS){
                return "MEDIUM";
            }
            else{
                return "LOW";
            }
        }
        else{
            if (item == Material.GRASS){
                return "HIGH";
            }
            else{
                return "MEDIUM";
            }
        }
        
    }

    public static String getSecretCode(String word, int[] positions) {
        StringBuilder sb = new StringBuilder();
        for (int num : positions) {
            sb.append(word.charAt(num - 1));
        }
        return sb.toString();
    }

    public static boolean no_palindromes(String[] values){
        for (String word:values){
            StringBuilder sb = new StringBuilder(word);
            sb.reverse();
            if(sb.toString().equals(word)){
                return false;
            }
        }
        return true;
        
    }

    public static ArrayList<Integer> getLeftPartition(int[] numbers){
        ArrayList<Integer> left = new ArrayList<Integer>();
        int pivot = numbers[0];
        for (int num :numbers){
            if (num < pivot){
                left.add(num);
            }
        }
        return left;

    }

    public static void clearExtremes(int[] numbers){
        for (int i = 0; i < numbers.length; i++){
            if (numbers[i] > 1000 || numbers[i]< -1000){
                numbers[i] = 0;
            }
        }
    }

    public static void removeExtremes(ArrayList<Integer> numbers){

        for (int i = numbers.size() - 1; i >=0; i--){
            if (numbers.get(i) > 1000 || numbers.get(i)< -1000){
                numbers.remove(i);
            }
        }
    }
}

enum Month{
    JAN("January brings the snow"),FEB("February brings the rain"),MAR("March brings breezes loud and shrill"),APR("April brings the primrose sweet");

    private String poem;
    private Month(String poem){
        this.poem = poem;
    }

    public String getPoem(){
        return this.poem;
    }
}

abstract class Property implements Comparable<Property>{
    protected int propertyId = 1, numberOfBedrooms = 1;
    protected int basicRent = 0, factor = 1;
    public Property(int id, int rent) {
      propertyId  = id;
      basicRent = rent;
    }
    public Property(int id, int rent, int factor, int number) {
      propertyId = id;
      basicRent = rent;
      this.factor = factor;
      numberOfBedrooms = number;
    }
    public int getPropertyId() { return propertyId; }
    public abstract int calculateRent();
    public String toString() { return String.format("%s(%d),rent=%d", getClass().getName(), propertyId, calculateRent()); }
    
    public int compareTo(Property other){
        return this.calculateRent() - other.calculateRent();
    }
}

class Apartment extends Property{
    private boolean gymAvailable;

    public Apartment(int id, int rent, boolean gymAvailable){
        super(id, rent);
        this.gymAvailable = gymAvailable;
    }

    public int calculateRent() {
        return this.basicRent + (this.gymAvailable ? 100:0);
    }
}

class House extends Property{
    private boolean hasGarden ;
    public House(int id, int rent,int bedrooms, boolean hasGarden){
        super(id, rent, 120, bedrooms);
        this.hasGarden = hasGarden;
    }

    public int calculateRent() {
        return this.basicRent + (this.numberOfBedrooms *this.factor) +(this.hasGarden ? 100:0);
    }

}


class CompareByID  implements Comparator<Property>{

    @Override
    public int compare(Property o1, Property o2) {
        // TODO Auto-generated method stub
        return o1.propertyId - o2.propertyId;
    }    
}


class BasicUniqueEven implements Iterable<Integer>{
    private ArrayList<Integer> items;

    public BasicUniqueEven(){
        this.items = new ArrayList<Integer>();

    }

    public boolean add (int number){
        if(number % 2 == 0 && !items.contains(number)){
            items.add(number);
            return false;
        }
        else{
            return false;
        }
    }
    public void addAll(int[] array){
        for (int number : array){
            this.add(number);
        }
    }

    public void removeByValue(int number) {
        if (this.contains(number)){
            this.items.remove(new Integer(number));
        }
    }
    public boolean contains(int number) {
        return this.items.contains(number);
    }
    public int getSize(){
        return this.items.size();
    }
    public int get(int index){
        return this.items.get(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < items.size(); i++){
            if(i != items.size() - 1){
                sb.append(this.items.get(i) +", ");
            }
            else{
                sb.append(this.items.get(i));
            }
        }
        return String.format("[%s]", sb.toString());
    }

    @Override
    public Iterator<Integer> iterator() {
        // TODO Auto-generated method stub
        return new UniqueEvenIterator(this);
    }
}
class UniqueEvenIterator  implements Iterator<Integer>{
    private int nextIndex = 0;
    private BasicUniqueEven list;
    
    public UniqueEvenIterator(BasicUniqueEven bue){
        this.list = bue;
    }
    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return (this.nextIndex < list.getSize());
    }

    @Override
    public Integer next() {
        // TODO Auto-generated method stub
        return this.list.get(nextIndex++);
    }
    
}


class SubUniqueEven  extends ArrayList<Integer>{
    public SubUniqueEven(){

    }
    public boolean add (int number){
        if(number % 2 == 0 && !this.contains(number)){
            super.add(number);
            return false;
        }
        else{
            return false;
        }
    }
    public void addAll(int[] array){
        for (int number : array){
            this.add(number);
        }
    }

    public void removeByValue(int number) {
        if (super.contains(number)){
            super.remove(new Integer(number));
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < super.size(); i++){
            if(i != super.size() - 1){
                sb.append(super.get(i) +", ");
            }
            else{
                sb.append(super.get(i));
            }
        }
        return String.format("[%s]", sb.toString());
    }
}

class Base {
    protected int x;
    public Base(){x = 1;}
}

class Derived extends Base{
    protected int x;
    public Derived(int x){this.x = super.x + x;}
}