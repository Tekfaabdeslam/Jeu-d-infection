package jeuDeReflexion;

public class Move {
	private Point start; //position de debut du pion
	private Point end;	 //position final
	private boolean type; /* vrai duplication
					* faux deplacement
					*/
	public Move(Point start, Point end, boolean type) {
		this.start = start;
		this.end = end;
		this.type = type;
	}

	public Move() {
	}
	
	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	public boolean getType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public String toString() {
		return "(" + this.start.toString() + "," + this.end.toString() + "," + this.type + ")";
	}

}
