package Sample;

public class DollarToRupee {

    private Double rupeeValue;
    private Double dollarValue;

    public Double converter(Double dollarValue){
        this.dollarValue = dollarValue;
        this.rupeeValue = this.dollarValue * 74;
        return this.rupeeValue;
    }
}
