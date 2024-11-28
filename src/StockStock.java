import java.util.Random;

public class StockStock extends Abstock {

    public StockStock(String name, double yesprice, double nowprice) {
        super(name, yesprice, nowprice);
    }

    @Override
    public void changeStockvalue() {
        Random rd = new Random();
        yesprice = nowprice; // 어제의 가격을 현재 가격으로 업데이트한다.

        // 가격 변화를 퍼센트로 적용하기 위한 코드
        double percentageChange = (rd.nextDouble() * 10) - 5; // -5% ~ +5% 사이의 변동률
        //todo : 이거 나중에 퍼센트로 바꾸는걸로 수정하자고 ㅇㅇ 이거 솔직히 좀 짜치긴 해
        //완료했음.
        nowprice = nowprice * (1 + percentageChange / 100);
        nowprice = Math.max(Math.round(nowprice * 100) / 100.0, 1); // 소수점 2자리로 반올림 후 최소값 보장
    }
}
