/* Generated By:JavaCC: Do not edit this line. FormulaParserTokenManager.java */
/*
 * $Id$
 * $URL$
 * ----------------------------------------------------------------------------
 * This file is part of JSBML. Please visit <http://sbml.org/Software/JSBML>
 * for the latest version of JSBML and more information about SBML.
 *
 * Copyright (C) 2009-2014 jointly by the following organizations:
 * 1. The University of Tuebingen, Germany
 * 2. EMBL European Bioinformatics Institute (EBML-EBI), Hinxton, UK
 * 3. The California Institute of Technology, Pasadena, CA, USA
 * 4. The University of California, San Diego, La Jolla, CA, USA
 * 5. The Babraham Institute, Cambridge, UK 
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation. A copy of the license agreement is provided
 * in the file named "LICENSE.txt" included with this software distribution
 * and also available online as <http://sbml.org/Software/JSBML/License>.
 * ----------------------------------------------------------------------------
 */
package org.sbml.jsbml.text.parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import org.sbml.jsbml.ASTNode;
import org.sbml.jsbml.ASTNode.Type;
import org.sbml.jsbml.resources.Resource;

/** Token Manager. */
public class FormulaParserTokenManager implements FormulaParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0x400000L) != 0L)
         {
            jjmatchedKind = 23;
            return 59;
         }
         if ((active0 & 0x2000L) != 0L)
            return 11;
         if ((active0 & 0x400L) != 0L)
            return 7;
         return -1;
      case 1:
         if ((active0 & 0x400000L) != 0L)
         {
            jjmatchedKind = 23;
            jjmatchedPos = 1;
            return 59;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 33:
         return jjStartNfaWithStates_0(0, 13, 11);
      case 40:
         return jjStopAtPos(0, 14);
      case 41:
         return jjStopAtPos(0, 15);
      case 42:
         return jjStopAtPos(0, 11);
      case 43:
         return jjStopAtPos(0, 8);
      case 45:
         return jjStartNfaWithStates_0(0, 10, 7);
      case 47:
         return jjStopAtPos(0, 12);
      case 94:
         return jjStopAtPos(0, 9);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x400000L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 111:
         return jjMoveStringLiteralDfa2_0(active0, 0x400000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 103:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(2, 22, 59);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 59;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 3)
                        kind = 3;
                     jjCheckNAddStates(0, 5);
                  }
                  else if ((0x7000000200000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 11;
                  else if ((0x2400L & l) != 0L)
                  {
                     if (kind > 26)
                        kind = 26;
                  }
                  else if (curChar == 46)
                     jjCheckNAddTwoStates(30, 8);
                  else if (curChar == 44)
                  {
                     if (kind > 7)
                        kind = 7;
                  }
                  else if (curChar == 45)
                     jjCheckNAddTwoStates(1, 7);
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 24)
                        kind = 24;
                  }
                  else if ((0x7000000000000000L & l) != 0L)
                  {
                     if (kind > 16)
                        kind = 16;
                  }
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(6, 8);
                  else if (curChar == 46)
                     jjCheckNAdd(8);
                  break;
               case 59:
               case 16:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 23)
                     kind = 23;
                  jjCheckNAdd(16);
                  break;
               case 1:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(6, 8);
                  break;
               case 2:
                  if (curChar == 46)
                     jjCheckNAdd(3);
                  break;
               case 3:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(3, 4);
                  break;
               case 5:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(6);
                  break;
               case 6:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjCheckNAdd(6);
                  break;
               case 8:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(8, 4);
                  break;
               case 9:
                  if (curChar == 44)
                     kind = 7;
                  break;
               case 10:
                  if ((0x7000000200000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 11;
                  break;
               case 11:
                  if ((0x7000000000000000L & l) != 0L && kind > 16)
                     kind = 16;
                  break;
               case 17:
                  if ((0x3ff000000000000L & l) != 0L && kind > 24)
                     kind = 24;
                  break;
               case 19:
                  if ((0x2400L & l) != 0L)
                     kind = 26;
                  break;
               case 24:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 3)
                     kind = 3;
                  jjCheckNAddStates(0, 5);
                  break;
               case 25:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 3)
                     kind = 3;
                  jjCheckNAdd(25);
                  break;
               case 26:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAddTwoStates(26, 27);
                  break;
               case 27:
                  if (curChar == 46)
                     jjCheckNAdd(28);
                  break;
               case 28:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAdd(28);
                  break;
               case 29:
                  if (curChar == 46)
                     jjCheckNAddTwoStates(30, 8);
                  break;
               case 30:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 5)
                     kind = 5;
                  jjCheckNAdd(30);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 24)
                        kind = 24;
                  }
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 23)
                        kind = 23;
                     jjCheckNAddTwoStates(15, 16);
                  }
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 25)
                        kind = 25;
                  }
                  if (curChar == 65)
                     jjAddStates(9, 12);
                  else if (curChar == 79)
                     jjCheckNAddStates(13, 16);
                  else if (curChar == 88)
                     jjAddStates(17, 20);
                  else if (curChar == 120)
                     jjAddStates(21, 22);
                  else if (curChar == 111)
                     jjCheckNAddTwoStates(37, 38);
                  else if (curChar == 97)
                     jjAddStates(23, 24);
                  else if (curChar == 78)
                     jjAddStates(25, 26);
                  else if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 59:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 23)
                        kind = 23;
                     jjCheckNAdd(16);
                  }
                  if ((0x7fffffe87fffffeL & l) != 0L)
                  {
                     if (kind > 23)
                        kind = 23;
                     jjCheckNAddTwoStates(15, 16);
                  }
                  break;
               case 4:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(27, 28);
                  break;
               case 12:
                  if (curChar == 116 && kind > 21)
                     kind = 21;
                  break;
               case 13:
               case 23:
                  if (curChar == 111)
                     jjCheckNAdd(12);
                  break;
               case 14:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 23)
                     kind = 23;
                  jjCheckNAddTwoStates(15, 16);
                  break;
               case 16:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 23)
                     kind = 23;
                  jjCheckNAdd(16);
                  break;
               case 17:
                  if ((0x7fffffe87fffffeL & l) != 0L && kind > 24)
                     kind = 24;
                  break;
               case 18:
                  if ((0x7fffffe07fffffeL & l) != 0L && kind > 25)
                     kind = 25;
                  break;
               case 20:
                  if (curChar == 78)
                     jjAddStates(25, 26);
                  break;
               case 21:
                  if (curChar == 84 && kind > 21)
                     kind = 21;
                  break;
               case 22:
                  if (curChar == 79)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 31:
                  if (curChar == 97)
                     jjAddStates(23, 24);
                  break;
               case 32:
                  if (curChar == 100 && kind > 17)
                     kind = 17;
                  break;
               case 33:
               case 55:
                  if (curChar == 110)
                     jjCheckNAdd(32);
                  break;
               case 34:
                  if (curChar == 100 && kind > 18)
                     kind = 18;
                  break;
               case 35:
               case 56:
                  if (curChar == 110)
                     jjCheckNAdd(34);
                  break;
               case 36:
                  if (curChar == 111)
                     jjCheckNAddTwoStates(37, 38);
                  break;
               case 37:
                  if (curChar == 114 && kind > 17)
                     kind = 17;
                  break;
               case 38:
                  if (curChar == 114 && kind > 19)
                     kind = 19;
                  break;
               case 39:
                  if (curChar == 120)
                     jjAddStates(21, 22);
                  break;
               case 40:
               case 44:
                  if (curChar == 111)
                     jjCheckNAdd(37);
                  break;
               case 41:
                  if (curChar == 114 && kind > 20)
                     kind = 20;
                  break;
               case 42:
               case 49:
                  if (curChar == 111)
                     jjCheckNAdd(41);
                  break;
               case 43:
                  if (curChar == 88)
                     jjAddStates(17, 20);
                  break;
               case 45:
                  if (curChar == 82 && kind > 17)
                     kind = 17;
                  break;
               case 46:
                  if (curChar == 79)
                     jjCheckNAdd(45);
                  break;
               case 47:
                  if (curChar == 82 && kind > 20)
                     kind = 20;
                  break;
               case 48:
                  if (curChar == 79)
                     jjstateSet[jjnewStateCnt++] = 47;
                  break;
               case 50:
                  if (curChar == 79)
                     jjCheckNAddStates(13, 16);
                  break;
               case 51:
                  if (curChar == 82 && kind > 19)
                     kind = 19;
                  break;
               case 52:
                  if (curChar == 65)
                     jjAddStates(9, 12);
                  break;
               case 53:
                  if (curChar == 68 && kind > 17)
                     kind = 17;
                  break;
               case 54:
                  if (curChar == 78)
                     jjstateSet[jjnewStateCnt++] = 53;
                  break;
               case 57:
                  if (curChar == 68 && kind > 18)
                     kind = 18;
                  break;
               case 58:
                  if (curChar == 78)
                     jjstateSet[jjnewStateCnt++] = 57;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 59 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   25, 26, 27, 1, 2, 4, 1, 2, 4, 54, 55, 56, 58, 37, 45, 51, 
   38, 44, 46, 48, 49, 40, 42, 33, 35, 22, 23, 5, 6, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, "\53", "\136", "\55", "\52", 
"\57", "\41", "\50", "\51", null, null, null, null, null, null, "\154\157\147", null, 
null, null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0x7fffff9L, 
};
static final long[] jjtoSkip = {
   0x6L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[59];
private final int[] jjstateSet = new int[118];
protected char curChar;
/** Constructor. */
public FormulaParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public FormulaParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 59; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind);
   t.kind = jjmatchedKind;
   t.image = curTokenImage;

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100000200L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
