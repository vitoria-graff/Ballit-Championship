package dados;

import java.util.Random;

public class Partida {
    private final Time time1;
    private final Time time2;
    private int pontosTime1;
    private int pontosTime2;

    public Partida(Time time1, Time time2) {
        this.time1 = time1;
        this.time2 = time2;
        this.pontosTime1 = 50;
        this.pontosTime2 = 50;
    }

    public Time getTime1() {
        return time1;
    }

    public Time getTime2() {
        return time2;
    }

    public int getPontosTime1() {
        return pontosTime1;
    }

    public int getPontosTime2() {
        return pontosTime2;
    }

    public void adicionarBlotTime1() {
        pontosTime1 += 5;
        time1.adicionarPontos(5);
        time1.adicionarBlots(1);
    }

    public void adicionarBlotTime2() {
        pontosTime2 += 5;
        time2.adicionarPontos(5);
        time2.adicionarBlots(1);
    }

    public void adicionarPlifTime1() {
        pontosTime1 += 3;
        time1.adicionarPontos(3);
        time1.adicionarPlifs(1);
    }

    public void adicionarPlifTime2() {
        pontosTime2 += 3;
        time2.adicionarPontos(3);
        time2.adicionarPlifs(1);
    }

    public Time getVencedor() {
        if (pontosTime1 > pontosTime2) {
            return time1;
        } else if (pontosTime2 > pontosTime1) {
            return time2;
        } else {
            return realizarGrusht();
        }
    }

    public Time realizarGrusht() {
        int decibeisTime1 = medirDecibeis(time1);
        int decibeisTime2 = medirDecibeis(time2);

        if (decibeisTime1 > decibeisTime2) {
            pontosTime1 += 3;
            return time1;
        } else {
            pontosTime2 += 3;
            return time2;
        }
    }

    private int medirDecibeis(Time time) {
        return new Random().nextInt(100);
    }

    public void aplicarAdvrungh(Time time) {
        if (time.equals(time1)) {
            pontosTime1 -= 10;
            time1.adicionarPontos(-10);
            time1.adicionarAdvrunghs(1);
        } else {
            pontosTime2 -= 10;
            time2.adicionarPontos(-10);
            time2.adicionarAdvrunghs(1);
        }
    }

    @Override
    public String toString() {
        return time1.getNome() + " (" + pontosTime1 + ") x (" + pontosTime2 + ") " + time2.getNome();
    }
}



