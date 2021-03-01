package jeuDeReflexion;

import java.util.*;
public class State {
	
	private int n,m;
	private int[][] board;
	private int currentPlayer;

	
	
	
	//constructeurs 
	public State(int n,int m,int[][] board,int currentPlayer) {
		this.n=n;
		this.m=m;
		this.board= board;
		this.currentPlayer=currentPlayer;
		
			
		
	}
	
	public State() {}
	 
	//getters et setters 
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getN() {
		return n;
	}
	public int getM() {
		return m;
	}
	
	
	
	//methode initialisant la grille au debut de partie  
	
	public int[][] initBoard()
	{
		int[][] board=new int[n][m];
		for (int i=0;i<this.n;i++)
		{
			for(int j=0;j<this.m;j++)
			{
				board[i][j]=0;
				
		}
	}
	board[0][0]=2; //joueur noir 2
	board[n-1][m-1]=1;//joueur blanc 1
		
	this.board=board;
	return this.board;
	
	}
	
	
	public void affichage_Board()
	{
		for(int i=0;i<this.n;i++)
		{
			for(int j=0;j<this.m;j++)
			{
				System.out.print(this.board[i][j]+" "); 
				}
			System.out.println(" ");
		}
	}
	
	// methode qui renvoie true si une partie est terminer false sinon 
	//on verifie d'abord si on a des mouvements possible ,ensuite si il exite qu'un joueur dans la grille
	//si c'est le cas donc la partie est terminee
	public boolean isFinished() {
		
		boolean b=false;
		int i=0,j=0;
		if((this.getMoves(1).size()==0)||
				(this.getMoves(2).size()==0))
		{
			return true;
		}
		else {
			while((i<this.n)&&(b=false)){
				while((j<this.m)&&(b=false)){
					if((this.board[i][j]!=0)||((this.board[i][j]==1)||
							(this.board[i][j]==0))||((this.board[i][j]==2)
									||(this.board[i][j]==0))) 
						{ 
							b=true;
						}
					j++;
				}
				i++;
		
		
			} 
	}
		return b;
}
	
	//methode qui retourne les positions des pions d'un joueur donee
	
	public ArrayList<Point> getPosition(int player )
	{	
		ArrayList<Point> pos=new ArrayList<Point>();
		for(int i=0;i<this.n;i++)
		{
			for(int j=0;j<this.m;j++)
			{
				if(this.board[i][j]==player)
				{
					Point p=new Point(i,j);
					pos.add(p);
				}
			}
		}
		return pos;
	}
	
	// methode retournant une array list des positions où l'on peut se deplacer 
	public ArrayList<Move> getMoves(int player){ //1 pour blanc 
												//2  pour noir
		ArrayList<Point> pos =this.getPosition(player);
		ArrayList<Move> moves=new ArrayList<Move>();
		for(Point p: pos)
		{
			//ajout des move de type duplication
					 
			
			if(p.getX()+1 < this.n && p.getX()+1 >= 0 && (this.board[p.getX()+1][p.getY()] == 0) && p.getX()+1 < this.n) {
				Point end=new Point(p.getX()+1,p.getY());				
            	Move m=new Move(p,end,true);			 //bas
            	moves.add(m);   
            }
            if(p.getX()-1 < this.n && p.getX()-1 >=0 && (this.board[p.getX()-1][p.getY()] == 0 )) {
            	Point end=new Point(p.getX()-1,p.getY());				
            	Move m=new Move(p,end,true);			//haut
            	moves.add(m);   
            }
            if(p.getY()+1 < this.m && p.getY()+1 >=0 && (this.board[p.getX()][p.getY()+1] == 0 )) {
            	Point end=new Point(p.getX(),p.getY()+1);				
            	Move m=new Move(p,end,true);			
            	moves.add(m);   					//droite
            }
            if(p.getY()-1 < this.m && p.getY()-1 >=0 && (this.board[p.getX()][p.getY()-1] == 0)) {
            	Point end=new Point(p.getX(),p.getY()-1);				
            	Move m=new Move(p,end,true);			  //gauche
            	moves.add(m);
            }
           
           
            //ajout des move de type deplacement
            
            if(p.getX()+2 < this.n && p.getX()+2 >=0 && (this.board[p.getX()+2][p.getY()] == 0 )) {
            	Point end=new Point(p.getX()+2,p.getY());				
            	Move m=new Move(p,end,false);			 //bas
            	moves.add(m);
            }
            if(p.getX()-2 < this.n && p.getX()-2 >=0 && (this.board[p.getX()-2][p.getY()] == 0 )) {
            	Point end=new Point(p.getX()-2,p.getY());				
            	Move m=new Move(p,end,false);			//haut
            	moves.add(m);
            }
            if(p.getY()+2 < this.m && p.getY()+2 >=0 &&(this.board[p.getX()][p.getY()+2] == 0 )) {
            	Point end=new Point(p.getX(),p.getY()+2);		 //droite		
            	Move m=new Move(p,end,false);			
            	moves.add(m);   
            }
            if(p.getY()-2 < this.m && p.getY()-2 >=0 &&(this.board[p.getX()][p.getY()-2] == 0 )) {
            	Point end=new Point(p.getX(),p.getY()-2);				
            	Move m=new Move(p,end,false);			       //gauche
            	moves.add(m);    
            }
								
			
			}
		
		return moves;
		
	}
	
