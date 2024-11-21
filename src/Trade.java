public class Trade {
    public void doTrade(int stockIndex, int amount, StockMarket stockMarket, Account ac, boolean BorS) {
        Abstock stock = stockMarket.stocks[stockIndex]; // 주식정보 가져오기

        if (BorS) { // 트루일 때 구매
            double totalCost = stock.nowprice * amount; // 전체 구매비용을 계산
            if (ac.account >= totalCost) { // 계좌돈이 전체 구매비용보다 많다면,
                ac.account -= totalCost; // 그만큼 제하고 주식 구매
                ac.buyStock(stockIndex, amount); // 보유 주식 수량 업데이트
                System.out.println(stock.name + " 주식 " + amount + "주를 구매했습니다.");
                System.out.println("남은 잔고: " + ac.account + "원");
            } else { // 계좌에 돈 없을 때
                System.out.println("잔고가 부족합니다.");
            }
        } else { // 펄스일 때 판매
            int ownedAmount = ac.havehave(stockIndex); // Account에서 현재 보유량 가져오기
            if (ownedAmount >= amount) { // 보유한 주식이 판매하려는 양보다 많다면
                ac.sellStock(stockIndex, amount); // 보유 주식 수량 업데이트
                double totalIncome = stock.nowprice * amount; // 총 수익을 계산한다.
                ac.account += totalIncome; // 계좌의 그 수익을 추가한다.
                stock.ownedstock -= amount; // 주식 수량 감소
                System.out.println(stock.name + " 주식 " + amount + "주를 판매했습니다.");
                System.out.println("현재 잔고: " + ac.account + "원");
            } else { // 보유한 주식 수량이 부족할 때
                System.out.println("보유한 주식 수량이 부족합니다.");
            }
        }

    }

}
