import java.io.*;

public class StockMarket { //

    public Abstock[] stocks;

    public StockMarket() {
        stocks = new Abstock[10]; // 주식 수는 10개다. 뭔가 가격 0원되면 도산해서 사라지고 시간 좀 지나면 없던 회사 생겨나고 그런거 넣고 싶었는데 그럼 너무 더 복잡해질게 뻔히 보여서 그냥 이렇게 하기로 했다.
        resetStock();
    }

    public void resetStock() {
        stocks[0] = new StockStock("GOOGL", 10000, 10000); // 이름, 전일가, 현재가
        stocks[1] = new StockStock("NAVER", 5500, 5500); // 이름, 전일가, 현재가
        stocks[2] = new StockStock("TOSS", 6000, 6000); // 이름, 전일가, 현재가
        stocks[3] = new StockStock("APPLE", 10000, 10000); // 이름, 전일가, 현재가
        stocks[4] = new StockStock("TESLA", 8500, 8500); // 이름, 전일가, 현재가
        stocks[5] = new StockStock("AMAZON", 9000, 9000); // 이름, 전일가, 현재가
        stocks[6] = new StockStock("MICROSOFT", 9500, 9500); // 이름, 전일가, 현재가
        stocks[7] = new StockStock("FACEBOOK", 7500, 7500); // 이름, 전일가, 현재가
        stocks[8] = new StockStock("SAMSUNG", 8000, 8000); // 이름, 전일가, 현재가
        stocks[9] = new StockStock("KAKAO", 5000, 5000); // 이름, 전일가, 현재가
    }

    public void updateMarket() { // 주식을 업데이트한다.
        for (Abstock stock : stocks) { // 가격변동을 적용한다.
            stock.changeStock(); // 이걸로 ㅇㅇ
        }
    }
}
