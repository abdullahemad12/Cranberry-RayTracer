package controllers.parsing;

public enum States {

    START(0) , INTERMEDIATE(1), INTERMEDIATE1(2), INTERMEDIATE2(3), INTERMEDIATE3(4),
    FINAL(5), ERROR(6);

    private int value;

    States(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}
