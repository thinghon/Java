package poketgame;

public class Monitor {
    private final int TOTAL_WIDTH = 50;

    public void printBorder() {
        System.out.println("─".repeat(TOTAL_WIDTH));
    }

    public void printBoxedMessage(String... lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public void printIntro() {
        printBorder();
        printBoxedMessage(
            "                  포켓몬 콘솔게임",
            "[ 조작법 안내 ]",
            "W: 위 | A: 왼쪽 | S: 아래 | D: 오른쪽 ",
            "B: 가방열기 | Q: 종료",
            "[ 게임 정보 ]",
            "*: 주인공 | O: 오박사 | X: 다른 트레이너",
            "P: 포켓몬 센터 | S: 상점 | T: 다음 맵 이동 | K: 보스"
        );
        printBorder();
    }
}
