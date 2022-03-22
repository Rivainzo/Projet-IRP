JFLAGS = -g -classpath .

JC = javac

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
      Game.java \
      Search.java \
      AlphaBeta.java \
      AlphaBetaDepth.java \
      AlphaBetaMemoire.java \
      MinimaxDepthSearch.java \
      MinimaxSearch.java \
      Nim.java \
      NimTest.java \
      NimJeu.java \
      Puissance4.java \
      Puissance4Test.java \
      Puissance4Jeu.java 


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class