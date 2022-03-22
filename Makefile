JFLAGS = -g -classpath ./jeux

JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
      ./Game.java \
	  ./search/Search.java \
      ./search/AlphaBeta.java \
      search/AlphaBetaDepth.java \
      search/AlphaBetaMemoire.java \
      search/MinimaxDepthSearch.java \
      search/MinimaxSearch.java \
      nim/Nim.java \
      nim/NimTest.java \
      nim/NimJeu.java \
      puissance4/Puissance4.java \
      puissance4/Puissance4Test.java \
      puissance4/Puissance4Jeu.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class