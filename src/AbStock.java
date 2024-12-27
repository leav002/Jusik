

public abstract class AbStock{
    protected String name;
    protected double yesprice; //어제의 가격
    protected double nowprice; //오늘의 가격
    protected int ownedstock; //가진 주식

    public AbStock(String name, double yesprice, double nowprice){
        this.name = name;
        this.yesprice = yesprice;
        this.nowprice = nowprice;
        this.ownedstock = 0;
    }
    public double changepersent(){
        return yesprice != 0 ? ((nowprice - yesprice)/yesprice) * 100 : 0;//전날과 비교하여 오늘 가격의 등락을 보여준다.
    }

    public abstract void changeStockvalue();//주식의 가격 등락율을 결정한다.

}