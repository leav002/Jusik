

public class Account {
    public int account;
    public int[] havestock;

    public Account(int stockCount, int initialBalance) { // 초기 잔고 추가
        havestock = new int[stockCount];
        this.account = initialBalance;
    }

    public void buyStock(int stockIndex, int amount) { //트레이드 클래스 구매에서 처리
        havestock[stockIndex] += amount;
    }

    public void sellStock(int stockIndex, int amount) { //트레이드 클래스 판매에서 처리
        havestock[stockIndex] -= amount;
    }

    public int havehave(int stockIndex) {
        return havestock[stockIndex];
    }
}
