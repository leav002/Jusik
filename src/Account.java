public class Account {
    public int account;
    public int[] havestock;

    public Account(int stockCount, int initialBalance) { // 초기 잔고 추가
        havestock = new int[stockCount];
        this.account = initialBalance;
    }

    public void buyStock(int stockIndex, int amount) {
        havestock[stockIndex] += amount;
    }

    public void sellStock(int stockIndex, int amount) {
        havestock[stockIndex] -= amount;
    }

    public int havehave(int stockIndex) {
        return havestock[stockIndex];
    }
}
