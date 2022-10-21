import java.lang.Math;

public class main {

    public static class PasswordChecker {
        int score;
        int lengthScore;
        int charScore;
        int numScore;
        int specialScore;
        int maxScore;

        public PasswordChecker(int a, int b, int c, int d, int e, int f) {
            this.score = a;
            this.lengthScore = b;
            this.charScore = c;
            this.numScore = d;
            this.specialScore = e;
            this.maxScore = f;
        }

        public PasswordChecker() {}

        @Override
        public String toString() {
            return "Score: " + this.score +"/" + this.maxScore + " => " + (double)this.score*100/this.maxScore + "%" + "\nLength: " + this.lengthScore + "\nChar: " + this.charScore + "\nNumber: " + this.numScore + "\nSpecial: " + this.specialScore;
        }

    }

    public static PasswordChecker pwChecker(String p) {
        int score = 0;

        int expDiv = 2;

        int lengthScore = 0;

        int upper = 0;
        int charScore = 0;

        int letter = 0;
        int numbers = 0;
        int numScore = 0;

        int special = 0;
        int specialScore = 0;

        int maxScore = 0;

        //Anzahl an Buchstaben, Groß, Kleinbuchstaben und Ziffern errechnen
        for(char c: p.toCharArray()) {
            letter += Character.isLetter(c) ? 1 : 0;
            upper += Character.isUpperCase(c) ? 1 : 0;
            numbers += Character.isDigit(c) ? 1 : 0;
            special += !Character.isLetterOrDigit(c) ? 1 : 0;
        }

        //Prüfen nach Länge (Muss 8 oder größer lang sein für Punkte)
        if (p.length() >= 8) {
            lengthScore += (int)Math.pow(expDiv, p.length()/3) - 1;
        }

        //Prüfen, nach Anzahl an Groß und Kleinbuchstaben
        double div = (double)upper/letter;
        if(div >= 0.45 && div <= 0.65){
            charScore += 10;
        } else if (div >= 0.4 && div <= 0.7) {
            charScore += 8;
        } else if (div >= 0.25 && div <= 0.75) {
            charScore += 4;
        } else if (div >= 0.15 && div <= 0.85) {
            charScore += 1;
        } 
        
        
        //Prüfen nach Zahlen
        double numdiv = (double)numbers/(p.length()-letter);
        if(numdiv >= 0.42 && numdiv <= 0.62){
            numScore += 4;
        } else if (numdiv >= 0.37 && numdiv <= 0.69) {
            numScore += 3;
        } else if (numdiv >= 0.25 && numdiv <= 0.75) {
            numScore += 2;
        } else if (numdiv >= 0.15 && numdiv <= 0.85) {
            numScore += 1;
        }


        //Prüfen nach Sonderzeichen
        double specialdiv = (double)special/(p.length()-letter);
        if(specialdiv >= 0.35 && specialdiv <= 0.60){
            specialScore += 8;
        } else if (specialdiv >= 0.3 && specialdiv <= 0.7) {
            specialScore += 6;
        } else if (specialdiv >= 0.25 && specialdiv <= 0.75) {
            specialScore += 3;
        } else if (specialdiv >= 0.15 && specialdiv <= 0.85) {
            specialScore += 1;
        }

        score = lengthScore + charScore + numScore + specialScore;

        maxScore = (p.length() > 8 ? (int)Math.pow(expDiv, p.length()/3) - 1 : ((int)Math.pow(expDiv, 8)) - 1) + 10 + 4 + 8;


        PasswordChecker check = new PasswordChecker(score, lengthScore, charScore, numScore, specialScore, maxScore);

        return check;
    }

    public static void main(String[] args) {
        PasswordChecker pw = new PasswordChecker();
        pw = pwChecker("aaaaAAAA1111____"); //Enter Password here
        
        System.out.println(pw.toString());
    }
}
