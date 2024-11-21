import javax.swing.*; //GUI
import java.awt.*; //레이아웃 관리
import java.awt.event.ActionEvent; //이벤트처리를 위해
import java.awt.event.ActionListener; //있는거라고 하네요

public class Main {
    private JFrame frame;
    private JTextArea stockList; // 주식목록
    private JTextArea newsArea; // 뉴스
    private JTextField zeroac; // 잔고
    private Account ac; // 계좌 끌어오고
    private Abstock stk;
    private StockMarket stockMarket = new StockMarket(); // 주식시장 끌어오고
    private Trade trade = new Trade(); // 거래 끌어오고
    private News news = new News(); // 뉴스 끌어오고
    private int currentDay = 1; // 현재 날짜를 1로 설정 나중에 다음날 버튼 누르면 계속 증가할거임 아마도


    public Main() {
        // 프레임 설정
        frame = new JFrame("주식 모의 투자 프로그램"); // 이름
        frame.setSize(600, 500); // 크기
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 나가면 끝나는거다?
        frame.setLayout(new BorderLayout()); // 레이아웃설정
        frame.setLocationRelativeTo(null); // 중앙에 위치

        JPanel Panel = new JPanel(); // 계좌 정보를 입력받기 위한 패널
        Panel.setLayout(new GridLayout(2, 2)); // 크기 지정

        JLabel Label = new JLabel("계좌 잔고:");
        zeroac = new JTextField("0", 10); // 잔고 표시할 텍스트 박스
        zeroac.setEditable(false); // 사용자 수정 불가
        Panel.add(Label);
        Panel.add(zeroac);

        JButton CAB = new JButton("계좌 생성"); // 계좌생성 버튼
        Panel.add(CAB); // 계좌 생성

        JTextField firstac = new JTextField(10); // 초기 잔고를 입력받는 텍스트필드를 추가
        Panel.add(new JLabel("초기 잔고 입력:"));
        Panel.add(firstac);

        CAB.addActionListener(new ActionListener() { // 계좌 생성 버튼 눌렀을때 발동
            public void actionPerformed(ActionEvent e) {
                try {
                    int savefac = Integer.parseInt(firstac.getText()); // int형으로 변환해 유효한지 검사
                    if (savefac < 0) {
                        JOptionPane.showMessageDialog(frame, "계좌 잔고는 0보다 커야 합니다."); // 0보다 작으면 오류 메세지 출력
                    } else {
                        ac = new Account(stockMarket.stocks.length, savefac);
                        zeroac.setText(String.valueOf(ac.account));
                        updateStockList(); // 주식 목록 갱신
                        JOptionPane.showMessageDialog(frame, "계좌가 생성되었습니다. 잔고: " + ac.account);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "숫자를 입력하세요."); // 숫자 입력하라고 메세지박스 출력
                }
            }
        });

        // 주식 목록 출력 영역
        stockList = new JTextArea(10, 30);
        stockList.setEditable(false); // 사용자수정불가
        updateStockList();

        JScrollPane slscroll = new JScrollPane(stockList); // 스크롤 기능

        // 뉴스 출력 영역
        newsArea = new JTextArea(5, 30);
        newsArea.setEditable(false);
        updateNews();

        JScrollPane nsscroll = new JScrollPane(newsArea);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        JButton buyButton = new JButton("주식 구매");
        JButton sellButton = new JButton("주식 판매");
        JButton nextDayButton = new JButton("다음 날");

        buttonPanel.add(buyButton);
        buttonPanel.add(sellButton);
        buttonPanel.add(nextDayButton);

        // 버튼 동작 설정

        buyButton.addActionListener(new ActionListener() { // 구매 버튼
            public void actionPerformed(ActionEvent e) {
                String indexinput = JOptionPane.showInputDialog(frame, "구매할 주식 번호:"); // 주식 번호 입력
                String amountbuy = JOptionPane.showInputDialog(frame, "구매할 수량:"); // 구매 수량 입력
                try {
                    int stockIndex = Integer.parseInt(indexinput) - 1; // 주식 인덱스 변환
                    int amount = Integer.parseInt(amountbuy); // 구매 수량 변환

                    // 구매할 총 금액 계산
                    double totalCost = stockMarket.stocks[stockIndex].nowprice * amount;

                    // 잔고 확인
                    if (totalCost > ac.account) {
                        JOptionPane.showMessageDialog(frame, "잔고가 부족합니다. 현재 잔고: " + ac.account + "원", "오류",
                                JOptionPane.ERROR_MESSAGE);
                        return; // 더 이상 진행하지 않음
                    }

                    // 거래 실행
                    trade.doTrade(stockIndex, amount, stockMarket, ac, true);

                    zeroac.setText(String.valueOf(ac.account));
                    updateStockList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "잘못된 입력입니다.");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "유효하지 않은 주식 번호입니다.");
                }
            }
        });

        sellButton.addActionListener(new ActionListener() { // 팔기 버튼
            public void actionPerformed(ActionEvent e) {
                String sellnum = JOptionPane.showInputDialog(frame, "판매할 주식 번호:");
                String sellamount = JOptionPane.showInputDialog(frame, "판매할 수량:");
                try {
                    int stockIndex = Integer.parseInt(sellnum) - 1; // 주식 인덱스 변환
                    int amount = Integer.parseInt(sellamount); // 판매 수량 변환

                    // 보유 주식 수량 확인
                    int haves = ac.havehave(stockIndex); // 보유 주식 수량 가져오기
                    if (amount > haves) {
                        JOptionPane.showMessageDialog(frame, "보유한 주식 수량이 부족합니다. 현재 보유 수량: " + haves + "주", "오류",
                                JOptionPane.ERROR_MESSAGE);
                        return; // 더 이상 진행하지 않음
                    }

                    // 거래 실행
                    trade.doTrade(stockIndex, amount, stockMarket, ac, false);

                    // 잔고 업데이트
                    zeroac.setText(String.valueOf(ac.account));
                    updateStockList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "잘못된 입력입니다.");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "유효하지 않은 주식 번호입니다.");
                }
            }
        });

        nextDayButton.addActionListener(new ActionListener() { // 다음날 버튼
            public void actionPerformed(ActionEvent e) {
                currentDay++; // 날짜를 하루 증가
                stockMarket.updateMarket(); // 주식 시장 갱신
                news.newDay(); // 새로운 날이 되면 뉴스 갱신 준비
                String dailyNews = news.getDailyNews(); // 새로운 뉴스 생성
                updateStockList(); // 주식 목록 갱신
                updateNews(); // 뉴스 갱신

                JOptionPane.showMessageDialog(frame, "현재 날짜: " + currentDay + "일째\n" + dailyNews);// 날짜와 뉴스를 표시
            }
        });

        // 프레임에 패널 추가
        frame.add(Panel, BorderLayout.NORTH);
        frame.add(slscroll, BorderLayout.CENTER);
        frame.add(nsscroll, BorderLayout.SOUTH);
        frame.add(buttonPanel, BorderLayout.EAST);

        frame.setVisible(true);
    }

    // 주식 목록 업데이트 (보유 수량 추가)
    private void updateStockList() {
        stockList.setText("구매 가능한 주식 목록:\n");

        // Account 객체가 생성되지 않았을 경우에는 주식 목록을 업데이트하지 않음
        if (ac == null) {
            stockList.append("계좌를 생성한 후 주식 목록을 볼 수 있습니다.\n");
            return;
        }

        // 계좌가 생성되었다면 주식 목록과 보유 수량을 출력
        for (int i = 0; i < stockMarket.stocks.length; i++) {
            double changePercent = stockMarket.stocks[i].changeper();
            int haves = ac.havehave(i); // 보유 수량 가져오기
            double stockMoney = (stk != null) ? stk.getstockmoney() : 0; // stk가 null인 경우 0으로 설정
            double totalbuycost;

            stockList.append(i + 1 + ". " + stockMarket.stocks[i].name + ": 현재가 " + stockMarket.stocks[i].nowprice
                    + "원 (변동률: " + String.format("%.2f", changePercent) + "%, 보유 수량: " + haves + " 수익 : " + stockMoney + " 총 구매가격 : " + ")\n");
        }
    }


    private void updateNews() {
        newsArea.setText(news.getDailyNews());
    }

    public static void main(String[] args) {
        new Main();
    }
}