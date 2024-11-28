import javax.swing.*; //GUI
import java.awt.*; //레이아웃 관리
import java.awt.event.ActionEvent; //이벤트처리용
import java.awt.event.ActionListener; //버튼 클릭 이벤트 리스너

public class Main {
    private JFrame frame; //에플리케이션 메인 윈도우
    private JTextArea stockList; // 주식목록 표시하는 텍스트 영역
    private JTextArea newsArea; // 뉴스 내용을 표시하는 텍스트 영역
    private JTextField zeroac; // 사용자의 계좌 잔고를 표시
    private Account ac; // 계좌 정보 관리
    private Abstock stk; //주식 정보 관리
    private StockMarket stockMarket = new StockMarket(); // 주식시장 정보 관리
    private Trade trade = new Trade(); // 거래 처리
    private News news = new News(); // 뉴스 처리
    private int currentDay = 1; // 현재 날짜를 1로 설정 나중에 다음날 버튼 누르면 계속 증가할거임 아마도?


    public Main() {
        // 프레임 설정
        frame = new JFrame("주식 모의 투자 프로그램"); // 이름
        frame.setSize(600, 500); //창 크기 설정 (가로 600px, 세로 500px).
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 나가면 끝나는거다?
        frame.setLayout(new BorderLayout()); // 레이아웃 매니저를 BorderLayout으로 설정.
        frame.setLocationRelativeTo(null); // 중앙에 위치

        JPanel Panel = new JPanel(); // GUI 컴포넌트들을 배치할 패널 생성.
        Panel.setLayout(new GridLayout(2, 2)); //2행 2열의 격자 레이아웃으로 구성.

        JLabel Label = new JLabel("계좌 잔고:"); //"계좌 잔고:"라는 텍스트 라벨 생성.
        zeroac = new JTextField("0", 10); //초기값 0, 열 길이 10인 텍스트 필드 생성.
        zeroac.setEditable(false); // 사용자 수정 불가
        Panel.add(Label); //패널에 라벨과 텍스트 필드를 추가.
        Panel.add(zeroac); //패널에 라벨과 텍스트 필드를 추가.

        JButton CAB = new JButton("계좌 생성"); // "계좌 생성" 버튼 생성.
        Panel.add(CAB); // 버튼 및 초기 잔고 입력 필드 추가.

        JTextField firstac = new JTextField(10); // 초기 잔고를 입력받는 텍스트필드를 추가
        Panel.add(new JLabel("초기 잔고 입력:"));
        Panel.add(firstac);

        CAB.addActionListener(new ActionListener() { // 계좌 생성 버튼 클릭 시 이벤트 설정.
            public void actionPerformed(ActionEvent e) {
                try {
                    int savefac = Integer.parseInt(firstac.getText()); // 초기 잔고 입력값을 정수로 변환.
                    if (savefac < 0) {
                        JOptionPane.showMessageDialog(frame, "계좌 잔고는 0보다 커야 합니다."); // 입력값이 0보다 작으면 오류 메시지 출력.
                    } else {
                        ac = new Account(stockMarket.stocks.length, savefac); // 계좌 생성.
                        zeroac.setText(String.valueOf(ac.account)); // 잔고 업데이트
                        updateStockList(); // 주식 목록 갱신
                        JOptionPane.showMessageDialog(frame, "계좌가 생성되었습니다. 잔고: " + ac.account); //계좌 생성 결과 메시지 출력.
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "숫자를 입력하세요."); // 숫자 입력하라고 메세지박스 출력
                }
            }
        });


        // 주식 목록 출력 영역
        stockList = new JTextArea(10, 30); //텍스트 영역 생성 (10줄, 30글자 너비).
        stockList.setEditable(false); // 사용자가 텍스트 내용을 수정하지 못하도록 설정.
        updateStockList(); //주식 목록을 업데이트하는 메서드 호출.

        JScrollPane slscroll = new JScrollPane(stockList); // 텍스트 영역에 스크롤 기능 추가.

        // 뉴스 출력 영역
        newsArea = new JTextArea(5, 30); //뉴스 표시를 위한 텍스트 영역 생성 (5줄, 30글자 너비).
        newsArea.setEditable(false); //사용자가 수정하지 못하도록 설정.
        updateNews(); //뉴스 데이터를 업데이트.

        JScrollPane nsscroll = new JScrollPane(newsArea); //뉴스 텍스트 영역에 스크롤 추가.

        // 버튼 패널
        JPanel buttonPanel = new JPanel(); //버튼을 배치할 패널 생성.
        JButton buyButton = new JButton("주식 구매"); //"주식 구매" 버튼 생성.
        JButton sellButton = new JButton("주식 판매"); //"주식 판매" 버튼 생성.
        JButton nextDayButton = new JButton("다음 날"); //"다음 날" 버튼 생성.

        buttonPanel.add(buyButton); //패널에 버튼을 추가.
        buttonPanel.add(sellButton); //패널에 버튼을 추가.
        buttonPanel.add(nextDayButton); //패널에 버튼을 추가.

        // 버튼 동작 설정

        buyButton.addActionListener(new ActionListener() { // 구매 버튼
            public void actionPerformed(ActionEvent e) {
                String indexinput = JOptionPane.showInputDialog(frame, "구매할 주식 번호:"); // 팝업 창을 띄워 주식 번호와 구매 수량을 입력받음.
                String amountbuy = JOptionPane.showInputDialog(frame, "구매할 수량:"); // 구매 수량 입력
                try {
                    int stockIndex = Integer.parseInt(indexinput) - 1; // 입력값을 정수로 변환.
                    int amount = Integer.parseInt(amountbuy); // 입력값을 정수로 변환.

                    // 구매할 총 금액 계산
                    double totalCost = stockMarket.stocks[stockIndex].nowprice * amount; //총 구매 비용 계산.

                    // 잔고 확인
                    if (totalCost > ac.account) {
                        JOptionPane.showMessageDialog(frame, "잔고가 부족합니다. 현재 잔고: " + ac.account + "원", "오류",
                                JOptionPane.ERROR_MESSAGE);
                        return; // 더 이상 진행하지 않음
                    }

                    // 거래 실행
                    trade.doTrade(stockIndex, amount, stockMarket, ac, true);

                    zeroac.setText(String.valueOf(ac.account)); //잔고 업데이트.
                    updateStockList(); //주식 목록 갱신.
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "잘못된 입력입니다.");
                } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(frame, "유효하지 않은 주식 번호입니다.");
                }
            }
        });

        sellButton.addActionListener(new ActionListener() { // 팔기 버튼
            public void actionPerformed(ActionEvent e) {
                String sellnum = JOptionPane.showInputDialog(frame, "판매할 주식 번호:"); //팝업에서 입력받은 판매할 주식 번호.
                String sellamount = JOptionPane.showInputDialog(frame, "판매할 수량:"); //팝업에서 입력받은 판매 수량.
                try {
                    int stockIndex = Integer.parseInt(sellnum) - 1; // 주식 인덱스 변환
                    int amount = Integer.parseInt(sellamount); // 판매 수량 변환

                    // 보유 주식 수량 확인
                    int haves = ac.havehave(stockIndex); // 사용자가 보유한 주식 수량 확인.
                    if (amount > haves) {
                        JOptionPane.showMessageDialog(frame, "보유한 주식 수량이 부족합니다. 현재 보유 수량: " + haves + "주", "오류",
                                JOptionPane.ERROR_MESSAGE); //보유 수량 부족 여부 확인.
                        return; // 더 이상 진행하지 않음
                    }

                    // 거래 실행
                    trade.doTrade(stockIndex, amount, stockMarket, ac, false);

                    // 잔고 업데이트
                    zeroac.setText(String.valueOf(ac.account));
                    updateStockList(); //주식 목록 갱신.
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
        stockList.setText("구매 가능한 주식 목록:\n"); //기존 내용을 지우고 새 데이터를 작성.

        // Account 객체가 없을 경우 메시지 출력
        if (ac == null) {
            stockList.append("계좌를 생성한 후 주식 목록을 볼 수 있습니다.\n"); //계좌가 없을 경우 경고 메시지 출력.
            return;
        }

        // 계좌가 생성되었을 때 주식 목록 갱신
        for (int i = 0; i < stockMarket.stocks.length; i++) { //모든 주식 데이터를 한 줄씩 추가.
            Abstock currentStock = stockMarket.stocks[i];
            double changePercent = currentStock.changepersent(); // 변동률 계산.
            int ownedAmount = ac.havehave(i); // 보유 주식 수량 확인.

            // 보유 수익 및 총 구매 가격
            double profitOrLoss = (currentStock.nowprice - currentStock.yesprice) * ownedAmount; //보유 주식의 수익/손실 계산.
            double totalValue = currentStock.nowprice * ownedAmount; //보유 주식의 총 가치 계산.

            stockList.append(i + 1 + ". " + currentStock.name + ": 현재가 " + currentStock.nowprice +
                    "원 (변동률: " + String.format("%.2f", changePercent) + "%, 보유 수량: " + ownedAmount +
                    ", 수익: " + String.format("%.2f", profitOrLoss) + "원, 총 가치: " + String.format("%.2f", totalValue) + "원)\n");
        }
    }
// 난 바부 멍청이야아아아아아ㅏ아아아아아아ㅏ아아아.....


    private void updateNews() {
        newsArea.setText(news.getDailyNews());
    }

    public static void main(String[] args) {
        new Main();
    }
}
