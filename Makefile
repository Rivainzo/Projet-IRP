JFLAGS = -g -classpath .

JC = javac
JVM = java

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
      Puissance4Jeu.java \
      Paire.java \
      Triple.java


default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

nim:
	$(JVM) -cp . NimJeu

nim_test:
	$(JVM) -cp . NimTest

puissance4:
	$(JVM) -cp . Puissance4Jeu

puissance4_test:
	$(JVM) -cp . Puissance4Test