package jeuDeReflexion;

import java.util.*;

public class IA {

	private int deepness;
	private int player;
	private boolean cut; // vrai pour alphabeta , faux pour minmax
	private int exp=0;  //variable servant a connaitre le nombre de noeuds visites
	 public int getDeepness() {
		return deepness;
	}

	

		

	public IA(int deepness, int player, boolean cut) {
		this.deepness = deepness;
		this.player = player;
		this.cut = cut;
	}

	
	
	//getters and setters
	
	public void setDeepness(int deepness) {
		this.deepness = deepness;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public boolean isCut() {
		return cut;
	}

	public void setCut(boolean cut) {
		this.cut = cut;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}
	
	
	public Move decide(State s, int deepness) {
		float v = Float.NEGATIVE_INFINITY;
		float v2;
		Move moveChoosed = new Move();
		ArrayList<Move> lm = s.getMoves(this.player);
		for (Move a : lm) {
			State s2 = s.play(a);
			if (this.cut == false) {
				v2 = minMax(s2, deepness - 1);
			} else {
				v2 = alphaBeta(s2, 1, 0, deepness - 1);
			}
			if (v2 > v) {
				v = v2;
				moveChoosed = a;

			}

		}

		return moveChoosed;

	}

	public float minMax(State s, int deepness) {
		exp++;
		float b;
		float m;
		ArrayList<Move> listM = s.getMoves(this.player);
		if ((deepness == 0) || (s.isFinished())) {
			return s.getScore(this.player);
		} else {
			if (s.getCurrentPlayer() == this.player) {
				b = Float.NEGATIVE_INFINITY;
				for (Move a : listM) {
					State s2 = s.play(a);
					m = minMax(s2, deepness - 1);
					if (b < m) {
						b = m;
					}

				}
				 

			}

			else {
				b = Float.POSITIVE_INFINITY;
				for (Move a : listM) {
					State s2 = s.play(a);
					m = minMax(s2, deepness - 1);
					if (b > m) {
						b = m;
					}

				}
			}
			return b;
		}
	}

	public float alphaBeta(State s, float alpha, float beta, int deepness) {
		exp++;
		ArrayList<Move> listM = s.getMoves(this.player);
		if ((deepness == 0) || (s.isFinished())) {
			return s.getScore(this.player);
		} else {
			if (s.getCurrentPlayer() == this.player) {
				for (Move a : listM) {
					State s2 = s.play(a);
					alpha = Float.max(alpha, alphaBeta(s2, alpha, beta, deepness - 1));
					if (alpha >= beta) {
						return alpha; 
					}

				}
				return alpha;
			}

			else {

				{
					for (Move a : listM) {
						State s2 = s.play(a);
						beta = Float.min(alpha, alphaBeta(s2, alpha, beta, deepness - 1));
						if (alpha >= beta) {
							return beta;
						}

					}
					return beta;
				}
			}

		}

	}
}
