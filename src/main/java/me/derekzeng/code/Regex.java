package me.derekzeng.code;
import java.util.*;

public class Regex {
    enum TokenType {
        CHAR, ANY, STAR
    }

    class Token {
        char ch;
        int starMatched=0;
        int startPos;
        TokenType type;
        Token(char c, TokenType t) {ch=c;type=t;}
        public String toString() {
            return String.format("(%s %s %s %s), ", ch, type.name(), startPos, starMatched);
        }
    }

    public boolean isMatch(String s, String p) {
        List<Token> tokens = getTokens(p);
        List<Token> stars = new LinkedList<>();
        int strPos = 0;
        int tokenPos= 0;

        while (tokenPos < tokens.size()) {
            Token token = tokens.get(tokenPos);
            // System.out.println(token + " strPos:" + strPos );
            boolean matched = false;
            switch(token.type) {
                case CHAR:
                case ANY:
                    if (strPos != s.length()) {
                        matched = token.ch == s.charAt(strPos) || token.type == TokenType.ANY;
                        if (matched) {
                            // System.out.println("matched! " + token.ch + " " + s.charAt(strPos));
                            strPos++;
                            tokenPos++;
                        }
                    }
                    break;
                case STAR:
                    // greedy
                    token.starMatched = 0;
                    token.startPos = strPos;
                    matched = true;
                    while (strPos < s.length() 
                            && (token.ch == '.' || token.ch == s.charAt(strPos))) {
                        token.starMatched += 1;
                        strPos++;
                    }
                    tokenPos+=1;
                    if (matched) {
                        // System.out.println("matched! " + token);
                    }
                    if (token.starMatched > 0) {
                        stars.add(token);
                    }
                    break;
            }

            if (!matched) {
                if (stars.size() == 0) {
                    return false;
                }
                Token last = stars.get(stars.size() - 1);
                last.starMatched -= 1;
                strPos = last.startPos + last.starMatched;
                tokenPos = tokens.indexOf(last) + 1;
                // System.out.println("backtrack * " + last);

                if (last.starMatched == 0) {
                    stars.remove(last);
                }
            }
        }

        return strPos == s.length();
    }

    List<Token> getTokens(String p) {
        List<Token> tokens = new LinkedList<>();
        int i=p.length() - 1;

        while (i>=0) {
            char c = p.charAt(i);
            if (c == '.') {
                tokens.add(0, new Token('.', TokenType.ANY));
                i--;
            } else if (c == '*') {
                char repeat = p.charAt(i-1);
                tokens.add(0, new Token(repeat, TokenType.STAR));
                i-=2;
            } else {
                tokens.add(0, new Token(c, TokenType.CHAR));
                i--;
            }
        }
        return tokens;
    }
}
