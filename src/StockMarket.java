public class StockMarket {

    public Abstock[] stocks;

    public StockMarket(){
        stocks = new Abstock[10]; //종목의 수를 지정한다.
        setStock();
    }

    public void setStock(){
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

    public void updateMarket(){ //주식의 가격을 업데이트 하는 메소드
        for(Abstock stock : stocks){
            stock.changeStockvalue();
        }
    }
}