COMP = javac
exec = java

fileClasses :
	mkdir fichierClasses
compiling:
	@echo "----------------------------------------------------------"
	@echo "debut de la compilation des fichier .java .."
	@echo "----------------------------------------------------------"
	$(COMP) -d "fichierClasses" jeuDeReflexion/*.java


launching:
	@echo "---------------------lancement----------------------------"
	@echo "----------------------------------------------------------"
	@echo "executions des .classes et affichages du filrouge"
	@echo "----------------------------------------------------------"
	$(exec) -cp "fichierClasses" jeuDeReflexion.Main
	@echo "----------------------------------------------------------"
	@echo "-------------------fin de l'application-------------------"


cleaning:
	@echo ".....nettoyage ....."
	rm -r fichierClasses
	@echo "-------------------fin du lancement ----------------------"

pipeline: fileClasses compiling launching cleaning
