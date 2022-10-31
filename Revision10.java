import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.io.*;
import java.util.*;
import javax.swing.event.*;
// import java.swing.tree.DefaultTreeModel;
class CountryTreeDemo extends JFrame {
	JTextField nameTextField, indexTextField;
	JButton addButton, removeButton;
	JTree tree;
	DefaultTreeModel treeModel;
	DefaultMutableTreeNode root;

  public CountryTreeDemo() {
	root = new DefaultMutableTreeNode("New Zealand");
	root.add(new DefaultMutableTreeNode("Auckland"));
	root.add(new DefaultMutableTreeNode("Hamilton"));
	root.add(new DefaultMutableTreeNode("Christchurch"));
	treeModel = new DefaultTreeModel(root);
	tree = new JTree(treeModel);

  	nameTextField = new JTextField("Name", 20);
  	indexTextField = new JTextField("1", 5);
  	addButton = new JButton("Add");
  	removeButton = new JButton("Remove");
	addButton.addActionListener(new AddListener());
  	removeButton.addActionListener(new RemoveListener());
	JPanel buttonsPanel = new JPanel();
	buttonsPanel.add(new JLabel("Enter:"));
    buttonsPanel.add(nameTextField);
    buttonsPanel.add(addButton);
    buttonsPanel.add(new JLabel("Remove by index:"));
    buttonsPanel.add(indexTextField);
	buttonsPanel.add(removeButton);
	getContentPane().add(buttonsPanel, BorderLayout.NORTH);
	getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(400, 200);
	setVisible(true);
	}
    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String text = nameTextField.getText();
            int numOfChildren = root.getChildCount();
            treeModel.insertNodeInto(new DefaultMutableTreeNode(text), root, numOfChildren);   
        }    
    }
    class RemoveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            int index = Integer.parseInt(indexTextField.getText());
            int numOfChildren = root.getChildCount();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)treeModel.getChild(root, index);
            if (index >=0 && numOfChildren > index){
                treeModel.removeNodeFromParent(node);
            }
        }    
    }

    class SelectListener implements TreeSelectionListener{

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            // TODO Auto-generated method stub
            if(tree.getLastSelectedPathComponent() != null){
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                String country = (String)selectedNode.getUserObject();
                nameTextField.setText(country);
            }
            
        }
        
    }
	//complete inner classes here

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() { new CountryTreeDemo();}
	});
  }
}
abstract class Person {
    private String name;
    Person parent = null;
    public Person(String name) { this.name = name; }
    public String getName() {return name; }
    public Person getParent() { return parent; }
    public void setParent(Person p) { parent = p; }
    public String toString() { return name; }
    public Person[] getPath() { return getPathToRoot(this, 0); }
    protected Person[] getPathToRoot(Person aNode, int depth) {
      Person[] retNodes;
      if(aNode == null) {
         if(depth == 0) return null;
         else retNodes = new Person[depth];
      } else {
         depth++;
         retNodes = getPathToRoot(aNode.getParent(), depth);
         retNodes[retNodes.length - depth] = aNode;
      }
      return retNodes;
    }
    public static void linkFamily(Person obj, Person[] kids) {
        CompositePerson p = (CompositePerson)obj;
        for (Person kid : kids)
         p.add(kid);
    }
  }
  class SinglePerson extends Person {
      public SinglePerson(String name) { super(name); }
  }
  class CompositePerson extends Person {
    private List<Person> children = new ArrayList<Person>();
    public CompositePerson(String name) { super(name); }
    public void add(Person p) {
      p.setParent(this);
      children.add(p);
    }
    public void remove(Person p) {
      children.remove(p);
      p.setParent(null);
    }
    public int getSize() { return children.size(); }
    public Person getChildAt(int index) { return children.get(index); }
    public int indexOf(Person kid) { return children.indexOf(kid); }
    public Person[] getChildren() {
      Person[] arr = new Person[children.size()];
      arr = children.toArray(arr);
      return arr;
    }
  }
  // complete the tree model
  
  class FamilyTreeDemo extends JFrame {
    JButton addButton, addParentButton, removeButton;
    JTextField nameTextField;
    JTree tree;
    FamilyTreeModel treeModel;
    Person root;
  
    public FamilyTreeDemo() {
      root = new CompositePerson("Jack");
      Person b1 = new SinglePerson("Peter");
      Person b2 = new SinglePerson("Zoe");
      Person.linkFamily(root, new Person[] { b1, b2 });
      treeModel = new FamilyTreeModel(root);
      tree = new JTree(treeModel);
      tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
      addButton = new JButton("Add");
      addParentButton = new JButton("Add Parent");
      removeButton = new JButton("Remove");
      nameTextField = new JTextField("David");
    //	addButton.addActionListener(new AddListener());
    //	addParentButton.addActionListener(new AddParentListener());
    //	removeButton.addActionListener(new RemoveListener());
      JPanel buttonsPanel = new JPanel();
      buttonsPanel.add(nameTextField);
      buttonsPanel.add(addButton);
      buttonsPanel.add(addParentButton);
      buttonsPanel.add(removeButton);
      getContentPane().add(buttonsPanel, BorderLayout.NORTH);
      getContentPane().add(new JScrollPane(tree), BorderLayout.CENTER);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setSize(400, 200);
      setVisible(true);
    }
    class FamilyTreeModel implements TreeModel{
        private Person root;
        private ArrayList<TreeModelListener> treeModelListeners;

        public FamilyTreeModel(Person root){
            this.root = root;
            treeModelListeners = new ArrayList<TreeModelListener>();

        }
        @Override
        public Object getRoot() {
            // TODO Auto-generated method stub
            
            return this.root;
        }

        @Override
        public Object getChild(Object parent, int index) {
            // TODO Auto-generated method stub
            if(parent instanceof CompositePerson){
                CompositePerson castedParent = (CompositePerson)parent;
                return index < this.getChildCount(castedParent) && index >=0 ? castedParent.getChildAt(index): null;
            }
            
            return null;
            
            
        }

        @Override
        public int getChildCount(Object parent) {
            // TODO Auto-generated method stub
            if(parent instanceof CompositePerson){
                CompositePerson castedParent = (CompositePerson)parent;
                return castedParent.getSize();
            }
            return 0;
        }

        @Override
        public boolean isLeaf(Object node) {
            // TODO Auto-generated method stub
            if(node instanceof CompositePerson){
                return this.getChildCount(node) == 0;
            }
            return true;
        }

        
        @Override
        public int getIndexOfChild(Object parent, Object child) {
            // TODO Auto-generated method stub
            if(parent instanceof CompositePerson){
                CompositePerson castedParent = (CompositePerson)parent;
                return castedParent.indexOf((Person)child);
            }
            return -1;
        }
        public void fireTreeNodesInserted(Object source, Object[] path,int[] childIndices,Object[] children){
            TreeModelEvent event = new TreeModelEvent(source,path,childIndices,children);
            for (TreeModelListener tml : treeModelListeners) {
                tml.treeNodesInserted(event);
            }
        }
        public void insertNodeInto(Person new_node, Person parent, int index) {
            if (parent instanceof CompositePerson){
                CompositePerson castedParent = (CompositePerson)parent;
                castedParent.add(new_node);
                int[] childIndex = {index};
                Object[] children = {new_node};
                fireTreeNodesInserted(this, castedParent.getPath(),childIndex, children);
            }
        }
        public void fireTreeNodesRemoved(Object source, Object[] path, int[] childIndices,Object[] children){
            TreeModelEvent tme = new TreeModelEvent(source, path, childIndices, children);
            for (TreeModelListener tml : treeModelListeners){
                tml.treeNodesRemoved(tme);
            }
        }
        public void removeNodeFromParent(Person selectedNode) {
        
            CompositePerson parent = (CompositePerson) selectedNode.getParent();
            int index = parent.indexOf(selectedNode);
            parent.remove(selectedNode); 
            int[] childIndex = {index};
            Object[] children = {selectedNode};
            fireTreeNodesRemoved(this, parent.getPath(),childIndex, children);
        }
        @Override
        public void addTreeModelListener(TreeModelListener l) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void removeTreeModelListener(TreeModelListener l) {
            // TODO Auto-generated method stub
            
        }
        @Override
        public void valueForPathChanged(TreePath path, Object newValue) {
            // TODO Auto-generated method stub
            
        }


    }
    class AddListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String name = nameTextField.getText();
            SinglePerson  newPerson = new SinglePerson (name);
            if (tree.getSelectionPath()!=null){
                Person node =   (Person)tree.getLastSelectedPathComponent();
                if (node instanceof CompositePerson){
                    CompositePerson composite = (CompositePerson)node;
                    int numOfChildren = composite.getSize();
                    treeModel.insertNodeInto(newPerson, composite, numOfChildren);
                }
            }
        }
        
    }
    class AddParentListener  implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            String name = nameTextField.getText();
            SinglePerson  newPerson = new SinglePerson (name);
            if (tree.getSelectionPath()!=null){
                Person node =   (Person)tree.getLastSelectedPathComponent();
                if (node instanceof CompositePerson){
                    CompositePerson composite = (CompositePerson)node;
                    int numOfChildren = composite.getSize();
                    treeModel.insertNodeInto(newPerson, composite, numOfChildren);
                }
            }
            
        }
        
    }
    class RemoveListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
           
            if (tree.getSelectionPath()!=null){
                Person node =   (Person)tree.getLastSelectedPathComponent();
                if (!node.equals(root)){
                    // Person composite = (Person)node;
                   treeModel.removeNodeFromParent(node);
                    
                }
            }
        }
        
    }
    //complete inner classes here
  
  
    public static void main(String[] args) {
     javax.swing.SwingUtilities.invokeLater(new Runnable() {
       public void run() { new FamilyTreeDemo(); } });
    }
  }

public class Revision10 {
    
}
