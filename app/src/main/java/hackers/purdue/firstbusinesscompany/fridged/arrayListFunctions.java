package hackers.purdue.firstbusinesscompany.fridged;
import java.util.ArrayList;

/**
 * Created by kimeric on 2016. 10. 15..
 */

public class arrayListFunctions {

    public static ArrayList<String> addItem (ArrayList<String> foodItems, String foodItem){
        foodItems.add(foodItem);
        return foodItems;
    }
    public static boolean searchItem (ArrayList<String> foodItems, String foodItem){
        return foodItems.contains(foodItem);
    }
    public static boolean deleteItem (ArrayList<String> foodItems, String foodItem){
        if(foodItems.contains(foodItems)){
            foodItems.remove(foodItem.indexOf(foodItem));
            return true;
        }
        else{
            return false;
        }

    }
    public static ArrayList<String> sortArrayList (ArrayList<String> foodItems, char ch){

        if(ch == 'A'){              //Ascending Order

        }
        else if(ch == 'D'){         //Descending Order
        }
        else{
            System.out.println("Wrong Input");
            return foodItems;
        }
        return null;
    }


}