	//methode retournant le  score d'un joueur donnee
	
	
	public float getScore(int player)
    {
        int s1=0,s2=0;
       
        for(int i=0;i<this.n;i++)
        {
            for(int j=0;j<this.m;j++)
            {
                if(this.board[i][j]==1) {
                    s1++;
                }
                if(this.board[i][j] == 2){
                    s2++;
                }
            }
        }
        float score = 0;
       
       
        if(player == 1) {
           
            score = (float) s1/(s1+s2);
        }
        if(player == 2){
           
            score = (float) s2/(s1+s2);
        }
       
        return score;
    }
   
	
	
	public  void  parcourmoves(ArrayList<Move> a)
	{
		for(Move o : a)
		{
			System.out.println(o.toString());
		}
	}
	
  //methode renvoyant un nouveau state apres execution d'un mouvement a
  //la serie de test sert à savoir si on depasse pas les extremites de la matrice
  //et executer correctement le mouvement suivant le type 
	public State play(Move a)
	{
		int[][] board2=new int[this.n][this.m];
		for(int i=0;i<this.n;i++)
		{
			for(int j=0;j<this.m;j++)
			{
				board2[i][j]=this.board[i][j]; 
			}
		}
		
		
		Move transition=a;
		
		if(transition.getType()==true) //duplication
		{	board2[transition.getEnd().getX()][transition.getEnd().getY()]=this.currentPlayer;
			if((transition.getEnd().getX() + 1 < this.n) && (board2[transition.getEnd().getX()+1]
					[transition.getEnd().getY()]  !=0) && (board2[transition.getEnd().getX()+1]
							[transition.getEnd().getY()] !=board2[transition.getStart().getX()]
									[transition.getStart().getY()])) {
										board2[transition.getEnd().getX()+1][transition.getEnd().getY()] 
										= board2[transition.getEnd().getX()][transition.getEnd().getY()] ;
            }
            if((transition.getEnd().getX() - 1 >= 0) && (board2[transition.getEnd().getX()-1]
            		[transition.getEnd().getY()] != 0 ) && (board2[transition.getEnd().getX()-1]
            				[transition.getEnd().getY()] !=board2[transition.getStart().getX()]
            						[transition.getStart().getY()])) {
                						board2[transition.getEnd().getX()-1][transition.getEnd().getY()] 
                						= board2[transition.getEnd().getX()][transition.getEnd().getY()] ;
            }
            if((transition.getEnd().getY() + 1 < this.m) && (board2[transition.getEnd().getX()]
            		[transition.getEnd().getY() + 1] != 0) && (board2[transition.getEnd().getX()]
            				[transition.getEnd().getY() + 1] !=board2[transition.getStart().getX()]
            						[transition.getStart().getY()])) {
                						board2[transition.getEnd().getX()][transition.getEnd().getY() + 1] 
                						= board2[transition.getEnd().getX()][transition.getEnd().getY()] ;
            }
            if((transition.getEnd().getY() -1 >= 0) && (board2[transition.getEnd().getX()]
            		[transition.getEnd().getY()-1] != 0) && (board2[transition.getEnd().getX()]
            				[transition.getEnd().getY()-1] !=board2[transition.getStart().getX()]
            						[transition.getStart().getY()])) {
            							board2[transition.getEnd().getX()][transition.getEnd().getY()-1] 
            							= board2[transition.getEnd().getX()][transition.getEnd().getY()] ;
            }
		}
		else
		{	//deplacement 
			board2[transition.getEnd().getX()][transition.getEnd().getY()]
			=board2[transition.getStart().getX()][transition.getStart().getY()];
			board2[transition.getStart().getX()][transition.getStart().getY()]=0;
		}
		// changement de tour 
		
		int cp=0;
		
		if(this.currentPlayer==1)
		{
			
			cp=2;
		}
		if(this.currentPlayer==2) {
			
			cp=1;
		 
		}
		State newerState=new State(this.n,this.m,board2,cp);
		return newerState;
	}
	
	//methode qui prends en parametre un nombre de coups donnée et renvoie le nouveau state apres 
	//x coups davance pour le joueur 1
	
	public State avantageJ1(int xAvance,IA ia)
	{	State s=this; 
		int i=1;
		Move m1=new Move();
		while((i<xAvance)&&(this.isFinished()!=true)){  //on commence de 1 et pas 0 car dans la partie principale
			ia.setPlayer(1);  //par le joueur 1 et donc le nombre de coups d'avances est de x-1
			this.currentPlayer=1; 
			s.currentPlayer=1;
		    m1=ia.decide(s,ia.getDeepness()); 
			s=s.play(m1); 
			i++;
			
			}
		ia.setPlayer(1);
		s.currentPlayer=1;
		
		return s; 
	} 

	
		
	}


