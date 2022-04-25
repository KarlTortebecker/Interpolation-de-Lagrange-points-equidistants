package interpolation.de.lagrange.points.equidistants;

/**
 *
 * @author Karl Tortebecker
 */
public class InterpolationLagrange {
    private  final double minValueIn;
    private  final double maxValueIn;
    private double[] points;
    private double[] valeurs;
    int n;

    public InterpolationLagrange(double minValue, double maxValue, int n){
        this.minValueIn = minValue;
        this.maxValueIn = maxValue;
        this.n = n;
        points = new double[n];
        valeurs = new double[n];
        double temp = (maxValue - minValue + 1) / (double)n;

        // L'on récupère ici les différents élements dont nous trouverons les images.
        for (int i = 0; i < n; i++) {
            points[i] = minValue + i*temp;
        }

        // Ce bloc nous permettra de trouver les images des points entrés précédemmeent et de les stocker dans le tableau valeurs
        for (int i = 0; i < n; i++) {
            valeurs[i] = function(points[i]);
        }
        

    }

    // Fonction qui prend un réel et renvoit son image par la fonction qui sera donnée ici
    protected double function (double x){

        return x; // A remplacer par la fonction en elle même
    }
    
    //Nous allons ici diviser le travail en 3 parties

    //La première ici fera le calcul du denominateur après l'inversera
    private double lamdaI(int i, int n) {
        double res = 1.0;
        for (int j = 0; j < n; j++) {
            if (i != j) {
                res = res / (points[i] - points[j]);
            }
        }
        return res;
    }

    //Cette seconde partie concerne le calcul des Li de la base de Lagrange
    private double lI(int i, int n, double x) {
        double res = 1.0;
         for (int j = 0; j < n; j++) {
             if (i != j) {
                 res = res*(x - points[j]);
             }
         }
         res = res*lamdaI(i, n);
        return res;
    }

    //Cette dernière partie nous permet de trouver la valeur que nous donnera  un terme du polynôme
    public double getValeur(double x) {
        double val = 0.0;
        for (int i = 0; i < n; i++) {
            val = val + valeurs[i]*lI(i, n, x);
        }
        return val;
    }
}
