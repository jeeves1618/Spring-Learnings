package net.phenotype.SpringBatch.ListReader.ListReader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.Iterator;
import java.util.List;

public class ListItemReader implements ItemReader<String > {

    private final Iterator<String> listIterator;

    public ListItemReader(List<String> listIterator) {
        this.listIterator = listIterator.iterator();
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if(this.listIterator.hasNext()){
            return this.listIterator.next();
        } else {
            /*
            The read will not be invoked after the null is returned
             */
            return null;
        }
    }
}
