public  class Account {
    public int account;
    public int[] havestock;

    public Account(int stockCount){
        havestock = new int[stockCount];
    }

    public void buyStock(int stockIndex, int amount){
        havestock[stockIndex] += amount;
    }

    public void sellStock(int stockIndex, int amount){
        havestock[stockIndex] -= amount;
    }

    public int havehave(int stockIndex){
        return havestock[stockIndex];
    }
}