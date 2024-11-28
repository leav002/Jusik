public abstract class Abstock{
    protected String name;
    protected double yesprice; //어제의 가격
    protected double nowprice; //오늘의 가격
    protected int ownedstock; //가진 주식

    public Abstock(String name, double yesprice, double nowprice){
        this.name = name;
        this.yesprice = yesprice;
        this.nowprice = nowprice;
        this.ownedstock = 0;
    }
    public double changepersent(){
        return yesprice != 0 ? ((nowprice - yesprice)/yesprice) * 100 : 0;//전날과 비교하여 오늘 가격의 등락을 보여준다.
    }

    //TODO : 이곳은  주식으로 얼마나 벌고 잃었는지를 표시해주는 메소드이다. 거래 클래스 만들고 다시 올 것.(아마도 일단은 완료했다. 아닌가? 아 그냥 됐다고 하자)
    //ㅇㅇ 완료한거 맞음
    public abstract void changeStockvalue();//주식의 가격 등락율을 결정한다.

}