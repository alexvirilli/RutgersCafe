package softmeth.project4fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.text.NumberFormat;
import java.util.Formatter;

public class Coffee extends MenuItem {

    private int cupSize;  //short = 0, tall = 1, grande = 2, venti = 3
    private ObservableList<String> addIns;  //each cost 0.30
    private int quantity;

    public static final int shortSize = 0;
    public static final int tallSize = 1;
    public static final int grandeSize = 2;
    public static final int ventiSize = 3;
    public static final double basePrice = 1.89;

    Formatter formatter = new Formatter();


    //constructors
    public Coffee(int cupSize){
        this.cupSize = cupSize;
        addIns = FXCollections.observableArrayList();
        this.quantity = 1;
    }

    public Coffee(int cupSize, ObservableList<String> addIns){
        this.cupSize = cupSize;
        this.addIns = addIns;
        this.quantity = 1;
    }
    //setter
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity){
        this.quantity -= quantity;
    }

    //private getters
    private String getSize(){
        if(cupSize == shortSize){
            return "short";
        } else if (cupSize == tallSize) {
            return "tall";
        } else if (cupSize == grandeSize) {
            return "grande";
        } else if (cupSize == ventiSize){
            return "venti";
        } else {
            return "invalid size";
        }
    }

    private String getAddIns(){
        if(addIns.size() == 0) {
            return "NONE";
        } else {
            String listAddIns = "";
            for(int i = 0; i < addIns.size(); i++){
                String add = addIns.get(i);
                if(i == 0) listAddIns = add;
                else listAddIns = listAddIns + ", " + add;
            }
            return listAddIns;
        }
    }

    //public getters
    public ObservableList<String> getListAddIns(){
        return this.addIns;
    }
    public int getLiteralCupSize(){
        return this.cupSize;
    }
    public int getQuantity(){
        return this.quantity;
    }

    //to string
    @Override
    public String toString(){
        String cup = getSize();
        String adds = getAddIns();
        return "COFFEE | SIZE: " + cup + " ADD INS: " + adds + " QUANTITY: " + quantity + " PRICE: $" + String.format("%.2f",itemPrice());
    }
    public void addIn(String addIn){
        addIns.add(addIn);
    }
    public double itemPrice(){
        double addInsPrice = addIns.size() * 0.30;
        double coffeePrice = basePrice + (0.40 * cupSize);
        double total = addInsPrice + coffeePrice * quantity;
        return total;
    }

    //equals
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(!(obj instanceof Coffee)) return false;
        Coffee coffee = (Coffee) obj;
        if(this.addIns.size() != coffee.getListAddIns().size()) return false;
        for(int i = 0; i < this.addIns.size(); i++ ){
            //we will always add the add-ins in the same order, so this will work
            if(!(this.addIns.get(i).equalsIgnoreCase(coffee.getListAddIns().get(i)))) return false;
        }
        if(this.cupSize != coffee.getLiteralCupSize()) return false;
        return true;
    }
}

