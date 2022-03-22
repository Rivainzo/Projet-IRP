JFLAGS = -g -classpath ./jeux

JC = javac
JVM = java

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
      jeux/Game.java \
	  jeux/Search.java \
      jeux/AlphaBeta.java \
      jeux/AlphaBetaDepth.java \
      jeux/AlphaBetaMemoire.java \
      jeux/MinimaxDepthSearch.java \
      jeux/MinimaxSearch.java \
      jeux/Nim.java \
      jeux/NimTest.java \
      jeux/NimJeu.java \
      jeux/Puissance4.java \
      jeux/Puissance4Test.java \
      jeux/Puissance4Jeu.java \
      jeux/Paire.java \
      jeux/Triple.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) jeux/*.class

nim:
	$(JVM) -cp ./jeux NimJeu

nim_test:
	$(JVM) -cp ./jeux NimTest

puissance4:
	$(JVM) -cp ./jeux Puissance4Jeu

puissance4_test:
	$(JVM) -cp ./jeux Puissance4Test