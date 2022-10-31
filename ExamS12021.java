import java.io.ObjectInputFilter.Status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
enum Temperature { HUCKLE, BOILING, HOT, WARM, COOL, COLD, FREEZING;
    private static final int SIZE = values().length;

    public static final Temperature getTemperature(int index) { 
      return values()[index < SIZE ? index : 6];
    }
  }
  enum RelationshipType { FAMILY, RELATIVE, FRIEND, COLLEAGUE; }
  class Person {
    protected String name, phoneNumber;
    public Person(String n, String p) {  name = n; phoneNumber = p; }
    public String getName() {return name;}
    public String getPhoneNumber() { return phoneNumber; }
    public String toString() { return String.format("%s (%s)", name, phoneNumber);}
  }

class Contact extends Person implements Comparable<Contact> {
    private RelationshipType relationship = RelationshipType.FRIEND;

    public Contact(String n, String p) {
        super(n, p);
        //TODO Auto-generated constructor stub
    }
    public Contact(String n, String p, RelationshipType rt) {
        super(n, p);
        this.relationship = rt;
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString() + String.format(", %s (%s)", this.getPhoneNumber(),this.relationship.toString());
    }

    public int compareTo(Contact other){
        if (this.relationship.equals(other.relationship)){
            return this.name.compareTo(other.name);
        }
        return this.relationship.compareTo(other.relationship);
        
    }
}

class PhoneBook implements Iterable<Contact> {
    private ArrayList<Contact> myContacts = new ArrayList<Contact>();
    private String name;
    public PhoneBook(String n) { name = n; }
    public void addContact(Contact c) { myContacts.add(c); }
    public java.util.Iterator<Contact> iterator() {
      return (java.util.Iterator<Contact>) new ContactsIterator();
    } 

    class ContactsIterator implements Iterator<Contact>{
        private int nextIndex  = 0;
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return nextIndex < myContacts.size();
        }

        @Override
        public Contact next() {
            // TODO Auto-generated method stub
            return myContacts.get(nextIndex++);
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            
        }

        
        
    }
}


class Table {
  private static final int MAX_CHAIRS_POSSIBLE = 4;
  private List<Chair> _chairs;
  //constructor
  public Table() {
    _chairs = new ArrayList<Chair>();
    Chair newChar = new Chair();
    this.assembleWith(newChar);
  }
  //method adds a chair to the table
  public boolean assembleWith(Chair chair) {
    if( _chairs.size() == MAX_CHAIRS_POSSIBLE )
        return false;   //returns failure
    _chairs.add(chair);
    // System.out.println(this.getChairs().size());
    chair.attachTo(this);
    return true;        //returns success
  }
  //method removes the last chair (in the list) from the table
  public boolean disassemble() {
    if( _chairs.size() <= 1 )
        return false;   //returns failure
    // _chairs.removeAll(_chairs);
    // for(Chair chair: _chairs) {
    //     chair.detach();
    // }
    Chair lastChair = _chairs.get(_chairs.size() -1);
    _chairs.remove(lastChair);
    lastChair.detach();
    return true;         //returns success
  }
  public List<Chair> getChairs(){
     return _chairs;
  }
}

class Chair {
  private Table _table;
  private boolean _isAttached = false;
  public Chair() {
  }
  //method for attaching a chair to a table
  public boolean attachTo(Table table) {
    if(_isAttached)
        return false;      //returns failure
    _table = table;
    _isAttached = true;
    return _isAttached;  //returns success
  }
  //method for detaching a chair from a table
  public boolean detach() {
    if(!_isAttached)
        return false;      //returns failure
    _table = null;
    _isAttached = false;
    return true;         //returns success
  }
  public Table getTable(){
      return _table;
  }
}
class Hospital {
    private static Hospital _hospital;
    private String _hospitalName = "Max Health";
    private Hospital(){

    }
    public static Hospital getHospital(){   
        if (_hospital == null){
            _hospital =  new Hospital();
        }     
        return _hospital;
    }
    public String getHospitalName() {
        return _hospitalName;
    } 
}

interface RentalProperty {
    public void getRent(int numberofBedrooms);	
  }
  
  class Apartment implements RentalProperty{
    int apartmentInflationFactor = 150;
      
    @Override
    public void getRent(int numberOfBedrooms){		  
      System.out.println("Rent for this Apartment is: "+ numberOfBedrooms*apartmentInflationFactor);
    }
  }
  class TownHouse implements RentalProperty{
    int townHouseInflationFactor = 200;
          
    @Override
    public void getRent(int numberOfBedrooms){		  
      System.out.println("Rent for this Town House is: "+ numberOfBedrooms*townHouseInflationFactor);
    }
  }
  class Unit implements RentalProperty{
    int unitInflationFactor = 240;
      
    @Override
    public void getRent(int numberOfBedrooms){		  
       System.out.println("Rent for this Unit is: "+ numberOfBedrooms*unitInflationFactor);
    }
  } 

class RentalFactory 
public class ExamS12021{
    public static int sum_over(int[][] numbers, int target){
        int sum = 0;
        for (int[] rows : numbers){
            for(int num : rows){
                if (num > target){
                    sum+=num;
                }
            }
        }
        return sum;
    } 
    public static void main(String[] args) {
        // Contact[] contacts = new Contact[]{ new Contact("John", "7589943"), new Contact("Albert", "1234567", RelationshipType.COLLEAGUE), new Contact("Ann", "2646754", RelationshipType.RELATIVE), new Contact("Andrew", "4533753", RelationshipType.FAMILY) };
        // Arrays.sort(contacts);
        // System.out.println(Arrays.toString(contacts));
        // Table t3 = new Table();
        // for(int i=0; i<3; i++){
        // t3.assembleWith(new Chair());
        // }
        // t3.disassemble();
        // t3.disassemble();
        // System.out.println(t3.getChairs().size());
                    
        Table t3 = new Table();
        Chair c3 = t3.getChairs().get(0);
        System.out.println(t3.disassemble());
    }
}
