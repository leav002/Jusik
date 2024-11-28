
import java.util.*;
import java.io.*;

public class News {
    private String currentNews; // 생선된 뉴스 문구를 저장한다.
    private boolean GenNews = false; // 뉴스 생성 여부 판단.


    private String[] who = { "테슬라", "애플", "구글", "삼성", "아마존", "네이버", "카카오", "토스", "마이크로소프트", "페이스북" };
    private String[] when = { "오늘", "어제", "이번 주", "올해", "최근 분기" };
    private String[] where = { "미국에서", "한국에서", "유럽에서", "중국에서", "일본에서", "주요 글로벌 시장에서", "뉴욕 증권거래소에서", "나스닥에서" };
    private String[] what = { "자율주행 기술", "신제품", "클라우드 서비스", "반도체", "AI 서비스", "물류 시스템", "새로운 사물인터넷 기술", "주식 분할", "IPO",
            "주식 환매", "신규 투자 발표", "실적 발표", "배당금 증가" };
    private String[] how = { "혁신적인 방법으로", "대규모로", "성공적으로", "신속하게", "비밀리에", "전략적으로", "투자자들의 기대 속에서",
            "주식 시장에 긍정적인 영향을 미치며", "기관 투자가들의 관심을 끌며" };
    private String[] why = { "경쟁에서 앞서기 위해", "시장 점유율을 확대하기 위해", "기술 리더십을 유지하기 위해", "신규 고객을 확보하기 위해", "주가 상승을 견인하기 위해",
            "주주 가치를 극대화하기 위해", "장기적인 성장 전략의 일환으로" };

    // 위에 있는 뭔가 쓸데없이 길어보이는 단어들을 조합하여 뉴스 문장을 만든다.(뉴스의 어떤 특정 키워드에 반응하여 가격변동에 영향을 주는거 만들고 싶은데 아직 구현 못함)

    public String getDailyNews() { // 이곳에서 단어들을 조합하여 뉴스를 생성한다.
        if (!GenNews) {
            Random rand = new Random();
            String whoPart = who[rand.nextInt(who.length)]; // 랜덤으로 하나 끌어온다.
            String whenPart = when[rand.nextInt(when.length)]; // 랜덤으로 하나 끌어온다.
            String wherePart = where[rand.nextInt(where.length)]; // 랜덤으로 하나 끌어온다.
            String whatPart = what[rand.nextInt(what.length)]; // 랜덤으로 하나 끌어온다.
            String howPart = how[rand.nextInt(how.length)]; // 랜덤으로 하나 끌어온다.
            String whyPart = why[rand.nextInt(why.length)]; // 랜덤으로 하나 끌어온다.

            currentNews = "뉴스 : " + whenPart + " " + wherePart + " " + whoPart + "이(가) " + whatPart + "을(를) " + howPart
                    + " 발표했습니다. 그 이유는 " + whyPart + "입니다."; // 뉴스 문장을 저장한다.
            GenNews = true; // 뉴스 만들어졌다고 다시 설정
        }
        return currentNews; // 생성된 뉴스 반환
    }

    public void newDay() {
        GenNews = false; // 새로운 날이 되면 뉴스가 없는걸로 바뀌고 새로운 뉴스를 만들도록 한다.
        System.out.println("다음 날로 넘어갑니다.");
    }

    public void newsImpact(){
        //TODO : 여기서 뉴스에 따라 가격 등락에 영향가게 하는거 만들어야 한다. 하는데...하는데... 아 몰라 못해 시간 없다. 미리 좀 할걸
        //그러게 미리 좀 하지
    }
}