package dados;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {
    private List<Time> times;
    private final List<Partida> partidas;

    public Campeonato() {
        times = new ArrayList<>();
        partidas = new ArrayList<>();
    }

    public void adicionarTime(Time time) {
        times.add(time);
    }

    public void adicionarPartida(Partida partida) {
        partidas.add(partida);
    }

    public List<Time> getTimes() {
        return times;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void limparPartidas() {
        partidas.clear();
    }

    public boolean numeroDeTimesValido() {
        int numTimes = times.size();
        return numTimes >= 8 && numTimes <= 16 && numTimes % 2 == 0;
    }

    public boolean podeAdicionarTime() {
        return times.size() < 16;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }
}


