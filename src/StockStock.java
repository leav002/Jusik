import java.util.Random;

public class StockStock extends Abstock {

    public StockStock(String name, double yesprice, double nowprice) {
        super(name, yesprice, nowprice);
    }

    public void changeStock() {
        Random ran = new Random();
        yesprice = nowprice; // 어제 가격을 현재 가격으로 업데이트
        int priceChange = ran.nextInt(1000) - 450; // 가격의 변동폭을 지정한다.
        nowprice = Math.max(nowprice + priceChange, 1); // 최소 가격을 1로 보장하고 현재가격에 가격변화를 적용한다.
    }
}
