JFLAGS = -g -classpath ./jeux

JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
      Game.java \
	  jeux/search/Search.java \
      jeux/search/AlphaBeta.java \
      jeux/search/AlphaBetaDepth.java \
      jeux/search/AlphaBetaMemoire.java \
      jeux/search/MinimaxDepthSearch.java \
      jeux/search/MinimaxSearch.java \
      jeux/nim/Nim.java \
      jeux/nim/NimTest.java \
      jeux/nim/NimJeu.java \
      jeux/puissance4/Puissance4.java \
      jeux/puissance4/Puissance4Test.java \
      jeux/puissance4/Puissance4Jeu.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class