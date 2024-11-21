public abstract class Abstock {
    protected String name;
    protected double yesprice; // 어제 가격
    protected double nowprice; // 오늘 가격
    protected int ownedstock; // 가진 주식

    public Abstock(String name, double yesprice, double nowprice) { // 뭐 그냥 주식 가격 정보 가지고 있는 클래스라고 해야하려나
        this.name = name;
        this.yesprice = yesprice;
        this.nowprice = nowprice;
        this.ownedstock = 0;
    }

    public double changeper() {
        return yesprice != 0 ? ((nowprice - yesprice) / yesprice) * 100 : 0; // 이건 아마 주식 어제 가격이랑 오늘 가격 비교해서 얼마나 오르고 내렸나 알려주는 부분이였을걸
    }

    public double getstockmoney() {
        return (nowprice - nowvalue()) * ownedstock; // 보유한 주식으로 얼마나 잃었나 벌었나 보는 용도
    }

    public double nowvalue() {
        return nowprice * ownedstock; // 이건 아마 보유주식의 총 가격 알려주는 용도
    }

    public abstract void changeStock(); // 이건 StockStock 가서 보세요
}
