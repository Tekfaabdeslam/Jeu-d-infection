package jeuDeReflexion;
import java.util.*;

public class Main {
		
		private static Scanner sc;

		public static void playing() {
		sc = new Scanner(System.in);
		System.out.print("\nEntrez la taille des la grille\nles lignes : ");
		
		int n=sc.nextInt();
		int profondeur1,profondeur2;
		
		System.out.print("\nles colonnes : ");
		int m=sc.nextInt();
	
		System.out.println("\n*Entrez la profondeur de raisonnement du joueur 1");
		profondeur1=sc.nextInt();
		
		System.out.println("\n*Entrez la profondeur de raisonnement du joueur 2");
		profondeur2=sc.nextInt();
		
		int choix;
		System.out.println("\n*Voullez vous une coupe alphabeta ?\n 1-OUI 2-NON");
		do {
			choix=sc.nextInt();
			if((choix!=1)&&(choix!=2)) {
				System.out.println("!!!CHOIX INVALIDE!!!");
			}
			}while((choix!=1)&&(choix!=2)); 
		
		boolean cut;
		if(choix==1) {
			cut=true;
		}
		else {
			cut=false;
		}
		
		int[][] board=null;
		State s=new State(n,m,board,1);
		System.out.println("etat initial  de la grille : \n");
		
		s.initBoard();
		s.affichage_Board();
		
		System.out.println("------------------\nscore initial :"+s.getScore(1)+"-"+s.getScore(2));
		
		
		
		
		IA ia=new IA(profondeur1,1,cut);
		IA ia2=new IA(profondeur2,2,cut);
		
		System.out.println("\nVoullez vous avantager le  joueur 1 ?\n1-OUI 2-NON");
		
		choix=sc.nextInt();
		
		if(choix==1) {
			System.out.print("Entrez le nombre de coups d'avance souhaiter (nombre <=:"+(s.getN()*s.getM())+")\n");
			
			
			do {
			choix=sc.nextInt();
			if((choix!=1)&&(choix!=2)) {
				System.out.println("!!!NOMBRE INVALIDE!!!");
			}
			}while(choix>(s.getN()*s.getM()));
			s=s.avantageJ1(choix,ia);
			}
		else {
			System.out.println("la partie commence normalement sans coups d'avance pour le joueur 1");
			}
		 
		System.out.println("\n** apres x-1 avantages **\n");// le joueur 1 commencera juste apres et donc aura x coups d'avances
		s.affichage_Board();
		Move goodMove=null,goodMove2=null;
		
		System.out.println("\n** debut du jeu **\n");
		if(!s.isFinished()) {
		while(!s.isFinished())
		{	
			
			goodMove=ia.decide(s,ia.getDeepness());
			s=s.play(goodMove);
			s.affichage_Board();
			System.out.println("----------------------\n"+s.getScore(1)+"-"+s.getScore(2)+"\n----------------------");
			if(!s.isFinished()) {
			goodMove2=ia2.decide(s,ia2.getDeepness());
			s=s.play(goodMove2);
			s.affichage_Board();
			System.out.println("----------------------\n"+s.getScore(1)+"-"+s.getScore(2)+"\n----------------------");}
			
		}
		}
			
			System.out.println("** partie terminee  **");
			System.out.println("score final :"+s.getScore(1)+"-"+s.getScore(2));
			System.out.println("-------------------------------------------");
			System.out.println("\n****details****");
			if(s.getScore(1)>s.getScore(2)) {
				System.out.println("\nVICTOIRE du joueur 1 (Blanc)");
			}
			else {
				System.out.println("\nVICTOIRE du joueur 2 (Noir)");
			}
			System.out.println("\nLe joueur 1 a parcouru  "+ia.getExp()+" noeuds");
			System.out.println("\nLe joueur 2 a parcouru  "+ia2.getExp()+" noeuds");
			System.out.println("\n*en total : "+(ia.getExp()+ia2.getExp())+"\n\n");
			
			
			
			}
		
	public static void main(String[] args) {

		Main.playing();

	}

}






