package logica;

public class HighScore implements Comparable<HighScore> {
	private String name;
	private Integer score;
	
	public HighScore(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}

	@Override
	public String toString() {
		return name+"\t"+score.toString();
	}
	
	public int getScore(){
		return score;
	}

	@Override
	public int compareTo(HighScore another) {
		return score.compareTo(another.getScore());
	}
	
	
	
	

}
