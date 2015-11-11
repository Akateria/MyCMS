package context;


import java.util.ArrayList;
import java.util.Objects;

public class PaginationBuilder {

    ArrayList<?> list;
    int amountOnPage;
    int pageNumber;

    public PaginationBuilder(ArrayList<?> list, int amountOnPage, int pageNumber) {
        this.list = list;
        this.amountOnPage = amountOnPage;
        this.pageNumber = pageNumber;
    }

    public ArrayList<?> build(Class T){
        ArrayList<Object> listPagination = new ArrayList<>();
        int lastNumber = amountOnPage*pageNumber;
        int firstNumber = lastNumber - amountOnPage + 1;
        int currentNumber = firstNumber;
        if(list.size() < lastNumber){
            lastNumber = list.size();
        }
        while (currentNumber <= lastNumber){
            Object objects = list.get(currentNumber - 1);
            listPagination.add(objects);
            currentNumber++;
        }
        return listPagination;

    }
}
