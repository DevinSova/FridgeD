package hackers.purdue.firstbusinesscompany.fridged;
import java.util.ArrayList;

public class arrayListFunctions {

    public static ArrayList<String> addItem (ArrayList<String> Items, String Item){
        Items.add(Item);
        return Items;
    }
    public static boolean searchItem (ArrayList<String> Items, String Item){
        return Items.contains(Item);
    }
    public static boolean deleteItem (ArrayList<String> Items, String Item){
        if(Items.contains(Items)){
            Items.remove(Item.indexOf(Item));
            return true;
        }
        else{
            return false;
        }

    }
    public static ArrayList<String> sortArrayList (ArrayList<String> Items, char ch){

        if(ch == 'A'){              //Ascending Order
            for(int i=0; i<(Items.size()-1); i++){
                if((Items.get(i).compareToIgnoreCase(Items.get(i+1))) > 0){
                    String temp = Items.get(i);
                    Items.set(i, Items.get(i+1));
                    Items.set((i+1), temp);
                }
                else{
                    continue;
                }
            }
        }
        else if(ch == 'D'){         //Descending Order
            for(int i=0; i<(Items.size()-1); i++){
                if((Items.get(i).compareToIgnoreCase(Items.get(i+1))) < 0){
                    String temp = Items.get(i);
                    Items.set(i, Items.get(i+1));
                    Items.set((i+1), temp);
                }
                else{
                    continue;
                }
            }
        }
        else{
            System.out.println("Wrong Input");
            return Items;
        }
//<<<<<<< HEAD
    return Items;
//=======
        //return null;
//>>>>>>> 0e848b4720eb724d771508f19c68f2f0287bb75a
    }


}
