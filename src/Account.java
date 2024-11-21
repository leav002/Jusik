public class Account {
    public int account;
    public int[] havestock;

    public Account(int stockCount, int initialBalance) {
        this.account = initialBalance;
        havestock = new int[stockCount]; // 주식 보유 수량을 저장하는 배열
    }

    public void buyStock(int stockIndex, int amount) {
        havestock[stockIndex] += amount; // 주식살때 주식증가
    }

    public void sellStock(int stockIndex, int amount) {
        havestock[stockIndex] -= amount; // 주식팔때 주식감소
    }

    public int havehave(int stockIndex) {
        return havestock[stockIndex]; // 가진 주식 보유량
    }
}
