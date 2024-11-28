
public class Trade {

    public void doTrade(int stockIndex, int amount, StockMarket stockMarket, Account ac, boolean BorS){

        AbStock stock = stockMarket.stocks[stockIndex];

        if(BorS){ //불린값이 트루일때 구매를 진행한다.
            double totalCost = stock.nowprice * amount;

            if(ac.account >= totalCost){
                ac.account -= totalCost;
                ac.buyStock(stockIndex,amount);
                System.out.println(stock.name + "주식 " + amount + "주를 구매했습니다.");
                System.out.println("남은 잔고 : " + ac.account + "원");
            }else{
                System.out.println("잔고가 부족합니다.");
            }
        } else { // 펄스일 때 판매
            int ownedAmount = ac.havehave(stockIndex);
            if (ownedAmount >= amount) {
                ac.sellStock(stockIndex, amount);
                double totalIncome = stock.nowprice * amount;
                ac.account += totalIncome;
                stock.ownedstock -= amount;
                System.out.println(stock.name + " 주식 " + amount + "주를 판매했습니다.");
                System.out.println("현재 잔고: " + ac.account + "원");
            } else { // 보유한 주식 수량이 부족할 때
                System.out.println("보유한 주식 수량이 부족합니다.");
            }
        }

    }

}
